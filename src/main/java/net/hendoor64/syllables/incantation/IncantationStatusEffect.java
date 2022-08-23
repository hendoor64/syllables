package net.hendoor64.syllables.incantation;

import net.hendoor64.syllables.Syllables;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class IncantationStatusEffect extends StatusEffect {

    public IncantationStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        int duration = entity.getStatusEffect(this).getDuration();
        if (duration <= 1) {
            entity.removeStatusEffect(this);
            timeoutEffect(entity);
        }
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
        // TODO NYI
        if (entity instanceof PlayerEntity) {
            entity.damage(DamageSource.MAGIC, 1);
        }
    }
}
