import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MovingBlock extends Objects
{
    private int range;  // value in greenfoot world coordinates between the block should move - startValue +- range
    private int speed; // value describing how many steps block should be moved in one "act"
    private boolean moving;

    public int startX;
    private int max;
    private int min;
    public boolean vertical; // whether block moves vertical or horizontal
    public boolean startDirection; // if true block starts to the right

    public MovingBlock(int range, int speed, boolean moving, boolean vertical, boolean startDirection) {
        setImage("plattform.png");
        this.range = range;
        this.speed = speed;
        this.moving = moving;
        this.vertical = vertical;
        this.startDirection = startDirection;
    }

    public void act() {
        if (startX == 0) {
            startX = getX();
            max = startX + range;
            min = startX - range;
        }
        if (moving) {
            movePlatform();
        }
        pusheenIsOnBlock();
    }    

    public void movePlatform() {
        if (vertical) {
            if (startDirection) setLocation(getX() + speed, getY());
            else setLocation(getX() - speed, getY());
            if (startDirection && getX() >= max) startDirection = false;
            else if (!startDirection && getX() <= min) startDirection = true;
        } else {
            if (startDirection) setLocation(getX(), getY() + speed);
            else setLocation(getX(), getY() - speed);
            if (startDirection && getY() >= max) startDirection = false;
            else if (!startDirection && getY() <= min) startDirection = true;
        }
    }

    public void pusheenIsOnBlock() {
        if (getOneObjectAtOffset(0,35,Pusheen.class) != null) {
            Actor pusheen = getOneObjectAtOffset(0,35,Pusheen.class);
            //pusheen.setLocation()
        }
    }
}
