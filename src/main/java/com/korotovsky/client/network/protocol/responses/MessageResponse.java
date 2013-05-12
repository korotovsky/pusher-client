package com.korotovsky.client.network.protocol.responses;

import com.korotovsky.client.network.protocol.*;

import java.io.BufferedWriter;

public class MessageResponse extends Response {
    protected String command = "Unknown message";

    public MessageResponse(BufferedWriter writer) {
        super(writer);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
