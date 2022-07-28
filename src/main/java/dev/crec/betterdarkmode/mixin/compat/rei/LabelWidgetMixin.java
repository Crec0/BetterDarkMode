package dev.crec.betterdarkmode.mixin.compat.rei;

import dev.crec.betterdarkmode.BetterDarkMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Pseudo
@Mixin(targets = "me.shedaniel.rei.impl.client.gui.widget.basewidgets.LabelWidget", remap = false)
public class LabelWidgetMixin {
	@ModifyArg(
		method = "render",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I",
			remap = true
		),
		remap = false,
		index = 4
	)
	private int onRenderText(int i) {
		return BetterDarkMode.selectedColor;
	}


	@ModifyArg(
		method = "render",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/Font;drawShadow(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I",
			remap = true
		),
		remap = false,
		index = 4
	)
	private int onRenderShadow(int i) {
		return BetterDarkMode.selectedColor;
	}
}
