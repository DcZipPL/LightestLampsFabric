package tk.dczippl.lightestlamp.util;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;
import tk.dczippl.lightestlamp.LightestLampsMod;

public class Networking
{
	public static final Identifier TOGGLEBUTTONS_CHANNEL = new Identifier(LightestLampsMod.MOD_ID,"togglebuttons_channel");
	
	public static void sendToggledButtonMessage(int id, BlockPos pos){
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeBlockPos(pos);
		buf.writeInt(id);
		ClientPlayNetworking.send(Networking.TOGGLEBUTTONS_CHANNEL, buf);
	}
}