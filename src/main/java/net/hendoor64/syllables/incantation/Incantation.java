package net.hendoor64.syllables.incantation;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hendoor64.syllables.Syllables;
import net.hendoor64.syllables.networking.SyllablesClientNetworking;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.List;

public enum Incantation {
    TEST (0,400, Syllables.TEST_INCANTATION_STATUS_EFFECT,"fus", "ro", "dah");

    private final List<String> phrases; // The phrases which must be chanted
    private final int id; // Unique identifier
    private final int countdown; // You have this much time to chant the next phrase, in ticks
    private final StatusEffect statusEffect; // Used to track casting in-progress

    Incantation(int id, int countdown, StatusEffect statusEffect, String... phrases) {
        this.id = id;
        this.phrases = List.of(phrases);
        this.countdown = countdown;
        this.statusEffect = statusEffect;
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
        int progress = player.hasStatusEffect(this.statusEffect) ? player.getStatusEffect(this.statusEffect).getAmplifier() + 1 : 0;

        if (phrase.equals(phrases.get(progress))) {
            // Phrase matches the next word in the sequence
            // Update player's status effect to show they've progressed one more word
            ++progress;

            player.sendMessage(Text.of("incantation progress: " + progress)); // TESTING

            player.removeStatusEffect(this.statusEffect);
            StatusEffectInstance effectInstance = new StatusEffectInstance(this.statusEffect, countdown, progress - 1,
                    false, false, true);
            player.addStatusEffect(effectInstance);
        } else {
            // The player has failed to progress the incantation
            return false;
        }

        if (progress >= this.phrases.size()) { // Player has completed speaking the incantation
            player.removeStatusEffect(this.statusEffect);
            doCompletedFX(player, progress);
            setTargeting(player);
        } else {
            doProgressFX(player, progress);
        }
        return true;
    }

    /**
     * Stops the player's progress in casting this incantation, if they're casting it.
     * @param player
     */
    public void stopIncanting(PlayerEntity player) {
        player.removeStatusEffect(this.statusEffect);
    }

    /**
     * Creates the VFX and audio which signal a successful progression of the incantation.
     * @param player the player casting the incantation
     */
    private void doProgressFX(PlayerEntity player, int progress) {
        player.sendMessage(Text.of("Progress effect!")); // TESTING

        player.getEntityWorld().playSound(null,
                player.getBlockPos(),
                SoundEvents.BLOCK_NOTE_BLOCK_BELL,
                SoundCategory.PLAYERS,
                1f, progress * 0.5f);

        player.getEntityWorld().playSound(null,
                player.getBlockPos(),
                SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
                SoundCategory.PLAYERS,
                1f, 1.6f);

        // Send a packet to the client to display particles
        ServerPlayNetworking.send((ServerPlayerEntity) player,
                SyllablesClientNetworking.INCANT_PROGRESS_PACKET_ID,
                PacketByteBufs.empty());
    }

    /**
     * Creates the VFX and audio which signal the incantation is complete (i.e., the player has finished
     * typing all phrases).
     * @param player
     */
    private void doCompletedFX(PlayerEntity player, int progress) {
        player.getEntityWorld().playSound(null,
                player.getBlockPos(),
                SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE,
                SoundCategory.PLAYERS,
                0.6f, 1.0f);
        player.getEntityWorld().playSound(null,
                player.getBlockPos(),
                SoundEvents.BLOCK_NOTE_BLOCK_CHIME,
                SoundCategory.PLAYERS,
                0.6f, 1.5f);
        player.getEntityWorld().playSound(null,
                player.getBlockPos(),
                SoundEvents.BLOCK_NOTE_BLOCK_BELL,
                SoundCategory.PLAYERS,
                1.4f, progress * 0.5f);

        ServerPlayNetworking.send((ServerPlayerEntity) player,
                SyllablesClientNetworking.INCANT_COMPLETE_PACKET_ID,
                PacketByteBufs.empty());
    }

    /**
     * Set the player in a state of targeting the incantation. Pressing "shift" should trigger the effect.
     * @param player
     */
    private void setTargeting(PlayerEntity player) {
        // TODO NYI
        player.sendMessage(Text.of("Targeting incantation!"));
        effect(player); // TESTING
    }

    /**
     * Create/apply the effect of the incantation.
     * @param player the player who cast the incantation.
     */
    private void effect(PlayerEntity player) {
        player.removeStatusEffect(this.statusEffect);
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
