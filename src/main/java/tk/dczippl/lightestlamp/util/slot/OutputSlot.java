package tk.dczippl.lightestlamp.util.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public abstract class OutputSlot extends Slot
{
    /** The player that is using the GUI where this slot resides. */
    protected final PlayerEntity thePlayer;
    private int numOutput;

    /**
     * Instantiates a new slot output.
     *
     * @param parPlayer
     *            the par player
     * @param inventory
     *            the par I inventory
     * @param parSlotIndex
     *            the par slot index
     * @param parXDisplayPosition
     *            the par X display position
     * @param parYDisplayPosition
     *            the par Y display position
     */
    public OutputSlot(PlayerEntity parPlayer, Inventory inventory, int parSlotIndex, int parXDisplayPosition, int parYDisplayPosition)
    {
        super(inventory, parSlotIndex, parXDisplayPosition, parYDisplayPosition);
        this.thePlayer = parPlayer;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    /*/**
     * Decrease the size of the stack in slot by the amount of the int arg. Returns the new stack.
     *
     * @param parAmount
     *            the par amount
     * @return the item stack
     */
    /*@Override
    public ItemStack decrStackSize(int parAmount)
    {
        if (getHasStack())
        {
            setNumOutput(getNumOutput() + Math.min(parAmount, getStack().getCount()));
        }

        return super.decrStackSize(parAmount);
    }*/

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.onCrafted(stack);
        super.onTakeItem(player, stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an internal count then calls onCrafting(item).
     *
     * @param stack
     *            the par item stack
     * @param amount
     *            the par count
     */
    @Override
    protected void onCrafted(ItemStack stack, int amount) {
        setNumOutput(getNumOutput() + amount);
        onCrafted(stack);
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
        // override this in your custom slot class
        // should do things like update achievements/advancements and create experience orbs
    }

    /**
     * Gets the num output.
     *
     * @return the num output
     */
    protected int getNumOutput()
    {
        return numOutput;
    }

    /**
     * Sets the num output.
     *
     * @param numOutput
     *            the new num output
     */
    protected void setNumOutput(int numOutput)
    {
        this.numOutput = numOutput;
    }
}