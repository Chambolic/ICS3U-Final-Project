package finalproject;

import hsa2.GraphicsConsole;


import javax.sound.sampled.Clip;
import java.awt.*;
public class Sword {
    private int x;
    private int y;
    private int targetX;
    private int targetY;
    private int speedX;
    private int speedY;

    private double slope;

    private int b;

    private boolean toss;

    public Sword(int x, int y, boolean t) {
        this.x = x;
        this.y = y;
        this.toss = t;
    }

    void drawSword (GraphicsConsole gc ){
        gc.fillOval(x,y,10, 10);
    }
    void throwSword(Player p, Alien a) throws InterruptedException {
        toss = true;
        this.x = p.getX()+200;
        this.y = p.getY()+500;

        this.targetX = a.getX()+50;
        this.targetY = a.getY()+200;

        // a + (b-a)*t

        // swordX = this.x = (this.target.x - this.x) * t   t = the percent between the points, 0.5 = 50%

        int startX = p.getX();
        int startY = p.getY();


        this.slope=Double.valueOf(targetY - y)/Double.valueOf(targetX - x);
        System.out.println("x "+this.x+" y "+this.y+" targetX "+this.targetX+" targety "+this.targetY+" slope "+this.slope);
        this.b=this.y;

    }

    boolean getToss(){
        return toss;
    }

    void moveSword(Alien a, Player p){


        this.slope = 0.5; //(p.getX() - a.getX()) / (p.getY() - a.getY());


        this.x += 15;
        double result = (this.slope * this.x);

        this.y =(int)result;
        System.out.println("x " +x+ " y "+y+" result "+result+" slope "+slope);


    }
}
