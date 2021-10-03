package tk.dczippl.lightestlamp.plugins.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GlowstoneCentrifugeDisplay extends BasicDisplay {
	public GlowstoneCentrifugeDisplay(GasCentrifugeRecipe recipe) {
		super(
				List.of(
						EntryIngredients.of(recipe.top_input),
						EntryIngredients.ofItems(new ArrayList<>(GasCentrifugeRecipe.getBurnTimes().keySet()))
				),
				List.of(
						EntryIngredients.of(recipe.right_up_output),
						EntryIngredients.of(recipe.left_up_output),
						EntryIngredients.of(recipe.right_bottom_output),
						EntryIngredients.of(recipe.left_bottom_output)
				)
		);
	}
	
	/**
	 * Gets the display display category identifier
	 *
	 * @return the identifier of the category
	 */
	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return LLREIPlugin.ID;
	}
}
