package com.korotovsky.client.gui.renderer;

import com.korotovsky.client.model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerCellRenderer extends JLabel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Player entry = (Player) value;
        setText(entry.getName());

        return this;
    }
}
