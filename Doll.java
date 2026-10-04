
import javax.swing.ImageIcon;
import javax.swing.JFrame; 
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout; 
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel; 
import java.awt.Graphics2D; 
import java.awt.Graphics; 
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Doll
{
	 static final double SPEED = 1.0;
	 static final int WIDTH = 110;
	 static final int LENGTH = 110;
	
	 double x,y;
	 int direction;
	
	 ImageIcon back, frontNorm, frontScan;
	ImageIcon toUse; 
	int start; 

	boolean facingBack;
	boolean facingFrontNorm;
	boolean scanning; 
	boolean scanDone; 
	boolean resetAudio; 

	File file;  
 	AudioInputStream audioStream; 
    Clip clip; 
        		
	
	public Doll(int x1, int y1, int direction1) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		x = x1;
		y = y1;
		direction = direction1;
		
		
		setDirection(direction1);
		
		
        back = new ImageIcon("backNorm.png");
        frontNorm = new ImageIcon("frontNorm.png"); 
        frontScan = new ImageIcon("frontScan.png"); 
		 toUse = back;
		
		 start = (int)System.currentTimeMillis();

		 facingBack = true; 
		 facingFrontNorm = false; 
		scanning = false; 
		scanDone = false; 
		resetAudio = true; 

		file = new File("dollSound.wav" );
		audioStream = AudioSystem.getAudioInputStream(file); 
		clip = AudioSystem.getClip();
		clip.open(audioStream);


		
	
				
	}

	

	//this method makes the doll turn around at intervals of time - ignore the bots parameter
	public void turn(Tank a, ArrayList<Bot> bots) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		

		
			if(resetAudio == true && a.gameOver == false )
			{
			
				audioStream = AudioSystem.getAudioInputStream(file); 
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.start();
				start = (int)System.currentTimeMillis(); 

				resetAudio = false; 
			}

			int current = (int)System.currentTimeMillis(); 

			if( current - start >= 6300 && facingBack == true )
			{
				
				toUse = frontNorm; 
				start = current; 
				facingFrontNorm = true; 
				facingBack = false; 
				 
			
			}
		
			if(current-start >= 300 && facingFrontNorm == true && scanDone == false)
			{
				
				toUse = frontScan; 
				start = current; 
				facingFrontNorm = false; 
				scanning = true; 
				
			}

			if(current - start <= 3500 && scanning == true )
			{
				if(a.moving == true &&  a.getY() > 180 )
				{
					a.moved = true; 
				}
			}

			if(a.moved)
			{
					if(a.startDeath == 0 )
					{
						a.startDeath = (int)System.currentTimeMillis(); 
					}
					a.endDeath = (int)System.currentTimeMillis(); 

					if(a.endDeath - a.startDeath >= 1000 )
					{
						a.alive = false;
					}
			}

			

			if(current-start >= 4000 && scanning == true)
			{
				 
			
				toUse = frontNorm; 
				start = current; 
				facingFrontNorm = true; 
				scanning = false; 
				scanDone = true; 
			
				
			}

			if(current-start >= 300 && facingFrontNorm == true && scanDone == true)
			{
				toUse = back; 
				start = current; 
				facingFrontNorm = false; 
				scanning = false; 
				scanDone = false; 
				facingBack = true; 
				resetAudio = true; 
				
			}

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
	public void draw(myJFrame frame, Graphics g)
	{
		
		
		
		g.drawImage(toUse.getImage(), getX()-WIDTH/2, getY()-LENGTH/2, WIDTH, LENGTH, frame);
	}

}
