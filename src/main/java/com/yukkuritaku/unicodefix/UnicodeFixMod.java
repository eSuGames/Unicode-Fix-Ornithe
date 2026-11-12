package com.yukkuritaku.unicodefix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;

public class UnicodeFixMod implements ClientModInitializer {

	public static final String MOD_ID = "unicodefix";
	public static final String VERSION = "1.3.2";
	public static final String NAME = "Unicode Fix";

	public static final Logger LOGGER = LogManager.getLogger(NAME);

	@Override
	public void onInitializeClient() {
		LOGGER.info("{} {} (Ornithe) loaded", NAME, VERSION);
	}
}
