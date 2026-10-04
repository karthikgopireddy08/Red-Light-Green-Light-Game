
import java.util.TimerTask;
import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*; 

    class Blood
{
	KeyList KL; 

	 static  int WIDTH = 40;
	 static int LENGTH = 40; 


	int start; 
	int start2; 
	 

	
	ImageIcon toUse; 
	 double x,y;
	 int direction;

	
	 ImageIcon image; 
	
	public Blood(int x1, int y1, int direction1, KeyList KL1)
	{
		x = x1;
		y = y1;
		direction = direction1;
		KL = KL1; 
		
		setDirection(direction1);
		
	
		image = new ImageIcon("bloodToUse.png"); 
		start = (int)System.currentTimeMillis(); 
		start2 = 0; 
		
	 
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

			

		
		toUse = image; 

		g.drawImage(toUse.getImage(), getX()-WIDTH/2, getY()-LENGTH/2, WIDTH, LENGTH, frame);
	}
	public void move(int minX, int minY, int maxX, int maxY)
	{
//		
		
		
	
		

		
	}
	
}
