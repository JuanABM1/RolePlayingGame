package characters;

import javax.swing.*;

public class Mage extends  Characters {

    // constructor
    public Mage(String nombre) {
        super(nombre);
        this.life = 3;
        this.speed = 7;
        this.go_down = new ImageIcon("src/images/wizard/wizard_down.gif");
        this.go_left = new ImageIcon("src/images/wizard/wizard_left.gif");
        this.go_right = new ImageIcon("src/images/wizard/wizard_right.gif");
        this.go_up = new ImageIcon("src/images/wizard/wizard_up.gif");
    }

}
