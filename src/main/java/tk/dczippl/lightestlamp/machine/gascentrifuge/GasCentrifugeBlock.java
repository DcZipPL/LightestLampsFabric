package tk.dczippl.lightestlamp.machine.gascentrifuge;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tk.dczippl.lightestlamp.init.ModFluids;

import static net.minecraft.state.property.Properties.FACING;

public class GasCentrifugeBlock extends BlockWithEntity
{
	public GasCentrifugeBlock(AbstractBlock.Settings builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new GasCentrifugeBlockEntity(pos, state);
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
		BlockEntity tileentity = worldIn.getBlockEntity(pos);
		if (tileentity instanceof GasCentrifugeBlockEntity) {
			if (player.getMainHandStack().getItem() == Items.BUCKET)
			{
				ItemStack bucket = player.getMainHandStack();
				if (player.getMainHandStack().getCount() == 1)
					if (((GasCentrifugeBlockEntity) tileentity).getTank().getFluidAmount() >= 1000)
					{
						((GasCentrifugeBlockEntity) tileentity).getTank().drain(1000, IFluidHandler.FluidAction.EXECUTE);
						player.getInventory().removeOne(bucket);
						player.getInventory().addItemStackToInventory(new ItemStack(ModFluids.BROMINE_FLUID_BUCKET.get()));
					}
			}
			else
			{
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileentity, buf -> buf.writeBlockPos(pos));
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
		if (stack.hasCustomName()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof GasCentrifugeBlockEntity) {
				((GasCentrifugeBlockEntity)tileentity).setCustomName(stack.hasCustomName());
			}
		}
		
	}
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof GasCentrifugeBlockEntity) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (GasCentrifugeBlockEntity)tileentity);
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