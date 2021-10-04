package betterdarkmode;

import betterdarkmode.command.CommandBetterDarkMode;
import betterdarkmode.util.Util;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class BetterDarkMode implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger("BetterDarkMode");
    public static BetterDarkMode INSTANCE = new BetterDarkMode();

    @Override
    public void onInitialize() {}

    public void onLoad(){
        Util.readSaveFile();
    }

    public void onShutdown(){
        Util.writeSaveFile();
    }

    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        CommandBetterDarkMode.register(dispatcher);
    }
}
