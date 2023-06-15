package finalproject;

import hsa2.GraphicsConsole;

import javax.sound.sampled.Clip;
import java.awt.*;

public class Player {
    private int x, y, w, h;
    private int health;
    private int damage;
    private int jumpForce;
    private int pushStrength;
    private boolean jumping;
    private boolean pushed;
    private boolean doubleJumped;
    private Clip pluh;
    private Image img;
    private boolean attack =false;



    public Player(int x, int y, int w, int h, int health, int damage, Image img, Clip pluh) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.health = health;
        this.damage = damage;
        this.img = img;
        this.pluh = pluh;
    }

    public void jump() {
        y -= jumpForce;
        jumpForce -= FinalProject.GRAVITY;
        if (jumpForce <= 0 && y >= FinalProject.GROUND_LEVEL) {
            jumping = false;
            doubleJumped = false;
            y = FinalProject.GROUND_LEVEL;
        }
    }

    public void push() {
        if (pushStrength > 0) {
            x -= pushStrength;
            pushStrength -= FinalProject.GRAVITY;
        } else {
            x += pushStrength;
            pushStrength += FinalProject.GRAVITY;
        }
        if (Math.abs(pushStrength) <= 0) {
            pushed = false;
        }
    }

    public void draw(GraphicsConsole gc) {
        gc.drawImage(img, x, y, w, h);

    }



    public void move(GraphicsConsole gc) {
        if (!pushed) {
            if (gc.isKeyDown('A')) {
                if (x > 0) {
                    x -= 30;
                }
            }

            if (gc.isKeyDown('D')) {
                if (x < 825) {
                    x += 30;
                }
            }
            if (gc.isKeyDown('W')) if (y >= FinalProject.GROUND_LEVEL && !jumping) {
                jumping = true;
                jumpForce = 25;
            } else if (!doubleJumped) {
                doubleJumped = true;
                jumping = true;
                jumpForce = 25;
            }
        }
        if (jumping) jump();

        if (pushed) push();
    }

    public void attack(GraphicsConsole gc) {
        if (gc.isKeyDown(' ')) {
            attack=true;
            gc.playSound(pluh);

        }
    }

    public boolean getAttack(){
        return attack;
    }

    public void setAttack(boolean a){
        attack=a;
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

    public void setPushed(int pushStrength) {
        this.pushStrength = pushStrength;
        pushed = true;
    }


}


