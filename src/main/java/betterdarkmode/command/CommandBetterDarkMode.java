package betterdarkmode.command;

import betterdarkmode.Configs;
import betterdarkmode.util.Util;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

public class CommandBetterDarkMode {
    public static LiteralArgumentBuilder<FabricClientCommandSource> getBuilder() {
        return literal("betterdarkmode")
                .then(literal("white")
                        .executes(c -> {
                            Configs.SELECTED_COLOR = Configs.DEFAULT_WHITE;
                            return 1;
                        })
                )
                .then(literal("black")
                        .executes(c -> {
                            Configs.SELECTED_COLOR = Configs.DEFAULT_BLACK;
                            return 1;
                        })
                )
                .then(literal("custom")
                        .then(argument("red", integer(0, 255))
                                .then(argument("green", integer(0, 255))
                                        .then(argument("blue", integer(0, 255))
                                                .executes(c -> {
                                                    Configs.SELECTED_COLOR = Util.getRGB(
                                                            c.getArgument(  "red", Integer.class),
                                                            c.getArgument("green", Integer.class),
                                                            c.getArgument( "blue", Integer.class)
                                                    );
                                                    return 1;
                                                })
                                        )
                                )
                        )
                );
    }
}
