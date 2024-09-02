package listeners;

import characters.*;
import dungeon.Mitra;
import dungeon.Potion;
import dungeon.Sword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MoveEnemyTimer implements ActionListener {

    ArrayList<JLabel> enemyLabels;
    ArrayList<Enemy> enemies;
    ArrayList<JLabel> wallLabels;
    JLabel characterLabel;
    ArrayList<JLabel> entryAndExitlabels;
    Characters characters;
    JPanel panelCenter;
    JPanel panelHud;
    JLabel amountGold;
    public MoveEnemyTimer(ArrayList<JLabel> enemyLabels, ArrayList<Enemy> enemies, ArrayList<JLabel> wallLabels,
                          JLabel characterLabel, ArrayList<JLabel> entryAndExitLabels, Characters character,
                          JPanel panelCenter, JPanel panelHud, JLabel amountGold) {
        this.enemyLabels = enemyLabels;
        this.enemies = enemies;
        this.wallLabels = wallLabels;
        this.characterLabel = characterLabel;
        this.characters = character;
        this.entryAndExitlabels = entryAndExitLabels;
        this.panelCenter = panelCenter;
        this.panelHud = panelHud;
        this.amountGold = amountGold;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        move();
        checkColision();

    }

    private void checkColision() {
        boolean itemUsed = false;
        int deleted = -1;
        for (int i = 0; i < enemyLabels.size(); i++) {
            if (enemyLabels.get(i).getBounds().intersects(characterLabel.getBounds())){
                for (int j = 0; j < characters.getInventory().size(); j++) {
                    if (characters instanceof Warrior && characters.getInventory().get(j) instanceof Sword){
                        deleted = j;
                        panelCenter.remove(enemyLabels.get(i));
                        itemUsed = true;
                    } else if (characters instanceof Mage && characters.getInventory().get(j) instanceof Potion) {
                        deleted = j;
                        characterLabel.setLocation(100,64);
                        characters.setLife(characters.getLife() + 1);
                        updateHp();
                        itemUsed = true;
                    } else if (characters instanceof Priest && characters.getInventory().get(j) instanceof Mitra) {
                        deleted = j;
                        characterLabel.setLocation(100,64);
                        itemUsed = true;
                    }
                }
                if (deleted != -1){
                    characters.getInventory().remove(deleted);

                }
                if (!itemUsed){
                    characters.setLife(characters.getLife() - 1);
                    characterLabel.setLocation(100,64);
                    if (characters.getLife() != 0){
                        updateHp();
                    } else {
                        panelCenter.removeAll();
                        panelHud.removeAll();
                        panelHud.repaint();
                        JLabel message = new JLabel("Vaya, has");
                        message.setSize(330,200);
                        Font font = new Font("Comic Sans MS", Font.PLAIN, 70);
                        message.setFont(font);
                        message.setLocation(panelCenter.getWidth()/2 - message.getWidth()/2, panelCenter.getHeight()/2 - 200);
                        message.setVisible(true);

                        JLabel message2 = new JLabel("perdido");
                        message2.setFont(font);
                        message2.setSize(250,100);
                        message2.setLocation(panelCenter.getWidth()/2 - message2.getWidth()/2, panelCenter.getHeight()/2 - 70);
                        message2.setVisible(true);

                        JButton exit = new JButton();
                        exit.setSize(200,50);
                        exit.setLocation(panelCenter.getWidth()/2 - exit.getWidth()/2 - 10, 350);
                        ImageIcon imageIcon = new ImageIcon("src/images/menu/exitButton.png");
                        Icon icon = new ImageIcon(
                                imageIcon.getImage().getScaledInstance(exit.getWidth(), exit.getHeight(), Image.SCALE_DEFAULT)
                        );
                        exit.setIcon(icon);;
                        exit.setContentAreaFilled(false);
                        exit.setFocusPainted(false);
                        exit.setBorderPainted(false);
                        exit.addMouseListener(new ExitButtonListener(characters.getNombre(),characters.getGold(), false));

                        panelCenter.add(message);
                        panelCenter.add(message2);
                        panelCenter.add(exit);
                        panelCenter.repaint();
                    }

                }
            }
        }

    }

    private void updateHp() {
        panelHud.removeAll();
        panelHud.repaint();

        int x = 140;
        for (int i = 0; i < characters.getLife(); i++) {
            showHP(x, panelHud);
            x += 60;
        }
        panelHud.repaint();
        showGold();
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
        amountGold.setFont(new Font("Comic Sans MS", Font.PLAIN, 45));
        amountGold.setText(String.valueOf(characters.getGold()));
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

    private void move() {

        int x, y;
        boolean valid;
        for (int i = 0; i < enemyLabels.size(); i++) {
            x = enemyLabels.get(i).getX();
            y = enemyLabels.get(i).getY();

            if (enemies.get(i).getDirection().equals("left")) {
                valid = checkNextPosition(x - 5, y, enemyLabels.get(i));
                if (valid) {
                    enemyLabels.get(i).setLocation(x - enemies.get(i).getSpeed(), y);
                } else {
                    Icon goRight = new ImageIcon(
                            enemies.get(i).getGo_right().getImage().getScaledInstance(enemyLabels.get(i).getWidth(), enemyLabels.get(i).getHeight(), Image.SCALE_DEFAULT)
                    );
                    enemyLabels.get(i).setIcon(goRight);
                    enemyLabels.get(i).setLocation(x + 5, y);
                    enemies.get(i).setDirection("right");
                }

            } else if (enemies.get(i).getDirection().equals("right")) {
                valid = checkNextPosition(x + 5, y, enemyLabels.get(i));
                if (valid) {
                    enemyLabels.get(i).setLocation(x + enemies.get(i).getSpeed(), y);
                } else {
                    Icon goLeft = new ImageIcon(
                            enemies.get(i).getGo_left().getImage().getScaledInstance(enemyLabels.get(i).getWidth(), enemyLabels.get(i).getHeight(), Image.SCALE_DEFAULT)
                    );
                    enemyLabels.get(i).setIcon(goLeft);
                    enemyLabels.get(i).setLocation(x - 5, y);
                    enemies.get(i).setDirection("left");
                }
            } else if (enemies.get(i).getDirection().equals("down")) {
                valid = checkNextPosition(x, y + 5, enemyLabels.get(i));
                if (valid) {
                    enemyLabels.get(i).setLocation(x , y + enemies.get(i).getSpeed());
                } else {
                    Icon goUp = new ImageIcon(
                            enemies.get(i).getGo_up().getImage().getScaledInstance(enemyLabels.get(i).getWidth(), enemyLabels.get(i).getHeight(), Image.SCALE_DEFAULT)
                    );
                    enemyLabels.get(i).setIcon(goUp);
                    enemyLabels.get(i).setLocation(x, y - 5);
                    enemies.get(i).setDirection("up");
                }
            } else {
                valid = checkNextPosition(x, y - 5, enemyLabels.get(i));
                if (valid) {
                    enemyLabels.get(i).setLocation(x , y - enemies.get(i).getSpeed());
                } else {
                    Icon goDown = new ImageIcon(
                            enemies.get(i).getGo_down().getImage().getScaledInstance(enemyLabels.get(i).getWidth(), enemyLabels.get(i).getHeight(), Image.SCALE_DEFAULT)
                    );
                    enemyLabels.get(i).setIcon(goDown);
                    enemyLabels.get(i).setLocation(x, y - 5);
                    enemies.get(i).setDirection("down");
                }
            }
        }
    }

    private boolean checkNextPosition(int x, int y, JLabel enemy) {
        boolean validPosition = true;
        enemy.setLocation(x,y);
        for (int i = 0; i < wallLabels.size() && validPosition; i++) {
            if (wallLabels.get(i).getBounds().intersects(enemy.getBounds())){
                validPosition = false;
            }
        }
        for (int i = 0; i < entryAndExitlabels.size() && validPosition; i++) {
            if (entryAndExitlabels.get(i).getBounds().intersects(enemy.getBounds())){
                validPosition = false;
            }
        }
        return validPosition;
    }
}
