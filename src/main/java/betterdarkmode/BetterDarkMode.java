package betterdarkmode;

import betterdarkmode.command.CommandBetterDarkMode;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("betterdarkmode")
public class BetterDarkMode {
    public BetterDarkMode() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommandEvent);
    }

    public void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandBetterDarkMode.register(event.getDispatcher());
    }
}
