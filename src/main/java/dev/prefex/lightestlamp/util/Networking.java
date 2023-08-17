package dev.prefex.lightestlamp.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import dev.prefex.lightestlamp.LightestLampsMod;

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