package tk.dczippl.lightestlamp;

import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import tk.dczippl.lightestlamp.init.ModBlocks;
import tk.dczippl.lightestlamp.init.ModMiscs;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeScreen;

public class LightestLampsClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        HandledScreens.register(ModMiscs.CENTRIFUGE_SH, GasCentrifugeScreen::new);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), ModBlocks.JUNGLE_LANTERN, ModBlocks.GLOWING_GLASS_BLOCK);
    }
}
