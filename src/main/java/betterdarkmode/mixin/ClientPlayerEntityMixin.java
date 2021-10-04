package betterdarkmode.mixin;

import betterdarkmode.Configs;
import betterdarkmode.command.FakeCommandSource;
import betterdarkmode.util.Util;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    /*
    Code referred from EarthComputer's ClientCommands
     */
    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void onChatMessage(String message, CallbackInfo ci) {
        if (message.startsWith("/")){
            StringReader reader = new StringReader(message);
            reader.skip();
            int cursor = reader.getCursor();
            String commandName = reader.canRead() ? reader.readUnquotedString() : "";
            reader.setCursor(cursor);
            if (Configs.COMMAND_NAME.equals(commandName)){
                try {
                    Util.getPlayer().networkHandler.getCommandDispatcher().execute(reader, new FakeCommandSource(Util.getPlayer()));
                    ci.cancel();
                } catch (CommandSyntaxException e) {
                    Util.sendMessage(new LiteralText(e.getMessage()).formatted(Formatting.RED));
                }
            }
        }
    }
}
