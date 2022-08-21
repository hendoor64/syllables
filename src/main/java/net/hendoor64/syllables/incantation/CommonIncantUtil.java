package net.hendoor64.syllables.incantation;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Utility methods for incantations to be called from either logical side (client or server).
 * The results of these methods should not depend on side-specific factors.
 */
public class CommonIncantUtil {

    /**
     * Returns whether the passed player is "incanting": currently partway through casting or targeting an incantation.
     * @param player the player to check
     * @return if the player is incanting
     */
    public static boolean isIncanting(PlayerEntity player) {
        // TODO not yet implemented
        return false;
    }

    /**
     * Attempts to enact an incantation for the given player using the passed phrase.
     * If the phrase should trigger the beginning of an incantation for the player, it will do so. If the phrase
     * is the next step in an ongoing incantation, the incantation will be appropriately progressed. Otherwise, this
     * method returns false and nothing happens.
     * @param player the player doing the incanting
     * @param phrase the incantation phrase
     * @return whether the phrase was an incantation phrase.
     */
    public static boolean incant(PlayerEntity player, String phrase) {
        // TODO not yet implemented
        return false;
    }

    /**
     * Determines whether the passed phrase triggers the beginning of an incantation for the given player.
     * Typically, this would mean the phrase is the first word in an incant that the player has prepared.
     * @param player the player to test
     * @param phrase a text message which the player presumably sent
     * @return a boolean indicating whether the key phrase should trigger the beginning of an incantation
     */
    private static boolean isIncantTrigger(PlayerEntity player, String phrase) {
        // TODO not yet implemented
        return false;
    }

    private CommonIncantUtil() { /* Should not be instantiated */ }
}
