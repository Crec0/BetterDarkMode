package betterdarkmode.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class Util {
    public static int getRGB(int r, int g, int b) {
        return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
    }

    public static Component getCleanText(Component text) {
        StringBuilder builder = new StringBuilder();
        boolean skipNext = false;
        for (char c : text.getString().toCharArray()) {
            if (skipNext) {skipNext = false;} else if (c == '§') {skipNext = true;} else builder.append(c);
        }
        return new TextComponent(builder.toString());
    }
}
