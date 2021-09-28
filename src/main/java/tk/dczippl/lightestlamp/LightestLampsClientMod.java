package tk.dczippl.lightestlamp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;
import tk.dczippl.lightestlamp.init.ModBlocks;
import tk.dczippl.lightestlamp.init.ModMiscs;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeScreen;

public class LightestLampsClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ModMiscs.CENTRIFUGE_SH, GasCentrifugeScreen::new);
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlocks.JUNGLE_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlocks.GLOWING_GLASS_BLOCK, RenderLayer.getCutout());
    }
}
