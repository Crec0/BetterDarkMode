package dev.crec.betterdarkmode.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.crec.betterdarkmode.BetterDarkMode;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public final class CommandBetterDarkMode {
	public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(literal("betterdarkmode")
			.then(
				literal("white")
					.executes(c -> setColor(BetterDarkMode.DEFAULT_WHITE)))
			.then(
				literal("black")
					.executes(c -> setColor(BetterDarkMode.DEFAULT_BLACK)))
			.then(
				literal("custom")
					.then(argument("hex-code", HexArgumentType.hex())
						.executes(CommandBetterDarkMode::setColor)
					)
				)
		);
	}

	private static int setColor(CommandContext<FabricClientCommandSource> context) {
		String hex = context.getArgument("hex-code", String.class);
		int color = Integer.parseInt(hex, 16);
		setColor(color);
		context.getSource().sendFeedback(
			Component.literal("Color set to: ").append(
				Component.literal(hex).withStyle(Style.EMPTY.withColor(color))
			)
		);
		return 1;
	}

	private static int setColor(final int color) {
		BetterDarkMode.selectedColor = color;
		return 1;
	}
}
