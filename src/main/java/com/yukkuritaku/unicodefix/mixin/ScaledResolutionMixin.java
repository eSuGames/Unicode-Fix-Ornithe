package com.yukkuritaku.unicodefix.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.render.Window;

@Mixin(Window.class)
public abstract class ScaledResolutionMixin {

	@Shadow
	private int scale;

	// vanilla (ScaledResolution constructor):
	//     if (isUnicode && scale % 2 != 0 && scale != 1) --scale;
	// Unicode Fix rounds the scale up instead of down (-- becomes ++):
	//     if (isUnicode && scale % 2 != 0 && scale != 1) ++scale;
	// The redirected instruction is the PUTFIELD of "--scale", which stores
	// (scale - 1); storing (value + 2) turns it into (scale + 1).
	@Redirect(
		method = "<init>",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/render/Window;scale:I",
			opcode = Opcodes.PUTFIELD,
			ordinal = 2
		)
	)
	private void unicodefix$roundScaleUpInsteadOfDown(Window self, int value) {
		((ScaledResolutionMixin) (Object) self).scale = value + 2;
	}
}
