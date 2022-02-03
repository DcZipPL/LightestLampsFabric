package tk.dczippl.lightestlamp.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BrominePoison extends StatusEffect {
    private int liquidColor;

    public BrominePoison(StatusEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        liquidColor = liquidColorIn;
    }

    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isClient)
            if (amplifier >= 20)
                entityLivingBaseIn.damage(DamageSource.MAGIC, 2.0F); // TODO: Implement REAL Bromine damage source
            else {
                entityLivingBaseIn.damage(DamageSource.MAGIC, 0.5F * (amplifier + 1));
            }
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        this.performEffect(entity, amplifier);
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public int getColor() {
        return liquidColor;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}