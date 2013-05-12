package com.korotovsky.client.network.protocol;

import java.io.BufferedWriter;
import java.io.IOException;

public class Response {
    protected String command = null;

    protected BufferedWriter writer;

    public Response(BufferedWriter writer) {
        this.writer = writer;
    }

    public void send() throws IOException {
        writer.write(getCommand());
        writer.newLine();
        writer.flush();
    }

    public String getCommand() {
        return command;
    }
}
