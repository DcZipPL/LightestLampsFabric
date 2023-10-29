package dev.prefex.lightestlamp.blocks;

import net.minecraft.block.Block;

import static net.minecraft.block.Blocks.REDSTONE_LAMP;

public class BlinkingLampBlock extends Block {
	public BlinkingLampBlock() {
		super(Settings.copy(REDSTONE_LAMP).luminance(__ -> 15));
	}
}
