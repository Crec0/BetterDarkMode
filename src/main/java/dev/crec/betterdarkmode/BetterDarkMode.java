package dev.crec.betterdarkmode;

import dev.crec.betterdarkmode.command.CommandBetterDarkMode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BetterDarkMode implements ModInitializer {

	public static Logger LOGGER = LogManager.getLogger(BetterDarkMode.class);
	public static final int DEFAULT_BLACK = 0x404040;
	public static final int DEFAULT_WHITE = 0xECF0F1;

	private static final Path saveFile = FabricLoader.getInstance().getConfigDir().resolve("better-dark-mode.conf");

	public static int selectedColor = DEFAULT_BLACK;

	@Override
	public void onInitialize() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			CommandBetterDarkMode.register(dispatcher);
		});
		ClientLifecycleEvents.CLIENT_STARTED.register(mc -> readSave());
		ClientLifecycleEvents.CLIENT_STOPPING.register(mc -> writeSave());
	}

	public static Component cleanText(Component original) {
		return Component.literal(original.getString().replaceAll("§.", "")).withStyle(original.getStyle());
	}

	public static void readSave() {
		try {
			if (!Files.exists(saveFile)) {
				writeSave();
			}

			List<String> data = Files.readAllLines(saveFile);
			if (data.size() > 1) {
				selectedColor = Integer.parseInt(data.get(0).trim(), 16);
			}

		} catch (IOException | NumberFormatException e) {
			LOGGER.warn("Failed to read save file. {}", e.getMessage());
		}
	}

	public static void writeSave() {
		try {
			if (!Files.exists(saveFile)) {
				Files.createDirectories(saveFile.getParent());
				Files.createFile(saveFile);
			}
			Files.write(saveFile, Integer.toString(selectedColor, 16).getBytes());
		} catch (IOException e) {
			LOGGER.warn("Failed to save the save file. {}", e.getMessage());
		}
	}
}
