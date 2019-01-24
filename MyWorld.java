import greenfoot.*;

public class MyWorld extends greenfoot.World
{
    
    Scroller scroller;
    //public int movedRight;
    
    public MyWorld() {    
        super(1200, 700, 1, false);
        GreenfootImage bgImage = new GreenfootImage("bg2.png");
        scroller = new Scroller(this, bgImage);
        addObject(new Pusheen(),120,450);
        addObject(new Block(),120,550);
        //X120 Y450
        //movedRight = 0;
    }

    public void act() {
        moveWorld();
    }

    public void moveWorld() {
        int moveSpeed = 5;
        int x = 0;
        int y = 0;
        if (Greenfoot.isKeyDown("right")) x++;
        if (Greenfoot.isKeyDown("left")) x--;
        scroller.scroll(x*moveSpeed, y);
    }
    
    public Scroller getScroller() {
        return scroller;
    }
}
