package dev.prefex.lightestlamp;

import dev.prefex.lightestlamp.init.ModBlockEntities;
import dev.prefex.lightestlamp.init.ModBlocks;
import dev.prefex.lightestlamp.init.ModItems;
import dev.prefex.lightestlamp.init.ModMiscs;
import dev.prefex.lightestlamp.plugins.EnergyIntegration;
import dev.prefex.lightestlamp.util.Networking;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import dev.prefex.lightestlamp.machine.gascentrifuge.GasCentrifugeBlockEntity;
import dev.prefex.lightestlamp.plugins.ModConfig;

@SuppressWarnings("NullableProblems")
public class LightestLampsMod implements ModInitializer
{
    public static final String MOD_ID = "lightestlamp";

    public static ModConfig CONFIG;
    public static final RegistryKey<PlacedFeature> LANTHANUM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID,"lanthanum_ore"));

    public LightestLampsMod(){}

    public static final TagKey<Item> GLOWSTONE_SMALL_DUSTS = TagKey.of(Registries.ITEM.getKey(), new Identifier("c", "glowstone_small_dusts"));
    
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);

        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        
        ServerPlayNetworking.registerGlobalReceiver(Networking.TOGGLEBUTTONS_CHANNEL, (client, handler, ctx, buf, responseSender) -> {
            // Read packet data on the event loop
            BlockPos target = buf.readBlockPos();
            int type = buf.readInt();
        
            client.execute(() -> {
                // Everything in this lambda is run on the thread
                BlockEntity be = ctx.player.getWorld().getBlockEntity(target);
                if (be instanceof GasCentrifugeBlockEntity gbe) {
                    if (type == 0) {
                        if (gbe.getRedstoneMode() >= 2)
                            gbe.setRedstoneMode(0);
                        else
                            gbe.setRedstoneMode(gbe.getRedstoneMode() + 1);
                    } else {
                        if (AutoConfig.getConfigHolder(ModConfig.class).getConfig().vanilla_mode) gbe.setPowerMode(0);
                        if (gbe.getPowerMode()>=2)
                            gbe.setPowerMode(0);
                        else
                            gbe.setPowerMode(gbe.getPowerMode()+1);
                    }
                }
            });
        });

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, LANTHANUM_ORE_PLACED_KEY);

        ModBlocks.init();
        ModItems.init();
        ModBlockEntities.init();
        ModMiscs.init();
    
        EnergyIntegration.register();
    }
}