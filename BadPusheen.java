import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BadPusheen extends Objects
{
    
    private int range;
    private int speed;
    private boolean moving;

    private boolean startDirection = true;

    public int startX;
    private int max;
    private int min;

    public BadPusheen(int range, int speed, boolean moving) {
        this.range = range;
        this.speed = speed;
        this.moving = moving;
    }

    public void act() 
    {
        if (startX == 0) {
            startX = getX();
            max = startX + range;
            min = startX - range;
        }
        if (moving) {
            moveBP();
        }
    }

    public void moveBP() {
        if (startDirection) setLocation(getX() + speed, getY());
        else setLocation(getX() - speed, getY());
        if (startDirection && getX() >= max) startDirection = false;
        else if (!startDirection && getX() <= min) startDirection = true;       
    }
}