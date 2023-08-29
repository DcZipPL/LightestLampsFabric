package dev.prefex.lightestlamp.plugins;

import dev.prefex.lightestlamp.LightestLampsMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config.Gui.Background;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@me.shedaniel.autoconfig.annotation.Config(name = "lightest-lamps")
@Background(LightestLampsMod.MOD_ID+":textures/block/xenon_block.png")
public class Config implements ConfigData
{
    @Comment("Glowstone Centrifuge: Glowstone fuel multiplier (Bigger multiplier less fuel used) Min: 2, Max: 20, IntValue")
    public static int glowstone_multiplier = 4;
    @Comment("Generation of Monazite Ore in The Nether")
    public static boolean monazite_gen = true;
    @Comment("Enable Vanilla Mode (Disable Energy and Passive mode penalty)")
    public static boolean vanilla_mode = false;
    @Comment("Power mode by default. Disable when you are using vanilla_mode.")
    public static boolean power_as_default = true;
}