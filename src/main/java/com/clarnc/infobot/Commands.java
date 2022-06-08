package com.clarnc.infobot;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Commands {


    public void setCommand(MessageCreateEvent event) {
        String command = event.getMessageContent();

        long channelId = event.getChannel().getId();
        Map<String, Runnable> map = new HashMap<>();
        String[] args = command.split(" ");
        map.put("c!weather", () -> {
            String query = "";
            if (args.length > 1) query = args[1];
            try {
                String weather = Weather.getWeather(query);
                Main.app.getTextChannelById(channelId).get().sendMessage(weather);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        map.put("c!currency", () -> {
            if (args.length < 3) {
                Main.app.getTextChannelById(channelId).get().sendMessage("Missing arguments");
            } else {
                try {
                    String amount = CurrencyConverter.getCurrency(args);
                    Main.app.getTextChannelById(channelId).get().sendMessage(amount);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        map.put("c!avatar", () -> {
            var user = args.length > 1 ?
                    event.getMessageAuthor().asUser().get() :
                    event.getMessage().getMentionedUsers().get(0);

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(user.getName())
                    .setImage(user.getAvatar())
                    .setColor(Color.green);
            Main.app.getTextChannelById(channelId).get().sendMessage(embedBuilder);
        });
        runCommand(args, map);
    }

    private void runCommand(String[] cmd, Map<String, Runnable> map) {
        if (map.containsKey(cmd[0])) {
            map.get(cmd[0]).run();
        }
    }
}



