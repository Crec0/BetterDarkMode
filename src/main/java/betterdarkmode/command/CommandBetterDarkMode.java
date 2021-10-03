package betterdarkmode.command;

import betterdarkmode.Configs;
import betterdarkmode.util.Util;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
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
        })).then(literal("custom")
                .then(argument("red", IntegerArgumentType.integer(0, 255))
                        .then(argument("green", IntegerArgumentType.integer(0, 255))
                                .then(argument("blue", IntegerArgumentType.integer(0, 255)).executes(c -> {
                                    Configs.SELECTED_COLOR = Util.getRGB(
                                                                    c.getArgument("red",   Integer.class),
                                                                    c.getArgument("green", Integer.class),
                                                                    c.getArgument("blue",  Integer.class)
                                                            );
                                    return 1;
                                }))
                        )
                )
        );
        dispatcher.register(builder);
    }
}
