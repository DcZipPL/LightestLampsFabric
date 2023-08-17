package tk.dczippl.lightestlamp.util.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import tk.dczippl.lightestlamp.items.FilterItem;

public class FilterSlot extends Slot {
	public FilterSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return stack.getItem() instanceof FilterItem;
	}
}
