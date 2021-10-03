package betterdarkmode.mixin;

import betterdarkmode.Configs;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TextRenderer.class)
public class TextRendererMixin {

    @Unique
    private Text updatedRenderableText;

    @Inject(
            method = "draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
            at = @At("HEAD")
    )
    public void onDrawInvoke(MatrixStack matrices, Text text, float x, float y, int color, CallbackInfoReturnable<Integer> cir) {
        StringBuilder builder = new StringBuilder();
        boolean skipNext = false;
        for (char c : text.getString().toCharArray()) {
            if (skipNext) skipNext = false;
            else if (c == '§') skipNext = true;
            else builder.append(c);
        }
        updatedRenderableText = new LiteralText(builder.toString());
    }

    @ModifyArgs(
            method = "draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/font/TextRenderer.draw(Lnet/minecraft/text/OrderedText;FFILnet/minecraft/util/math/Matrix4f;Z)I"
            )
    )
    public void overrideTextStyle(Args args) {

        args.set(0, updatedRenderableText.asOrderedText());
        args.set(1, args.get(1));
        args.set(2, args.get(2));
        args.set(3, Configs.SELECTED_COLOR);
        args.set(4, args.get(4));
        args.set(5, args.get(5));
    }
}

