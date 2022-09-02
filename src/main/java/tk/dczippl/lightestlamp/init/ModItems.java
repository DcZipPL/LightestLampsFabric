package tk.dczippl.lightestlamp.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import tk.dczippl.lightestlamp.LightestLampsMod;
import tk.dczippl.lightestlamp.items.*;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class ModItems
{
    public static final Item.Settings globalSettings = new Item.Settings().maxCount(64).group(ModMiscs.LAMPS_TAB);
    
    public static final Item GLOW_LICHEN_FIBER = registerItem(new Item(globalSettings), "glow_lichen_fiber");
    public static final Item CHORUS_FIBER = registerItem(new Item(globalSettings), "chorus_fiber");
    //public static final Item MOON_SHARD = registerItem(new Item(globalSettings), "moon_shard");
    public static final Item RADON_DUST = registerItem(new Item(globalSettings), "radon_dust");
    public static final Item XENON_DUST = registerItem(new Item(globalSettings), "xenon_dust");
    public static final Item KRYPTON_DUST = registerItem(new Item(globalSettings), "krypton_dust");
    public static final Item ARGON_DUST = registerItem(new Item(globalSettings), "argon_dust");
    public static final Item RADON_PILE = registerItem(new Item(globalSettings), "radon_pile");
    public static final Item XENON_PILE = registerItem(new Item(globalSettings), "xenon_pile");
    public static final Item KRYPTON_PILE = registerItem(new Item(globalSettings), "krypton_pile");
    public static final Item ARGON_PILE = registerItem(new Item(globalSettings), "argon_pile");
    public static final Item NEON_DUST = registerItem(new Item(globalSettings), "neon_dust");
    public static final Item NEON_PILE = registerItem(new Item(globalSettings), "neon_pile");
    public static final Item EMPTY_ROD = registerItem(new Item(globalSettings), "empty_rod");
    public static final Item RADON_ROD = registerItem(new Item(globalSettings), "radon_rod");
    public static final Item XENON_ROD = registerItem(new Item(new Item.Settings().maxCount(16).group(ModMiscs.LAMPS_TAB)), "xenon_rod");
    public static final Item KRYPTON_ROD = registerItem(new Item(globalSettings), "krypton_rod");
    public static final Item ARGON_ROD = registerItem(new Item(globalSettings), "argon_rod");
    public static final Item NEON_ROD = registerItem(new Item(globalSettings), "neon_rod");
    public static final Item CARBON_NANOTUBE = registerItem(new Item(globalSettings), "carbon_nanotube");
    public static final Item BORON_MESH = registerItem(new Item(new Item.Settings().maxCount(16).group(ModMiscs.LAMPS_TAB)), "lanthanum_mesh");
    public static final Item NETHERITE_MESH = registerItem(new Item(new Item.Settings().maxCount(16).group(ModMiscs.LAMPS_TAB)), "netherite_mesh");
    public static final Item ALCHEMICAL_DUST = registerItem(new Item(globalSettings), "alchemical_dust");
    public static final Item STICKANDBOWL = registerItem(new StickAndBowlItem(), "stickandbowl");
    public static final Item BASIC_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(124).group(ModMiscs.LAMPS_TAB),"tooltip.lightestlamp.basic_filter"), "basic_centrifuge_filter");
    public static final Item NEON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(118).group(ModMiscs.LAMPS_TAB),"tooltip.lightestlamp.neon_filter"), "neon_centrifuge_filter");
    public static final Item ARGON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(105).group(ModMiscs.LAMPS_TAB),"tooltip.lightestlamp.argon_filter"), "argon_centrifuge_filter");
    public static final Item KRYPTON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(83).group(ModMiscs.LAMPS_TAB),"tooltip.lightestlamp.krypton_filter"), "krypton_centrifuge_filter");
    public static final Item XENON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(92).group(ModMiscs.LAMPS_TAB),"tooltip.lightestlamp.xenon_filter"), "xenon_centrifuge_filter");
    public static final Item RADON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(62).group(ModMiscs.LAMPS_TAB),"tooltip.lightestlamp.radon_filter"), "radon_centrifuge_filter");
    public static final Item LANTHANUM_DUST = registerItem(new Item(globalSettings), "lanthanum_dust");
    public static final Item LANTHANUM_SMALL_DUST = registerItem(new Item(globalSettings), "lanthanum_small_dust");
    public static final Item LANTHANUM_INGOT = registerItem(new Item(globalSettings), "lanthanum_ingot");
    public static final Item LANTHANUM_NUGGET = registerItem(new Item(globalSettings), "lanthanum_nugget");
    public static final Item RAW_LANTHANUM = registerItem(new Item(globalSettings), "raw_lanthanum");
    
    public static Item registerItem(Item item,String id) {
        return SimpleRegistry.register(Registry.ITEM, new Identifier(MOD_ID,id), item);
    }

    public static void init() {
    }
}