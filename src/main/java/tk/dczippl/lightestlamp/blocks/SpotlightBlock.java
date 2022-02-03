package tk.dczippl.lightestlamp.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import static net.minecraft.block.Blocks.REDSTONE_LAMP;

public class SpotlightBlock extends Block {
    public SpotlightBlock() {
        super(FabricBlockSettings.copyOf(REDSTONE_LAMP).luminance(__ -> 8));
    }
}
