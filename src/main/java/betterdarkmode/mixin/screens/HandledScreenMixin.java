package betterdarkmode.mixin.screens;

import betterdarkmode.Configs;
import betterdarkmode.util.Util;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {
    @ModifyArgs(method = "drawForeground", at = @At(value = "INVOKE", target = "net/minecraft/client/font/TextRenderer.draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"))
    public void onDrawCall(Args args){
        args.set(1, Util.getCleanText(args.get(1)));
        args.set(4, Configs.SELECTED_COLOR);
    }
}
