package tk.dczippl.lightestlamp.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tk.dczippl.lightestlamp.blocks.*;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeBlock;

import java.util.ArrayList;

import static net.minecraft.block.Blocks.*;
import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

@SuppressWarnings("unused")
public class ModBlocks
{
    public static final ArrayList<ItemStack> ITEMS = new ArrayList<>();

    public static final Block LIGHT_AIR = registerBlock("light_air", new LightAirBlock(),false);
    public static final Block WATERLOGGABLE_LIGHT_AIR = registerBlock("waterloggable_light_air", new WaterLoggableLightAirBlock(),false);
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
    //public static final Block TRAFFIC_LAMP = registerBlock("traffic_lamp", new DescRedstoneLampBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(15),"tooltip.lightestlamp.analog"));
    //public static final Block SOUL_LAMP = registerBlock("soul_lamp", new DescRedstoneLampBlock(FabricBlockSettings.copyOf(GLOWSTONE).luminance(13),"tooltip.lightestlamp.soul_lamp"));
    //public static final Block SPOTLIGHT = registerBlock("spotlight", new SpotlightBlock());
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
    public static final Block VANTA_BLACK = registerBlock("vanta_black", new Block(FabricBlockSettings.copyOf(BLACK_WOOL)
            .hardness(1f).resistance(1)));
    public static final Block LANTHANUM_ORE = registerBlock("lanthanum_ore", new Block(FabricBlockSettings.copyOf(IRON_ORE).requiresTool()));
    public static final Block RAW_LANTHANUM_BLOCK = registerBlock("raw_lanthanum_block", new Block(FabricBlockSettings.copyOf(RAW_IRON_BLOCK).requiresTool()));

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
        BlockItem blockItem = Registry.register(Registries.ITEM, new Identifier(MOD_ID,id), new BlockItem(block, new Item.Settings()));
        ITEMS.add(new ItemStack(blockItem));
        return Registry.register(Registries.BLOCK, new Identifier(MOD_ID,id), block);
    }
    
    public static Block registerBlock(String id, Block block, boolean hasItem) {
        Block registered = Registry.register(Registries.BLOCK, new Identifier(MOD_ID,id), block);
        if (hasItem) {
            Item blockItem = Registry.register(Registries.ITEM, new Identifier(MOD_ID, id), new BlockItem(registered, new Item.Settings()));
            ITEMS.add(new ItemStack(blockItem));
        }
        return registered;
    }

    public static void init() {
    }
}