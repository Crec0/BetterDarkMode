package betterdarkmode;

import betterdarkmode.command.CommandBetterDarkMode;
import betterdarkmode.util.Util;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class BetterDarkMode implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger("BetterDarkMode");
    public static BetterDarkMode INSTANCE = new BetterDarkMode();
    public MinecraftServer MINECRAFT_SERVER;

    @Override
    public void onInitialize() {}

    public void onServerLoaded(MinecraftServer server){
        MINECRAFT_SERVER = server;
        Util.readSaveFile();
    }

    public void onServerShutdown(MinecraftServer server){
        Util.writeSaveFile();
    }

    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        CommandBetterDarkMode.register(dispatcher);
    }
}
