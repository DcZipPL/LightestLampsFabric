package tk.dczippl.lightestlamp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;
import tk.dczippl.lightestlamp.init.ModBlocks;

public class LightestLampsClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenManager.registerFactory(ModContainers.GAS_CENTRIFUGE, GasCentrifugeScreen::new);
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlocks.JUNGLE_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlock(ModBlocks.GLOWING_GLASS_BLOCK, RenderLayer.getCutout());
    }
}
