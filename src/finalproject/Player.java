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
    private boolean attack = false;
    private Sword sword;


    public Player(int x, int y, int w, int h, int health, int damage, Image img, Clip pluh, Sword sword) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.health = health;
        this.damage = damage;
        this.img = img;
        this.pluh = pluh;
        this.sword = sword;
    }

    public void jump() {
        y -= jumpForce;
        jumpForce -= FinalProject.GRAVITY;
        if (jumpForce <= 0 && y >= FinalProject.GROUND_LEVEL - 178) {
            jumping = false;
            doubleJumped = false;
            y = FinalProject.GROUND_LEVEL - 178;
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

        if (sword.isThrown()) sword.drawSword(gc);
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
                jumpForce = 50;
            } else if (!doubleJumped) {
                doubleJumped = true;
                jumping = true;
                jumpForce = 50;
            }
        }
        if (jumping) jump();

        if (pushed) push();

        sword.setStartLoc(x, y + (178 / 2));

        if (sword.isThrown()) sword.move();
    }

    public void attack(GraphicsConsole gc) {
        if (!sword.isThrown()) {
            if (gc.isKeyDown(' ')) {
                attack = true;
                gc.playSound(pluh);
                sword.setThrown(true);
            }
        }
    }

    public boolean getAttack() {
        return attack;
    }

    public void setAttack(boolean a) {
        attack = a;
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


    public int getDamage() {
        return damage;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getHealth() {
        return health;
    }
}


