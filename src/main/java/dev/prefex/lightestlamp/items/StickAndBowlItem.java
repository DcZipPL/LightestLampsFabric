package dev.prefex.lightestlamp.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class StickAndBowlItem extends Item
{
	public StickAndBowlItem()
	{
		super(new Item.Settings().maxCount(1).maxDamage(10));
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}

	@Override
	public ItemStack getRecipeRemainder(ItemStack itemStack) {
		ItemStack stack = itemStack.copy();
		if (stack.getDamage() != stack.getMaxDamage())
			stack.setDamage(stack.getDamage()+1);
		else stack = new ItemStack(Items.BOWL);
		return stack;
	}

	@Override
	public boolean hasRecipeRemainder() {
		return true;
	}
}
