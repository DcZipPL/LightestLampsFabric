package dev.prefex.lightestlamp.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

@SuppressWarnings({"NullableProblems", "deprecation"})
public class LightAirBlock extends AirBlock
{
	public LightAirBlock()
	{
		super(FabricBlockSettings.copyOf(Blocks.AIR).air().luminance(15).nonOpaque().dropsNothing().noCollision().notSolid().replaceable());
	}

	VoxelShape rs = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return rs;
	}
}