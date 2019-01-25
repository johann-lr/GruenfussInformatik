import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Main Actor class
// Pusheen is a nice cat lol

public class Pusheen extends Actor {

    public int jumpHeight;
    public int grav;
    public int speed;
    public static int lifes;
    public boolean isJumping;
    public int jumpCounter;
    public int cookiePoints;
    private long timeStamp;
    public boolean isBoosted;
    public int animationSpeed;
    public int animationCounter;
    public int imageCounter;
    public int[] startPos = {0, 0};
    public int gravityIsEvenMoreBad;
    public int lastSolidX;
    public int lastSolidY;
    public boolean noAcceleration;
    
    public Pusheen() {
        setImage("pusheen-right.png");
        this.isJumping = false;
        this.jumpCounter = 0;
        this.lifes = 5;
        this.speed = 5;
        this.jumpHeight = 20;
        this.grav = 5;
        this.cookiePoints = 0;
        this.isBoosted = false;
        this.animationSpeed = 5;
        this.animationCounter = 0;
        this.imageCounter = 1;
        this.noAcceleration = false;
    }
    
    public void act() {
        // save start position after game start in array
        if (startPos[0] == 0) {
            startPos[0] = getX();
            startPos[1] = getY();
        }
        lastSolidCoords();
        run();
        jump();
        if (!Greenfoot.isKeyDown("space")) isJumping = false;
        gravityIsBad();
        eatYummyShit();
        onMovingBlock();
        // cases that cause death of actor - contact with fire or enemy + y-coordinate under (over) 800
        if (getOneIntersectingObject(Fire.class) != null || getY() > 1500 || getOneIntersectingObject(BadPusheen.class) != null) RIP();
        if (getOneIntersectingObject(EndFlag.class) != null) getWorld().showText("Du hast das Level geschafft!", 600, 350);
        clearPowerUps();
        if (getY() > 800) RIP();
    }

    /**
     * @return true if pusheen is on Block or moving block - on solid ground
     */
    private boolean onSolidThing() {
       Actor groundObject = getOneObjectAtOffset(0,35,Block.class);
       Actor movingGround = getOneObjectAtOffset(0, 35, MovingBlock.class);
       return groundObject != null || movingGround != null;
    }
    
    public void run() {
        // move if the way is not blocked by block ;)
        if (Greenfoot.isKeyDown("right") && getOneObjectAtOffset(30, 0, Block.class) == null && getOneObjectAtOffset(30, 0, MovingBlock.class) == null) {
            //setImage("pusheen-right.png");
            if (animationCounter == animationSpeed) {
                switchImageRight();
                animationCounter = 0;
                return;
            }
            else animationCounter++;
            move(speed);
        }
        if (Greenfoot.isKeyDown("left") && getOneObjectAtOffset(-30, 0, Block.class) == null && getOneObjectAtOffset(-30, 0, MovingBlock.class) == null) {
            //setImage("pusheen-left.png");
            if (animationCounter == animationSpeed) {
                switchImageLeft();
                animationCounter = 0;
                return;
            }
            else animationCounter++;
            move(-speed);
        }
    }
    
    public void jump() {
        if (!Greenfoot.isKeyDown("space")) return;
        if (jumpCounter > jumpHeight) {
            isJumping = false;
            return;
        }
        //System.out.println(jumpCounter);
        isJumping = true;
        jumpCounter++;
        if (jumpCounter<5) {
            setLocation(getX(), getY()-10);
            return;
        } else if (jumpCounter<10) {
            setLocation(getX(), getY()-9);
            return;
        } else if (jumpCounter<14) {
            setLocation(getX(), getY()-7);
            return;
        } else if (jumpCounter<16) {
            setLocation(getX(), getY()-5);
            return;
        } else if (jumpCounter<18) {
            setLocation(getX(), getY()-4);
            return;
        } else if (jumpCounter<19) {
            setLocation(getX(), getY()-3);
            return;
        } else {
            setLocation(getX(), getY()-1);
            return;
        }
    }

    // 
    public void gravityIsBad() {
        if (onSolidThing()||isJumping) return;
        gravityIsEvenMoreBad++;
        if (noAcceleration) {
            setLocation(getX(), getY()+grav);
        }
        setLocation(getX(), getY()+grav+gravityIsEvenMoreBad/2);
        if (onSolidThing()) {
            jumpCounter = 0;
            gravityIsEvenMoreBad = 0;
            noAcceleration = false;
        }
    }

    // eat cookies or donuts if pusheen touches them
    public void eatYummyShit() {
        // remove cookie and increment cookieCounter
        if (getOneIntersectingObject(Cookie.class) != null) {
            Actor toRemove = getOneIntersectingObject(Cookie.class);
            getWorld().removeObject(toRemove);
            cookiePoints++;
            getWorld().getObjects(Score.class).get(0).incScore(cookiePoints); // get score label and increment its number
        }
        // eat donut - power up for pusheen
        if (getOneIntersectingObject(Donut.class) != null) {
            Actor toRemove = getOneIntersectingObject(Donut.class);
            getWorld().removeObject(toRemove);
            speed += 3;
            jumpHeight += 10;
            isBoosted = true;
            timeStamp = System.currentTimeMillis(); // save timestamp
        }
    }

    public void clearPowerUps() {
        if (!isBoosted) return;
        // set pusheen back to her normal and standard values after 10000milliseconds
        if (System.currentTimeMillis() >= timeStamp + 10000) {
            speed = 5;
            jumpHeight = 20;
            isBoosted = false;
        }
    }

    public void RIP() {
        World world = getWorld();
        int width = world.getWidth();
        int height = world.getHeight();
        gravityIsEvenMoreBad = 0;
        jumpCounter = 0;
        setLocation(lastSolidX, 30); // set actor to last block's x value
        lifes = lifes -1;
        //System.out.println(lifes);
        if (lifes == 0) {
            world.showText("Du hast verloren! :(", width/2, height/2);
            Greenfoot.delay(100);
            return Greenfoot.setWorld(new MyWorld()); // just create new world after death to respawn easily ;)
        }
        noAcceleration = true;
    }
    
    public void switchImageLeft() {
        if (imageCounter == 2) {
            setImage("pusheen-walk-left-2.png");
            imageCounter = 1;
            return;
        } else {
            setImage("pusheen-walk-left-1.png");
            imageCounter = 2;
            return;
        }
    }
    
    public void switchImageRight() {
        if (imageCounter == 2) {
            setImage("pusheen-walk-right-2.png");
            imageCounter = 1;
            return;
        } else {
            setImage("pusheen-walk-right-1.png");
            imageCounter = 2;
            return;
        }
    }

    public void onMovingBlock() {
        // if pusheen is on moving block
        if (getOneObjectAtOffset(0,35,MovingBlock.class) != null) {
            Actor block = getOneIntersectingObject(MovingBlock.class);
            // return if one of the keys is pressed to make escaping possible
            if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")) return;
            // set actor's location over the moving block while she's standing on it
            setLocation(block.getX(), block.getY() +-50);
        }
    }
    
    // save coords of last block for respawn
    public void lastSolidCoords() {
        if (onSolidThingForCoords()) {
            lastSolidX = getX();
            lastSolidY = getY();
        }
    }
    
    /**
     * @return true if pusheen is on moving block/normal block
     * simular to onSolidThing method
     */
    private boolean onSolidThingForCoords() {
       Actor groundObject = getOneObjectAtOffset(0,50,Block.class);
       Actor movingGround = getOneObjectAtOffset(0, 50, MovingBlock.class);
       return groundObject != null || movingGround != null;
    }
}