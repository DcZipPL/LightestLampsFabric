package tk.dczippl.lightestlamp.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import tk.dczippl.lightestlamp.blocks.GenerableLampBlock;
import tk.dczippl.lightestlamp.blocks.entity.AlchemicalLampBlockEntity;
import tk.dczippl.lightestlamp.blocks.entity.ChunkCleanerBlockEntity;
import tk.dczippl.lightestlamp.blocks.entity.GenerableLampBlockEntity;
import tk.dczippl.lightestlamp.blocks.entity.OmegaChunkCleanerBlockEntity;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeBlockEntity;

public class ModBlockEntities
{
	public static final BlockEntityType<GenerableLampBlockEntity> GENERAL_LAMP_BE = FabricBlockEntityTypeBuilder.create(
	        (pos,state)->new GenerableLampBlockEntity(((GenerableLampBlock)state.getBlock()).lampType,((GenerableLampBlock)state.getBlock()).waterResistant,pos,state),
            ModBlocks.ALPHA_LAMP,ModBlocks.BETA_LAMP,ModBlocks.GAMMA_LAMP,ModBlocks.DELTA_LAMP,ModBlocks.EPSILON_LAMP,ModBlocks.ZETA_LAMP,ModBlocks.ETA_LAMP,ModBlocks.OMEGA_LAMP,
            ModBlocks.CLEAR_SEA_LANTERN,ModBlocks.DEEP_SEA_LANTERN,ModBlocks.OCEAN_LANTERN,ModBlocks.DEEP_OCEAN_LANTERN,ModBlocks.ABYSSAL_LANTERN
    ).build();
    public static BlockEntityType<ChunkCleanerBlockEntity> CC_BE = FabricBlockEntityTypeBuilder.create(ChunkCleanerBlockEntity::new,ModBlocks.CHUNK_CLEANER).build();
    public static BlockEntityType<OmegaChunkCleanerBlockEntity> OCC_BE = FabricBlockEntityTypeBuilder.create(OmegaChunkCleanerBlockEntity::new,ModBlocks.OCC).build();

    public static BlockEntityType<AlchemicalLampBlockEntity> ALCHEMICALLAMP_TE = FabricBlockEntityTypeBuilder.create(AlchemicalLampBlockEntity::new,ModBlocks.ALCHEMICAL_LAMP).build();
    public static BlockEntityType<GasCentrifugeBlockEntity> CENTRIFUGE_BE = FabricBlockEntityTypeBuilder.create(GasCentrifugeBlockEntity::new,ModBlocks.GLOWSTONE_CENTRIFUGE).build();

    public static void init() {
    }
}