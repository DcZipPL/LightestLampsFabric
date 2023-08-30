package dev.prefex.lightestlamp.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FilterItem extends Item
{
	private String tooltip;

	public FilterItem(Item.Settings settings, String tooltip)
	{
		super(settings);
		this.tooltip = tooltip;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.translatable(this.tooltip).setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))));

		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}