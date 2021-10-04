package betterdarkmode.command;

import betterdarkmode.Configs;
import betterdarkmode.util.Util;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CommandBetterDarkMode {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        Class<Integer> intClass = Integer.class;
        dispatcher.register(literal("betterdarkmode").then(literal("white").executes(c -> {
            Configs.SELECTED_COLOR = Configs.DEFAULT_WHITE;
            return 1;
        })).then(literal("black").executes(c -> {
            Configs.SELECTED_COLOR = Configs.DEFAULT_BLACK;
            return 1;
        })).then(literal("custom").then(argument("red", integer(0, 255)).then(argument("green", integer(0, 255)).then(argument("blue", integer(0, 255)).executes(c -> {
            Configs.SELECTED_COLOR = Util.getRGB(c.getArgument("red", intClass), c.getArgument("green", intClass), c.getArgument("blue", intClass));
            return 1;
        }))))));
    }
}
