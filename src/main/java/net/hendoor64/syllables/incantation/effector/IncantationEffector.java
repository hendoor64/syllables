package net.hendoor64.syllables.incantation.effector;

import net.minecraft.entity.player.PlayerEntity;

/**
 * A class which implements the effects of an incantation.
 */
public interface IncantationEffector {
    /**
     * Progresses the casting of a particular incantation for the player. Returns false if the incantation could not
     * be correctly progressed using the given phrase (i.e. if the phrase was incorrect, or some other problem interfered).
     * @param player
     * @param phrase
     * @return whether the incantation was successfully progressed.
     */
    public boolean progress(PlayerEntity player, String phrase);
}
