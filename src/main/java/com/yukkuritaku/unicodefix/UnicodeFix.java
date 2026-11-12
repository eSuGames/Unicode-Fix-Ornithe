package com.yukkuritaku.unicodefix;

import net.minecraft.client.Minecraft;

/**
 * Helpers shared by the mixins.
 */
public final class UnicodeFix {

	private UnicodeFix() {
	}

	/**
	 * Whether the "Force Unicode Font" video setting is enabled.
	 *
	 * <p>After the mixins are applied this is the single source of truth for
	 * whether the game should render with the unicode font, even for languages
	 * such as Japanese or Chinese which are unicode locales by themselves.
	 */
	public static boolean isForceUnicodeFont() {
		Minecraft minecraft = Minecraft.getInstance();

		return minecraft != null && minecraft.options != null && minecraft.options.forceUnicodeFont;
	}
}
