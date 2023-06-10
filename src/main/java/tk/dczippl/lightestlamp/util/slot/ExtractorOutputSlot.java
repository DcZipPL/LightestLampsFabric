package tk.dczippl.lightestlamp.util.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class ExtractorOutputSlot extends OutputSlot
{
    public ExtractorOutputSlot(PlayerEntity parPlayer, Inventory inventory, int parSlotIndex, int parXDisplayPosition, int parYDisplayPosition)
    {
        super(parPlayer, inventory, parSlotIndex, parXDisplayPosition, parYDisplayPosition);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     *
     * @param parItemStack
     *            the par item stack
     */
    @Override
    protected void onCrafted(ItemStack parItemStack)
    {
        if (!thePlayer.getWorld().isClient)
        {
            int expEarned = getNumOutput();
            float expFactor = 0.0F;

            if (expFactor == 0.0F)
            {
                expEarned = 0;
            }
            else if (expFactor < 1.0F)
            {
                int possibleExpEarned = MathHelper.floor(expEarned * expFactor);

                if (possibleExpEarned < MathHelper.ceil(expEarned * expFactor) && Math.random() < expEarned * expFactor - possibleExpEarned)
                {
                    ++possibleExpEarned;
                }

                expEarned = possibleExpEarned;
            }
        }

        setNumOutput(0);
    }
}