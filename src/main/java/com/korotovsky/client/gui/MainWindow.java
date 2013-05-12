package com.korotovsky.client.gui;

import com.korotovsky.client.gui.events.WindowMonitor;
import com.korotovsky.client.gui.renderer.PlayerCellRenderer;
import com.korotovsky.client.core.Player;
import com.korotovsky.client.model.PlayerModel;
import com.korotovsky.client.network.protocol.responses.HandshakeResponse;
import com.korotovsky.client.network.protocol.responses.ReadyResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Logger;

public class MainWindow extends JFrame implements ActionListener {
    private Logger logger;
    private Player player;
    private HashMap<String, JComponent> components;

    public MainWindow(Player player, Logger logger) {
        super("Pusher GUI");

        this.components = new HashMap<String, JComponent>();
        this.player = player;
        this.logger = logger;

        setLookAndFeel();
        addWindowListener(new WindowMonitor());

        init();
    }

    public HashMap<String, JComponent> getRegisteredComponents() {
        return components;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println(event.getActionCommand());
    }

    private void init() {
        setSize(640, 480);

        JPanel content = (JPanel)getContentPane();

        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        setJMenuBar(buildMenuBar());
        setAuthWidgets();

        /**
         * Right list
         */
        PlayerModel playerModel1 = new PlayerModel();
        playerModel1.setName("1234");
        PlayerModel playerModel2 = new PlayerModel();
        playerModel2.setName("foobar");

        DefaultListModel<PlayerModel> listModel = new DefaultListModel<PlayerModel>();
        listModel.addElement(playerModel1);
        listModel.addElement(playerModel2);

        JList playerJList = new JList(listModel);
        playerJList.setFixedCellWidth(100);
        playerJList.setCellRenderer(new PlayerCellRenderer());
        content.add(playerJList);

        /**
         * Button
         */
        content.add(new JButton("Push!"));
    }

    private void setLookAndFeel() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            logger.warning(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.warning(e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            logger.warning(e.getMessage());
        } catch (InstantiationException e) {
            logger.warning(e.getMessage());
        }
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Exit");

        menu.add(menuItem);
        menuBar.add(menu);

        return menuBar;
    }

    private void setAuthWidgets() {
        JPanel content = (JPanel)getContentPane();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JTextField textField1 = new JTextField();
        textField1.setColumns(10);
        panel1.add(textField1);
        components.put("userName", textField1);

        JButton button1 = new JButton("Set name");
        components.put("button1", button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JTextField textField = (JTextField)components.get("userName");
                player.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new HandshakeResponse(player.getWriter()).setName(textField.getText()).send();
                        } catch (Throwable e) {
                            logger.warning(e.getMessage());
                        }
                    }
                });
            }
        });
        panel1.add(button1);

        JButton button2 = new JButton("Set ready");
        button2.setEnabled(false);
        components.put("button2", button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new ReadyResponse(player.getWriter()).send();
                        } catch (Throwable e) {
                            logger.warning(e.getMessage());
                        }
                    }
                });
            }
        });
        panel1.add(button1);
        panel2.add(button2);

        panel.add(panel1);
        panel.add(panel2);

        content.add(panel);
    }
}
