package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import static net.minecraft.state.property.Properties.WATERLOGGED;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class LightAirBlock extends AirBlock
{
    public LightAirBlock()
    {
        super(QuiltBlockSettings.of(Material.AIR).air().luminance(15).nonOpaque().dropsNothing());
    }

    VoxelShape rs = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rs;
    }

    /*@Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8 ? super.getPlacementState(ctx) : null;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState blockState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (!blockState.isAir()) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return blockState;
    }

    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getStill(false);
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }*/
}