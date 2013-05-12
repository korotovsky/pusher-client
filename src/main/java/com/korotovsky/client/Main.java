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

    public void run() {
        /*System.out.println("Welcome to Client side");

        Socket fromserver = null;

        if (args.length == 0) {
            System.out.println("use: client hostname");
            System.exit(-1);
        }

        System.out.println("Connecting to... " + args[0]);

        fromserver = new Socket(args[0], 10000);
        BufferedReader in = new
                BufferedReader(new
                InputStreamReader(fromserver.getInputStream()));
        PrintWriter out = new
                PrintWriter(fromserver.getOutputStream(), true);
        BufferedReader inu = new
                BufferedReader(new InputStreamReader(System.in));

        String fuser, fserver;

        while ((fuser = inu.readLine()) != null) {
            out.println(fuser);
            fserver = in.readLine();
            System.out.println(fserver);
            if (fuser.equalsIgnoreCase("close")) break;
            if (fuser.equalsIgnoreCase("exit")) break;
        }

        out.close();
        in.close();
        inu.close();
        fromserver.close();*/
    }


}

