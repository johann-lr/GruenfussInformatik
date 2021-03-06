import greenfoot.*;

/**
 * Main World class where Pusheen (actor) moves
 */
public class MyWorld extends greenfoot.World {
    
    Scroller scroller; // scroller class to move world
    public boolean isWorldNew;
    public int newX;
    public int newY;
    public int oldX;
    public int oldY;
    
    public MyWorld() {
        super(1200, 700, 1, false); //false means unbounded world
        GreenfootImage bgImage = new GreenfootImage("bg2.png");
        scroller = new Scroller(this, bgImage);
        addObject(new Pusheen(),120,450); // add pusheen with block
        addObject(new Block(),120,550);
        addObject(new Heart(),1150,50);
        addObject(new Heart(),1080,50);
        addObject(new Heart(),1010,50);
        addObject(new Heart(),940,50);
        addObject(new Heart(),870,50);
        addObject(new Score(),1150, 115);
        isWorldNew = true;
        levelCreator();
    }

    public void act() {
        moveWorld();
    }

    public void moveWorld() {
        int moveSpeed = 3;
        int x = 0;
        int y = 0;
        //if (Greenfoot.isKeyDown("right")) x++;
        //if (Greenfoot.isKeyDown("left")) x--;
        Actor pusheenObj = getObjects(Pusheen.class).get(0);
        if (pusheenObj.getX() > 600) x++;
        else if (pusheenObj.getX() < 600 && Greenfoot.isKeyDown("left")) x--;

        scroller.scroll(x*moveSpeed, y);
    }
    // generates random blocks and other objects
    public void levelCreator() {
        if (isWorldNew = true) {
            oldX = 120;
            oldY = 550;
            
            for (int i = 1; i <= 70; i++) {
                newX = oldX + Greenfoot.getRandomNumber(60) + 80;
                newY = oldY + Greenfoot.getRandomNumber(200) - 100;
                int randomMovingIn = Greenfoot.getRandomNumber(10)+1;
                
                while (newY > 666 | newY < 190) {
                    newY = oldY + Greenfoot.getRandomNumber(200) - 100;
                }
                if (randomMovingIn == 10) {
                    int randomSpeed = Greenfoot.getRandomNumber(3)+1;
                    int randomDistance = Greenfoot.getRandomNumber(24)+15;
                    addObject(new MovingBlock(0, randomSpeed, randomDistance, true), newX, newY);
                }
                else addObject(new Block(),newX,newY);
                oldX = newX;
                oldY = newY;
                if (i == 70) addObject(new EndFlag(), oldX, oldY);
            }
            // run loop through each object of Block.class
            for (Object obj : getObjects(Block.class)) {
                int rdm = Greenfoot.getRandomNumber(10) +1;
                Actor actor = (Actor) obj;
                int y = actor.getY();
                int x = actor.getX();
                // place cookie/donut over Block in some cases (random number)
                if (rdm == 1) addObject(new Cookie(), x, y - 50);
                if (rdm == 10) addObject(new Donut(), x, y -50);
            }
            
        }
    }
    
    // does not work nvm
    public Scroller getScroller() {
        return scroller;
    }
}
