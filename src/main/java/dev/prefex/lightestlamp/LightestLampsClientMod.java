package dev.prefex.lightestlamp;

import dev.prefex.lightestlamp.init.ModBlocks;
import dev.prefex.lightestlamp.init.ModMiscs;
import dev.prefex.lightestlamp.machine.gascentrifuge.GasCentrifugeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class LightestLampsClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(ModMiscs.CENTRIFUGE_SH, GasCentrifugeScreen::new);
		BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlocks.LAMP_FRUIT, RenderLayer.getCutout());
		BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlocks.GLOWING_GLASS_BLOCK, RenderLayer.getCutout());
	}
}
