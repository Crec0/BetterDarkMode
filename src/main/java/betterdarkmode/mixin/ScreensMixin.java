package betterdarkmode.mixin;

import betterdarkmode.Config;
import betterdarkmode.util.Util;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ContainerScreen.class, CreativeScreen.class, MerchantScreen.class, InventoryScreen.class})
public abstract class ScreensMixin {
    @Redirect(method = "renderLabels", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/FontRenderer.draw(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/util/text/ITextComponent;FFI)I"))
    public int onDrawCall(FontRenderer instance, MatrixStack matrixStack, ITextComponent component, float x, float y, int color) {
        return instance.draw(matrixStack, Util.getCleanText(component), x, y, Config.CLIENT.selectedColor.get());
    }
}
