package listeners;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameWindowListener extends WindowAdapter {

    JFrame window;


    public FrameWindowListener(JFrame window) {
        this.window = window;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to close the game?",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION){
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }else {
            window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }
}
