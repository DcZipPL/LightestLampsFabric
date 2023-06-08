package tk.dczippl.lightestlamp.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3d;

import java.util.List;

public class BlockUtil {
    public static void repelEntitiesInBoxFromPoint(World world, Box effectBox, double x, double y, double z, boolean ignore) {
        List<Entity> list = world.getNonSpectatingEntities(Entity.class, effectBox);

        for (Entity ent : list) {
            if ((ent instanceof LivingEntity) || (ent instanceof ProjectileEntity)) {
                if (!ignore && !(ent instanceof MobEntity || ent instanceof ProjectileEntity)) {
                    continue;
                } else {
                    if (ent instanceof ArrowEntity && ((ArrowEntity) ent).isOnGround())
                    {
                        continue;
                    }
                    Vec3d p = new Vec3d(x, y, z);
                    Vec3d t = ent.getPos();
                    double distance = p.distanceTo(t) + 0.1D;

                    Vector3d r = new Vector3d(t.x - p.x, t.y - p.y, t.z - p.z);

                    ent.setVelocity((r.x / 1.5D / distance + ent.getVelocity().x), (r.y / 1.5D / distance + ent.getVelocity().y), (r.z / 1.5D / distance + ent.getVelocity().z));
                }
            }
        }
    }
}