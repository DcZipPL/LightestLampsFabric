package tk.dczippl.lightestlamp;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.screenhandler.Networking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.dczippl.lightestlamp.init.*;
import tk.dczippl.lightestlamp.util.WorldGenerator;

import java.util.Random;

@SuppressWarnings("NullableProblems")
public class LightestLampsMod implements ModInitializer
{
    public static final String MOD_ID = "lightestlamp";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public LightestLampsMod(){}

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ModFluids.init();
        ModBlocks.init();
        ModItems.init();
        ModBlockEntities.init();
        WorldGenerator.register();
    }

/*    @SubscribeEvent
    public void onEntityLivingDeath(LivingDeathEvent event)
    {
        if (event.getSource().getDamageType().equals("player"))
        {
            if(event.getEntityLiving() instanceof IMob)
            {
                if (event.getEntityLiving().getEntityWorld().getMoonFactor() == 1.0F)
                {
                    Random rnd = new Random();
                    if (rnd.nextInt(4) == 2)//25% chance
                    event.getEntityLiving().entityDropItem(new ItemStack(ModItems.MOON_SHARD));
                }
            }
        }
    }

    @SubscribeEvent
    public void EntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        boolean disabled = false;
        if (entity instanceof PlayerEntity)
        {
            if (((PlayerEntity)entity).isSpectator())
                disabled = true;
        }
        if (!disabled)
        {
            BlockPos pos = new BlockPos(entity.getPositionVec());
            Block b = entity.getEntityWorld().getBlockState(pos.offset(Direction.UP)).getBlock();
            Block b1 = entity.getEntityWorld().getBlockState(pos).getBlock();
            if (b.equals(ModFluids.BROMINE_FLUID_BLOCK.get()))
            {
                entity.setMotion(new Vector3d(entity.getMotion().x, 0.100000011620D, entity.getMotion().z));
            } else if (b1.equals(ModFluids.BROMINE_FLUID_BLOCK.get()))
            {
                entity.setMotion(new Vector3d(entity.getMotion().x, 0.100000011620D, entity.getMotion().z));
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void setFog(EntityViewRenderEvent.FogColors fog)
    {
        World w = fog.getInfo().getRenderViewEntity().getEntityWorld();
        BlockPos pos = fog.getInfo().getBlockPos();
        BlockState bs = w.getBlockState(pos);
        Block b = bs.getBlock();
        if(b.equals(ModFluids.BROMINE_FLUID_BLOCK.get()))
        {
            float red = 0.2F, green = 0.05F, blue = 0.05F;
            fog.setRed(red); fog.setGreen(green); fog.setBlue(blue);

        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void setFogDensity(EntityViewRenderEvent.FogDensity fog)
    {
        World w = fog.getInfo().getRenderViewEntity().getEntityWorld();
        BlockPos pos = fog.getInfo().getBlockPos();
        BlockState bs = w.getBlockState(pos);
        Block b = bs.getBlock();
        if(b.equals(ModFluids.BROMINE_FLUID_BLOCK.get()))
        {
            fog.setDensity(1f);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void setFogLength(EntityViewRenderEvent.RenderFogEvent fog)
    {
        World w = fog.getInfo().getRenderViewEntity().getEntityWorld();
        BlockPos pos = fog.getInfo().getBlockPos();
        BlockState bs = w.getBlockState(pos);
        Block b = bs.getBlock();
        if(b.equals(ModFluids.BROMINE_FLUID_BLOCK.get()))
        {
            float progress = 36f / 30f;
            RenderSystem.fogStart((1 - progress) * fog.getFarPlaneDistance() * .75f);
            RenderSystem.fogEnd(fog.getFarPlaneDistance() * (1 - .8f * progress));
        }
    }*/
}