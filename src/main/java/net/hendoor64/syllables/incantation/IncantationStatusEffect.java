package net.hendoor64.syllables.incantation;

import net.hendoor64.syllables.Syllables;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;

public class IncantationStatusEffect extends StatusEffect {

    public IncantationStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        doTickEffect(entity);
        int duration = entity.getStatusEffect(this).getDuration();
        if (duration <= 1) {
            entity.removeStatusEffect(this);
            timeoutEffect(entity);
        }
    }

    /**
     * Creates the VFX which signal the effect is in action.
     * A circle of particles around the player.
     * @param entity
     */
    private void doTickEffect(LivingEntity entity) {
        double h = 2.0D;
        double theta = entity.age % 360;
        double x = Math.sin(theta) * h;
        double z = Math.cos(theta) * h;

        entity.world.addParticle(ParticleTypes.ENCHANT,
                entity.getEyePos().x + x,
                entity.getEyeY() - 1.0D,
                entity.getEyePos().getZ() + z,
                0.0D,
                0.0D,
                0.0D);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Always apply
    }

    /**
     * To be executed when the effect times out naturally.
     * @param entity
     */
    private void timeoutEffect(LivingEntity entity) {
        if (entity.world.isClient()) { return; }

        // TODO NYI
        if (entity instanceof PlayerEntity) {
            entity.damage(DamageSource.MAGIC, 1);
            ((PlayerEntity) entity).sendMessage(Text.of("Timeout effect!"));
        }
    }
}
