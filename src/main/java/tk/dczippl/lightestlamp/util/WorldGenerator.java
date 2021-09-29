package tk.dczippl.lightestlamp.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import tk.dczippl.lightestlamp.init.ModBlocks;

import java.util.ArrayList;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class WorldGenerator
{
    private static ConfiguredFeature<?, ?> MOZAITE_ORE = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_NETHER, // We use OreFeatureConfig.Rules.BASE_STONE_NETHER for the Nether
                    ModBlocks.MONAZITE_ORE.getDefaultState(),
                    3))
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.fixed(12), YOffset.fixed(84))))
            .spreadHorizontally()
            .repeat(16);

    public static void register(){
        RegistryKey<ConfiguredFeature<?, ?>> mozaiteOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(MOD_ID, "mozaite_ore"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, mozaiteOre.getValue(), MOZAITE_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, mozaiteOre);
    }
}