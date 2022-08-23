package net.hendoor64.syllables.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hendoor64.syllables.Syllables;
import net.hendoor64.syllables.incantation.util.IncantUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class SyllablesServerNetworking {
    public static final Identifier INCANT_PACKET_ID = new Identifier(Syllables.MOD_ID, "incant_packet_id");

    public static void registerServerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(INCANT_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            String phrase = buf.readString();

            server.execute(() -> {
                IncantUtil.incant(player, phrase);
            });
        });
    }

    private SyllablesServerNetworking() {
        // Do not instantiate
    }
}
