package tk.dczippl.lightestlamp.init;

import net.minecraft.block.entity.BlockEntityType;
import tk.dczippl.lightestlamp.blocks.entity.GenerableLampBlockEntity;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeTile;

public class ModBlockEntities
{
	public static final BlockEntityType<GenerableLampBlockEntity> GENERAL_LAMP_BE = null;
	public static BlockEntityType<AntiLampBlockEntity> ANTILAMP_TE;

    public static BlockEntityType<LightAirBlockEntity> AIR_TE;

    public static BlockEntityType<OmegaChunkCleanerBlockEntity> OCC_TE;

    public static BlockEntityType<AlchemicalLampBlockEntity> ALCHEMICALLAMP_TE;
    public static BlockEntityType<GasCentrifugeTile> CENTRIFUGE_TE;

    public static void init() {
    }
}