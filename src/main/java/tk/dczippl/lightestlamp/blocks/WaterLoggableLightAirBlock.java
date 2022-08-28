package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import static net.minecraft.state.property.Properties.WATERLOGGED;

public class WaterLoggableLightAirBlock extends Block implements Waterloggable
{
    public WaterLoggableLightAirBlock() {
        super(QuiltBlockSettings.of(Material.AIR).air().luminance(15).nonOpaque().dropsNothing());
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    VoxelShape rs = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rs;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8 ? super.getPlacementState(ctx) : null;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState blockState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (!blockState.isAir()) { // TODO: Fix this
            //world.getFluidTickScheduler().scheduleTick(new OrderedTick<Fluid>(Fluids.WATER, pos, Fluids.WATER.getTickRate(world)));
        }

        return blockState;
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return true;
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }
    
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}
