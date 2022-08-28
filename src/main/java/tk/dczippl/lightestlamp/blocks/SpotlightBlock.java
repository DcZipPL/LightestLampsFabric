package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.Block;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import static net.minecraft.block.Blocks.REDSTONE_LAMP;

public class SpotlightBlock extends Block {
    public SpotlightBlock() {
        super(QuiltBlockSettings.copyOf(REDSTONE_LAMP).luminance(__ -> 8));
    }
}
