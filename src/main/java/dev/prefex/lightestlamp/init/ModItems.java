package dev.prefex.lightestlamp.init;

import dev.prefex.lightestlamp.LightestLampsMod;
import dev.prefex.lightestlamp.items.FilterItem;
import dev.prefex.lightestlamp.items.StickAndBowlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import dev.prefex.lightestlamp.items.*;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class ModItems
{
    public static final ArrayList<ItemStack> ITEMS = new ArrayList<>();
    public static final Item.Settings globalSettings = new Item.Settings().maxCount(64);
    
    public static final Item GLOW_LICHEN_FIBER = registerItem(new Item(globalSettings), "glow_lichen_fiber");
    public static final Item CHORUS_FIBER = registerItem(new Item(globalSettings), "chorus_fiber");
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
    public static final Item XENON_ROD = registerItem(new Item(new Item.Settings().maxCount(16)), "xenon_rod");
    public static final Item KRYPTON_ROD = registerItem(new Item(globalSettings), "krypton_rod");
    public static final Item ARGON_ROD = registerItem(new Item(globalSettings), "argon_rod");
    public static final Item NEON_ROD = registerItem(new Item(globalSettings), "neon_rod");
    public static final Item CARBON_NANOTUBE = registerItem(new Item(globalSettings), "carbon_nanotube");
    public static final Item BORON_MESH = registerItem(new Item(new Item.Settings().maxCount(16)), "lanthanum_mesh");
    public static final Item NETHERITE_MESH = registerItem(new Item(new Item.Settings().maxCount(16)), "netherite_mesh");
    public static final Item ALCHEMICAL_DUST = registerItem(new Item(globalSettings), "alchemical_dust");
    public static final Item STICKANDBOWL = registerItem(new StickAndBowlItem(), "stickandbowl");
    public static final Item BASIC_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(124),"tooltip.lightestlamp.basic_filter"), "basic_centrifuge_filter");
    public static final Item NEON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(118),"tooltip.lightestlamp.neon_filter"), "neon_centrifuge_filter");
    public static final Item ARGON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(105),"tooltip.lightestlamp.argon_filter"), "argon_centrifuge_filter");
    public static final Item KRYPTON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(83),"tooltip.lightestlamp.krypton_filter"), "krypton_centrifuge_filter");
    public static final Item XENON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(92),"tooltip.lightestlamp.xenon_filter"), "xenon_centrifuge_filter");
    public static final Item RADON_FILTER = registerItem(new FilterItem(new Item.Settings().maxCount(1).maxDamage(62),"tooltip.lightestlamp.radon_filter"), "radon_centrifuge_filter");
    public static final Item LANTHANUM_DUST = registerItem(new Item(globalSettings), "lanthanum_dust");
    public static final Item LANTHANUM_PILE = registerItem(new Item(globalSettings), "lanthanum_small_dust");
    public static final Item LANTHANUM_INGOT = registerItem(new Item(globalSettings), "lanthanum_ingot");
    public static final Item LANTHANUM_NUGGET = registerItem(new Item(globalSettings), "lanthanum_nugget");
    public static final Item RAW_LANTHANUM = registerItem(new Item(globalSettings), "raw_lanthanum");
    
    public static Item registerItem(Item item,String id) {
        Item registered = Registry.register(Registries.ITEM, new Identifier(LightestLampsMod.MOD_ID,id), item);
        ITEMS.add(new ItemStack(registered));
        return registered;
    }

    public static void init() {
    }
}