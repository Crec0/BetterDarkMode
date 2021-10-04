package betterdarkmode.mixin;

import betterdarkmode.BetterDarkMode;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    public void postClientInit(CallbackInfo ci) {
        BetterDarkMode.INSTANCE.onLoad();
    }

    @Inject(method = "scheduleStop", at = @At("RETURN"))
    public void preClientShutdown(CallbackInfo ci) {
        BetterDarkMode.INSTANCE.onShutdown();
    }
}
