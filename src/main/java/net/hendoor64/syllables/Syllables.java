package net.hendoor64.syllables;

import net.fabricmc.api.ModInitializer;
import net.hendoor64.syllables.incantation.status.IncantationStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Syllables implements ModInitializer {
	public static final String MOD_ID = "syllables";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("syllables");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Beginning Syllables init.");
		registerStatusEffects();
	}

	// Status effects
	public static final StatusEffect INCANTATION_STATUS_EFFECT = new IncantationStatusEffect(null);

	private void registerStatusEffects() {
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "incantationStatusEffect"), INCANTATION_STATUS_EFFECT);
	}
}
