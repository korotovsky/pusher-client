package com.korotovsky.client.network;

import com.korotovsky.client.core.Player;

import java.io.BufferedReader;

public class Request {
    private static String IS_MESSAGE = "message";
    private static String STATUS_OK = "ok";
    private static String SUCCESS_HANDSHAKE_SIGNAL = "Successful handshake";
    private static String PLAYER_READY_SIGNAL = "Ready";

    private String line;
    private String[] parts;
    private BufferedReader reader;
    private Player player;


    public Request(Player player, BufferedReader reader) {
        this.reader = reader;
        this.player = player;
    }

    public void dispatch() throws Throwable {
        line = reader.readLine();
        if (line == null) {
            return;
        }

        parts = parseResponse();

        if (isMessageOK(SUCCESS_HANDSHAKE_SIGNAL)) {
            player.onSuccessfulHandshake(this);
        } else if (isMessageOK(PLAYER_READY_SIGNAL)) {
            player.onPlayerReady(this);
        }
    }

    public String getLine() {
        return line;
    }

    private String[] parseResponse() {
        return line.split(":");
    }

    private Boolean isMessageOK(String expected) {
        return parts[0].equals(IS_MESSAGE)
                && parts[1].equals(STATUS_OK)
                && parts[2].equals(expected);
    }
}
