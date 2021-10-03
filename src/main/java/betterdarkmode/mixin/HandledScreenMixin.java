package betterdarkmode.mixin;

import betterdarkmode.Configs;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin extends Screen {
    @Shadow protected int titleX;

    @Shadow protected int titleY;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Redirect(
            method = "drawForeground",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/font/TextRenderer.draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
                    ordinal = 0
            )
    )
    public int onDrawInvoke(TextRenderer textRenderer, MatrixStack matrices, Text text, float x, float y, int color) {
        StringBuilder builder = new StringBuilder();
        boolean skipNext = false;
        for (char c : text.getString().toCharArray()) {
            if (skipNext) skipNext = false;
            else if (c == '§') skipNext = true;
            else builder.append(c);
        }
        this.textRenderer.draw(matrices, new LiteralText(builder.toString()), (float) this.titleX, (float) this.titleY, Configs.SELECTED_COLOR);
        return 1;
    }
}
