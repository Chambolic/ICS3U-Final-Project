package finalproject;

import hsa2.GraphicsConsole;

import java.awt.*;

public class Alien {
    private int x, y, w, h;
    private int health;
    private int maxHealth;
    private int damage;
    private Image img;
    private int deathCount = 0;

    private int centerX, centerY;
    private double angle = 0;

    public Alien(int x, int y, int w, int h, int health, int damage, Image img) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.health = maxHealth = health;
        this.damage = damage;
        this.img = img;

        centerX = x + (296 / 2);
        centerY = y + (220 / 2);
    }

    public void draw(GraphicsConsole gc) {
        if (health > 0) {
            gc.drawImage(img, x, y, w, h);
        }
    }

    public void move() {
        if (health > 0) {
            angle += 0.20;

            x = centerX + (int) (25 * Math.cos(angle));
            y = centerY + (int) (25 * Math.sin(angle));
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
     public int getWidth() {
        return w;
     }

     public int getHeight() {
        return h;
     }

     public void setHealth(int newHealth) {
        health = newHealth;
     }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int newCount) {
        deathCount = newCount;
    }

    public void reset() {
        deathCount = 0;
        health = maxHealth;
    }
}


