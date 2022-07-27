package dev.crec.betterdarkmode.mixin;

import dev.crec.betterdarkmode.BetterDarkMode;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({AbstractContainerScreen.class, InventoryScreen.class, MerchantScreen.class, CreativeModeInventoryScreen.class})
public class ScreensMixin {
	@ModifyArgs(
		method = "renderLabels",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I"
		)
	)
	private void onRender(Args args) {
		args.set(1, BetterDarkMode.cleanText(args.get(1)));
		args.set(4, BetterDarkMode.selectedColor);
	}
}
