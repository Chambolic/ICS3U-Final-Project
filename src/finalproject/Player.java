package finalproject;

import hsa2.GraphicsConsole;

import java.awt.*;

public class Player {
    private int x, y;
    private int health;
    private int damage;
    private int jumpForce;
    private boolean jumping;
    private boolean doubleJumped;
    private Image img;

    public Player(int x, int y, int health, int damage, Image img) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        this.img = img;
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

    public void draw(GraphicsConsole gc) {
        gc.drawImage(img, x, y, 175, 178);
    }

    public void move(GraphicsConsole gc) {
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
        if (jumping) jump();
    }
}
