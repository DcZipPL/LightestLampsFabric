package dev.prefex.lightestlamp.machine.gascentrifuge;

import dev.prefex.lightestlamp.LightestLampsMod;
import dev.prefex.lightestlamp.util.Networking;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class GasCentrifugeScreen extends HandledScreen<GasCentrifugeScreenHandler>
{
	public static final Identifier texture = new Identifier(LightestLampsMod.MOD_ID,"textures/gui/container/glowstone_centrifuge.png");
	private final GasCentrifugeScreenHandler sc;

	public GasCentrifugeScreen(GasCentrifugeScreenHandler screenContainer, PlayerInventory inv, Text titleIn)
	{
		super(screenContainer, inv, titleIn);
		sc = screenContainer;
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(context);
		super.render(context, mouseX, mouseY, partialTicks);
		this.drawMouseoverTooltip(context, mouseX, mouseY);
	}

	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		int tmp = 162;
		context.drawText(textRenderer, this.title, this.backgroundWidth / 2 - tmp / 2, -12, 4210752, false);
		context.drawText(textRenderer, this.playerInventoryTitle, 8, this.backgroundHeight - 96 + 2, 4210752, false);

		String redstone_tooltip = switch (sc.delegate.get(1)) {
			case 0 -> "tooltip.lightestlamp.machine.redstone_ignore";
			case 1 -> "tooltip.lightestlamp.machine.redstone_off";
			case 2 -> "tooltip.lightestlamp.machine.redstone_on";
			default -> "tooltip.lightestlamp.machine.redstone_na";
		};
		String power_tooltip = switch (sc.delegate.get(4)) {
			case 0 -> "tooltip.lightestlamp.machine.passive_mode";
			case 1 -> "tooltip.lightestlamp.machine.normal_mode";
			case 2 -> "tooltip.lightestlamp.machine.overclock_mode";
			default -> "tooltip.lightestlamp.machine.mode_na";
		};

		int marginHorizontal = (this.width - this.backgroundWidth) / 2;
		int marginVertical = (this.height - this.backgroundHeight) / 2;

		HoverChecker checker = new HoverChecker(marginHorizontal+9,marginHorizontal+20,marginVertical+20,marginVertical+9);
		if (checker.checkHover(mouseX,mouseY, true))
		{
			context.drawTooltip(textRenderer, Collections.singletonList(Text.translatable(redstone_tooltip)),mouseX-marginHorizontal+4,mouseY-marginVertical+4);
		}
		checker = new HoverChecker(marginHorizontal+25,marginHorizontal+36,marginVertical+20,marginVertical+9);
		if (checker.checkHover(mouseX,mouseY, true))
		{
			context.drawTooltip(textRenderer, formatUTooltip(power_tooltip),mouseX-marginHorizontal+4,mouseY-marginVertical+4);
		}
	}

	private List<Text> formatUTooltip(String utooltip) {
		return Arrays.stream(I18n.translate(utooltip).replace("Format error: ","*").split("¬")).map(
				s -> Text.literal(s).setStyle(Style.EMPTY.withColor(
						s.contains("§7") ? Formatting.GRAY : s.contains("§c") ? Formatting.RED : s.contains("§a") ? Formatting.GREEN : Formatting.WHITE
				))
		).collect(Collectors.toUnmodifiableList());
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

		int marginHorizontal = (this.width - this.backgroundWidth) / 2;
		int marginVertical = (this.height - this.backgroundHeight) / 2;

		context.drawTexture(texture, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight, 256,256);

		int i = x;
		int j = y;

		if (this.sc.func_217061_l()) { // GasCentrifugeScreenHandler
			int k = this.sc.getBurnLeftScaled(); // GasCentrifugeScreenHandler
			//Z Y T-Z T-Y W H
			context.drawTexture(texture,i + 41 + 17 - k, j + 54, 194 - k, 100, k + 1,  5);
		}
		if (sc.delegate.get(4)!=0){
			int m = (int) this.sc.getPowerScaled(); // GasCentrifugeScreenHandler
			//Z Y T-Z T-Y W H
			context.drawTexture(texture,i + 154, j + 19 + 50 - m + 1 - 3, 177, 99 - m - 1, 13, m + 1);
		} else {
			context.drawTexture(texture,i + 153, j + 19 + 1 - 3, 218, 99 - 1 - 50, 14, 51);
		}

		switch (sc.delegate.get(1))
		{
			case 0:
				context.drawTexture(texture,marginHorizontal+9, marginVertical+9, 176, 128, 12, 12);
				break;
			case 1:
				context.drawTexture(texture,marginHorizontal+9, marginVertical+9, 176, 141, 12, 12);
				break;
			case 2:
				context.drawTexture(texture,marginHorizontal+9, marginVertical+9, 176, 154, 12, 12);
				break;
		}
		switch (sc.delegate.get(4))
		{
			case 0:
				context.drawTexture(texture,marginHorizontal+25, marginVertical+9, 192, 128, 12, 12);
				break;
			case 1:
				context.drawTexture(texture,marginHorizontal+25, marginVertical+9, 192, 141, 12, 12);
				break;
			case 2:
				context.drawTexture(texture,marginHorizontal+25, marginVertical+9, 192, 154, 12, 12);
				break;
		}

		int l = this.sc.getCookProgressionScaled(); // GasCentrifugeScreenHandler
		context.drawTexture(texture,i + 63, j + 34, 176, 14, l + 1, 16);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int id) {
		int marginHorizontal = (this.width - this.backgroundWidth) / 2;
		int marginVertical = (this.height - this.backgroundHeight) / 2;

		if (mouseX - marginHorizontal >= 9 && mouseX - marginHorizontal <= 20)
		{
			if (mouseY - marginVertical >= 9 && mouseY - marginVertical <= 20)
			{
				if (sc.delegate.get(1) == 2)
				{
					sc.delegate.set(1, 0);
				} else {
					sc.delegate.set(1, sc.delegate.get(1)+1);
				}
				Networking.sendToggledButtonMessage(0,sc.getPos());
			}
		}
		if (mouseX - marginHorizontal >= 25 && mouseX - marginHorizontal <= 36)
		{
			if (mouseY - marginVertical >= 9 && mouseY - marginVertical <= 20)
			{
				if (sc.delegate.get(4) == 2)
				{
					sc.delegate.set(4, 0);
				} else {
					sc.delegate.set(4, sc.delegate.get(4)+1);
				}
				Networking.sendToggledButtonMessage(1,sc.getPos());
			}
		}

		return super.mouseClicked(mouseX, mouseY, id);
	}

	@SuppressWarnings("InnerClassMayBeStatic")
	public class HoverChecker{
		double buttonX0,buttonX1,buttonY0,buttonY1;

		public HoverChecker(double buttonX0, double buttonX1,double buttonY0, double buttonY1){
			this.buttonX0 = buttonX0;
			this.buttonX1 = buttonX1;
			this.buttonY0 = buttonY0;
			this.buttonY1 = buttonY1;
		}

		public boolean checkHover(double mouseX, double mouseY,boolean simulate){
			return mouseX >= buttonX0 && mouseY >= buttonY1 && mouseX <= buttonX1 && mouseY <= buttonY0;
		}
	}
}