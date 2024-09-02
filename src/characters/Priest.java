package characters;

import javax.swing.*;

public class Priest extends Characters{

    // constructor
    public Priest(String nombre) {
        super(nombre);
        this.life = 4;
        this.speed = 5;
        this.go_down = new ImageIcon("src/images/priest/priest_down.gif");
        this.go_left = new ImageIcon("src/images/priest/priest_left.gif");
        this.go_right = new ImageIcon("src/images/priest/priest_right.gif");
        this.go_up = new ImageIcon("src/images/priest/priest_up.gif");
    }

}
