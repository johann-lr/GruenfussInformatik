import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MovingBlock extends Objects
{
    private int range;
    private int speed;
    private boolean moving;

    public int startX;
    private int max;
    private int min;

    public MovingBlock(int range, int speed, boolean moving) {
        this.range = range;
        this.speed = speed;
        this.moving = moving;
    }

    public void act() {
        if (startX == 0) {
            startX = getX();
            max = startX + range;
            min = startX - range;
        }
        if (!moving) return;
        movePlatform();        
    }    

    public void movePlatform() {
        if (getX() >= max || getX() <= range) {
            setLocation(getX() - speed, getY());
        } else {
            setLocation(getX() + speed, getY());
        }
    }
}
