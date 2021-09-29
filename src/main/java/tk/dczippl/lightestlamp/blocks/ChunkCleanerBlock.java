package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tk.dczippl.lightestlamp.blocks.entity.ChunkCleanerBlockEntity;
import tk.dczippl.lightestlamp.init.ModBlockEntities;

public class ChunkCleanerBlock extends BlockWithEntity
{
    public ChunkCleanerBlock()
    {
        super(AbstractBlock.Settings.copy(Blocks.STRUCTURE_BLOCK));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChunkCleanerBlockEntity(pos, state);
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CC_BE, ChunkCleanerBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}