import greenfoot.*;

/**
 * @author Johann Laur, greenfoot.org example
 * Score label to show how many cookies pusheen did eat
 */

public class Score extends Actor {

    private GreenfootImage image;

    public Score() {
        this.image = new GreenfootImage(200, 50);
        image.drawString("Cookie Score: 0", 2, 20);
        setImage(this.image);
    }

    public void incScore(int score) {
        GreenfootImage img = getImage();
        img.clear();
        String string = String.format("Cookie Score: %s", score);
        img.drawString(string, 2, 20);
    }
}
