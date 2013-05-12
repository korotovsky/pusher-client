package com.korotovsky.client;

import com.korotovsky.client.core.Player;

import javax.swing.*;
import java.io.*;
import java.util.logging.Logger;

/**
 *
 */
public class Main {
    public static void main(final String[] args) {
        final Logger logger = Logger.getLogger("Pusher-GUI");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Player(logger, args);
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            }
        });

    }
}

