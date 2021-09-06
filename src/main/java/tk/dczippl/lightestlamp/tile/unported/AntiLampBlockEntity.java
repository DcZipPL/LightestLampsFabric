package tk.dczippl.lightestlamp.tile.unported;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import tk.dczippl.lightestlamp.init.ModTileEntities;

public class AntiLampBlockEntity extends TileEntity implements ITickableTileEntity
{
    public AntiLampBlockEntity()
    {
        super(ModTileEntities.ANTILAMP_TE);
    }

    @Override
    public void tick()
    {
        //world.setLightFor();
    }
}