package net.hendoor64.syllables.incantation.util;

import net.hendoor64.syllables.Syllables;
import net.hendoor64.syllables.incantation.Incantation;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Utility methods for incantations to be called from either logical side (client or server).
 * The results of these methods should not depend on side-specific factors.
 */
public class IncantUtil {
    /**
     * Sets the player to have prepared the given incantation.
     * @param player
     * @param incantation
     * @return whether the incantation was successfully prepared.
     */
    public static boolean prepareIncantation(PlayerEntity player, Incantation incantation) {
        // TODO NYI
        return true; // TESTING
    }

    /**
     * Gets the list of incantations the player has prepared. Incantations in progress are still "prepared", but
     * spent incantations are not.
     * @param player
     * @return a list of the player's prepared incantations.
     */
    public static List<Incantation> getPreparedIncantations(PlayerEntity player) {
        // TODO NYI
        return List.of(Incantation.TEST); // TESTING
    }

    /**
     * Checks whether the player has prepared the given incantation.
     * @param incantation
     * @return true if the incantation is prepared.
     */
    public static boolean hasPrepared(PlayerEntity player, Incantation incantation) {
        return getPreparedIncantations(player).contains(incantation);
    }

    /**
     * Determines whether the passed phrase should trigger the beginning of an incantation for the given player.
     * Typically, this would mean that the phrase is the first one of the incantation.
     * @param player the player to test
     * @param phrase a text message which the player presumably sent
     * @return either an Optional of the Incantation which should trigger, or Optional.empty()
     */
    private static Optional<Incantation> isIncantTrigger(PlayerEntity player, String phrase) {
        List<Incantation> prepared = getPreparedIncantations(player);
        for (Incantation i : prepared) {
            if (phrase.equals(i.getPhrases().get(0))) {
                return Optional.of(i);
            }
        }
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
                incantation.progress(player, phrase);
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
     * Gets the passed player's current active incantation, if any.
     * @param player
     * @return Optional.empty() if the player is not incanting, otherwise Optional.of(their incantation)
     */
    public static Optional<Incantation> getCurrentIncantation(PlayerEntity player) {
        for (Incantation i : Incantation.values()) {
            if (player.hasStatusEffect(i.getStatusEffect())) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    /**
     * Triggers a backfire event on the player. A backfire is the result of a disastrously failed incantation.
     * @param player
     */
    private static void backfire(PlayerEntity player) {
        player.sendMessage(Text.of("Incantation backfired!!!"));
        // TODO NYI
    }

    private IncantUtil() { /* Should not be instantiated */ }
}
