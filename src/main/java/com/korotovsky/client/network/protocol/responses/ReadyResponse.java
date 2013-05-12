package com.korotovsky.client.network.protocol.responses;

import com.korotovsky.client.core.Player;
import com.korotovsky.client.network.protocol.Response;

import java.io.BufferedWriter;

public class ReadyResponse extends Response {
    protected String command = "ready";

    public ReadyResponse(BufferedWriter writer) {
        super(writer);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
