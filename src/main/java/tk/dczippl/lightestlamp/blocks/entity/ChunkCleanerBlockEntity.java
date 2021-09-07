package tk.dczippl.lightestlamp.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import tk.dczippl.lightestlamp.init.ModBlockEntities;
import tk.dczippl.lightestlamp.init.ModBlocks;

import static net.minecraft.state.property.Properties.WATERLOGGED;

public class ChunkCleanerBlockEntity extends BlockEntity implements BlockEntityTicker
{
    private int cooldown = 0;
    
    public ChunkCleanerBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CC_BE,pos,state);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity)
    {
        if (world.isClient) return;
        if (cooldown >= 1)
        {
            BlockPos.iterate(pos.offset(Direction.UP, 18).offset(Direction.NORTH,18).offset(Direction.WEST,18), pos.offset(Direction.DOWN,18).offset(Direction.SOUTH,18).offset(Direction.EAST,18)).forEach((pos1) -> {
                if (world.getBlockState(pos1).getBlock() == ModBlocks.LIGHT_AIR)
                {
                    world.setBlockState(pos1, Blocks.AIR.getDefaultState());
                }
                else if (world.getBlockState(pos1).getBlock() == ModBlocks.WATERLOGGABLE_LIGHT_AIR && !world.getBlockState(pos1).get(WATERLOGGED))
                {
                    world.setBlockState(pos1, Blocks.AIR.getDefaultState());
                }
                else if (world.getBlockState(pos1).getBlock() == ModBlocks.WATERLOGGABLE_LIGHT_AIR && world.getBlockState(pos1).get(WATERLOGGED))
                {
                    world.setBlockState(pos1, Blocks.WATER.getDefaultState());
                }
            });
            /*BlockPos.iterate(pos.offset(Direction.UP, 19).offset(Direction.NORTH,19).offset(Direction.WEST,19), pos.offset(Direction.DOWN,19).offset(Direction.SOUTH,19).offset(Direction.EAST,19)).forEach((pos1) -> {
                if (isAir(pos1))
                    world.notifyBlockUpdate(pos1,world.getBlockState(pos1),world.getBlockState(pos1),3);
            });*/
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.removeBlockEntity(pos);
            //Recalc Light in nearby chunks
        }
        cooldown++;
    }

    private boolean isAir(BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == Blocks.CAVE_AIR || world.getBlockState(pos).getBlock() == ModBlocks.LIGHT_AIR;
    }
}