package dev.prefex.lightestlamp.blocks;

import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DescRedstoneLampBlock extends RedstoneLampBlock {
	private String desc;
	public DescRedstoneLampBlock(Settings settings, String desc) {
		super(settings);
		this.desc = desc;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		if (Screen.hasShiftDown()){
			tooltip.add(Text.translatable(desc+"_full").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
		}else{
			tooltip.add(Text.translatable(desc).setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
			tooltip.add(Text.literal(""));
			tooltip.add(Text.translatable("tooltip.lightestlamp.hold0").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY))).append(Text.translatable("tooltip.lightestlamp.hold1").setStyle(Style.EMPTY.withColor(Formatting.AQUA))).append(Text.translatable("tooltip.lightestlamp.hold2").setStyle(Style.EMPTY.withColor(TextColor.fromFormatting(Formatting.GRAY)))));
		}
		super.appendTooltip(stack, world, tooltip, options);
	}
}
