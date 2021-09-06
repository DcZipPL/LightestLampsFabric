package tk.dczippl.lightestlamp.machine.gascentrifuge;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import tk.dczippl.lightestlamp.init.ModFluids;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

public class GasCentrifugeBlock extends ContainerBlock
{
    public GasCentrifugeBlock(Block.Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new GasCentrifugeTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_)
    {
        if (p_225533_2_.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            this.interactWith(p_225533_2_, p_225533_3_, p_225533_4_);
            return ActionResultType.SUCCESS;
        }
    }

    /**
     * Interface for handling interaction with blocks that impliment AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof GasCentrifugeTile) {
            if (player.getHeldItemMainhand().getItem() == Items.BUCKET)
            {
                ItemStack bucket = player.getHeldItemMainhand();
                if (player.getHeldItemMainhand().getCount() == 1)
                    if (((GasCentrifugeTile) tileentity).getTank().getFluidAmount() >= 1000)
                    {
                        ((GasCentrifugeTile) tileentity).getTank().drain(1000, IFluidHandler.FluidAction.EXECUTE);
                        player.inventory.deleteStack(bucket);
                        player.inventory.addItemStackToInventory(new ItemStack(ModFluids.BROMINE_FLUID_BUCKET.get()));
                    }
            }
            else
            {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileentity, buf -> buf.writeBlockPos(pos));
                player.addStat(Stats.INTERACT_WITH_FURNACE);
            }
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof GasCentrifugeTile) {
                ((GasCentrifugeTile)tileentity).setCustomName(stack.getDisplayName());
            }
        }

    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof GasCentrifugeTile) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (GasCentrifugeTile)tileentity);
            }
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}