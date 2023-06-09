package finalproject;

import hsa2.GraphicsConsole;

import java.awt.*;

public class FinalProject {
    public static void main(String[] args) throws InterruptedException {
        new FinalProject();
    }

//graphics console

    private Player player;
    private Alien alien;
    int enemyX = 500, enemyY = 200;

    int p;
    public static int GROUND_LEVEL = 450;
    static int GRAVITY = 5;
    GraphicsConsole gc = new GraphicsConsole(1000, 900,
            "Chronicles of the Celestial Realms: Ascendancy of Eternal Dominion");

    FinalProject() throws InterruptedException {
        Image backgroundImage = Toolkit.getDefaultToolkit()
                .getImage(gc.getClass().getClassLoader().getResource("Images/Forest.jpg"));
        Image alienImg = gc.loadImage("src/Images/Boss.png");
        Image playerImg = gc.loadImage("src/Images/User1.png");

        player = new Player(175, GROUND_LEVEL, 230, 30, playerImg);
        alien = new Alien(500, 200, 750, 70, alienImg);

        while (true) {
            synchronized (gc) {
                gc.clear();
                gc.drawImage(backgroundImage, 0, 0, gc.getDrawWidth(), gc.getDrawHeight());
                alien.draw(gc);
                player.draw(gc);
            }

            player.move(gc);
            alien.move();

            Thread.sleep(50);
        }

    }



}