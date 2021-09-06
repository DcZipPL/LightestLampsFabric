package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class GlowingBlock extends Block {
	public GlowingBlock(AbstractBlock.Settings properties, int lightValue) {
		super(properties.luminance(ignore -> lightValue));
	}
}
