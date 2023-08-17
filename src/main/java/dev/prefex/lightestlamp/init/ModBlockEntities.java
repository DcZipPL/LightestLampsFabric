package dev.prefex.lightestlamp.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import dev.prefex.lightestlamp.blocks.GenerableLampBlock;
import dev.prefex.lightestlamp.blocks.entity.AlchemicalLampBlockEntity;
import dev.prefex.lightestlamp.blocks.entity.ChunkCleanerBlockEntity;
import dev.prefex.lightestlamp.blocks.entity.GenerableLampBlockEntity;
import dev.prefex.lightestlamp.blocks.entity.OmegaChunkCleanerBlockEntity;
import dev.prefex.lightestlamp.machine.gascentrifuge.GasCentrifugeBlockEntity;

import static dev.prefex.lightestlamp.LightestLampsMod.MOD_ID;

public class ModBlockEntities
{
	public static final BlockEntityType<GenerableLampBlockEntity> GENERAL_LAMP_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "general_lamp"), FabricBlockEntityTypeBuilder.create(
	        (pos,state)->new GenerableLampBlockEntity(((GenerableLampBlock)state.getBlock()).lampType,((GenerableLampBlock)state.getBlock()).waterResistant,pos,state),
            ModBlocks.ALPHA_LAMP,ModBlocks.BETA_LAMP,ModBlocks.GAMMA_LAMP,ModBlocks.DELTA_LAMP,ModBlocks.EPSILON_LAMP,ModBlocks.ZETA_LAMP,ModBlocks.ETA_LAMP,ModBlocks.OMEGA_LAMP,
            ModBlocks.CLEAR_SEA_LANTERN,ModBlocks.DEEP_SEA_LANTERN,ModBlocks.OCEAN_LANTERN,ModBlocks.DEEP_OCEAN_LANTERN,ModBlocks.ABYSSAL_LANTERN
    ).build());
    public static BlockEntityType<ChunkCleanerBlockEntity> CC_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "cc"),FabricBlockEntityTypeBuilder.create(ChunkCleanerBlockEntity::new,ModBlocks.CHUNK_CLEANER).build());
    public static BlockEntityType<OmegaChunkCleanerBlockEntity> OCC_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "occ"),FabricBlockEntityTypeBuilder.create(OmegaChunkCleanerBlockEntity::new,ModBlocks.OCC).build());

    public static BlockEntityType<AlchemicalLampBlockEntity> ALCHEMICALLAMP_TE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "alchemical_lamp"),FabricBlockEntityTypeBuilder.create(AlchemicalLampBlockEntity::new,ModBlocks.ALCHEMICAL_LAMP).build());
    public static BlockEntityType<GasCentrifugeBlockEntity> CENTRIFUGE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "glowstone_centrifuge"),FabricBlockEntityTypeBuilder.create(GasCentrifugeBlockEntity::new,ModBlocks.GLOWSTONE_CENTRIFUGE).build());

    public static void init() {
    }
}