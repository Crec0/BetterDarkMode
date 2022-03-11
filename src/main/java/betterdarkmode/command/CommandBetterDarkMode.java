package betterdarkmode.command;

import betterdarkmode.Configs;
import betterdarkmode.util.Util;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

public final class CommandBetterDarkMode {
    public static LiteralArgumentBuilder<FabricClientCommandSource> getBuilder() {
        return literal("betterdarkmode")
            .then(literal("white")
                .executes(c -> setColor(Configs.DEFAULT_WHITE)))
            .then(literal("black")
                .executes(c -> setColor(Configs.DEFAULT_BLACK)))
            .then(literal("custom")
                .then(argument("red", integer(0, 255))
                    .then(argument("green", integer(0, 255))
                        .then(argument("blue", integer(0, 255))
                            .executes(CommandBetterDarkMode::setColor))
                    )
                )
            );
    }

    private static int setColor(CommandContext<FabricClientCommandSource> context) {
        Configs.SELECTED_COLOR = Util.getRGB(
            context.getArgument("red", Integer.class),
            context.getArgument("green", Integer.class),
            context.getArgument("blue", Integer.class)
        );
        return 1;
    }

    private static int setColor(final int color) {
        Configs.SELECTED_COLOR = color;
        return 1;
    }
}
