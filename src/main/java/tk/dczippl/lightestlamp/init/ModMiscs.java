package tk.dczippl.lightestlamp.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeScreenHandler;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class ModMiscs {

	public static final ItemGroup LAMPS_TAB = FabricItemGroup.builder()
			.displayName(Text.translatable("lightestlamp.lamps"))
			.icon(()->new ItemStack(ModBlocks.OMEGA_LAMP,16))
			.entries((context, entries) -> {
				entries.addAll(ModItems.ITEMS);
				entries.addAll(ModBlocks.ITEMS);
			})
			.build();

	public static final ExtendedScreenHandlerType<GasCentrifugeScreenHandler> CENTRIFUGE_SH = new ExtendedScreenHandlerType<>(GasCentrifugeScreenHandler::new);

	public static void init() {
		Registry.register(Registries.SCREEN_HANDLER, new Identifier(MOD_ID, "gas_centrifuge"), CENTRIFUGE_SH);
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "lamps"), LAMPS_TAB);
	}
}