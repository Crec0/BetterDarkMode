package betterdarkmode.mixin;

import betterdarkmode.Configs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen {
    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @ModifyArgs(
            method = "drawForeground",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/font/TextRenderer.draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"
            )
    )
    public void onDrawCall(Args args){
        StringBuilder builder = new StringBuilder();
        boolean skipNext = false;
        for (char c : ((Text) args.get(1)).getString().toCharArray()) {
            if (skipNext) skipNext = false;
            else if (c == '§') skipNext = true;
            else builder.append(c);
        }
        args.set(1, new LiteralText(builder.toString()));
        args.set(4, Configs.SELECTED_COLOR);
    }
}
