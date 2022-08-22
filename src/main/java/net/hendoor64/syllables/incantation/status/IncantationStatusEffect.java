package net.hendoor64.syllables.incantation.status;

import net.hendoor64.syllables.incantation.Incantation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class IncantationStatusEffect extends StatusEffect {
    private final Incantation incantation;

    public IncantationStatusEffect(Incantation incantation) {
        super(StatusEffectCategory.BENEFICIAL, 0xFFFFFF);
        this.incantation = incantation;
    }

    // Called every tick to check whether the effect should be applied
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Effect should always apply
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // If time is up, trigger timeout effect
        if (entity.hasStatusEffect(this) && entity.getStatusEffect(this).getDuration() <= 5) {
            entity.removeStatusEffect(this);
            timeoutEffect(entity);
        }
    }

    /**
     * Effects the event which should trigger when the effect times out
     * @param entity
     */
    protected void timeoutEffect(LivingEntity entity) {
        // TODO NYI
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity)entity).sendMessage(Text.of("Incantation timed out!"));
        }
    }

    public Incantation getIncantation() {
        return this.incantation;
    }
}
