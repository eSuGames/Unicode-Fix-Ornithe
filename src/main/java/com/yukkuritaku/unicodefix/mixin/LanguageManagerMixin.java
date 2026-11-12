package com.yukkuritaku.unicodefix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.yukkuritaku.unicodefix.UnicodeFix;

import net.minecraft.client.resource.language.LanguageManager;

@Mixin(LanguageManager.class)
public abstract class LanguageManagerMixin {

	// vanilla returns Locale#isUnicode(), which makes the current locale force
	// the unicode font no matter what the "Force Unicode Font" setting says.
	// Unicode Fix: report the setting instead, so e.g. Japanese/Chinese users
	// can turn the unicode font off.
	@Inject(method = "isUnicode", at = @At("HEAD"), cancellable = true)
	private void unicodefix$isUnicode(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(UnicodeFix.isForceUnicodeFont());
	}
}
