import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Main Actor class
// Pusheen is a nice cat lol

public class Pusheen extends Actor {

    public int jumpHeight;
    public int grav;
    public int speed;
    public int lifes;
    public int levelTry;
    public boolean isJumping; // to prevent running in the air
    public int jumpCounter;
    public int cookiePoints;
    private long timeStamp;
    public boolean isBoosted;
    
    public Pusheen() {
        setImage("pusheen.png");
        this.isJumping = false;
        this.levelTry = 1;
        this.jumpCounter = 0;
        this.lifes = 3;
        this.speed = 5;
        this.jumpHeight = 20;
        this.grav = 5;
        this.cookiePoints = 0;
        this.isBoosted = false;
        //setImage() nach rechts, standardlaufrichtung gucken
    }
    
    // act method
    public void act() {
        run();
        jump();
        if (!Greenfoot.isKeyDown("up")) isJumping = false;
        gravityIsBad();
        eatYummyShit();
        if (getY() > 1500) RIP(); // remove falling pusheens
        clearPowerUps();
    }

    private boolean onSolidThing() {
       Actor groundObject = getOneObjectAtOffset(0,35,Block.class);
       return groundObject != null;
    }
    
    public void run() {
        if (Greenfoot.isKeyDown("right") && getOneObjectAtOffset(30, 0, Block.class) == null) move(speed); //bild entsprechend der laufrichtung ändern
        if (Greenfoot.isKeyDown("left") && getOneObjectAtOffset(-30, 0, Block.class) == null) move(-speed);
    }
    
    public void jump() {
        if (!Greenfoot.isKeyDown("up")) return;
        if (jumpCounter > jumpHeight) {
            isJumping = false;
            return;
        }
        //System.out.println(jumpCounter);
        isJumping = true;
        setLocation(getX(), getY()-5);
        jumpCounter++;
    }

    public void gravityIsBad() {
        if (onSolidThing()||isJumping) return;
        setLocation(getX(), getY()+grav);
        if (onSolidThing()) jumpCounter = 0;
    }

    public void eatYummyShit() {
        if (getOneIntersectingObject(Cookie.class) != null) {
            Actor toRemove = getOneIntersectingObject(Cookie.class);
            getWorld().removeObject(toRemove);
            cookiePoints++;
        }
        if (getOneIntersectingObject(Donut.class) != null) {
            Actor toRemove = getOneIntersectingObject(Donut.class);
            getWorld().removeObject(toRemove);
            speed += 3;
            jumpHeight += 3;
            isBoosted = true;
            timeStamp = System.currentTimeMillis();            
        }
    }

    public void clearPowerUps() {
        if (!isBoosted) return;
        if (System.currentTimeMillis() >= timeStamp + 10000) {
            speed = 5;
            jumpHeight = 20;
            isBoosted = false;
        }
    }

    public void RIP() {
        getWorld().removeObject(this);
    }
}