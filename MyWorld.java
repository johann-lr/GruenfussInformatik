import greenfoot.*;

public class MyWorld extends greenfoot.World
{
    
    Scroller scroller;
    //public int movedRight;
    public boolean isWorldNew;
    public int newX;
    public int newY;
    public int oldX;
    public int oldY;
    
    public MyWorld() {    
        super(1200, 700, 1, false);
        GreenfootImage bgImage = new GreenfootImage("bg2.png");
        scroller = new Scroller(this, bgImage);
        addObject(new Pusheen(),120,450);
        addObject(new Block(),120,550);
        //X120 Y450
        //movedRight = 0;
        isWorldNew = true;
        levelCreator();
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
        
    public void levelCreator() {
        if (isWorldNew = true) {
            oldX = 120;
            oldY = 550;
            
            for (int i = 1; i <= 100; i++) {
                newX = oldX + Greenfoot.getRandomNumber(100) + 20;
                newY = oldY + Greenfoot.getRandomNumber(200) - 100;
                addObject(new Block(),newX,newY);
                oldX = newX;
                oldY = newY;
            }
            
        }
    }
    
    public Scroller getScroller() {
        return scroller;
    }
}
