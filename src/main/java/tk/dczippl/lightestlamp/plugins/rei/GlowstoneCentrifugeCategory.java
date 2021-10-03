package tk.dczippl.lightestlamp.plugins.rei;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import tk.dczippl.lightestlamp.init.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class GlowstoneCentrifugeCategory implements DisplayCategory<GlowstoneCentrifugeDisplay> {
	/**
	 * Returns the renderer of the icon.
	 *
	 * @return the renderer of the icon
	 */
	@Override
	public Renderer getIcon() {
		return EntryStacks.of(new ItemStack(ModBlocks.GLOWSTONE_CENTRIFUGE));
	}
	
	/**
	 * Returns the category title.
	 *
	 * @return the title
	 */
	@Override
	public Text getTitle() {
		return new TranslatableText("rei.lightestlamp.glowstonecentrifuge");
	}
	
	@Override
	public int getDisplayHeight() {
		return 80;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CategoryIdentifier<? extends GlowstoneCentrifugeDisplay> getCategoryIdentifier() {
		return (CategoryIdentifier<? extends GlowstoneCentrifugeDisplay>) LLREIPlugin.ID;
	}
	
	@Override
	public List<Widget> setupDisplay(GlowstoneCentrifugeDisplay display, Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 24);
		List<Widget> widgets = Lists.newArrayList();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createArrow(new Point(startPoint.x + 27, startPoint.y + 13)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61, startPoint.y)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61 + 30, startPoint.y)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61, startPoint.y + 30)));
		widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 61 + 30, startPoint.y + 30)));
		widgets.add(Widgets.createSlot(new Point(startPoint.x - 14, startPoint.y + 13)).entries(display.getInputEntries().get(0)).markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 4, startPoint.y + 13)).entries(display.getInputEntries().get(1)).markInput());
		
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61, startPoint.y)).entries(display.getOutputEntries().get(0)).disableBackground().markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + 30, startPoint.y)).entries(display.getOutputEntries().get(1)).disableBackground().markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61, startPoint.y + 30)).entries(display.getOutputEntries().get(2)).disableBackground().markInput());
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 61 + 30, startPoint.y + 30)).entries(display.getOutputEntries().get(3)).disableBackground().markInput());
		
		return widgets;
	}
}
