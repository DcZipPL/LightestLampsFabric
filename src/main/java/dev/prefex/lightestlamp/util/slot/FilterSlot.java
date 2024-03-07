package dev.prefex.lightestlamp.util.slot;

import dev.prefex.lightestlamp.items.FilterItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class FilterSlot extends Slot {
	public FilterSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return stack.getItem() instanceof FilterItem;
	}
}
