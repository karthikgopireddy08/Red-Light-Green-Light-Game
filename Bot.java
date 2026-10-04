
import java.util.TimerTask;
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*; 


//IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS IGNORE THIS CLASS
public class Bot 
{
    

    KeyList KL; 
	 static double SPEED = 0.0;//this dictates how fast it will go
	 static  int WIDTH = 40;
	 static int LENGTH = 40; 
	static double acc = 0.0050;
	static double maxSPEED = 0.05; 

	Blood blood; 

	boolean alive; 
	boolean moved; 

	int start; 
	int start2; 
	 
	boolean moving; 
	ImageIcon toUse; 
	 double x,y;
	 int direction;

	 int startDeath; 
	 int endDeath; 

	 File file;  
 	AudioInputStream audioStream; 
    Clip clip; 

	boolean shotComplete; 

	int cycleStart; 
	
	 ImageIcon leftDown, rightDown, both, down;

	 int first; 
	 int second; 
	
	public Bot(int x1, int y1, int direction1, KeyList KL1)
	{

		first = (int)(Math.random()*1000)+6000; 
		second = (int)(Math.random()*1000)+10000;

		x = x1;
		y = y1;
		direction = direction1;
		KL = KL1; 
	
		startDeath = 0; 
		endDeath = 0; 
		setDirection(direction1);

		shotComplete = false; 
		moved = false; 
		
		leftDown = new ImageIcon("left.png");
		rightDown = new ImageIcon("right.png");
		both = new ImageIcon("both.png");

		down = new ImageIcon("both.png");

		moving = false;
		alive = true; 
		toUse = both; 
		start = (int)System.currentTimeMillis(); 
		start2 = 0; 
		cycleStart = (int)System.currentTimeMillis(); 
	 
	}
	public int getX()
	{
		return (int) x;
	}
	public int getY()
	{
		return (int) y;
	}
	public int getDirection()
	{
		return direction;
	}
	public void setDirection(int newDir)
	{
		direction = newDir;
		if(direction != 0 && direction != 90 && direction != 180 && direction != 270)
			direction = 0;
	}

	public void alternate()
	{

		if(start2 == 0)
		{
			start2 = (int)System.currentTimeMillis(); 
		}

		int current = (int)System.currentTimeMillis(); 

		if(current > start2 + 100 && toUse == both)
		{
		
			toUse = rightDown; 
			start2 = current; 
		}

		if(current > start2 + 100 && toUse == rightDown)
		{
			
			toUse = leftDown; 
			start2 = current; 
		}

		if(current > start2 + 100 && toUse == leftDown )
		{
		
			toUse = both; 
			start2 = current; 
		}

	}

	public void draw(myJFrame frame, Graphics g) throws LineUnavailableException, IOException, UnsupportedAudioFileException
	{

			 
					
			if(alive == false )
			{
				 
				toUse = new ImageIcon("deadPhase.png"); 
				g.drawImage(toUse.getImage(), getX()-WIDTH/2, getY()-LENGTH/2, WIDTH, LENGTH, frame);
				blood = new Blood(getX()-WIDTH/2+35, getY()-LENGTH/2+10, 0, KL );
				

					file = new File("gunShot.wav" );
				audioStream = AudioSystem.getAudioInputStream(file); 
				clip = AudioSystem.getClip();
				clip.open(audioStream);


				if(!shotComplete)
					{
						clip.start(); 
						shotComplete = true; 
					}
				return; 
			}

		if(moving == true )
		{
			alternate(); 
			
		}

		if(moving == false)
		{
			start2 = 0; 
			toUse = both; 
		}

	

		g.drawImage(toUse.getImage(), getX()-WIDTH/2, getY()-LENGTH/2, WIDTH, LENGTH, frame);
	}


	

	public void move(int minX, int minY, int maxX, int maxY)
	{
//		
		
		if(alive == false)
		{
			return; 
		}
	
		
		moving = false; 
	



		int movePhase = 0; 

		int current = (int)System.currentTimeMillis(); 


		if(current >= cycleStart + 10900)
		{
			cycleStart = current; 
		}

		if(current < cycleStart + first || current > cycleStart + 10600)
		{
			 movePhase = 1; 
		}

		
		if(getDirection() == 0 && (y-LENGTH/2 > minY)  )
		{

			
			if( movePhase == 1 )
			{
				if(Math.abs(SPEED) < maxSPEED - 0.01)
				{
					SPEED+=acc/2; 
				}
				
				y -= SPEED;
				moving = true; 

			}
	
		
		}

		

	
		
		
	}

	public void turnUp()
	{
		setDirection(0);
	}
	public void turnDown()
	{
		setDirection(180);
	}
}

