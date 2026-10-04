

import javax.swing.ImageIcon;
import javax.swing.JFrame; 
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout; 
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel; 
import java.awt.Graphics2D; 
import java.awt.Graphics; 
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Tree
{

    private static final double SPEED = 1.0;
	private static final int WIDTH = 150;
	private static final int LENGTH = 150;
	
	private double x,y;
	private int direction;
	
	private ImageIcon image;
	
	public Tree(int x1, int y1, int direction1)
	{
		x = x1;
		y = y1;
		direction = direction1;
		
		setDirection(direction1);
	
        
         image =  new ImageIcon("tree.png");
		
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
		ImageIcon toUse = image;
	
		
		g.drawImage(toUse.getImage(), getX()-WIDTH/2, getY()-LENGTH/2, WIDTH, LENGTH, frame);//how the image, location, and size are set
	}

}