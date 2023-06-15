package finalproject;

import hsa2.GraphicsConsole;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;

public class FinalProject {
    public static void main(String[] args) throws InterruptedException {
        new FinalProject();
    }

//graphics console

    public static Player player;
    private Alien alien;

    private Sword sword;
    public static int GROUND_LEVEL = 450;
    static int GRAVITY = 5;
    static GraphicsConsole gc = new GraphicsConsole(1000, 900,
            "Chronicles of the Celestial Realms: Ascendancy of Eternal Dominion");

    FinalProject() throws InterruptedException {
        Image backgroundImage = Toolkit.getDefaultToolkit()
                .getImage(gc.getClass().getClassLoader().getResource("Images/Forest.jpg"));
        Image alienImg = gc.loadImage("src/Images/Boss.png");
        Image playerImg = gc.loadImage("src/Images/User1.png");
        Image swordImg = gc.loadImage ("src/Images/Sword.png");
        Clip pluh = gc.loadSound("audio/PLUH.wav");
        FloatControl volume = (FloatControl) pluh.getControl(FloatControl.Type.MASTER_GAIN) ;
        volume.setValue(volume.getMaximum());
        Clip song = gc.loadSound("audio/cupid.wav");
        volume = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN) ;
        volume.setValue(volume.getMinimum() / 3);
        gc.playSoundLoop(song);
        player = new Player(175, GROUND_LEVEL, 175, 178, 230, 30, playerImg, pluh);
        alien = new Alien(500, 200, 296, 220, 750, 70, alienImg);
        sword = new Sword (0, 0, false);

        while (true) {
            synchronized (gc) {
                gc.clear();
                gc.drawImage(backgroundImage, 0, 0, gc.getDrawWidth(), gc.getDrawHeight());
                alien.draw(gc);
                player.draw(gc);
                sword.drawSword(gc);
            }


            player.move(gc);
            alien.move();
            if (player.getAttack()==true&&sword.getToss()==false){
                player.setAttack(false);
                sword.throwSword(player,alien);
            }
            if (sword.getToss()==true){
                sword.moveSword(alien, player);
            }

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


}