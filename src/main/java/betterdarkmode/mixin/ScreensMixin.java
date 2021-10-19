package betterdarkmode.mixin;

import betterdarkmode.Config;
import betterdarkmode.util.Util;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({AbstractContainerScreen.class, CreativeModeInventoryScreen.class, MerchantScreen.class, InventoryScreen.class})
public abstract class ScreensMixin {
    @Redirect(method = "renderLabels", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/Font.draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I"))
    public int onDrawCall(Font instance, PoseStack poseStack, Component component, float x, float y, int color) {
        return instance.draw(poseStack, Util.getCleanText(component), x, y, Config.CLIENT.selectedColor.get());
    }
}
