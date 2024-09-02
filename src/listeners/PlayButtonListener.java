package listeners;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayButtonListener extends MouseAdapter {

    JPanel mainPanel;
    JPanel newPanel;
    public PlayButtonListener(JPanel mainPanel, JPanel newPanel) {
        this.mainPanel = mainPanel;
        this.newPanel = newPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(newPanel);
        mainPanel.repaint();

    }
}
