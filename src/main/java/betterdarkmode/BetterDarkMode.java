package betterdarkmode;

import betterdarkmode.command.CommandBetterDarkMode;
import betterdarkmode.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class BetterDarkMode implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger("BetterDarkMode");

    @Override
    public void onInitialize() {
        ClientLifecycleEvents.CLIENT_STARTED.register(this::onLoad);
        ClientLifecycleEvents.CLIENT_STOPPING.register(this::onShutdown);
        ClientCommandManager.DISPATCHER.register(CommandBetterDarkMode.getBuilder());
    }

    public void onLoad(MinecraftClient client) {
        Util.readSaveFile();
    }

    public void onShutdown(MinecraftClient client) {
        Util.writeSaveFile();
    }
}
