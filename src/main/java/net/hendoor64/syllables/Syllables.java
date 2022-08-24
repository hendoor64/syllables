package net.hendoor64.syllables;

import net.fabricmc.api.ModInitializer;
import net.hendoor64.syllables.incantation.IncantationStatusEffect;
import net.hendoor64.syllables.networking.SyllablesClientNetworking;
import net.hendoor64.syllables.networking.SyllablesServerNetworking;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
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
		LOGGER.info("Registering status effects.");
		registerStatusEffects();
		LOGGER.info("Registering server networking receivers.");
		SyllablesServerNetworking.registerServerReceivers();
		LOGGER.info("Registering client networking receivers.");
		SyllablesClientNetworking.registerClientReceivers();
	}

	// Status effects
	public static IncantationStatusEffect TEST_INCANTATION_STATUS_EFFECT;

	private void registerStatusEffects() {
		TEST_INCANTATION_STATUS_EFFECT = Registry.register(Registry.STATUS_EFFECT, 0, MOD_ID + ":test_incantation_status_effect", new IncantationStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFFFFFF));
	}
}
