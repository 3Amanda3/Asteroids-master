import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private GreenfootImage frame = new GreenfootImage(200,30);
    private GreenfootImage healthBar = new GreenfootImage(200,30);
    
    private Color good;
    private Color warning;
    private Color danger;
    
    private int max;
    private int current;
    private int speed;
    private int target;
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(current > target)
        {
            current = current - speed;  
            
            if(current <= target)
            {                        
                current = target;                
            }
        }        
        else
        {
            current = current + speed;
            
            if(current >= target)
            {                        
                current = target;                
            }
        }
        
        updateBar();
        checkLost();
    } 
    
    /**
     * HealthBar will make the frame and set the colours and variables to there abreations
     * @Param there are no parameters
     * @Return nothing is returned
     */
    public HealthBar()
    {                                              
        frame.setColor(Color.GRAY);
        frame.fillRect(0,0,200,30);
                
        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;
        
        max = 5;
        current = 5;
        speed = 1;
        target = current;
        
        updateBar();
    }
    
    /**
     * HealthBar will make the frame and set the colours and variables to there abreations
     * @Param is the integer m, c, s
     * @Return nothing is returned
     */
    public HealthBar(int c,  int m, int s)
    {
        frame.setColor(Color.GRAY);
        frame.fillRect(0,0,200,30);
               
        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;
        
        max = m;
        current = c;
        target = current;
        speed = s;
        
        updateBar();
    }
    
    /**
     * HealthBar will make the frame and set all the colour and varaibles nad update the bar
     * @Param is the integers m, c, and s and the Colors g, w, and d.
     * @Return nothing is returned
     */
    public void HealthBar(int c,  int m, int s, Color g, Color w, Color d)
    {
        frame.setColor(Color.GRAY);
        frame.fillRect(0,0,200,30);
               
        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;
        
        good = g;
        warning = w;
        danger = d;
        
        max = m;
        current = c;
        target = current;
        speed = s;
        
        updateBar();
    }
    
    /**
     * updateBar will set the colour of health bar acording to the amount current is at and it will
     * put text on the healthBar
     * @Param there are no parameters
     * @Return nothing is returned
     */
    private void updateBar()
    {
        GreenfootImage text = new GreenfootImage(200,30);
        double ratio = current / (max * 1.0);
        int healthWidth = (int)Math.round(ratio * frame.getWidth());
        
        if(current >= max / 2)
        {
            healthBar.setColor(good);
        }
        else if(current <=max / 2 && current >=max /4)
        {
           healthBar.setColor(warning);
        }
        else
        {
           healthBar.setColor(danger);        
        }
        
        healthBar.clear();
        healthBar.fillRect(0,0, healthWidth, 30);
        
        text.clear();
        text.setColor(Color.BLACK);        
        text.setFont( new Font("Times New Roman",Font.PLAIN,20) );
        text.drawString(current+"/"+max,0,30-text.getFont().getSize()/2);      
        
        frame.clear();
        frame.setColor(Color.GRAY);
        frame.fillRect(0,0,200,30);                
        frame.drawImage(healthBar,0,0);
        frame.drawImage(text,frame.getWidth()/3,0);
        
        setImage(frame);
    }
    
    /**
     * add will make target = to target added to current and check to see if its at the max or min
     * so it changes the score
     * @Param is the integer change
     * @Return nothing is returned
     */
    public void add(int change)
    {
      target = target + change;  
      
      if(target > max)
      {
          target = max;    
      }
      if(target<0)
      {
          target = 0;
      }
    }
    
    /**
     * setTarget will set target = t
     * @Param is the integer t
     * @Return nothing is returned
     */
    public void setTarget(int t)
    {
       target = t; 
    }
    
    /**
     * setCurrent will set current = c
     * @Param is the integer c
     * @Return nothing is returned
     */
    public void setCurrent(int c)
    {
        current = c;
    }
    
    /**
     * setSpeed will set speed = s
     * @Param is the integer s
     * @Return nothing is returned
     */
    public void setSpeed(int s)
    {
        speed = s;
    }
    
    /**
     * setMax will set max = m
     * @Param is the integer m
     * @Return nothing is returned
     */
    public void setMax(int m)
    {
        max = m;
    }
    
    /**
     * getMax will return the max
     * @Param there are no parameters
     * @Return max gets returned
     */
    public int getMax()
    {
        return max;
    }
    
    /**
     * getCurrent will return the current 
     * @Param there are no parameters
     * @Return current gets returned
     */
    public int getCurrent()
    {
        return current;
    }
    
    /**
     * checkLost will check to see if current is less than 0 and display a message and stop the 
     * program
     * @Param there are no parameters
     * @Return nothing is returned
     */
    private void checkLost()
    {
       if(current == 0)
       {
          getWorld().showText("GAME OVER!",getWorld().getWidth()/2,getWorld().getHeight()/2);
          Greenfoot.stop();
       }
    }

}
