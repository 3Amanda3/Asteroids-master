import greenfoot.*;

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.1
 */
public class Rocket extends SmoothMover
{
    private static final int gunReloadTime = 5;         // The minimum delay between firing the gun.
    private static final int WAVE_RELOAD_TIME = 500;
    
    private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private int waveDelayCount;
    
    private GreenfootImage rocket = new GreenfootImage("rocket.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");

    /**
     * Initialise this rocket.
     */
    public Rocket()
    {
        Vector startMotion = new Vector( getRotation(), 0.7 );
        addToVelocity(startMotion);
        
        reloadDelayCount = 5;
        
        waveDelayCount = 500;
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {       
        move();
        checkKeys();
        reloadDelayCount++; 
        waveDelayCount++;
        checkCollision(); 
        checkWin();
    }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        if (Greenfoot.isKeyDown("space")) 
        {
            fire();
        }
        
        if(Greenfoot.isKeyDown("left"))
        {
            turn(-5);
        }
        
         if(Greenfoot.isKeyDown("right"))
        {
            turn(5); 
        }
        
        if(Greenfoot.isKeyDown("z"))
        {
           startProtonWave(); 
        }
        
        ignite( Greenfoot.isKeyDown("up") );
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet bullet = new Bullet (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
    /**
     * startProtonWave will create a new proton wave and add it to the world if the charge has been reloaded
     * @param there is no parameters
     * @return nothing is returned
     **/
    private void startProtonWave()
    {
        if( waveDelayCount >= WAVE_RELOAD_TIME)
        {
            ProtonWave wave = new ProtonWave();
            getWorld().addObject( wave, getX(), getY() );
            waveDelayCount = 0;
        }
    }
    
    /**
     * ignite will make the rocket go faster nad change the image of rocket
     * @param boosterOn is a boolean that will check if true make it go faster
     * @return nothing is returned
     */
    private void ignite ( boolean boosterOn )
    {
        if( boosterOn == true)
        {
            setImage( rocketWithThrust );
            addToVelocity( new Vector( getRotation(), 0.3) );
        }
        
        else
        {
            setImage( rocket );
        }
    }
    
    /**
     * checkCollision will check if an asteriod has hit the rocket and create an explosion accordinly
     * @param nothing is returned
     * @return there are no parameters
     */
    private void checkCollision()
    {
        Space world = (Space)getWorld();       
        Asteroid currentAsteroid = (Asteroid)getOneIntersectingObject(Asteroid.class);
        HealthBar bar = getWorld().getObjects(HealthBar.class).get(0);
        
        if(currentAsteroid != null )
        {
           bar.add(-1);
           world.removeObject(currentAsteroid);
           world.addObject( new Explosion(),getX(),getY() );
           if(bar.getCurrent() == 0)
           {                          
            world.removeObject( this );
            world.gameOver(); 
           }
        }                
    }
    
    /**
     * checkWin will check to see if there are any asteroid left in the world and stop the game when there are none
     * @param there are no parameters
     * @return nothing is returned
     */
    private void checkWin()
    {
         Space world = (Space)getWorld();
         
         if(world.getObjects(Asteroid.class) == null)
         {
            world.gameOver(); 
         }
    }
}
   