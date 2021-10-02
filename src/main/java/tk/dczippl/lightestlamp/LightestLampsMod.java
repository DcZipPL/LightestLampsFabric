package tk.dczippl.lightestlamp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TimeCheckLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.dczippl.lightestlamp.init.*;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeBlockEntity;
import tk.dczippl.lightestlamp.util.WorldGenerator;
import tk.dczippl.lightestlamp.util.Networking;

@SuppressWarnings("NullableProblems")
public class LightestLampsMod implements ModInitializer
{
    public static final String MOD_ID = "lightestlamp";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public LightestLampsMod(){}

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (id.toString().contains(":entities")){
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1)).with(EmptyEntry.Serializer().weight(10))
                        .with(ItemEntry.builder(ModItems.MOON_SHARD).weight(2)).withCondition(TimeCheckLootCondition.create(BoundedIntUnaryOperator.create(12000,24000)).build());
    
                table.pool(poolBuilder);
            }
        });
        
        ServerPlayNetworking.registerGlobalReceiver(Networking.TOGGLEBUTTONS_CHANNEL, (client, handler, ctx, buf, responseSender) -> {
            // Read packet data on the event loop
            BlockPos target = buf.readBlockPos();
            int type = buf.readInt();
        
            client.execute(() -> {
                // Everything in this lambda is run on the thread
                BlockEntity be = ctx.player.getServerWorld().getBlockEntity(target);
                if (be instanceof GasCentrifugeBlockEntity gbe) {
                    if (type == 0) {
                        if (gbe.getRedstoneMode() >= 2)
                            gbe.setRedstoneMode(0);
                        else
                            gbe.setRedstoneMode(gbe.getRedstoneMode() + 1);
                    } else {
                        if (gbe.getLiquidMode()>=2)
                            gbe.setLiquidMode(0);
                        else
                            gbe.setLiquidMode(gbe.getLiquidMode()+1);
                    }
                }
            });
        });
        
        //ModFluids.init();
        ModBlocks.init();
        ModItems.init();
        ModBlockEntities.init();
        WorldGenerator.register();
    }
}