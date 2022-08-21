package net.hendoor64.syllables.incantation;

import net.hendoor64.syllables.incantation.effector.IncantationEffector;
import net.hendoor64.syllables.incantation.effector.TestIncantationEffector;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public enum Incantation {
    TEST (new TestIncantationEffector("fus", "ro", "dah"));

    private IncantationEffector effector;

    Incantation(IncantationEffector effector) {
        this.effector = effector;
    }

    public boolean progress(PlayerEntity player, String phrase) {
        return effector.progress(player, phrase);
    }

    public List<String> getPhrases() {
        return effector.getPhrases();
    }
}
