package characters;

import javax.swing.*;

public class Warrior extends Characters {

    // constructor
    public Warrior(String nombre) {
        super(nombre);
        this.life = 5;
        this.speed = 3;
        this.go_down = new ImageIcon("src/images/warrior/warrior_down.gif");
        this.go_left = new ImageIcon("src/images/warrior/warrior_left.gif");
        this.go_right = new ImageIcon("src/images/warrior/warrior_right.gif");
        this.go_up = new ImageIcon("src/images/warrior/warrior_up.gif");
    }

}
