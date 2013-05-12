package com.korotovsky.client.core;

import com.korotovsky.client.events.PlayerEvents;
import com.korotovsky.client.gui.MainWindow;
import com.korotovsky.client.network.Request;
import com.korotovsky.client.network.protocol.responses.HandshakeResponse;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Player implements PlayerEvents {
    public static Integer CLIENT_VERSION = 1;

    private ExecutorService workers = Executors.newCachedThreadPool();
    private BufferedReader reader;
    private BufferedWriter writer;
    private MainWindow window;
    private Logger logger;

    public Player(Logger logger, String[] args) throws IOException {
        this.logger = logger;

        init(args);
    }

    public void init(String[] args) throws IOException {
        window = new MainWindow(logger);
        window.setVisible(true);

        Socket socket = new Socket("127.0.0.1", 10001);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        workers.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        read();
                    } catch (Throwable e) {
                        logger.info(e.getMessage());
                        break;
                    }
                }
            }
        });
        workers.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    new HandshakeResponse(writer).send();
                } catch (Throwable e) {
                    logger.warning(e.getMessage());
                }
            }
        });
    }

    private void read() throws Throwable {
        Request request = new Request(this, reader);

        request.dispatch();
    }

    @Override
    public void onPlayerConnected(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPlayerDisconnected(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onGameStarted(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onGameEnded(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onWinnerReceived(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSuccessfulHandshake(Request request) {
        JTextField textField = (JTextField)window.getRegisteredComponents().get("authTextField");

        textField.setText("Successful auth");
    }
}
