package characters;

import dungeon.Items;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Characters {
// atributos

    protected String nombre;
    protected int life;
    protected int gold;
    protected int speed;
    protected ArrayList<Items> inventory;
    protected ImageIcon go_down;
    protected ImageIcon go_left;
    protected ImageIcon go_right;
    protected ImageIcon go_up;


    // constructores

    public Characters(String nombre) {
        this.nombre = nombre;
        this.gold = 0;
        this.inventory = new ArrayList<>();
    }

    // getters y setters

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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSpeed() {
        return speed;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

}
