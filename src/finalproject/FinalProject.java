package finalproject;

import hsa2.GraphicsConsole;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;

public class FinalProject {
    public static Player player;

//graphics console
    public static int GROUND_LEVEL = 750;
    public static int GRAVITY = 5;
    static GraphicsConsole gc = new GraphicsConsole(1000, 900,
            "Chronicles of the Celestial Realms: Ascendancy of Eternal Dominion");
    private Alien alien;
    private boolean alive;
    private int killCount = 0;

    FinalProject() throws InterruptedException {
        alive = true;
        Image backgroundImage = Toolkit.getDefaultToolkit()
                .getImage(gc.getClass().getClassLoader().getResource("Images/Forest.jpg"));
        Image alienImg = gc.loadImage("src/Images/Boss.png");
        Image playerImg = gc.loadImage("src/Images/User1.png");
        Image swordImg = gc.loadImage("src/Images/Sword.png");
        Clip pluh = gc.loadSound("audio/PLUH.wav");
        FloatControl volume = (FloatControl) pluh.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(volume.getMaximum());
        Clip song = gc.loadSound("audio/cupid.wav");
        volume = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(volume.getMinimum() / 3);
        gc.playSoundLoop(song);
        Sword sword = new Sword(175, (GROUND_LEVEL - (178 / 2)), 38, 17, swordImg);
        player = new Player(175, GROUND_LEVEL - 178, 175, 178, 230, 100, playerImg, pluh, sword);
        alien = new Alien(500, 200, 296, 220, 750, 70, alienImg);

        while (alive) {
            synchronized (gc) {
                gc.clear();
                gc.drawImage(backgroundImage, 0, 0, gc.getDrawWidth(), gc.getDrawHeight());
                alien.draw(gc);
                player.draw(gc);

                gc.drawString("Aliens defeated: " + killCount, 10, 10);
            }


            player.move(gc);
            alien.move();

            player.attack(gc);

            if (player.getX() < alien.getX() + alien.getWidth() && player.getX() + player.getWidth() > alien.getX() && player.getY() < alien.getY() + alien.getHeight() && player.getY() + player.getHeight() > alien.getY() && alien.getHealth() > 0) {
                // Do damage and shove the player away from the enemy
                if (player.getX() > alien.getX())
                    player.setPushed(40);
                else
                    player.setPushed(-40);

                player.setHealth(player.getHealth() - alien.getDamage());
            }

            if (sword.getX() < alien.getX() + alien.getWidth() && sword.getX() + sword.getWidth() > alien.getX() && sword.getY() < alien.getY() + alien.getHeight() && sword.getY() + sword.getHeight() > alien.getY()) {
                alien.setHealth(alien.getHealth() - player.getDamage());
                System.out.println(alien.getHealth());
                sword.setThrown(false);
            }

            if (player.getHealth() <= 0) {
                alive = false;
            }

            if (alien.getHealth() <= 0) {
                if (alien.getDeathCount() < 20) {
                    alien.setDeathCount(alien.getDeathCount() + 1);
                } else {
                    alien.reset();
                    killCount ++;
                }
            }

            Thread.sleep(50);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.err.println("Credits to Michael for helping!");
        new FinalProject();
    }


}