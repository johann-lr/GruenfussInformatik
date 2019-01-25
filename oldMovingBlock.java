import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Johann Laur
 * @deprecated Because the block moves and checks the coordinates instead of the distance
 * That is why the block moves with the world if the world moves
 */
public class oldMovingBlock extends Objects
{
    private int range;  // value in greenfoot world coordinates between the block should move - startValue +- range
    private int speed; // value describing how many steps block should be moved in one "act"
    private boolean moving;

    public int startX;
    private int max;
    private int min;
    public boolean vertical; // whether block moves vertical or horizontal
    public boolean startDirection; // if true block starts to the right

    public oldMovingBlock(int range, int speed, boolean moving, boolean vertical, boolean startDirection) {
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
}
