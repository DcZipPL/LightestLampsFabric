package tk.dczippl.lightestlamp.util.network;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class PacketButtonModeControl
{
    private final BlockPos pos;
    private final int type;

    public PacketButtonModeControl(PacketByteBuf buf)
    {
        pos = buf.readBlockPos();
        type = buf.readInt();
    }

    public PacketButtonModeControl(BlockPos pos, int type)
    {
        this.pos = pos;
        this.type = type;
    }

    public void toBytes(PacketByteBuf buf)
    {
        buf.writeBlockPos(pos);
        buf.writeInt(type);
    }

    /*public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            BlockEntity te = ctx.get().getSender().getServerWorld().getTileEntity(pos);
            if (te instanceof GasCentrifugeTile)
            {
                GasCentrifugeTile gte = ((GasCentrifugeTile) te);
                gte.startTicksBeforeDumping();
                if (gte.getLiquidMode()>=2)
                    gte.setLiquidMode(0);
                else
                    gte.setLiquidMode(gte.getLiquidMode()+1);
            }
        });
        ctx.get().setPacketHandled(true);
    }*/
}