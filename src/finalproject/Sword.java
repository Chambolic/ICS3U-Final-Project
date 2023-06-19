package finalproject;

import hsa2.GraphicsConsole;


import javax.sound.sampled.Clip;
import java.awt.*;
public class Sword {
    private int x;
    private int y;
    private int w, h;
    private int startX;
    private int startY;
    private int throwForce;
    private Image image;

    private double slope;

    private int b;

    private boolean thrown;

    public Sword(int x, int y, int w, int h, Image image) {
        this.x = startX = x;
        this.y = startY = y;
        this.w = w;
        this.h = h;
        thrown = false;
        throwForce = 0;
        this.image = image;
    }

    public void drawSword (GraphicsConsole gc ){
        gc.drawImage(image, x, y);
    }

    public boolean isThrown() {
        return thrown;
    }

    void move(){
        if (y < FinalProject.GROUND_LEVEL) {
            x += 80;
            y -= throwForce;
            throwForce -= FinalProject.GRAVITY;
        } else {
            thrown = false;
            x = startX;
            y = startY;
        }
    }

    public void setStartLoc(int x, int y) {
        startX = x;
        startY = y;
    }

    public void setThrown(boolean thrown) {
        this.thrown = thrown;
        throwForce = 20;

        x = startX;
        y = startY;
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
}
