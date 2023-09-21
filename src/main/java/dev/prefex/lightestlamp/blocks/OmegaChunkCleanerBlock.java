package dev.prefex.lightestlamp.blocks;

import dev.prefex.lightestlamp.blocks.entity.OmegaChunkCleanerBlockEntity;
import dev.prefex.lightestlamp.init.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OmegaChunkCleanerBlock extends BlockWithEntity
{
	public OmegaChunkCleanerBlock()
	{
		super(AbstractBlock.Settings.copy(Blocks.STRUCTURE_BLOCK));
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new OmegaChunkCleanerBlockEntity(pos, state);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, ModBlockEntities.OCC_BE, OmegaChunkCleanerBlockEntity::tick);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}
}