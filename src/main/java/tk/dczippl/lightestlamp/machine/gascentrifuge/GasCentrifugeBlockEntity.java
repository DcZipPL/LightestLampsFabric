package tk.dczippl.lightestlamp.machine.gascentrifuge;

import com.google.common.collect.Maps;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.impl.transfer.transaction.TransactionManagerImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import tk.dczippl.lightestlamp.init.ModBlockEntities;
import tk.dczippl.lightestlamp.init.ModMiscs;
import tk.dczippl.lightestlamp.items.FilterItem;

import java.util.Map;
import java.util.Random;

import static tk.dczippl.lightestlamp.plugins.Config.power_as_default;
import static tk.dczippl.lightestlamp.plugins.Config.vanilla_mode;

@SuppressWarnings("UnstableApiUsage")
public class GasCentrifugeBlockEntity extends LockableContainerBlockEntity implements ExtendedScreenHandlerFactory, SidedInventory
{
    public GasCentrifugeBlockEntity(BlockPos blockPos, BlockState state) {
        super(ModBlockEntities.CENTRIFUGE_BE, blockPos, state);
        items = DefaultedList.ofSize(6, ItemStack.EMPTY);
        if (power_as_default) powerMode = 1;
    }
    
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(1600, 128, 0);
    
    @Override
    protected Text getContainerName()
    {
        return Text.translatable("container.gascentrifuge");
    }

    protected ScreenHandler createScreenHandler(int id, PlayerInventory player)
    {
        return new GasCentrifugeScreenHandler(ModMiscs.CENTRIFUGE_SH,id, player, this, this.dataDelegate);
    }

    private static final int[] SLOTS_UP = new int[]{0,1};
    private static final int[] SLOTS_DOWN = new int[]{2, 3, 4, 5};
    //private static final int[] SLOTS_HORIZONTAL = new int[]{1};
    protected DefaultedList<ItemStack> items;
    private int burnTimeTotal;
    private int burnTime;
    private float cookTime;
    private int cookTimeTotal;
    private int redstoneMode;
    private int powerMode;
    public final PropertyDelegate dataDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> GasCentrifugeBlockEntity.this.burnTime;
                case 1 -> GasCentrifugeBlockEntity.this.redstoneMode;
                case 2 -> (int) GasCentrifugeBlockEntity.this.cookTime;
                case 3 -> GasCentrifugeBlockEntity.this.cookTimeTotal;
                case 4 -> GasCentrifugeBlockEntity.this.powerMode;
                case 5 -> (int) GasCentrifugeBlockEntity.this.energyStorage.amount;
                case 6 -> GasCentrifugeBlockEntity.this.burnTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> GasCentrifugeBlockEntity.this.burnTime = value;
                case 1 -> GasCentrifugeBlockEntity.this.redstoneMode = value;
                case 2 -> GasCentrifugeBlockEntity.this.cookTime = value;
                case 3 -> GasCentrifugeBlockEntity.this.cookTimeTotal = value;
                case 4 -> GasCentrifugeBlockEntity.this.powerMode = value;
                case 5 -> GasCentrifugeBlockEntity.this.energyStorage.amount = value;
                case 6 -> GasCentrifugeBlockEntity.this.burnTimeTotal = value;
            }

        }

        public int size() {
            return 7;
        }
    };

    public void setRedstoneMode(int redstoneMode)
    {
        dataDelegate.set(1,redstoneMode);
    }

    public int getRedstoneMode()
    {
        return dataDelegate.get(1);
    }

    public void setPowerMode(int mode)
    {
        dataDelegate.set(4,mode);
    }

    public int getPowerMode()
    {
        return dataDelegate.get(4);
    }

    public void startTicksBeforeDumping()
    {
        dataDelegate.set(6,60);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.items = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.items);
        this.burnTime = nbt.getInt("BurnTime");
        this.cookTime = nbt.getFloat("CookTime");
        this.cookTimeTotal = nbt.getInt("CookTimeTotal");
        this.redstoneMode = nbt.getInt("RedstoneMode");
        this.powerMode = nbt.getInt("LiquidMode");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("BurnTime", this.burnTime);
        nbt.putFloat("CookTime", this.cookTime);
        nbt.putInt("CookTimeTotal", this.cookTimeTotal);
        nbt.putInt("RedstoneMode", this.redstoneMode);
        nbt.putInt("LiquidMode", this.powerMode);
        Inventories.writeNbt(nbt, this.items);
    }
    
    public static void tick(World world, BlockPos pos, BlockState state, GasCentrifugeBlockEntity be)
    {
        boolean flag = be.isBurning();
        boolean flag1 = false;
        if (be.isBurning() && (be.energyStorage.amount > (14*1.6f) || be.powerMode == 0)) {
            --be.burnTime;
        }
        
        if (!be.world.isClient) {
            ItemStack itemstack = be.items.get(1);
            if (be.isBurning() || !itemstack.isEmpty() && !be.items.get(0).isEmpty()) {
                if (!be.isBurning() && be.canSmelt()) {
                    be.burnTime = be.getBurnTime(itemstack);
                    be.burnTimeTotal = be.getBurnTime(itemstack);
                    if (be.isBurning()) {
                        flag1 = true;
                        if (itemstack.getItem().hasRecipeRemainder())
                            be.items.set(1, new ItemStack(itemstack.getItem().getRecipeRemainder()));
                        else
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.decrement(1);
                            if (itemstack.isEmpty()) {
                                be.items.set(1, new ItemStack(itemstack.getItem().getRecipeRemainder()));
                            }
                        }
                    }
                }
                
                if (be.isBurning() && be.canSmelt() && (be.energyStorage.amount > (14*1.6f) || be.powerMode == 0)) {
                    if (be.powerMode != 0) be.energyStorage.amount -= (be.powerMode == 2 ? (int)(14*1.6f) : 14);
                    be.cookTime += be.getEfficiency();
                    if ((int)be.cookTime >= be.cookTimeTotal) {
                        be.cookTime = 0;
                        be.cookTimeTotal = be.getCookTimeTotal();
                        be.placeItemsInRightSlot();
                        flag1 = true;
                    }
                } else {
                    be.cookTime = 0;
                }
            } else if (!be.isBurning() && be.cookTime > 0) {
                be.cookTime = MathHelper.clamp(be.cookTime - 2, 0, be.cookTimeTotal);
            }
            
            if (flag != be.isBurning()) {
                flag1 = true;
                //be.world.setBlockState(be.pos, be.world.getBlockState(be.pos).with(AbstractFurnaceBlock.LIT, Boolean.valueOf(be.isBurning())), 3);
            }
        }
        
        if (flag1) {
            be.markDirty();
        }
    }
    
    private float getEfficiency() {
        return !vanilla_mode ? (powerMode == 0 ? 0.2f : powerMode == 1 ? 1f : 2f) : 1f;
    }
    
    private int getCookTimeTotal()
    {
        return 100;
    }

    protected boolean canSmelt() {
        if (!this.items.get(0).isEmpty()) {
            ItemStack[] itemstacks = GasCentrifugeRecipe.getRecipeOutputs(items.get(0));
            if (itemstacks[0].isEmpty()&&itemstacks[1].isEmpty()&&itemstacks[2].isEmpty()&&itemstacks[3].isEmpty())
            {
                return false;
            } else if (redstoneMode==1&&world.getReceivedRedstonePower(pos)>0||redstoneMode==2&&world.getReceivedRedstonePower(pos)<1)
            {
                return false;
            } else {
                ItemStack[] itemstacks1 = new ItemStack[] {this.items.get(2),this.items.get(3),this.items.get(4),this.items.get(5)};
                boolean[] output0 = new boolean[] {true,true,true,true};
                for (int i = 0; i < itemstacks.length-1; i++)
                {
                    ItemStack itemstack = itemstacks[i];
                    ItemStack itemstack1 = itemstacks1[i];
                    if (itemstack1.isEmpty()) {
                        output0[i] =  true;
                    } else if (!ItemStack.canCombine(itemstack1, itemstack)) {
                        output0[i] =  false;
                    } else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxCountPerStack() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxCount()) {
                        // Forge fix: make furnace respect stack sizes in furnace recipes
                        output0[i] =  true;
                    } else {
                        output0[i] =  itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxCount();
                        // Forge fix: make furnace respect stack sizes in furnace recipes
                    }
                }
                return output0[0]&&output0[1]&&output0[2]&&output0[3];
            }
        } else {
            return false;
        }
    }

    private void placeItemsInRightSlot(/*Here was recipe*/)
    {
        if (this.canSmelt()) {
            ItemStack itemstack = this.items.get(0);
            ItemStack[] itemstacks = GasCentrifugeRecipe.getRecipeOutputs(items.get(0));
            ItemStack itemstack2 = this.items.get(2);
            ItemStack itemstack3 = this.items.get(3);
            ItemStack itemstack4 = this.items.get(4);
            ItemStack itemstack5 = this.items.get(5);
            if (itemstack2.isEmpty()) {
                this.items.set(2, itemstacks[0].copy());
            } else if (itemstack2.getItem() == itemstacks[0].getItem()) {
                itemstack2.increment(itemstacks[0].getCount());
            }
            if (itemstack3.isEmpty()) {
                this.items.set(3, itemstacks[1].copy());
            } else if (itemstack3.getItem() == itemstacks[1].getItem()) {
                itemstack3.increment(itemstacks[1].getCount());
            }
            if (itemstack4.isEmpty()) {
                this.items.set(4, itemstacks[2].copy());
            } else if (itemstack4.getItem() == itemstacks[2].getItem()) {
                itemstack4.increment(itemstacks[2].getCount());
            }
            if (itemstack5.isEmpty()) {
                this.items.set(5, itemstacks[3].copy());
            } else if (itemstack5.getItem() == itemstacks[3].getItem()) {
                itemstack5.increment(itemstacks[3].getCount());
            }

            int unbreaking_lvl = 0;
            for (NbtElement enchantment : itemstack.getEnchantments())
            {
                if (((NbtCompound) enchantment).getString("id").equals("minecraft:unbreaking"))
                unbreaking_lvl = ((NbtCompound)enchantment).getShort("lvl");
            }

            itemstack.setDamage(itemstack.getDamage()+
                    (
                            unbreaking_lvl==0
                                    ?
                                    1
                                    :
                                    (new Random().nextInt(unbreaking_lvl+1)==1?1:0)
                    ));
            //unbreaking_lvl==0 ? 1 : (new Random().nextInt(unbreaking_lvl+1)==1?1:0)

            if (itemstack.getDamage() >= itemstack.getMaxDamage())
                this.items.set(0, ItemStack.EMPTY);
        }
    }

    protected int getBurnTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            Item item = stack.getItem();
            return GasCentrifugeRecipe.getBurnTimes().getOrDefault(item, 0);
        }
    }

    public boolean isFuel(ItemStack stack) {
        return getBurnTime(stack) > 0;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return SLOTS_UP;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    @Override
    public boolean canInsert(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.isValid(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction)
    {
        return true;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    @Override
    public ItemStack getStack(int slot) {
        return this.items.get(slot);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.items, slot, amount);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.items, slot);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemstack = this.items.get(slot);
        boolean flag = !stack.isEmpty() && ItemStack.canCombine(stack, itemstack);
        this.items.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        if (slot == 0 && !flag) {
            this.cookTimeTotal = getCookTimeTotal();
            this.cookTime = 0;
            this.markDirty();
        }
    }
    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public boolean isFilter(ItemStack stack){
        return stack.getItem() instanceof FilterItem;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 0) {
            return isFilter(stack);
        } else if (slot == 1) {
            return isFuel(stack);
        } else {
            return false;
        }
    }
    
    @Override
    public void clear() {
        this.items.clear();
    }
    
    /**
     * Writes additional server -&gt; client screen opening data to the buffer.
     *
     * @param player the player that is opening the screen
     * @param buf    the packet buffer
     */
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }
    
    /*net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (!this.removed && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(capability, LazyOptional.of(() -> new FluidHandlerWrapper(this, facing)));
        }
        return super.getCapability(capability, facing);
    }*/
}