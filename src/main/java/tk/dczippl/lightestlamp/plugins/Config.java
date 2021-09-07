package tk.dczippl.lightestlamp.plugins;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import tk.dczippl.lightestlamp.LightestLampsMod;

@me.shedaniel.autoconfig.annotation.Config(name = "lightest-lamps")
@me.shedaniel.autoconfig.annotation.Config.Gui.Background(LightestLampsMod.MOD_ID+":textures/block/xenon_block.png")
public class Config implements ConfigData
{
    @Comment("Glowstone Centrifuge: Glowstone fuel multiplier (Bigger multiplier less fuel used) Min: 2, Max: 20, IntValue")
    public int glowstone_multiplier = 4;
    @Comment("Generation of Monazite Ore in nether")
    public boolean monazite_gen = true;
}