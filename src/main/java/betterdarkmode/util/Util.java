package betterdarkmode.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class Util {
    public static int getRGB(int r, int g, int b) {
        return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
    }

    public static ITextComponent getCleanText(ITextComponent text) {
        StringBuilder builder = new StringBuilder();
        boolean skipNext = false;
        for (char c : text.getString().toCharArray()) {
            if (skipNext) {skipNext = false;} else if (c == '§') {skipNext = true;} else builder.append(c);
        }
        return new StringTextComponent(builder.toString());
    }
}
