package com.korotovsky.client.gui;

import com.korotovsky.client.gui.events.WindowMonitor;
import com.korotovsky.client.gui.renderer.PlayerCellRenderer;
import com.korotovsky.client.model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Logger;

public class MainWindow extends JFrame implements ActionListener {
    private Logger logger;
    private HashMap<String, JComponent> components;

    public MainWindow(Logger logger) {
        super("Pusher GUI");

        this.components = new HashMap<String, JComponent>();
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

        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));
        /**
         * Menu bar
         */
        setJMenuBar(buildMenuBar());

        /**
         * Auth status
         */
        JTextField textField = new JTextField("Not auth");
        textField.setEditable(false);

        content.add(textField);
        components.put("authTextField", textField);

        /**
         * Right list
         */
        Player player1 = new Player();
        player1.setName("1234");
        Player player2 = new Player();
        player2.setName("foobar");

        DefaultListModel<Player> listModel = new DefaultListModel<Player>();
        listModel.addElement(player1);
        listModel.addElement(player2);

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
}
