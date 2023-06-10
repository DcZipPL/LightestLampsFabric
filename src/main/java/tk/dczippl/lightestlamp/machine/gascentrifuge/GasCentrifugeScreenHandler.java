package tk.dczippl.lightestlamp.machine.gascentrifuge;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tk.dczippl.lightestlamp.init.ModMiscs;
import tk.dczippl.lightestlamp.plugins.Config;

public class GasCentrifugeScreenHandler extends ScreenHandler
{
    private final Inventory furnaceInventory;
    private BlockPos pos;
    protected final World world;
    public final PropertyDelegate delegate;
    public final PlayerEntity player;

    protected GasCentrifugeScreenHandler(ScreenHandlerType<?> containerTypeIn, int id, PlayerInventory playerInventoryIn, Inventory furnaceInventoryIn, PropertyDelegate delegate) {
        super(containerTypeIn, id);
        checkSize(furnaceInventoryIn, 6);
        checkDataCount(delegate, 7);
        this.furnaceInventory = furnaceInventoryIn;
        this.delegate = delegate;
        pos = BlockPos.ORIGIN;
        this.world = playerInventoryIn.player.getWorld();
        this.player = playerInventoryIn.player;
        this.addSlot(new Slot(furnaceInventoryIn, 0, 16, 35));
        this.addSlot(new Slot(furnaceInventoryIn, 1, 41, 35));
        this.addSlot(new FurnaceOutputSlot(playerInventoryIn.player, furnaceInventoryIn, 2, 99, 19));
        this.addSlot(new FurnaceOutputSlot(playerInventoryIn.player, furnaceInventoryIn, 3, 127, 19));
        this.addSlot(new FurnaceOutputSlot(playerInventoryIn.player, furnaceInventoryIn, 4, 99, 51));
        this.addSlot(new FurnaceOutputSlot(playerInventoryIn.player, furnaceInventoryIn, 5, 127, 51));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }

        this.addProperties(delegate);
    }

	public GasCentrifugeScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf buf) {
        this(ModMiscs.CENTRIFUGE_SH, syncId, inv, new SimpleInventory(6), new ArrayPropertyDelegate(7));
        pos = buf.readBlockPos();
	}
    
    public GasCentrifugeScreenHandler(ScreenHandlerType<?> containerTypeIn, int id, PlayerInventory playerInventoryIn, Inventory furnaceInventoryIn, PropertyDelegate delegate, PacketByteBuf buf) {
        this(containerTypeIn,id,playerInventoryIn,furnaceInventoryIn,delegate);
        pos = buf.readBlockPos();
    }
    
    public void clear() {
        this.furnaceInventory.clear();
    }

    /**
     * Determines whether supplied player can use this container
     */
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.furnaceInventory.canPlayerUse(player);
    }
    
    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
    public ItemStack quickMove(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.insertItem(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (index >= 3 && index < 30) {
                    if (!this.insertItem(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.insertItem(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(playerIn, itemstack1);
        }

        return itemstack;
    }

    @Environment(EnvType.CLIENT)
    public int getCookProgressionScaled() {
        int i = this.delegate.get(2);
        int j = this.delegate.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @Environment(EnvType.CLIENT)
    public int getBurnLeftScaled()
    {
        int multiplier = Config.glowstone_multiplier >= 2 ? Config.glowstone_multiplier : 2;
        return MathHelper.clamp((int)((this.delegate.get(0) / (delegate.get(6)+0.01f))*18),0,80);
    }

    @Environment(EnvType.CLIENT)
    public float getPowerScaled()
    {
        return this.delegate.get(5) / 33f;
    }

    @Environment(EnvType.CLIENT)
    public boolean func_217061_l() {
        return this.delegate.get(0) > 0;
    }
    
    public BlockPos getPos() {
        return pos;
    }
}