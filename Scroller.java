import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is not self-written
 * Copied Scroller.class to move world from greenfoot.org
 * @author https://www.greenfoot.org/users/2991
 */

public class Scroller extends Actor
{

    private World world;
    private GreenfootImage image;
    private int scrolledX, scrolledY;
    private int wide, high;

/**
 * @param world greenfoot world
 * @param image background image of game world
 */

    public Scroller(World world, GreenfootImage image) {
        this.world = world;
        this.image = image;
        if (image != null) {
            wide = image.getWidth();
            high = image.getHeight();
        }
        scroll(0,0);
    }
    public void act() {}
    public void scroll(int dsx, int dsy) {
        scrolledX += dsx;
        if (image != null) {
            int imageX = scrolledX*world.getCellSize();
            int imageY = scrolledY*world.getCellSize();
            imageX = imageX%wide;
            imageY = imageY%high;
            if (imageX < 0) imageX += wide;
            if (imageX < 0) imageX += high;
            GreenfootImage hold = new GreenfootImage(image);
            hold.drawImage(image, -imageX, -imageY);
            if (imageX > 0) hold.drawImage(image, wide-imageX, -imageY);
            if (imageY > 0) hold.drawImage(image, -imageX, high-imageY);
            if (imageX > 0 && imageY > 0) hold.drawImage(image, wide-imageX, high-imageY);
            world.setBackground(hold);
        }
    for (Object obj : world.getObjects(null)) {
        if (obj.getClass() != Heart.class && obj.getClass() != Score.class) {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX()-dsx, actor.getY()-dsy);
        }
    }
}

    /**
    * @return int how far the world moved in x direction
    */
    public int getScrolledX() {
        return scrolledX;
    }
}
