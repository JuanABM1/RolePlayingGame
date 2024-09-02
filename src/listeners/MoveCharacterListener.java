package listeners;

import characters.Characters;
import dungeon.Gold;
import dungeon.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MoveCharacterListener extends KeyAdapter {

    JLabel characterLabel;
    Characters character;
    String direction = "down";
    ArrayList<JLabel> wallLabels;
    ArrayList<JLabel> entryAndExitlabels;
    ArrayList<JLabel> itemLabels;
    ArrayList<Items> items;
    JLabel amountGold;
    JPanel panelCenter;
    public MoveCharacterListener(Characters character, JLabel characterLabel, ArrayList<JLabel> wallLabels,
                                 ArrayList<JLabel> entryAndExitLabels, ArrayList<JLabel> itemsLabels,
                                 ArrayList<Items> items, JPanel panelCenter, JLabel amountGold) {
        this.characterLabel = characterLabel;
        this.character = character;
        this.wallLabels = wallLabels;
        this.entryAndExitlabels = entryAndExitLabels;
        this.itemLabels = itemsLabels;
        this.items = items;
        this.panelCenter = panelCenter;
        this.amountGold = amountGold;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int x = characterLabel.getX();
        int y = characterLabel.getY();
        boolean validPosition;


        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT -> {
                if (!direction.equals("right")){
                    Icon goRight = new ImageIcon(
                            character.getGo_right().getImage().getScaledInstance(characterLabel.getWidth(), characterLabel.getHeight(), Image.SCALE_DEFAULT)
                    );
                    characterLabel.setIcon(goRight);
                }
                x += character.getSpeed();
                direction = "right";
            }
            case KeyEvent.VK_LEFT -> {
                if (!direction.equals("left")){
                    Icon goLeft = new ImageIcon(
                            character.getGo_left().getImage().getScaledInstance(characterLabel.getWidth(), characterLabel.getHeight(), Image.SCALE_DEFAULT)
                    );
                    characterLabel.setIcon(goLeft);
                }

                direction = "left";
                x -= character.getSpeed();
            }
            case KeyEvent.VK_DOWN -> {
                if (!direction.equals("down")){
                    Icon goDown = new ImageIcon(
                            character.getGo_down().getImage().getScaledInstance(characterLabel.getWidth(), characterLabel.getHeight(), Image.SCALE_DEFAULT)
                    );
                    characterLabel.setIcon(goDown);
                }
                direction = "down";
                y += character.getSpeed();
            }
            case KeyEvent.VK_UP -> {
                if (!direction.equals("up")){
                    Icon goUp = new ImageIcon(
                            character.getGo_up().getImage().getScaledInstance(characterLabel.getWidth(), characterLabel.getHeight(), Image.SCALE_DEFAULT)
                    );
                    characterLabel.setIcon(goUp);
                }
                direction = "up";
                y -= character.getSpeed();
            }
        }
        int actual_x = characterLabel.getX();
        int actual_y = characterLabel.getY();
        validPosition = checkNextPosition(x,y);
        if (!validPosition){
            characterLabel.setLocation(actual_x,actual_y);
        }
        takeItem();
    }

    private void takeItem() {
        int itemToTake = -1;
        for (int i = 0; i < itemLabels.size(); i++) {
            if (characterLabel.getBounds().intersects(itemLabels.get(i).getBounds())){
                if (items.get(i) instanceof Gold) {
                    character.setGold(character.getGold() + 10);
                    itemLabels.get(i).setLocation((int) (Math.random()*(750-75+1) + 75 ),(int) (Math.random()*(430-80+1) + 80));
                    amountGold.setText(String.valueOf(character.getGold()));

                } else {
                    itemToTake = i;

                }
            }
        }
        if (itemToTake != -1){
            itemLabels.get(itemToTake).setLocation(panelCenter.getWidth(),panelCenter.getHeight());
            panelCenter.remove(itemLabels.get(itemToTake));
            character.getInventory().add(items.get(itemToTake));

        }

    }

    private boolean checkNextPosition(int x, int y) {
        boolean validPosition = true;
        characterLabel.setLocation(x, y);
        for (int i = 0; i < wallLabels.size() && validPosition; i++) {
            if (wallLabels.get(i).getBounds().intersects(characterLabel.getBounds())){
                validPosition = false;
            }
        }
        if (character.getGold() < 50){
            for (int i = 0; i < entryAndExitlabels.size() && validPosition; i++) {
                if (entryAndExitlabels.get(i).getBounds().intersects(characterLabel.getBounds())){
                    validPosition = false;
                    JOptionPane.showMessageDialog(null, "No has conseguido aún todas las monedas");
                }
            }
        }else {
            for (int i = 0; i < entryAndExitlabels.size() && validPosition; i++) {
                if (entryAndExitlabels.get(i).getBounds().intersects(characterLabel.getBounds())){
                    panelCenter.removeAll();
                    panelCenter.revalidate();
                    panelCenter.repaint();
                    showExitButton();
                }
            }
        }
        return validPosition;
    }

    private void showExitButton() {
        JLabel message = new JLabel("Felicidades, has");
        message.setSize(550,200);
        Font font = new Font("Comic Sans MS", Font.PLAIN, 70);
        message.setFont(font);
        message.setLocation(panelCenter.getWidth()/2 - message.getWidth()/2, panelCenter.getHeight()/2 - 200);
        message.setVisible(true);

        JLabel message2 = new JLabel("Ganado");
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
        exit.addMouseListener(new ExitButtonListener(character.getNombre(),character.getGold(),true));

        panelCenter.add(message);
        panelCenter.add(message2);
        panelCenter.add(exit);
        panelCenter.repaint();
    }
}
