package dev.prefex.lightestlamp.blocks.entity;

import dev.prefex.lightestlamp.init.ModBlockEntities;
import dev.prefex.lightestlamp.util.BlockUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AlchemicalLampBlockEntity extends BlockEntity
{
	public AlchemicalLampBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModBlockEntities.ALCHEMICALLAMP_TE,pos,state);
	}
	
	public static void tick(World world, BlockPos pos, BlockState state, AlchemicalLampBlockEntity blockEntity) {
		BlockUtil.repelEntitiesInBoxFromPoint(world, new Box(pos.add(-8, -8, -8), pos.add(8, 8, 8)), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, false);
	}
}
