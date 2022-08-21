package net.hendoor64.syllables.incantation;

import net.hendoor64.syllables.incantation.effector.IncantationEffector;
import net.minecraft.entity.player.PlayerEntity;

public enum Incantation {
    DEFAULT (null);

    private IncantationEffector effector;

    Incantation(IncantationEffector effector) {
        this.effector = effector;
    }

    public boolean progress(PlayerEntity player, String phrase) {
        return effector.progress(player, phrase);
    }
}
