package tk.dczippl.lightestlamp.plugins;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import team.reborn.energy.api.EnergyStorage;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import team.reborn.energy.api.EnergyStorage;
import tk.dczippl.lightestlamp.init.ModBlockEntities;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeBlockEntity;

public class EnergyIntegration {
	private static boolean registered = false;
	private static Logger LOGGER = LogManager.getLogger();
	
	public static void register() {
		if (FabricLoader.getInstance().isModLoaded("team_reborn_energy")) {
			LOGGER.debug("Trying to register Reborn Energy Handler.");
			try {
				if (RebornEnergyIntegration.register()) {
					registered = true;
				}
			} catch (Throwable error) {
				var rebornMods = QuiltLoader.getAllMods().stream()
						.map(ModContainer::metadata).filter(s -> s.id().contains("reborn"))
						.map(m -> String.format("%s@%s", m.id(), m.version().raw())).toList();
				LOGGER.error("Lightest Lamps caught energy integration error. RebornMods: " + rebornMods, error);
			}
		}
	}
	
	public static boolean hasAnyEnergyModule() {
		return registered;
	}
}

class RebornEnergyIntegration {
	static boolean register() {
		EnergyStorage.SIDED.registerForBlockEntities((blockEntity, context) -> {
					if (blockEntity instanceof GasCentrifugeBlockEntity be) return be.energyStorage;
					else return null;
				},
				ModBlockEntities.CENTRIFUGE_BE);
		return true;
	}
}