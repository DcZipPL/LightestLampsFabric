package dev.prefex.lightestlamp.plugins.rei;

import dev.prefex.lightestlamp.machine.gascentrifuge.GasCentrifugeRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.ArrayList;
import java.util.List;

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
	 * Gets the display category identifier
	 *
	 * @return the identifier of the category
	 */
	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return LLREIPlugin.ID;
	}
}
