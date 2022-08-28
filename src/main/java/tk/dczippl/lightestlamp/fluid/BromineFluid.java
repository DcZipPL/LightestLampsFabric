package tk.dczippl.lightestlamp.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import tk.dczippl.lightestlamp.init.ModFluids;

public abstract class BromineFluid extends AbstractFluid {
	@Override
	public Fluid getStill() {
		return ModFluids.STILL_BROMINE;
	}

	@Override
	public Fluid getFlowing() {
		return ModFluids.FLOWING_BROMINE;
	}

	@Override
	public Item getBucketItem() {
		return ModFluids.BROMINE_BUCKET;
	}

	@Override
	protected BlockState toBlockState(FluidState fluidState) {
		// getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
		return ModFluids.BROMINE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
	}

	public static class Flowing extends BromineFluid {
		@Override
		public boolean isSource(FluidState state) {
			return false;
		}

		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
	}

	public static class Still extends BromineFluid {
		@Override
		public boolean isSource(FluidState state) {
			return true;
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
	}
}