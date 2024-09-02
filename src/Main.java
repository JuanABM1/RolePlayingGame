import characters.*;
import listeners.ClassButtonListener;
import listeners.FrameWindowListener;
import listeners.PlayButtonListener;

import javax.swing.*;
import java.awt.*;

public class Main {

    private JPanel mainPanel;
    private Characters character = null;

    public Main() {
        mainPanel = new JPanel();

        mainPanel.setPreferredSize(new Dimension(896,640));
        mainPanel.setSize(new Dimension(896,640));
        mainPanel.setLayout(null);
        mainPanel.setFocusable(true);
        mainPanel.add(setBackground("src/images/menu/mainBackgroung.jpeg",mainPanel.getSize()));

        JPanel selectClassMenu = new JPanel();
        selectClassMenu.setSize(mainPanel.getSize());
        selectClassMenu.setLayout(null);
        selectClassMenu.setLocation(0,0);

        showTitleAndButton(mainPanel, selectClassMenu);

        JPanel gamePanel = new JPanel();
        gamePanel.setSize(mainPanel.getSize());
        gamePanel.setLayout(null);
        gamePanel.setLocation(0,0);
        gamePanel.setBackground(Color.gray);
        showButtonsSelectClass(selectClassMenu, character, gamePanel);

        selectClassMenu.setVisible(true);
        selectClassMenu.add(setBackground("src/images/menu/chooseClassBackground.jpeg", selectClassMenu.getSize()));
    }

    private void showButtonsSelectClass(JPanel selectClassMenu, Characters character, JPanel gamePanel) {
        JButton warriorButton, mageButton, priestButton, title;
        warriorButton = new JButton();
        warriorButton.setSize(200,165);
        warriorButton.setLocation(50, 450);
        addImageToButton(warriorButton, "src/images/menu/warriorButton.png");
        hideBorderButton(warriorButton);
        selectClassMenu.add(warriorButton);
        warriorButton.addMouseListener(new ClassButtonListener(character,"Warrior", mainPanel, gamePanel));


        mageButton = new JButton();
        mageButton.setSize(200,165);
        mageButton.setLocation(330, 450);
        addImageToButton(mageButton, "src/images/menu/mageButton.png");
        hideBorderButton(mageButton);
        selectClassMenu.add(mageButton);
        mageButton.addMouseListener(new ClassButtonListener(character, "Mage", mainPanel, gamePanel));


        priestButton = new JButton();
        priestButton.setSize(200,165);
        priestButton.setLocation(630, 450);
        addImageToButton(priestButton, "src/images/menu/priestButton.png");
        hideBorderButton(priestButton);
        selectClassMenu.add(priestButton);
        priestButton.addMouseListener(new ClassButtonListener(character, "Priest", mainPanel, gamePanel));



        title = new JButton();
        title.setSize(400,150);
        title.setLocation(mainPanel.getWidth()/2 - title.getWidth()/2,30);
        hideBorderButton(title);
        addImageToButton(title, "src/images/menu/classTitle.png");
        selectClassMenu.add(title);
    }

    private void addImageToButton(JButton button, String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Icon icom = new ImageIcon(
                imageIcon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT)
        );
        button.setIcon(icom);

    }

    private void showTitleAndButton(JPanel mainPanel, JPanel selectClassMenu) {
        JButton title = new JButton();
        title.setSize(600,200);
        title.setLocation(mainPanel.getWidth()/2 - title.getWidth()/2,30);
        hideBorderButton(title);
        addImageToButton(title, "src/images/menu/title.png");

        mainPanel.add(title);
        mainPanel.setComponentZOrder(title,0);

        JButton play = new JButton();
        play.setSize(200,50);
        play.setLocation(mainPanel.getWidth()/2 - play.getWidth()/2 - 10, 500);
        addImageToButton(play, "src/images/menu/playButton.png");
        play.setContentAreaFilled(false);
        play.setFocusPainted(false);
        play.setBorderPainted(false);
        mainPanel.add(play);
        mainPanel.setComponentZOrder(play,0);

        play.addMouseListener(new PlayButtonListener(mainPanel, selectClassMenu));
    }

    private void hideBorderButton(JButton button) {
        button.setVisible(true);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    private JLabel setBackground(String path,Dimension d) {
        JLabel label = new JLabel();
        label.setSize(d);
        label.setLocation(0,0);

        ImageIcon imageIcon = new ImageIcon(path);
        Icon background = new ImageIcon(
                imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)
        );
        label.setIcon(background);

        return label;
    }

    public static void main(String[] args) {

        // inicializa el frame
        JFrame window = new JFrame();
        window.setContentPane(new Main().mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setTitle("RPG Game");
        window.setVisible(true);


        // asigna valores especiales como ubicacion o logo
        window.setLocationRelativeTo(null);

        Toolkit screen = Toolkit.getDefaultToolkit();

        Image logo = screen.getImage("src/images/politecnics.png");
        window.setIconImage(logo);

        window.addWindowListener(new FrameWindowListener(window));

    }
}