import greenfoot.*;

public class MyWorld extends greenfoot.World
{
    
    Scroller scroller;
    
    public MyWorld() {    
        super(1200, 700, 1, false);
        GreenfootImage bgImage = new GreenfootImage("bg.png");
        scroller = new Scroller(this, bgImage);
    }

    public void act() {
        moveWorld();
    }

    public void moveWorld() {
        int moveSpeed = 2;
        int x = 0;
        int y = 0;
        if (Greenfoot.isKeyDown("right")) x++;
        if (Greenfoot.isKeyDown("left")) x--;
        scroller.scroll(x*moveSpeed, y);
    }
}
