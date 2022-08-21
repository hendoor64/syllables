package net.hendoor64.syllables.incantation.effector;

import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

/**
 * A class which implements the effects of an incantation.
 */
public abstract class IncantationEffector {
    // The words or phrases which comprise the incantation
    private final List<String> phrases;

    public IncantationEffector(String... phrases) {
        this.phrases = List.of(phrases);
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
        int progress = 0; // TODO set the number of words already spoken

        if (phrase.equals(phrases.get(progress))) { // Phrase matches the next word in the sequence
            // TODO update the player's state to have completed one more word
            ++progress;
        } else { // The player has failed to progress the incantation
            return false;
        }

        if (progress >= length()) { // Player has completed speaking the incantation
            setTargeting(player);
        }
        return true;
    }

    /**
     * Set the player in a state of targeting the incantation. Pressing "shift" should trigger the effect.
     * @param player
     */
    protected abstract void setTargeting(PlayerEntity player);

    /**
     * Create/apply the effect of the incantation.
     * @param player the player who cast the incantation.
     */
    protected abstract void effect(PlayerEntity player);

    public int length() {
        return this.phrases.size();
    }

    public List<String> getPhrases() {
        return this.phrases;
    }
}
