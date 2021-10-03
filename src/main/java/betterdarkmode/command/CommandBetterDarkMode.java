package betterdarkmode.command;

import betterdarkmode.Configs;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

@Environment(EnvType.CLIENT)
public class CommandBetterDarkMode {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> builder = literal("betterdarkmode");
        builder.then(literal("white").executes(c -> {
            Configs.SELECTED_COLOR = Configs.DEFAULT_WHITE;
            return 1;
        })).then(literal("black").executes(c -> {
            Configs.SELECTED_COLOR = Configs.DEFAULT_BLACK;
            return 1;
        }));
        dispatcher.register(builder);
    }
}
