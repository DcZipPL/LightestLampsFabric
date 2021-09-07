package tk.dczippl.lightestlamp.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import tk.dczippl.lightestlamp.fluid.BromineFluidBlock;

import java.awt.*;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class ModFluids
{
    public static final Identifier FLUID_STILL = new Identifier(MOD_ID,"fluid/bromine_still");
    public static final Identifier FLUID_FLOWING = new Identifier(MOD_ID,"fluid/bromine_flow");

    public static FlowingFluid BROMINE_FLUID = FLUIDS.register("bromine_fluid",
            new ForgeFlowingFluid.Source(ModFluids.test_fluid_properties)
    );
    public static FlowingFluid BROMINE_FLUID_FLOWING = FLUIDS.register("bromine_fluid_flowing",
            new ForgeFlowingFluid.Flowing(ModFluids.test_fluid_properties)
    );

    public static FlowingFluidBlock BROMINE_FLUID_BLOCK = ModBlocks.registerBlock("bromine_fluid_block",
            new BromineFluidBlock(BROMINE_FLUID, AbstractBlock.Settings.of(Material.WATER).dropsNothing()));
    public static Item BROMINE_FLUID_BUCKET = ModItems.register("bromine_fluid_bucket",
            new BucketItem(BROMINE_FLUID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(ItemGroup.MISC))
    );

    public static final ForgeFlowingFluid.Properties test_fluid_properties =
            new ForgeFlowingFluid.Properties(BROMINE_FLUID, BROMINE_FLUID_FLOWING, FluidAttributes.builder(FLUID_STILL, FLUID_FLOWING).color(new Color(102,16,0).getRGB()).density(31028).temperature(316).viscosity(600))
                    .bucket(BROMINE_FLUID_BUCKET).block(BROMINE_FLUID_BLOCK);

    public static void init() {
    }
}