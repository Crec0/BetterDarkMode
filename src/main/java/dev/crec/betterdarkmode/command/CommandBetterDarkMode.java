package dev.crec.betterdarkmode.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.crec.betterdarkmode.BetterDarkMode;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;


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
			new TextComponent("Color set to: ").append(
				new TextComponent(hex).withStyle(Style.EMPTY.withColor(color))
			)
		);
		return 1;
	}

	private static int setColor(final int color) {
		BetterDarkMode.selectedColor = color;
		BetterDarkMode.writeSave();
		return 1;
	}
}
