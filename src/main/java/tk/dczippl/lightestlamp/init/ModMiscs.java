package tk.dczippl.lightestlamp.init;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import tk.dczippl.lightestlamp.machine.gascentrifuge.GasCentrifugeScreenHandler;
import tk.dczippl.lightestlamp.potion.BrominePoison;

import java.awt.*;

import static tk.dczippl.lightestlamp.LightestLampsMod.MOD_ID;

public class ModMiscs {
	public static final StatusEffect BROMINE_POISON = new BrominePoison(StatusEffectType.HARMFUL,new Color(102,16,0).getRGB());
	//public static final DamageSource BROMINE = new DamageSource("bromine").setDamageBypassesArmor();

	public static final ItemGroup LAMPS_TAB = QuiltItemGroup.builder(new Identifier(MOD_ID,"lamps")).appendItems(c->c.add(new ItemStack(ModBlocks.OMEGA_LAMP,16))).build();
	public static final ScreenHandlerType<GasCentrifugeScreenHandler> CENTRIFUGE_SH = ScreenHandlerRegistry.registerExtended(new Identifier("glowstone_centrifuge"),GasCentrifugeScreenHandler::new);
}