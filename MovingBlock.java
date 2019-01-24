import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

// angle code (with sin/cos) is inspired by a guy from the greenfoot.org page

public class MovingBlock extends Objects
{
    private int movingSpeed;
    private int distance;
    private int angle;
    private double velX, velY;
    
    private double distanceTraveled;
    
    private boolean moving;
    private boolean movingBack = false;

    public MovingBlock(int angle, int movingSpeed, int distance, boolean moving) {
        this.angle = angle;
        this.movingSpeed = movingSpeed;
        this.distance = distance;
        this.moving = moving;
        velX = Math.cos(Math.toRadians(angle)) * movingSpeed;
        velY = Math.sin(Math.toRadians(angle)) * movingSpeed;
    }
    
    public void act() {
        run();
    }
    
    public void run() {
        if (moving) {
            if (distanceTraveled >= distance) {
                velX = -velX;
                velY = -velY;
                distanceTraveled = -(distanceTraveled - distance);
                movingBack = !movingBack;
            }
            move();
        }
    }
    
    public void move() {
        distanceTraveled += Math.hypot(velX, velY);
        setLocation(getX() + (int)Math.floor(velX), getY() + (int)Math.floor(velY));
    }
    
    public int getStartingX() {
        return (int) (getX() - (Math.cos(Math.toRadians(angle)) * (movingBack ? distance - distanceTraveled : distanceTraveled)));
    }
    public int getStartingY() {
        return (int) (getY() - (Math.sin(Math.toRadians(angle)) * (movingBack ? distance - distanceTraveled : distanceTraveled)));
    }
}