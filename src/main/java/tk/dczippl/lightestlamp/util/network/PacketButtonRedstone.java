package tk.dczippl.lightestlamp.util.network;

import net.fabricmc.fabric.impl.networking.NetworkingImpl;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public class PacketButtonRedstone
{
    private final BlockPos pos;
    private final int type;

    public PacketButtonRedstone(PacketByteBuf buf)
    {
        pos = buf.readBlockPos();
        type = buf.readInt();
    }

    public PacketButtonRedstone(BlockPos pos, int type)
    {
        this.pos = pos;
        this.type = type;
    }

    public void toBytes(PacketByteBuf buf)
    {
        buf.writeBlockPos(pos);
        buf.writeInt(type);
    }

    /*public void handle(Supplier<NetworkingImpl> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            BlockEntity te = ctx.get().getSender().getServerWorld().getTileEntity(pos);
            if (te instanceof GasCentrifugeTile)
            {
                GasCentrifugeTile gte = ((GasCentrifugeTile) te);
                if (gte.getRedstoneMode()>=2)
                    gte.setRedstoneMode(0);
                else
                    gte.setRedstoneMode(gte.getRedstoneMode()+1);
            }
        });
        ctx.get().setPacketHandled(true);
    }*/
}