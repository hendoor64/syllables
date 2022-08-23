package net.hendoor64.syllables.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hendoor64.syllables.incantation.util.IncantUtil;
import net.hendoor64.syllables.networking.SyllablesServerNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class PlayerChatMixin {

    /**
     * Triggered just before a client player sends a chat message.
     * Cancelling the CallbackInfo will cause the chat not to be sent.
     * @param message the message to be sent
     * @param preview a preview of the message
     * @param ci the callback context of the injection
     */
    @Inject(at = @At(value = "HEAD"), method = "sendChatMessage(Ljava/lang/String;Lnet/minecraft/text/Text;)V", cancellable = true)
    private void onChat(String message, Text preview, CallbackInfo ci) {
        // Send a packet to the server, since this event is only triggered on the client side
        PacketByteBuf buf = PacketByteBufs.create().writeString(message);
        ClientPlayNetworking.send(SyllablesServerNetworking.INCANT_PACKET_ID, buf);
    }

}
