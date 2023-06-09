package finalproject;

import hsa2.GraphicsConsole;

import java.awt.*;

public class FinalProject {
    public static void main(String[] args) throws InterruptedException {
        new FinalProject();
    }

//graphics console

    int enemyX = 500, enemyY = 200;
    int centerX = enemyX + (296 / 2), centerY = enemyY + (220 / 2);
    double enemyAngle = 0;

    int p;
    int GROUND_LEVEL = 450;
    int playerY = GROUND_LEVEL;
    int groundY;
    boolean playerJumping;
    int GRAVITY = 5;
    int jumpForce;
    int playerX = 175;
    GraphicsConsole gc = new GraphicsConsole(1000, 900,
            "Chronicles of the Celestial Realms: Ascendancy of Eternal Dominion");
    boolean doubleJumped;

    FinalProject() throws InterruptedException {
        Image backgroundImage = Toolkit.getDefaultToolkit()
                .getImage(gc.getClass().getClassLoader().getResource("Images/Forest.jpg"));
        Image backgroundImage1 = Toolkit.getDefaultToolkit()
                .getImage(gc.getClass().getClassLoader().getResource("Images/Boss.png"));
        Image backgroundImage2 = Toolkit.getDefaultToolkit()
                .getImage(gc.getClass().getClassLoader().getResource("Images/User1.png"));


        while (true) {
            synchronized (gc) {
                gc.clear();
                gc.drawImage(backgroundImage, 0, 0, gc.getDrawWidth(), gc.getDrawHeight());
                gc.drawImage(backgroundImage1, enemyX, enemyY, 296, 220);
                gc.drawImage(backgroundImage2, playerX, playerY, 175, 178);
            }

            enemyAngle += 0.20;

            enemyX = centerX + (int) (25 * Math.cos(enemyAngle));
            enemyY = centerY + (int) (25 * Math.sin(enemyAngle));

            if (gc.isKeyDown('A')) {
                if (playerX > 0) {
                    playerX -= 30;

                }
            }

            if (gc.isKeyDown('D')) {
                if (playerX < 825) {
                    playerX += 30;

                }
            }
            if (gc.isKeyDown('W'))
                if (playerY >= groundY && playerJumping == false) {
                    playerJumping = true;
                    jumpForce = 25;
                } else if (!doubleJumped) {
                    doubleJumped = true;
                    playerJumping = true;
                    jumpForce = 25;
                }
            if (playerJumping)
                jump();
            Thread.sleep(50);
        }

    }

    public void jump() {
        playerY -= jumpForce;
        jumpForce -= GRAVITY;
        if (jumpForce <= 0 && playerY >= GROUND_LEVEL) {
            playerJumping = false;
            doubleJumped = false;
            playerY = GROUND_LEVEL;
        }
    }

}