package net.hendoor64.syllables.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.hendoor64.syllables.Syllables;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;

public class SyllablesClientNetworking {
    public static final Identifier INCANT_PROGRESS_PACKET_ID = new Identifier(Syllables.MOD_ID, "incant_success_packet_id");
    public static final Identifier INCANT_COMPLETE_PACKET_ID = new Identifier(Syllables.MOD_ID, "incant_complete_packet_id");

    public static void registerClientReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(INCANT_PROGRESS_PACKET_ID, ((client, handler, buf, responseSender) -> {
            client.execute(() -> {
                doIncantationProgressFX(client.player);
            });
        }));

        ClientPlayNetworking.registerGlobalReceiver(INCANT_COMPLETE_PACKET_ID, ((client, handler, buf, responseSender) -> {
            client.execute(() -> {
                doIncantationCompleteFX(client.player);
            });
        }));
    }

    /**
     * Plays the VFX for successfully progressing an incantation.
     *
     * TODO * this seems like it belongs somewhere else, but I don't know where.
     * TODO * A static method in Incantation is the closest... maybe IncantUtil?
     * @param player the PlayerEntity who cast the incantation
     */
    private static void doIncantationProgressFX(PlayerEntity player) {
        int numParticles = 20;
        for (int i = 0; i < numParticles; i++) {
            player.world.addParticle(ParticleTypes.GLOW,
                    player.getParticleX(2.0D),
                    player.getRandomBodyY() + 0.6D,
                    player.getParticleZ(2.0),
                    (player.getRandom().nextDouble() - 0.5D) * 0.2D,
                    (player.getRandom().nextDouble() - 0.5D) * 0.2D,
                    (player.getRandom().nextDouble() - 0.5D) * 0.2D);
        }
    }

    /**
     * Plays the VFX for successfully progressing an incantation.
     * @param player the PlayerEntity who cast the incantation
     */
    private static void doIncantationCompleteFX(PlayerEntity player) {
        for (int i = 0; i < 15; i++) {
            player.world.addParticle(ParticleTypes.ENCHANT,
                    player.getParticleX(4.0D),
                    player.getRandomBodyY() + 0.6D,
                    player.getParticleZ(4.0),
                    0.0D,
                    0.0D,
                    0.0D);
            player.world.addParticle(ParticleTypes.GLOW,
                    player.getParticleX(5.0D),
                    player.getRandomBodyY() + 0.6D,
                    player.getParticleZ(5.0),
                    (player.getRandom().nextDouble() - 0.5D) * 0.2D,
                    (player.getRandom().nextDouble() - 0.5D) * 0.2D,
                    (player.getRandom().nextDouble() - 0.5D) * 0.2D);
        }
    }

    private SyllablesClientNetworking() {
        // Do not instantiate
    }
}
