package net.hendoor64.syllables.incantation;

import net.hendoor64.syllables.incantation.status.IncantationStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

public enum Incantation {
    TEST (0, 400, "fus", "ro", "dah");

    private final List<String> phrases; // The phrases which must be chanted
    private final int id; // Unique identifier
    private final int countdown; // You have this much time to chant the next phrase, in ticks
    private final StatusEffect statusEffect; // Used to track casting in-progress

    Incantation(int id, int countdown, String... phrases) {
        this.id = id;
        this.phrases = List.of(phrases);
        this.countdown = countdown;
        this.statusEffect = new IncantationStatusEffect(this);
    }

    /**
     * Progresses the casting of a particular incantation for the player. Returns false if the incantation could not
     * be correctly progressed using the given phrase (i.e. if the phrase was incorrect, or some other problem interfered).
     * @param player
     * @param phrase
     * @return whether the incantation was successfully progressed.
     */
    public boolean progress(PlayerEntity player, String phrase) {
        // How far into the incantation is the player?
        int progress = player.hasStatusEffect(this.statusEffect) ? player.getStatusEffect(this.statusEffect).getAmplifier() : 0;

        if (phrase.equals(phrases.get(progress))) {
            // Phrase matches the next word in the sequence
            // Update player's status effect to show they've progressed one more word
            ++progress;
            player.removeStatusEffect(this.statusEffect);
            StatusEffectInstance effectInstance = new StatusEffectInstance(this.statusEffect, countdown, progress,
                    false, true, true);
            player.addStatusEffect(effectInstance);
        } else {
            // The player has failed to progress the incantation
            return false;
        }

        if (progress >= this.phrases.size()) { // Player has completed speaking the incantation
            setTargeting(player);
        }
        return true;
    }

    /**
     * Set the player in a state of targeting the incantation. Pressing "shift" should trigger the effect.
     * @param player
     */
    protected void setTargeting(PlayerEntity player) {
        // TODO NYI
        player.sendMessage(Text.of("Targeting incantation!"));
    }

    /**
     * Create/apply the effect of the incantation.
     * @param player the player who cast the incantation.
     */
    protected void effect(PlayerEntity player) {
        // TODO NYI
        player.sendMessage(Text.of("Effecting incantation!"));
    }

    public List<String> getPhrases() {
        return this.phrases;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }
}
