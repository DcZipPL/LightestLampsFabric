package tk.dczippl.lightestlamp.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.block.Blocks.JUNGLE_LEAVES;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class JungleLanternBlock extends Block implements Waterloggable
{
    public JungleLanternBlock()
    {
        super(FabricBlockSettings.copyOf(JUNGLE_LEAVES).luminance(__ -> 12));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return Block.createCuboidShape(0,0,0,0,0,0);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos
    {
        return Block.createCuboidShape(4,2,4,12,14,12);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(4,2,4,12,14,12);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(new TranslatableText("tooltip.lightestlamp.speed_grow").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));

        super.appendTooltip(stack, world, tooltip, options);
    }

    @Nullable
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
        return true;
    }
}