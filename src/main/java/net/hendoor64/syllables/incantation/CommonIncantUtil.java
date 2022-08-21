package net.hendoor64.syllables.incantation;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

/**
 * Utility methods for incantations to be called from either logical side (client or server).
 * The results of these methods should not depend on side-specific factors.
 */
public class CommonIncantUtil {
    /**
     * Determines whether the passed phrase should trigger the beginning of an incantation for the given player.
     * Typically, this would mean that the phrase is the first one of the incantation.
     * @param player the player to test
     * @param phrase a text message which the player presumably sent
     * @return either an Optional of the Incantation which should trigger, or Optional.empty()
     */
    private static Optional<Incantation> isIncantTrigger(PlayerEntity player, String phrase) {
        // TODO not yet implemented
        return Optional.empty();
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
        Incantation incantation = getCurrentIncantation(player).orElse(null);
        if (incantation == null) { // Player is not currently incanting
            incantation = isIncantTrigger(player, phrase).orElse(null);
            if (incantation != null) {
                beginIncantation(player, incantation);
                return true;
            }
            return false;
        }

        // Player is incanting; attempt to progress their current incantation
        if (!incantation.progress(player, phrase)) {
            backfire(player);
        }
        return true;
    }

    /**
     * Begins the casting of the given incantation for the given player.
     * @param player
     * @param incantation
     */
    private static void beginIncantation(PlayerEntity player, Incantation incantation) {
        // TODO NYI
    }

    /**
     * Gets the passed player's current active incantation, if any.
     * @param player
     * @return Optional.empty() if the player is not incanting, otherwise Optional.of(their incantation)
     */
    public static Optional<Incantation> getCurrentIncantation(PlayerEntity player) {
        // TODO NYI
        return Optional.empty();
    }

    /**
     * Triggers a backfire event on the player. A backfire is the result of a disastrously failed incantation.
     * @param player
     */
    private static void backfire(PlayerEntity player) {
        // TODO NYI
    }

    private CommonIncantUtil() { /* Should not be instantiated */ }
}
