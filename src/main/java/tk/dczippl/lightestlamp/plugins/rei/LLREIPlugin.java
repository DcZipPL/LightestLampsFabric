package tk.dczippl.lightestlamp.plugins.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import tk.dczippl.lightestlamp.LightestLampsMod;
import tk.dczippl.lightestlamp.init.ModBlocks;
import tk.dczippl.lightestlamp.init.ModItems;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeRecipe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LLREIPlugin implements REIClientPlugin {
	public static final CategoryIdentifier<? extends GlowstoneCentrifugeDisplay> ID = CategoryIdentifier.of(LightestLampsMod.MOD_ID,"glowstone_centrifuge");
	
	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(new GlowstoneCentrifugeCategory());
		registry.addWorkstations(ID, EntryStacks.of(new ItemStack(ModBlocks.GLOWSTONE_CENTRIFUGE)));
	}
	
	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.add(new GlowstoneCentrifugeDisplay(GasCentrifugeRecipe.basic));
		registry.add(new GlowstoneCentrifugeDisplay(GasCentrifugeRecipe.neon));
		registry.add(new GlowstoneCentrifugeDisplay(GasCentrifugeRecipe.argon));
		registry.add(new GlowstoneCentrifugeDisplay(GasCentrifugeRecipe.krypton));
		registry.add(new GlowstoneCentrifugeDisplay(GasCentrifugeRecipe.xenon));
		registry.add(new GlowstoneCentrifugeDisplay(GasCentrifugeRecipe.radon));
	}
	
	private static void addDescription(DisplayRegistry registry, Item itemDefinition, String... message) {
		DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStacks.of(itemDefinition),
				Text.translatable("rei.obtainingmoonshard"));
		info.lines(Arrays.stream(message).map(Text::translatable).collect(Collectors.toList()));
		registry.add(info);
	}
}
