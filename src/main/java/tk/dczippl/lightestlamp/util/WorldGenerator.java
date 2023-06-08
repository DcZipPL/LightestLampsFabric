package tk.dczippl.lightestlamp.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.BlockState;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import tk.dczippl.lightestlamp.init.ModBlocks;

import java.util.List;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class WorldGenerator
{
    protected record CoreOre(Identifier id, int veinsPerChunk, int veinSize, int minOffset, int maxY, BlockState ore, RuleTest rule){
        public ConfiguredFeature<?, ?> create(){
            return new ConfiguredFeature(Feature.ORE,
                    new OreFeatureConfig(
                            rule,
                            ore,
                            veinSize));
        }

        public PlacedFeature createPlaced(){
            return new PlacedFeature(RegistryEntry.of(create()),
                    List.of(
                    CountPlacementModifier.of(veinsPerChunk),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(
                            YOffset.aboveBottom(minOffset),
                            YOffset.fixed(maxY)),
                    BiomePlacementModifier.of()
            ));
        }
    }

    protected static final CoreOre LANTHANUM = new CoreOre(new Identifier(MOD_ID, "mozaite_ore"),24,3,12,84,ModBlocks.LANTHANUM_ORE.getDefaultState(),OreConfiguredFeatures.NETHERRACK);

    public static void register(){
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, LANTHANUM.id, LANTHANUM.create());
        Registry.register(BuiltinRegistries.PLACED_FEATURE, LANTHANUM.id, LANTHANUM.createPlaced());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, LANTHANUM.id));
    }
}