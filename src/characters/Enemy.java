package characters;

import javax.swing.*;

public class Enemy {

    // atributos
    private int speed;
    private ImageIcon go_down;
    private ImageIcon go_left;
    private ImageIcon go_right;
    private ImageIcon go_up;
    private String direction;

    // constructor
    public Enemy() {
        this.speed = 5;
        go_down = new ImageIcon("src/images/skeleton/skeleton_down.gif");
        go_left = new ImageIcon("src/images/skeleton/skeleton_left.gif");
        go_right = new ImageIcon("src/images/skeleton/skeleton_right.gif");
        go_up = new ImageIcon("src/images/skeleton/skeleton_up.gif");
    }

    // getters y setters


    public int getSpeed() {
        return speed;
    }

    public ImageIcon getGo_down() {
        return go_down;
    }

    public ImageIcon getGo_left() {
        return go_left;
    }

    public ImageIcon getGo_right() {
        return go_right;
    }

    public ImageIcon getGo_up() {
        return go_up;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
