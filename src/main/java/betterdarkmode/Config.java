package betterdarkmode;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import static net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class Config {
    public static class Client {
        public IntValue selectedColor;

        Client(ForgeConfigSpec.Builder builder){
            builder.comment("BetterDarkMode selector color for labels")
                    .push("general");

            selectedColor = builder
                    .comment("The color to be used for lables in game")
                    .defineInRange("selectedColor", Constants.DEFAULT_WHITE, 0, 16777215);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;
    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }
}
