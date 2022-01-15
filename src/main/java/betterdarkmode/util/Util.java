package betterdarkmode.util;

import betterdarkmode.BetterDarkMode;
import betterdarkmode.Configs;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    public static int getRGB(int r, int g, int b) {
        return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
    }

    private static Path getSaveFile() {
        try {
            Path saveFile = FabricLoader
                    .getInstance()
                    .getConfigDir()
                    .resolve("BetterDarkMode")
                    .resolve("selected_color.conf");

            if (!Files.exists(saveFile.getParent())) {
                Files.createDirectory(saveFile.getParent());
            }

            if (!Files.exists(saveFile)) {
                Files.createFile(saveFile);
            }

            return saveFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readSaveFile() {
        Path saveFile = Util.getSaveFile();

        if (saveFile == null) {
            BetterDarkMode.LOGGER.info("Save file doesn't exist and couldn't create it.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(saveFile)) {
            String line = reader.readLine();
            Configs.SELECTED_COLOR = Integer.parseInt(line.trim());
            BetterDarkMode.LOGGER.info("Successfully read save file.");
        } catch (IOException | NumberFormatException e) {
            BetterDarkMode.LOGGER.error("Failed to read save file.");
            e.printStackTrace();
        }
    }

    public static void writeSaveFile() {
        Path saveFile = Util.getSaveFile();
        if (saveFile == null) {
            BetterDarkMode.LOGGER.info("Save file doesn't exist and couldn't create it.");
            return;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(saveFile)) {
            writer.write(Integer.toString(Configs.SELECTED_COLOR));
            BetterDarkMode.LOGGER.info("Successfully wrote save file.");
        } catch (IOException e) {
            BetterDarkMode.LOGGER.error("Failed to write save file.");
            e.printStackTrace();
        }
    }

    public static Text getCleanText(Text text) {
        return new LiteralText(text.getString().replaceAll("§.", ""));
    }
}
