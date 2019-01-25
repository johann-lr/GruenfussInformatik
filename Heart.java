import greenfoot.*;
import java.util.List;

/**
 * @author Emil Jahnke
 * Hearts top right corner to present actor's lifes
 */

public class Heart extends Actor
{
    // simply remove heart image after death by getting its coordinates

    public void act() 
    {
        World world = getWorld();
        
        List objectslookingfor = world.getObjects(Heart.class);
        if (objectslookingfor.size() == 5) {
            if (getX() == 870 && Pusheen.lifes <5) world.removeObject(this);
        }
        if (objectslookingfor.size() == 4) {
            if (getX() == 940 && Pusheen.lifes <4) world.removeObject(this);
        }
        if (objectslookingfor.size() == 3) {
            if (getX() == 1010 && Pusheen.lifes <3) world.removeObject(this);
        }
        if (objectslookingfor.size() == 2) {
            if (getX() == 1080 && Pusheen.lifes <2) world.removeObject(this);
        }
        if (objectslookingfor.size() == 1) {
            if (getX() == 1150 && Pusheen.lifes <1) world.removeObject(this);
        }
    }    
}
