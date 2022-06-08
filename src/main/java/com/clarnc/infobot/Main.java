package com.clarnc.infobot;

import org.javacord.api.*;
import com.clarnc.apis.tokens;



public class Main {
    public static DiscordApi app = new DiscordApiBuilder()
            .setToken(tokens.getBotToken())
            .login().join();

    public static void main(String[] args) {
        String status = "Servers : " +app.getServers().size();
        app.updateActivity(status);
        Commands command = new Commands();
        app.addMessageCreateListener(event -> {
            command.setCommand(event);

        });



    }


}