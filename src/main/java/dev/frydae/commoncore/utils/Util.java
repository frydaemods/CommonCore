package dev.frydae.commoncore.utils;

import com.google.errorprone.annotations.FormatString;
import dev.frydae.commoncore.CommonCore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;

public final class Util {
    /**
     * Converts {@literal &} characters in a string into section signs
     * This conversion acts as a bypass to the new {@link net.minecraft.util.Formatting} system for message.
     * <p></p>
     * While using this method, an ampersand can be included in the final product by escaping it with a backslash.
     * <p></p>
     *
     * <pre>{@code
     * "§aThis is &a message" = color("&aThis is \&a message")
     * }</pre>
     *
     * @param message
     * @return
     */
    public static String color(String message) {
        Matcher matcher = Patterns.CHAT_COLOR_CODES.matcher(message);

        message = matcher.replaceAll("\u00A7");;
        message = message.replaceAll("\\\\&", "&");

        return message;
    }

    public static String formatMessage(@FormatString String message, Object... replacements) {
        return String.format(message, replacements);
    }

    public static void sendMessage(@NotNull ServerPlayerEntity player, @FormatString String message, Object... replacements) {
        message = color(message);
        message = formatMessage(message, replacements);

        player.sendMessage(Text.literal(message));
    }

    public static void sendMessage(@NotNull ServerPlayerEntity player, List<String> lines) {
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            builder.append(line).append("\n");
        }

        sendMessage(player, builder.toString());
    }

    public static void broadcastMessage(String message, Object... replacements) {
        String newMessage = formatMessage(message, replacements);

        broadcastMessage(Text.literal(newMessage));
    }

    public static void broadcastMessage(Text text) {
        CommonCore.getSingleton().getServer().getPlayerManager().getPlayerList().forEach(player -> player.sendMessage(text));
    }
}
