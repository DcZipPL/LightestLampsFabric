package dev.prefex.lightestlamp.machine.gascentrifuge;

import dev.prefex.lightestlamp.init.ModMiscs;
import dev.prefex.lightestlamp.plugins.ModConfig;
import dev.prefex.lightestlamp.util.slot.FilterSlot;
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

import static dev.prefex.lightestlamp.LightestLampsMod.CONFIG;

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
        this.addSlot(new FilterSlot(furnaceInventoryIn, 0, 16, 35));
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

    protected boolean isFilter(ItemStack itemStack) {
        return true;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
    public ItemStack quickMove(PlayerEntity playerIn, int slotIndex) {
        ItemStack copiedStack = ItemStack.EMPTY;
        Slot sourceSlot = this.slots.get(slotIndex);
        if (sourceSlot != null && sourceSlot.hasStack()) {
            ItemStack sourceStack = sourceSlot.getStack();
            copiedStack = sourceStack.copy();
            if (slotIndex >= 2 && slotIndex < 6) {
                if (!this.insertItem(sourceStack, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }

                sourceSlot.onQuickTransfer(sourceStack, copiedStack);
            } else if (slotIndex != 1 && slotIndex != 0) {
                if (this.isFilter(sourceStack)) {
                    if (!this.insertItem(sourceStack, 0, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 6 && slotIndex < 33) {
                    if (!this.insertItem(sourceStack, 33, 42, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 33 && slotIndex < 42 && !this.insertItem(sourceStack, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(sourceStack, 6, 42, false)) {
                return ItemStack.EMPTY;
            }

            if (sourceStack.isEmpty()) {
                sourceSlot.setStack(ItemStack.EMPTY);
            } else {
                sourceSlot.markDirty();
            }

            if (sourceStack.getCount() == copiedStack.getCount()) {
                return ItemStack.EMPTY;
            }

            sourceSlot.onTakeItem(player, sourceStack);
        }

        return copiedStack;
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
        int multiplier = CONFIG.glowstone_multiplier >= 2 ? CONFIG.glowstone_multiplier : 2;
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