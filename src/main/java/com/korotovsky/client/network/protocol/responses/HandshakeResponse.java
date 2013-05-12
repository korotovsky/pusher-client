package com.korotovsky.client.network.protocol.responses;

import com.korotovsky.client.core.Player;
import com.korotovsky.client.network.protocol.Response;

import java.io.BufferedWriter;

public class HandshakeResponse extends Response {
    protected String command = "helo:" + Player.CLIENT_VERSION + ":123456";

    public HandshakeResponse(BufferedWriter writer) {
        super(writer);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
