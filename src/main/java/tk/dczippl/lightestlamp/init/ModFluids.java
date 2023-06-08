package tk.dczippl.lightestlamp.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tk.dczippl.lightestlamp.fluid.BromineFluid;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class ModFluids
{
    public static final Identifier FLUID_STILL = new Identifier(MOD_ID,"fluid/bromine_still");
    public static final Identifier FLUID_FLOWING = new Identifier(MOD_ID,"fluid/bromine_flow");

    public static Block BROMINE;
    public static FlowableFluid STILL_BROMINE;
    public static FlowableFluid FLOWING_BROMINE;
    public static Item BROMINE_BUCKET;
    
    public static void init() {
        STILL_BROMINE = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "bromine"), new BromineFluid.Still());
        FLOWING_BROMINE = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_bromine"), new BromineFluid.Flowing());
        BROMINE_BUCKET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bromine_bucket"),
                new BucketItem(STILL_BROMINE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        BROMINE = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "bromine_fluid"), new FluidBlock(STILL_BROMINE, FabricBlockSettings.copy(Blocks.WATER)){});
    }
}