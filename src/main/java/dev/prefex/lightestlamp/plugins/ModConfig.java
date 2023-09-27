package dev.prefex.lightestlamp.plugins;

import dev.prefex.lightestlamp.LightestLampsMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.Config.Gui.Background;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "lightest-lamps")
@Background(LightestLampsMod.MOD_ID+":textures/block/xenon_block.png")
public class ModConfig implements ConfigData
{
	@Comment("Glowstone Centrifuge: Glowstone fuel multiplier (Bigger multiplier less fuel used) Min: 2, Max: 20, IntValue")
	public int glowstone_multiplier = 4;
	@Comment("Enable Vanilla Mode (Disable Energy and Passive mode penalty)")
	public boolean vanilla_mode = false;
	@Comment("Power mode by default. Disable when you are using vanilla_mode.")
	public boolean power_as_default = true;
}