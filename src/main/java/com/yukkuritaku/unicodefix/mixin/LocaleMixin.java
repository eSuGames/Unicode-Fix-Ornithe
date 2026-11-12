package com.yukkuritaku.unicodefix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.yukkuritaku.unicodefix.UnicodeFix;

import net.minecraft.client.resource.language.Locale;

@Mixin(Locale.class)
public abstract class LocaleMixin {

	// vanilla returns the "unicode" flag computed from the loaded language files,
	// which forces the unicode font for languages like Japanese or Chinese.
	// Unicode Fix: base the decision on the "Force Unicode Font" setting only.
	@Inject(method = "isUnicode", at = @At("HEAD"), cancellable = true)
	private void unicodefix$isUnicode(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(UnicodeFix.isForceUnicodeFont());
	}

	// the "unicode" flag computed here is unused after the injection above,
	// so skip scanning every translation entry (same as upstream Unicode Fix).
	@Inject(method = "checkUnicodeUsage", at = @At("HEAD"), cancellable = true)
	private void unicodefix$skipCheckUnicodeUsage(CallbackInfo ci) {
		ci.cancel();
	}
}
