package com.clarnc.infobot;

import org.javacord.api.*;

public class Main {
    public static DiscordApi app = new DiscordApiBuilder()
            .setToken(Tokens.getBotToken())
            .login().join();

    public static void main(String[] args) {
        String status = "Servers : " + app.getServers().size();
        app.updateActivity(status);
        Commands command = new Commands();
        app.addMessageCreateListener(command::setCommand);
    }
}
