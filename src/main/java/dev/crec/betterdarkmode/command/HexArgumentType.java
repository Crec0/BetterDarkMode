package dev.crec.betterdarkmode.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

public class HexArgumentType implements ArgumentType<String> {
	private static final Collection<String> EXAMPLES = Arrays.asList("404040", "ECF0F1");
	private final DynamicCommandExceptionType WRONG_HEX = new DynamicCommandExceptionType(
		component -> new TranslatableComponent("Invalid hex value: %s", component)
	);
	private static final Pattern VALID_HEX = Pattern.compile("^[0-9a-f]{1,6}$");
	private static final Pattern COMPLETE_HEX_COLOR = Pattern.compile("^[0-9a-f]{6}$");

	public static HexArgumentType hex() {
		return new HexArgumentType();
	}

	@Override
	public String parse(StringReader reader) throws CommandSyntaxException {
		String remaining = reader.getRemaining().toLowerCase().trim();
		if (COMPLETE_HEX_COLOR.matcher(remaining).matches()) {
			return reader.readString();
		} else {
			throw WRONG_HEX.create(remaining);
		}
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		List<String> suggestions = new ArrayList<>();
		String remaining = builder.getRemainingLowerCase().trim();
		if (VALID_HEX.matcher(remaining).matches()) {
			int currentValue = Integer.parseInt(remaining, 16) << ((6 - remaining.length()) * 4);
			String hex;
			for (int i = 0; i < 20; i++) {
				hex = Integer.toString(currentValue + i, 16);
				suggestions.add("000000".substring(hex.length()) + hex);
			}
		}
		return SharedSuggestionProvider.suggest(suggestions, builder);
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
