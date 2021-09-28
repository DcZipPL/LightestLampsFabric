package tk.dczippl.lightestlamp.machine.gascentrifuge;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tk.dczippl.lightestlamp.LightestLampsMod;
import tk.dczippl.lightestlamp.util.network.Networking;
import tk.dczippl.lightestlamp.util.network.PacketButtonModeControl;
import tk.dczippl.lightestlamp.util.network.PacketButtonRedstone;

import java.util.Collections;

@SuppressWarnings("NullableProblems")
@Environment(EnvType.CLIENT)
public class GasCentrifugeScreen extends HandledScreen<GasCentrifugeScreenHandler>
{
    public static final Identifier texture = new Identifier(LightestLampsMod.MOD_ID,"textures/gui/container/gas_centrifuge.png");
    private boolean field_214090_m;
    private GasCentrifugeScreenHandler sc;

    public GasCentrifugeScreen(GasCentrifugeScreenHandler screenContainer, PlayerInventory inv, Text titleIn)
    {
        super(screenContainer, inv, titleIn);
        sc = screenContainer;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(matrixStack, mouseX, mouseY);
    }
    
    @Override
    protected void drawForeground(MatrixStack matrixStack, int mouseX, int mouseY) {
        //super.drawGuiContainerForegroundLayer(matrixStack, x, y);
        int tmp = 75;
        this.textRenderer.draw(matrixStack, this.title, (float)(this.x / 2 - tmp / 2), 6.0F, 4210752);
        this.textRenderer.draw(matrixStack, this.playerInventoryTitle, 8.0F, (float)(this.y - 96 + 2), 4210752);

        String redstone_tooltip = "Mode: Ignore Redstone";
        switch (sc.delegate.get(1))
        {
            case 0:
                redstone_tooltip = "Mode: Ignore Redstone";
                break;
            case 1:
                redstone_tooltip = "Mode: Redstone off";
                break;
            case 2:
                redstone_tooltip = "Mode: Redstone on";
                break;
        }
        String fluid_tooltip = "Mode: Neutralize Waste";
        switch (sc.delegate.get(4))
        {
            case 0:
                fluid_tooltip = "Mode: Neutralize Waste";
                break;
            case 1:
                fluid_tooltip = "Mode: Store";
                break;
            case 2:
                fluid_tooltip = "Mode: Dump (Starts dumping after 3 sec)";
                break;
        }

        int marginHorizontal = (width - x) / 2;
        int marginVertical = (height - y) / 2;

        //(marginHorizontal+9 <V>,marginHorizontal+20,marginVertical+9 <V>,marginVertical+20, 0 <V>)
        HoverChecker checker = new HoverChecker(marginHorizontal+9,marginHorizontal+20,marginVertical+20,marginVertical+9,0);
        if (checker.checkHover(x,y, true))
        {
            renderTooltip(matrixStack, Collections.singletonList(new LiteralText(redstone_tooltip)),x-marginHorizontal+4,y-marginVertical+4);
        }
        checker = new HoverChecker(marginHorizontal+25,marginHorizontal+36,marginVertical+20,marginVertical+9,0);
        if (checker.checkHover(x,y, true))
        {
            renderTooltip(matrixStack, Collections.singletonList(new LiteralText(fluid_tooltip)),x-marginHorizontal+4,y-marginVertical+4);
        }
        //renderHoveredToolTip(mouseX-marginHorizontal+4,mouseY-marginVertical+4);
    }

    @Override
    protected void drawBackground(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    
        MinecraftClient.getInstance().getTextureManager().bindTexture(this.texture);

        int marginHorizontal = (this.width - this.x) / 2;
        int marginVertical = (this.height - this.y) / 2;

        drawTexture(matrixStack, x, y, 0, 0, this.x, this.y, 256,256);

        int i = 0;
        int j = 0;

        if (((GasCentrifugeScreenHandler)this.sc).func_217061_l()) {
            int k = ((GasCentrifugeScreenHandler)this.sc).getBurnLeftScaled()*2;
            if (k >= 300)
                k = 299;
            //Z Y T-Z T-Y W H
            this.drawTexture(matrixStack,i + 151, j + 65 + 12 - k, 203, 118 - k, 9, k + 1);
        }
        int m = ((GasCentrifugeScreenHandler)this.sc).getLiquidScaled();
        //Z Y T-Z T-Y W H
        this.drawTexture(matrixStack,i + 160, j + 65 + 13 - m, 212, 120 - m, 9, m + 1);
        
        switch (sc.delegate.get(1))
        {
            case 0:
                this.drawTexture(matrixStack,marginHorizontal+9, marginVertical+9, 176, 128, 12, 12);
                break;
            case 1:
                this.drawTexture(matrixStack,marginHorizontal+9, marginVertical+9, 176, 141, 12, 12);
                break;
            case 2:
                this.drawTexture(matrixStack,marginHorizontal+9, marginVertical+9, 176, 154, 12, 12);
                break;
        }
        switch (sc.delegate.get(4))
        {
            case 0:
                this.drawTexture(matrixStack,marginHorizontal+25, marginVertical+9, 192, 128, 12, 12);
                break;
            case 1:
                this.drawTexture(matrixStack,marginHorizontal+25, marginVertical+9, 192, 141, 12, 12);
                break;
            case 2:
                this.drawTexture(matrixStack,marginHorizontal+25, marginVertical+9, 192, 154, 12, 12);
                break;
        }

        int l = ((GasCentrifugeScreenHandler)this.sc).getCookProgressionScaled();
        this.drawTexture(matrixStack,i + 63, j + 34, 176, 14, l + 1, 16);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int id) {
        int marginHorizontal = (width - x) / 2;
        int marginVertical = (height - y) / 2;
        //Main.LOGGER.info("Clicked at: " + mouseX + ":" + mouseY + ":" + id + ", With margin: " + (mouseX - marginHorizontal) + ":" + (mouseY - marginVertical) + ":" + id);

        if (mouseX - marginHorizontal >= 9 && mouseX - marginHorizontal <= 20)
        {
            if (mouseY - marginVertical >= 9 && mouseY - marginVertical <= 20)
            {
                //Main.LOGGER.info("redstone button clicked!");
                if (sc.delegate.get(1) == 2)
                {
                    sc.delegate.set(1, 0);
                } else {
                    sc.delegate.set(1, sc.delegate.get(1)+1);
                }
                //Networking.INSTANCE.sendToServer(new PacketButtonRedstone(sc.getBlockPos(),0));
            }
        }
        if (mouseX - marginHorizontal >= 25 && mouseX - marginHorizontal <= 36)
        {
            if (mouseY - marginVertical >= 9 && mouseY - marginVertical <= 20)
            {
                //Main.LOGGER.info("redstone button clicked!");
                if (sc.delegate.get(4) == 2)
                {
                    sc.delegate.set(4, 0);
                } else {
                    sc.delegate.set(4, sc.delegate.get(4)+1);
                }
                //Networking.INSTANCE.sendToServer(new PacketButtonModeControl(sc.getBlockPos(),0));
            }
        }

        return super.mouseClicked(mouseX, mouseY, id); //Forge, Call parent to release buttons
    }

    public class HoverChecker{
        double buttonX0,buttonX1,buttonY0,buttonY1;

        public HoverChecker(double buttonX0, double buttonX1,double buttonY0, double buttonY1,int id){
            this.buttonX0 = buttonX0;
            this.buttonX1 = buttonX1;
            this.buttonY0 = buttonY0;
            this.buttonY1 = buttonY1;
        }

        public boolean checkHover(double mouseX, double mouseY,boolean simulate){
            return mouseX >= buttonX0 && mouseY >= buttonY1 && mouseX <= buttonX1 && mouseY <= buttonY0;
        }
        /*public boolean checkHoverLegacy(double mouseX, double mouseY, double buttonX0, double buttonX1,double buttonY0, double buttonY1){
            return mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_;
        }*/
    }
}