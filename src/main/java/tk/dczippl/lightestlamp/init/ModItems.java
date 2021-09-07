package tk.dczippl.lightestlamp.init;

import net.minecraft.item.Item;
import tk.dczippl.lightestlamp.LightestLampsMod;
import tk.dczippl.lightestlamp.items.*;

public class ModItems
{
    public static final Item.Settings globalSettings = new Item.Settings().maxCount(64).group(LightestLampsMod.main_group);
    
    public static final Item CHORUS_FIBER = registerItem(new Item(globalSettings), "chorus_fiber");
    public static final Item MOON_SHARD = registerItem(new Item(globalSettings), "moon_shard");
    public static final Item KRYPTON_PILE = registerItem(new Item(globalSettings), "krypton_small_dust");
    public static final Item RADON_DUST = registerItem(new Item(globalSettings), "radon_dust");
    public static final Item XENON_DUST = registerItem(new Item(globalSettings), "xenon_dust");
    public static final Item KRYPTON_DUST = registerItem(new Item(globalSettings), "krypton_dust");
    public static final Item ARGON_DUST = registerItem(new Item(globalSettings), "argon_dust");
    public static final Item NEON_DUST = registerItem(new Item(globalSettings), "neon_dust");
    public static final Item RADON_PILE = registerItem(new Item(globalSettings), "radon_pile");
    public static final Item XENON_PILE = registerItem(new Item(globalSettings), "xenon_pile");
    public static final Item ARGON_PILE = registerItem(new Item(globalSettings), "argon_pile");
    public static final Item NEON_PILE = registerItem(new Item(globalSettings), "neon_pile");
    public static final Item EMPTY_ROD = registerItem(new Item(globalSettings), "empty_rod");
    public static final Item RADON_ROD = registerItem(new Item(globalSettings), "radon_rod");
    public static final Item XENON_ROD = registerItem(new Item(new Item.Settings().maxCount(16).group(LightestLampsMod.main_group)), "xenon_rod");
    public static final Item KRYPTON_ROD = registerItem(new Item(globalSettings), "krypton_rod");
    public static final Item ARGON_ROD = registerItem(new Item(globalSettings), "argon_rod");
    public static final Item NEON_ROD = registerItem(new Item(globalSettings), "neon_rod");
    public static final Item CARBON_NANOTUBE = registerItem(new Item(globalSettings), "carbon_nanotube");
    public static final Item BROMINE_CRYSTAL = registerItem(new Item(globalSettings), "bromine_crystal");
    public static final Item BORON_MESH = registerItem(new Item(new Item.Settings().maxCount(16).group(LightestLampsMod.main_group)), "lanthanum_mesh");
    public static final Item NETHERITE_MESH = registerItem(new Item(new Item.Settings().maxCount(16).group(LightestLampsMod.main_group)), "netherite_mesh");
    public static final Item DEBUG_STICK = registerItem(new DebugStickItem(new Item.Settings().group(LightestLampsMod.main_group)), "debug_stick");
    public static final Item ALCHEMICAL_DUST = registerItem(new Item(globalSettings), "alchemical_dust");
    public static final Item STICKANDBOWL = registerItem(new StickAndBowlItem(new Item.Settings().maxCount(1).group(LightestLampsMod.main_group)), "stickandbowl");
    public static final Item BASIC_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(124).group(LightestLampsMod.main_group),"tooltip.lightestlamp.basic_filter"), "basic_centrifuge_filter");
    public static final Item NEON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(118).group(LightestLampsMod.main_group),"tooltip.lightestlamp.neon_filter"), "neon_centrifuge_filter");
    public static final Item ARGON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(105).group(LightestLampsMod.main_group),"tooltip.lightestlamp.argon_filter"), "argon_centrifuge_filter");
    public static final Item KRYPTON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(83).group(LightestLampsMod.main_group),"tooltip.lightestlamp.krypton_filter"), "krypton_centrifuge_filter");
    public static final Item BROMINE_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(31).group(LightestLampsMod.main_group),"tooltip.lightestlamp.bromine_filter"), "bromine_centrifuge_filter");
    public static final Item XENON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(92).group(LightestLampsMod.main_group),"tooltip.lightestlamp.xenon_filter"), "xenon_centrifuge_filter");
    public static final Item RADON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(62).group(LightestLampsMod.main_group),"tooltip.lightestlamp.radon_filter"), "radon_centrifuge_filter");
    public static final Item LANTHANUM_DUST = registerItem(new Item(globalSettings), "lanthanum_dust");
    public static final Item LANTHANUM_PILE = registerItem(new Item(globalSettings), "lanthanum_pile");
    public static final Item LANTHANUM_INGOT = registerItem(new Item(globalSettings), "lanthanum_ingot");
    public static final Item LANTHANUM_NUGGET = registerItem(new Item(globalSettings), "lanthanum_nugget");

    public static final Item GLOWING_DUST_AGGLOMERATIO = registerItem(new Item(globalSettings), "glowing_dust_agglomeratio");

    public static void init() {
    }
}