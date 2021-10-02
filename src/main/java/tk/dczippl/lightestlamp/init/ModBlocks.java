package tk.dczippl.lightestlamp.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import tk.dczippl.lightestlamp.blocks.*;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeBlock;

import static net.minecraft.block.Blocks.*;
import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class ModBlocks
{
    //public static final Block DARK_AIR = registerBlock("dark_air", () -> new DarkAirBlock());
    public static final Block LIGHT_AIR = registerBlock("light_air", new LightAirBlock());
    public static final Block WATERLOGGABLE_LIGHT_AIR = registerBlock("waterloggable_light_air", new WaterLoggableLightAirBlock());
    public static final Block CLEAR_LAMP = registerBlock("clear_lamp", new RedstoneLampBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block ALPHA_LAMP = registerBlock("alpha_lamp", new GenerableLampBlock(LampType.ALPHA,false));
    public static final Block BETA_LAMP = registerBlock("beta_lamp", new GenerableLampBlock(LampType.BETA,false));
    public static final Block GAMMA_LAMP = registerBlock("gamma_lamp", new GenerableLampBlock(LampType.GAMMA,false));
    public static final Block DELTA_LAMP = registerBlock("delta_lamp", new GenerableLampBlock(LampType.DELTA,false));
    public static final Block EPSILON_LAMP = registerBlock("epsilon_lamp", new GenerableLampBlock(LampType.EPSILON,false));
    public static final Block ZETA_LAMP = registerBlock("zeta_lamp", new GenerableLampBlock(LampType.ZETA,false));
    public static final Block ETA_LAMP = registerBlock("eta_lamp", new GenerableLampBlock(LampType.ETA,false));
    public static final Block OMEGA_LAMP = registerBlock("omega_lamp", new GenerableLampBlock(LampType.OMEGA,false));
    public static final Block CLEAR_SEA_LANTERN = registerBlock("clear_sea_lantern", new GenerableLampBlock(LampType.ALPHA,true));
    public static final Block DEEP_SEA_LANTERN = registerBlock("deep_sea_lantern", new GenerableLampBlock(LampType.BETA,true));
    public static final Block OCEAN_LANTERN = registerBlock("ocean_lantern", new GenerableLampBlock(LampType.EPSILON,true));
    public static final Block DEEP_OCEAN_LANTERN = registerBlock("deep_ocean_lantern", new GenerableLampBlock(LampType.OCEAN_BETWEEN,true));
    public static final Block ABYSSAL_LANTERN = registerBlock("abyssal_lantern", new GenerableLampBlock(LampType.ZETA,true));
    public static final Block ALCHEMICAL_LAMP = registerBlock("alchemical_lamp", new AlchemicalLampBlock());
    //public static final Block SPOTLIGHT = registerBlock("spotlight", () -> new AlchemicalLampBlock());
    public static final Block CHUNK_CLEANER = registerBlock("debug_chunk_cleaner", new ChunkCleanerBlock());

    //Other lamps

    //public static final Block ANTI_LAMP = registerBlock("anti_lamp", new GenerableLampBlock(LampType.ANTI,false));
    public static final Block JUNGLE_LANTERN = registerBlock("jungle_lantern", new JungleLanternBlock());

    //Other Blocks

    public static final Block NEON_ROD_BLOCK = registerBlock("neon_rod_block", new PillarBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(10)));
    public static final Block ARGON_ROD_BLOCK = registerBlock("argon_rod_block", new PillarBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(10)));
    public static final Block KRYPTON_ROD_BLOCK = registerBlock("krypton_rod_block", new PillarBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(14)));
    public static final Block XENON_ROD_BLOCK = registerBlock("xenon_rod_block", new PillarBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(14)));
    public static final Block RADON_ROD_BLOCK = registerBlock("radon_rod_block", new PillarBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block VANTA_BLACK = registerBlock("vanta_black", new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL)
            .hardness(1f).resistance(1)));
    public static final Block MONAZITE_ORE = registerBlock("monazite_ore", new Block(FabricBlockSettings.copyOf(IRON_ORE).breakByTool(FabricToolTags.PICKAXES,2)));
    public static final Block LUMINATIUM_BLOCK = registerBlock("luminatium_block", new Block(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));

    //Glowstones
    public static final Block NEON_BLOCK = registerBlock("neon_block", new Block(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block ARGON_BLOCK = registerBlock("argon_block", new Block(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block KRYPTON_BLOCK = registerBlock("krypton_block", new Block(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block XENON_BLOCK = registerBlock("xenon_block", new Block(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block RADON_BLOCK = registerBlock("radon_block", new Block(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15)));
    public static final Block GLOWING_GLASS_BLOCK = registerBlock("glowing_glass_block", new GlowingGlassBlock());
    public static final Block GLOWSTONE_CENTRIFUGE = registerBlock("glowstone_centrifuge", new GasCentrifugeBlock());

    public static final Block OCC = registerBlock("occ", new OmegaChunkCleanerBlock());

    public static Block registerBlock(String id, Block block) {
        SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), new BlockItem(block, new Item.Settings().group(ModMiscs.LAMPS_TAB)));
        return SimpleRegistry.register(Registry.BLOCK, new Identifier(MOD_ID,id), block);
    }

    public static void init() {
    }
}