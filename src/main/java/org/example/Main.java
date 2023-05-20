package org.example;

import java.util.TimeZone;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Date;

    @Plugin(id = "chattime_plugin", name = "a plugin that adds a timestamp to chat messages", version = "1.0.0")
    public class Main {

    @Listener // to player's messages
    public void onChatMessage(MessageChannelEvent.Chat event) {
        Date now = new Date();

        TimeZone timeZone = TimeZone.getTimeZone("GMT-3");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(timeZone);

        String timestamp = dateFormat.format(now);
        String message = event.getRawMessage().toPlain();

        Player player = event.getCause().first(Player.class).orElse(null);
        String playerName = player != null ? player.getName() : "Unknown";

        Text separator = Text.of(":"); // add two points after player name

        Text newMessage = Text.builder()
                .append(Text.of("[" + timestamp + "] "))
                .append(Text.of(playerName))
                .append(separator) // add separator
                .append(Text.of(" " + message))
                .build();

        event.setCancelled(true);
        Sponge.getServer().getBroadcastChannel().send(newMessage);
    }
    @Listener // message when player joins
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        Player player = event.getTargetEntity();

        Text joinMessage = Text.of("Bakosos ChatTime v1.1 carregado"); // message

        player.sendMessage(joinMessage);
    }
}




