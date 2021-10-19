package betterdarkmode.command;

import betterdarkmode.Config;
import betterdarkmode.Constants;
import betterdarkmode.util.Util;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CommandBetterDarkMode {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("betterdarkmode")
                .then(literal("white")
                        .executes(c -> {
                            Config.CLIENT.selectedColor.set(Constants.DEFAULT_WHITE);
                            return 1;
                        }))
                .then(literal("black")
                        .executes(c -> {
                            Config.CLIENT.selectedColor.set(Constants.DEFAULT_BLACK);
                            return 1;
                        }))
                .then(literal("custom")
                        .then(argument("red", integer(0, 255))
                                .then(argument("green", integer(0, 255))
                                        .then(argument("blue", integer(0, 255))
                                                .executes(c -> {
                                                    Config.CLIENT.selectedColor.set(Util.getRGB(
                                                            c.getArgument("red", Integer.class),
                                                            c.getArgument("green", Integer.class),
                                                            c.getArgument("blue", Integer.class)
                                                    ));
                                                    return 1;
                                                })
                                        )
                                )
                        )));
    }
}
