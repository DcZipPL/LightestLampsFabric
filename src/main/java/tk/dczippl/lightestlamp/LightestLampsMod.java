package tk.dczippl.lightestlamp;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.dczippl.lightestlamp.init.*;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeBlockEntity;
import tk.dczippl.lightestlamp.plugins.Config;
import tk.dczippl.lightestlamp.plugins.EnergyIntegration;
import tk.dczippl.lightestlamp.util.WorldGenerator;
import tk.dczippl.lightestlamp.util.Networking;

@SuppressWarnings("NullableProblems")
public class LightestLampsMod implements ModInitializer
{
    public static final String MOD_ID = "lightestlamp";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public LightestLampsMod(){}

    public static final TagKey<Item> GLOWSTONE_SMALL_DUSTS = TagKey.of(Registries.ITEM.getKey(), new Identifier("c", "glowstone_small_dusts"));
    
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        AutoConfig.register(Config.class, JanksonConfigSerializer::new);
        
        ServerPlayNetworking.registerGlobalReceiver(Networking.TOGGLEBUTTONS_CHANNEL, (client, handler, ctx, buf, responseSender) -> {
            // Read packet data on the event loop
            BlockPos target = buf.readBlockPos();
            int type = buf.readInt();
        
            client.execute(() -> {
                // Everything in this lambda is run on the thread
                BlockEntity be = ctx.player.getWorld().getBlockEntity(target);
                if (be instanceof GasCentrifugeBlockEntity gbe) {
                    if (type == 0) {
                        if (gbe.getRedstoneMode() >= 2)
                            gbe.setRedstoneMode(0);
                        else
                            gbe.setRedstoneMode(gbe.getRedstoneMode() + 1);
                    } else {
                        if (Config.vanilla_mode) gbe.setPowerMode(0);
                        if (gbe.getPowerMode()>=2)
                            gbe.setPowerMode(0);
                        else
                            gbe.setPowerMode(gbe.getPowerMode()+1);
                    }
                }
            });
        });
        
        //ModFluids.init();
        ModBlocks.init();
        ModItems.init();
        ModBlockEntities.init();
        WorldGenerator.register();
    
        EnergyIntegration.register();
    }
}