package net.hendoor64.syllables.incantation.effector;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

public class TestIncantationEffector extends IncantationEffector {

    public TestIncantationEffector(String... phrases) {
        super(phrases);
    }

    @Override
    protected void setTargeting(PlayerEntity player) {
        // TODO NYI
    }

    @Override
    protected void effect(PlayerEntity player) {
        player.sendMessage(Text.of("Effecting TestEffect!"));
        // TODO NYI
    }
}
