package finalproject;

import hsa2.GraphicsConsole;

import java.awt.*;

public class Alien {
    private int x, y;
    private int health;
    private int damage;
    private Image img;

    private int centerX, centerY;
    private double angle = 0;

    public Alien(int x, int y, int health, int damage, Image img) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        this.img = img;

        centerX = x + (296 / 2);
        centerY = y + (220 / 2);
    }

    public void draw(GraphicsConsole gc) {
        gc.drawImage(img, x, y, 296, 220);
    }

    public void move() {
        angle += 0.20;

        x = centerX + (int) (25 * Math.cos(angle));
        y = centerY + (int) (25 * Math.sin(angle));
    }
}


