package tk.dczippl.lightestlamp.fluid;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import tk.dczippl.lightestlamp.init.ModMiscs;

import java.util.Random;

public class BromineFluidBlock extends FluidBlock
{
    public BromineFluidBlock(FlowableFluid fluid, AbstractBlock.Settings properties)
    {
        super(fluid, properties);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, RandomGenerator random)
    {
        worldIn.getNonSpectatingEntities(Entity.class, new Box(pos.offset(Direction.NORTH, 4).offset(Direction.WEST, 4).offset(Direction.UP, 4),
                pos.offset(Direction.SOUTH, 4).offset(Direction.EAST, 4).offset(Direction.DOWN, 4))).forEach(entity ->
        {
            if (entity instanceof LivingEntity)
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(ModMiscs.BROMINE_POISON, 80, 0));
        });
        super.randomTick(state, worldIn, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity)
            ((LivingEntity) entityIn).addStatusEffect(new StatusEffectInstance(ModMiscs.BROMINE_POISON, 80, 2));
    }
}