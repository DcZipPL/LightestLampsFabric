package tk.dczippl.lightestlamp.blocks;

import net.minecraft.block.GlassBlock;

import static net.minecraft.block.Blocks.GLASS;

public class GlowingGlassBlock extends GlassBlock
{
    public GlowingGlassBlock()
    {
        super(Settings.copy(GLASS).luminance(__ -> 15));
    }
}