package betterdarkmode.mixin;

import betterdarkmode.BetterDarkMode;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "loadWorld", at = @At("RETURN"))
    public void onServerLoadedWorlds(CallbackInfo ci) {
        BetterDarkMode.INSTANCE.onServerLoaded((MinecraftServer) (Object) this);
    }

    @Inject(method = "shutdown", at = @At("RETURN"))
    public void onServerDoneShutdown(CallbackInfo ci) {
        BetterDarkMode.INSTANCE.onServerShutdown((MinecraftServer) (Object) this);
    }
}
