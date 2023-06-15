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
    FinalProject() throws InterruptedException {
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
        player = new Player(175, GROUND_LEVEL - 178, 175, 178, 230, 30, playerImg, pluh, sword);
        alien = new Alien(500, 200, 296, 220, 750, 70, alienImg);

        while (true) {
            synchronized (gc) {
                gc.clear();
                gc.drawImage(backgroundImage, 0, 0, gc.getDrawWidth(), gc.getDrawHeight());
                alien.draw(gc);
                player.draw(gc);
            }


            player.move(gc);
            alien.move();

            player.attack(gc);

            if (player.getX() < alien.getX() + alien.getWidth() && player.getX() + player.getWidth() > alien.getX() && player.getY() < alien.getY() + alien.getHeight() && player.getY() + player.getHeight() > alien.getY()) {
                // Do damage and shove the player away from the enemy
                if (player.getX() > alien.getX())
                    player.setPushed(40);
                else
                    player.setPushed(-40);
            }

            Thread.sleep(50);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Michael was here :)");
        new FinalProject();
    }


}