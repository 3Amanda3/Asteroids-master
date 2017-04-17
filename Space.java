import greenfoot.*;
import java.awt.Color;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Space extends World
{
    private Counter scoreCounter;
    private int startAsteroids = 1;

    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();

        paintStar(2000);

        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);

        addAsteroids(startAsteroids);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
        prepare();
    }

    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }

    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        ScoreBoard endGame = new ScoreBoard( scoreCounter.getValue() );
        addObject(endGame,getWidth() /2, getHeight() /2);
    }

    /**
     * paintStar will add stars to the background of the world with rsandom x and y and
     * different transparencys
     *@param count is a number of stars that will create
     *@return nothing is returned
     */
    private void paintStar(int count)
    {
        int x;
        int y;
        int transparency;

        GreenfootImage background = getBackground();
        for( int i = 0; i < count; i++)
        {
            x = Greenfoot.getRandomNumber( getWidth() );
            y = Greenfoot.getRandomNumber( getHeight() );
            transparency = Greenfoot.getRandomNumber( 256 );
            background.setColor( new Color(255,255,255,transparency) );
            background.fillOval(x, y, 2, 2);
        }
    }

    /**
     * countScore will add points to the scoreCounter
     * @param score is a number that will be added to the score counter
     * @return nothing is returned
     */
    public void countScore(int score)
    {
        scoreCounter.add(score);   
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        HealthBar healthbar = new HealthBar();
        addObject(healthbar,498,45);
        healthbar.setLocation(489,25);
    }    
}