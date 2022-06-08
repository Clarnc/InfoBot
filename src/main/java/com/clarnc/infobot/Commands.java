package com.clarnc.infobot;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Commands {


    public void setCommand(MessageCreateEvent event) {
        String command = event.getMessageContent();
        long channelid = event.getChannel().getId();
        Map<String, Runnable> map = new HashMap<>();
        String[] cmd = command.split(" ");
        map.put("c!weather", () -> {
            String secondarg = "";
            if (cmd.length > 1) secondarg = cmd[1];
            try {
                String weather = Weather.getWeather(secondarg);
                Main.app.getTextChannelById(channelid).get().sendMessage(weather);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        map.put("c!currency",() ->{
            String ammount = null;
        if(cmd.length<3) Main.app.getTextChannelById(channelid).get().sendMessage("Missing arguments");else {
            try {
                ammount = CurrencyConvertor.getCurrency(cmd);
                Main.app.getTextChannelById(channelid).get().sendMessage(ammount);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }



        });
        map.put("c!avatar",()->{
            if(!(cmd.length>1)) {
                MessageAuthor user = event.getMessageAuthor();
                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle(user.getName())
                        .setImage(user.getAvatar())
                        .setColor(Color.green);
                Main.app.getTextChannelById(channelid).get().sendMessage(embedBuilder);
            }else {
                try {
                    String userbuiler1 = cmd[1].substring(2);
                    String userbuiler2 = userbuiler1.substring(0,userbuiler1.length()-1);
                    User user = Main.app.getUserById(userbuiler2).get();
                    EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setTitle(user.getName())
                            .setImage(user.getAvatar())
                            .setColor(Color.green);
                    Main.app.getTextChannelById(channelid).get().sendMessage(embedBuilder);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        completecmd(cmd, map);
    }

    private void completecmd(String[] cmd, Map<String, Runnable> map) {
        if (map.containsKey(cmd[0])) {
            map.get(cmd[0]).run();
        }


    }
}



