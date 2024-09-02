package listeners;

import characters.*;
import dungeon.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class ClassButtonListener extends MouseAdapter {

    Characters character;
    String clase;
    JPanel mainPanel;
    JPanel gamePanel;
    JPanel panelCenter;
    JPanel panelHud;
    JLabel characterLabel;
    JLabel amountGold;
    ArrayList<JLabel> wallLabels = new ArrayList<>();
    ArrayList<JLabel> floorLabels = new ArrayList<>();
    ArrayList<JLabel> entryAndExitLabels = new ArrayList<>();
    ArrayList<JLabel> enemyLabels = new ArrayList<>();
    ArrayList<JLabel> itemsLabels = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Items> items = new ArrayList<>();

    public ClassButtonListener(Characters character, String clase, JPanel mainPanel, JPanel gamePanel) {
        this.character = character;
        this.clase = clase;
        this.mainPanel = mainPanel;
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        String text = "Introduce the name of your " + clase;
        String name = JOptionPane.showInputDialog(null, text);
        if (name != null && !name.isEmpty()){
            if (this.clase.equals("Warrior")){
                character = new Warrior(name);
            } else if (this.clase.equals("Mage")) {
                character = new Mage(name);
            }else {
                character = new Priest(name);
            }

            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.add(gamePanel);
            mainPanel.repaint();

            showHud();
            createAndShowMap();
            createAndShowEnemies();
            createAndShowItems();

            Timer enemyMovementTimer = new Timer(50, new MoveEnemyTimer(enemyLabels, enemies, wallLabels,
                    characterLabel, entryAndExitLabels, character, panelCenter, panelHud, amountGold));
            enemyMovementTimer.start();

        }
    }

    private void createAndShowItems() {
        JLabel itemLabel;
        Items item;
        for (int i = 0; i < 4; i++) {
            itemLabel = new JLabel();
            if (i == 0){
                item = new Potion();
            } else if (i == 1) {
                item = new Mitra();
            } else if (i == 2) {
                item = new Sword();
            }else {
                item = new Gold();
            }
            itemLabel.setSize(32,32);
            itemLabel.setLocation((int) (Math.random()*(750-75+1) + 75 ),(int) (Math.random()*(430-80+1) + 80));
            itemLabel.setVisible(true);
            itemLabel.setIcon(item.getPicture());

            panelCenter.add(itemLabel);
            panelCenter.setComponentZOrder(itemLabel,0);

            itemsLabels.add(itemLabel);
            items.add(item);
        }
    }

    private void createAndShowEnemies() {
        JLabel enemyLabel;
        for (int i = 0; i < 4; i++) {
            enemyLabel = new JLabel();
            Enemy enemy = new Enemy();
            enemyLabel.setSize(64,64);
            enemyLabel.setLocation((int) (Math.random()*(750-75+1) + 75 ),(int) (Math.random()*(430-80+1) + 80));
            enemyLabel.setVisible(true);
            if (i == 0 || i == 2){
                ImageIcon imageIcon = enemy.getGo_down();
                Icon goDown = new ImageIcon(
                        imageIcon.getImage().getScaledInstance(enemyLabel.getWidth(), enemyLabel.getHeight(), Image.SCALE_DEFAULT)
                );
                enemyLabel.setIcon(goDown);
                enemy.setDirection("down");
            } else {
                ImageIcon imageIcon = enemy.getGo_left();
                Icon goLeft = new ImageIcon(
                        imageIcon.getImage().getScaledInstance(enemyLabel.getWidth(), enemyLabel.getHeight(), Image.SCALE_DEFAULT)
                );
                enemyLabel.setIcon(goLeft);
                enemy.setDirection("left");
            }

            panelCenter.add(enemyLabel);
            panelCenter.setComponentZOrder(enemyLabel,0);

            enemyLabels.add(enemyLabel);
            enemies.add(enemy);
        }
    }

    private void createAndShowMap() {
        int[][] map = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int x = 32;
        int y = 32;
        JLabel bloque;
        ImageIcon imagen;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 26; j++) {
                bloque = new JLabel();
                bloque.setSize(32,32);
                bloque.setLocation(x + (j * bloque.getWidth()), y + (i * bloque.getHeight()));
                if (map[i][j] == 1) {
                    imagen = new ImageIcon("src/images/dungeon/tile004.png");
                    Icon icon = new ImageIcon(
                            imagen.getImage().getScaledInstance(bloque.getWidth(), bloque.getHeight(), Image.SCALE_DEFAULT)
                    );
                    bloque.setIcon(icon);
                    wallLabels.add(bloque);
                } else if (map[i][j] == 2) {
                    imagen = new ImageIcon("src/images/dungeon/tile002.png");
                    Icon icon = new ImageIcon(
                            imagen.getImage().getScaledInstance(bloque.getWidth(), bloque.getHeight(), Image.SCALE_DEFAULT)
                    );
                    bloque.setIcon(icon);
                    entryAndExitLabels.add(bloque);

                } else {
                    imagen = new ImageIcon("src/images/dungeon/tile001.png");
                    Icon icon = new ImageIcon(
                            imagen.getImage().getScaledInstance(bloque.getWidth(), bloque.getHeight(), Image.SCALE_DEFAULT)
                    );
                    bloque.setIcon(icon);
                    floorLabels.add(bloque);
                }
                panelCenter.add(bloque);
                panelCenter.setComponentZOrder(bloque, 0);
                panelCenter.setComponentZOrder(characterLabel, 0);

            }
        }


    }



    private void showHud() {
        panelHud = new JPanel();
        gamePanel.add(panelHud);
        panelHud.setLayout(null);
        panelHud.setBackground(Color.gray);
        panelHud.setSize(new Dimension(gamePanel.getWidth(), 64));
        panelHud.setLocation(mainPanel.getWidth()/2 - panelHud.getWidth()/2, mainPanel.getY());


        int x = 140;
        for (int i = 0; i < character.getLife(); i++) {
            showHP(x, panelHud);
            x += 60;
        }
        showGold();
        showPanelCenter(panelHud);
    }

    private void showGold() {
        JLabel goldPicture = new JLabel();
        goldPicture.setSize(50,60);

        ImageIcon imageIcon =  new ImageIcon("src/images/dungeon/dollar.png");
        Icon picture = new ImageIcon(
                imageIcon.getImage().getScaledInstance(goldPicture.getWidth(),goldPicture.getHeight(), Image.SCALE_DEFAULT)
        );
        goldPicture.setIcon(picture);
        goldPicture.setLocation(600,panelHud.getY());
        goldPicture.setVisible(true);

        amountGold = new JLabel();
        amountGold.setSize(50,50);
        amountGold.setText("0");
        amountGold.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        amountGold.setLocation(670,panelHud.getY());
        amountGold.setVisible(true);

        panelHud.add(goldPicture);
        panelHud.add(amountGold);
        panelHud.revalidate();
        panelHud.repaint();
    }

    private void showHP(int x, JPanel panelHud) {
        JLabel labelHP = new JLabel();
        labelHP.setSize(50, 60);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/heart.png");
        Icon heart = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelHP.getWidth(), labelHP.getHeight(), Image.SCALE_DEFAULT)
        );
        labelHP.setIcon(heart);
        labelHP.setLocation(x , panelHud.getY());

        panelHud.add(labelHP);
    }

    private void showPanelCenter(JPanel panelHud) {
        panelCenter = new JPanel();
        panelCenter.setSize(mainPanel.getWidth(), gamePanel.getHeight() - panelHud.getHeight());
        panelCenter.setLayout(null);
        panelCenter.setLocation(0,panelHud.getHeight());
        panelCenter.setBackground(Color.gray);
        mainPanel.setFocusable(true);
        gamePanel.add(panelCenter);


        showCharacter(panelCenter);
        mainPanel.addKeyListener(new MoveCharacterListener(character, characterLabel, wallLabels, entryAndExitLabels,
                itemsLabels, items, panelCenter, amountGold));


    }



    private void showCharacter(JPanel panelCenter) {
        characterLabel = new JLabel();
        characterLabel.setSize(64, 64);
        characterLabel.setLocation(100, 64);
        characterLabel.setVisible(true);
        ImageIcon imageIcon = character.getGo_down();
        Icon goDown = new ImageIcon(
                imageIcon.getImage().getScaledInstance(characterLabel.getWidth(), characterLabel.getHeight(), Image.SCALE_DEFAULT)
        );
        characterLabel.setIcon(goDown);

        panelCenter.add(characterLabel);

    }


}
