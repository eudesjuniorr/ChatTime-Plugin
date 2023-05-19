package org.example;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    @Listener
    public void onChatMessage(MessageChannelEvent.Chat event) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String timestamp = dateFormat.format(now);
        String message = event.getRawMessage().toPlain();

        Text newMessage = Text.builder()
                .append(Text.of("[" + timestamp + "] "))
                .append(Text.of(message))
                .build();

        event.setMessage(newMessage);
    }
}


