package tk.dczippl.lightestlamp.machine.gascentrifuge;

import com.google.common.collect.Maps;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import tk.dczippl.lightestlamp.init.ModItems;
import tk.dczippl.lightestlamp.plugins.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static tk.dczippl.lightestlamp.LightestLampsMod.GLOWSTONE_SMALL_DUSTS;

public class GasCentrifugeRecipe
{
    protected static List<GasCentrifugeRecipe> recipes = new ArrayList<GasCentrifugeRecipe>();
    public static final GasCentrifugeRecipe basic = new GasCentrifugeRecipe(
            new ItemStack(ModItems.BASIC_FILTER), new ItemStack(Items.GLOWSTONE),new ItemStack(ModItems.NEON_DUST),new ItemStack(ModItems.ARGON_DUST),new ItemStack(ModItems.KRYPTON_PILE),ItemStack.EMPTY);
    public static final GasCentrifugeRecipe neon = new GasCentrifugeRecipe(
            new ItemStack(ModItems.NEON_FILTER), new ItemStack(Items.GLOWSTONE),new ItemStack(ModItems.NEON_DUST,2),new ItemStack(ModItems.NEON_PILE),new ItemStack(ModItems.ARGON_PILE),ItemStack.EMPTY);
    public static final GasCentrifugeRecipe argon = new GasCentrifugeRecipe(
            new ItemStack(ModItems.ARGON_FILTER), new ItemStack(Items.GLOWSTONE),new ItemStack(ModItems.ARGON_DUST,2),new ItemStack(ModItems.NEON_PILE,2),new ItemStack(ModItems.XENON_PILE,1),ItemStack.EMPTY);
    public static final GasCentrifugeRecipe krypton = new GasCentrifugeRecipe(
            new ItemStack(ModItems.KRYPTON_FILTER), new ItemStack(Items.GLOWSTONE),new ItemStack(ModItems.ARGON_PILE,3),new ItemStack(ModItems.KRYPTON_DUST),new ItemStack(ModItems.KRYPTON_PILE,2),new ItemStack(ModItems.XENON_PILE,2));
    public static final GasCentrifugeRecipe xenon = new GasCentrifugeRecipe(
            new ItemStack(ModItems.XENON_FILTER), new ItemStack(Items.GLOWSTONE),new ItemStack(ModItems.KRYPTON_PILE,2),new ItemStack(ModItems.XENON_DUST),new ItemStack(ModItems.XENON_PILE,2),new ItemStack(ModItems.RADON_PILE,1));
    public static final GasCentrifugeRecipe radon = new GasCentrifugeRecipe(
            new ItemStack(ModItems.RADON_FILTER), new ItemStack(Items.GLOWSTONE),new ItemStack(ModItems.XENON_PILE,2),new ItemStack(ModItems.RADON_PILE,3),ItemStack.EMPTY,ItemStack.EMPTY);
    /*public static final GasCentrifugeRecipe bromine = new GasCentrifugeRecipe(
            new ItemStack(ModItems.BROMINE_FILTER), new ItemStack(Items.GLOWSTONE),ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY);*/

    public GasCentrifugeRecipe(ItemStack top_input, ItemStack bottom_input, ItemStack right_up_output, ItemStack left_up_output, ItemStack right_bottom_output, ItemStack left_bottom_output)
    {
        this.top_input = top_input;
        //this.bottom_input = bottom_input;
        this.right_up_output = right_up_output;
        this.left_up_output = left_up_output;
        this.right_bottom_output = right_bottom_output;
        this.left_bottom_output = left_bottom_output;
    }

    public static ItemStack[] getRecipeOutputs(ItemStack input)
    {
        for (GasCentrifugeRecipe recipe : getRecipes())
        {
            if (input.getItem() == recipe.top_input.getItem())
            {
                ItemStack[] items = new ItemStack[]{recipe.right_up_output, recipe.left_up_output, recipe.right_bottom_output, recipe.left_bottom_output};
                return items;
            }
        }
        ItemStack[] items = new ItemStack[]{ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};
        return items;
    }

    public static List<GasCentrifugeRecipe> getRecipes()
    {
        recipes.add(basic);
        recipes.add(neon);
        recipes.add(argon);
        recipes.add(krypton);
        recipes.add(xenon);
        recipes.add(radon);
        return recipes;
    }

    public ItemStack top_input;
    public ItemStack bottom_input;
    public ItemStack right_up_output;
    public ItemStack left_up_output;
    public ItemStack right_bottom_output;
    public ItemStack left_bottom_output;
    
    public static Map<Item, Integer> getBurnTimes() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        
        int multiplier = Config.glowstone_multiplier >= 2 ? Config.glowstone_multiplier : 2;

        Optional<RegistryEntryList.Named<Item>> glowstone_small_dusts = Registry.ITEM.getEntryList(GLOWSTONE_SMALL_DUSTS);
        glowstone_small_dusts.ifPresent(registryEntries -> addItemTagBurnTime(map, registryEntries.getStorage().right().orElseGet(ArrayList::new).stream().map(RegistryEntry::value).toList(), 10 * multiplier));
        
        addItemBurnTime(map, ModItems.GLOW_LICHEN_FIBER,5*multiplier);
        addItemBurnTime(map, Items.GLOW_BERRIES,60*multiplier);
        addItemBurnTime(map, Items.GLOWSTONE_DUST,40*multiplier);
        addItemBurnTime(map, Blocks.GLOWSTONE.asItem(), 160*multiplier);
        addItemBurnTime(map, Blocks.SHROOMLIGHT.asItem(), 240*multiplier);
        return map;
    }
    
    private static void addItemTagBurnTime(Map<Item, Integer> map, List<Item> itemTag, int burnTimeIn) {
        for(Item item : itemTag) {
            map.put(item, burnTimeIn);
        }
        
    }
    
    private static void addItemBurnTime(Map<Item, Integer> map, Item itemProvider, int burnTimeIn) {
        map.put(itemProvider, burnTimeIn);
    }
}