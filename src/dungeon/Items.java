package dungeon;

import javax.swing.*;
import java.awt.*;

public abstract class Items {

    protected Icon picture;

    public void setPicture(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        this.picture = new ImageIcon(
            imageIcon.getImage().getScaledInstance(32,32, Image.SCALE_DEFAULT)
        );
    }

    public Icon getPicture() {
        return picture;
    }


}
