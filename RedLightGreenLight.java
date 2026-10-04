
//  __________
// |          |		Using JFrame and JPanel Tutorial
// |          |		By Evan Koschik	
// |      ____|___            _________       _____      ___  
// |     |___\    \          /    /    \     |     \    |   |
// |         |\    \        /    /      \    |      \   |   |
// |      ___| \    \      /    /   /\   \   |       \  |   |
// |     |      \    \    /    /          \  |    _   \_|   |
// |     |_____  \    \__/    /     __     \ |   | \        |
// |           |  \          /     /  \     \|   |  \       |
// |           |   \        /     /    \     |   |   \      |
// |___________|    \______/_____/      \____|___|    \_____|

//I GOT HELP FROM BRO CODE ON YOUTUBE
//I MODIFIED THE MOVING THE TANK TUTORIAL


import java.util.ArrayList;
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


//TANK REFERS TO THE PLAYER - I JUST DIDN'T CHANGE THE NAME
class MAIN
{	
	
	//I GOT HELP FROM STACKOVERFLOW
	public static void main(String[] args) throws Exception
	{	
		
		myJFrame frame = new myJFrame();	



		
		frame.show();

	
	


		try
		{
			Thread.sleep(10000); //delay so you can read the instructions
		}
		catch(InterruptedException e )
		{
			
		}
		frame.panel.instLabel.setVisible(false); //takes away the instructions
		

		

		frame.startGame();

		
	}
}

class myJFrame extends JFrame
{
	myJPanel panel;
	KeyList KL; 
	Container container; 
	

	public myJFrame() throws Exception, Exception, LineUnavailableException
	{

		
		
		this.setUndecorated(true); 
		this.setVisible(true); 
		this.setResizable(false); //I GOT HELP FROM STACKOVERFLOW
		setSize(new Dimension(800, 1100));
			
			
		
		

		
		
		
		KL = new KeyList();
		addKeyListener(KL);
		
	
		panel = new myJPanel(KL, this);

		

		container = getContentPane();
		container.add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		

		 
		
		
			


		repaint();
		
	}
	public void startGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		
	

		
		panel.startGame();
	}
	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			System.exit(0);
		}
	}
	
}
class KeyList implements KeyListener
{
	boolean up, down, left, right, enter;
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case 27:/* esc */			
			case KeyEvent.VK_E:		System.exit(0);
			
			case KeyEvent.VK_LEFT:
				left = true; 

				break;
			case KeyEvent.VK_RIGHT:		
				right= true;	
				break;
			case KeyEvent.VK_UP:		
				up = true;		
				break;
			case KeyEvent.VK_DOWN:		
				down = true;	
				break;
			case KeyEvent.VK_ENTER:		
				enter = true;	
				break;
		}
		
	}
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:		
				left = false;
					break;
			case KeyEvent.VK_RIGHT:		
				right= false;	
				break;
			case KeyEvent.VK_UP:	
				up = false;		
				break;
			case KeyEvent.VK_DOWN:	
				down = false;	
				break;
			case KeyEvent.VK_ENTER:	
				enter = false;	
				break;
		}
	}
		
	public void keyTyped(KeyEvent e){}
}	
//I GOT HELP FROM GEEKsFORGEEKS
class myJPanel extends JPanel implements ActionListener
{
	JLabel instLabel; 

	KeyList KL;
	myJFrame frame;
	
	Tank tank;
	Doll doll; 
	Guard pinkGuard1; 
	Guard pinkGuard2; 
	
	Tree tree; 

	TimerTask task;
	 
	int current; 
	JLabel timer; 
	ArrayList<Bot> bots; 
	ArrayList<Bot> temp;

	int startPaint; 
	int endPaint; 
	
	int blackStart; 
	int blackEnd; 

	public myJPanel(KeyList KL1, myJFrame frame1) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{

		//start and end variables for the black screen at the end
		blackStart = 0; 
		blackEnd = 0; 

		startPaint = (int)System.currentTimeMillis(); 
		KL = KL1;		//now I have the pointer to the Key Listener in the panel
		frame = frame1;	//now I have the pointer to the Key Listener in the frame
		
		//set Background Color
		setBackground(new Color(250, 235, 215) );//I got the RGB code from Figma
		
		setPreferredSize(frame.getSize()) ; 

		//makes the player start at a random starting position in the bounds
		int xInit = (int)(Math.random()*600)+100; 
		int yInit = (int)(Math.random()*100) + 900; 

		tank = new Tank(xInit, yInit , 0, KL);

		

		
	
		pinkGuard1 = new Guard(300, 128, 0); 
		pinkGuard2 = new Guard(500, 128, 0); 
		tree = new Tree(400, 80, 0); 
		doll = new Doll(400, 100, 0); 
		
		
		timer = new JLabel(); 
		
		this.add(timer); 
		 		
	
		


		//instructions for the game
		ImageIcon inst = new ImageIcon("inst3.png"); 


		 instLabel = new JLabel(); 
		instLabel.setSize(800, 1100 ); 
		instLabel.setIcon(inst); 
		instLabel.setVisible(true); 
		instLabel.setOpaque(true); 
		this.add(instLabel); 
		instLabel.setVerticalAlignment(JLabel.CENTER ); 
		instLabel.setHorizontalAlignment(JLabel.CENTER ); 
	
		 

		
	}
	//I GOT HELP FROM TUTORIALSPOINT	
 	public void actionPerformed(ActionEvent e)
	{

	}

	//I GOT HELP FROM RYISNOW ON YOUTUBE
	public void startGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException//Panel's start game method
	{

		int start = (int)System.currentTimeMillis(); 


		int beginning = (int)System.currentTimeMillis();
        timer.setHorizontalTextPosition(JLabel.CENTER); 
        timer.setVerticalTextPosition(JLabel.CENTER);

		timer.setHorizontalAlignment(JLabel.RIGHT );
		timer.setVerticalAlignment(JLabel.TOP );

				

		while(true)
		{


			
			int totalTime = 35; 
			int end = (int)System.currentTimeMillis()/1000;
			int displayTime = 0; 
			int temp = 0; 
			temp = totalTime-(end - beginning/1000);//calculated time left that will be displayed

			if(temp >= 0)
			{
				displayTime = temp; 
			}
			  

			String str = ""; 
			
			
				 str = "a" + "0:" +  (displayTime);
			//uses a string so that the int can be in the timer
			 
			str = str.substring(1); 
			timer.setText(  str);

			if(displayTime <= 0)//if the game is done
			{
				

			

				if(tank.getY() > 180 )
				{
					tank.alive = false; 
				}
				if(tank.alive == true)
				{
					tank.won = true; 
				}
				tank.gameOver = true; 
			}
			doll.turn(tank, bots); 

			 

		

				repaint();
			
			
			 if(tank.gameOver == true && tank.won == true)
			 {
				try
				{
					Thread.sleep(5000); 
				}
				catch(InterruptedException e)
				{

				}
				System.exit(0); 
			 }
			
			
			
		}
	}

	int printed = 0;

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
	
		

		//I am writing most of the game itself in this method.  Why? Cus Evan Rocks.
		
		Graphics2D g2D = (Graphics2D)g; 
		

		//paints the finish line
		g2D.setPaint(Color.pink);
		g2D.setStroke(new BasicStroke(10) ); 
		g2D.drawLine(0, 180, 800, 180); 

		
		
		tank.move(0, 0, 800, 1100);//sets the limitations for the movement

		


		

		if(tank.alive == false )
		{
			try {
				tank.draw(frame, g);//draw player
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			tank.blood.draw(frame, g); //draws blood
		}
		try {
			tank.draw(frame, g);//draw player again
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// boolean print = true; 
		 
	
	
		
		 
		
		
		pinkGuard1 .draw(frame, g); 
		pinkGuard2.draw(frame, g); 

		
		tree.draw(frame, g); 
		doll.draw(frame, g); 
		
		
		

		
		
		
		if(KL.up)
			tank.turnUp();
		if(KL.down)
			tank.turnDown();

		if(tank.gameOver == true)
		{
			
			if(blackStart == 0)
			{
				blackStart = (int)System.currentTimeMillis();
			}
			blackEnd = (int)System.currentTimeMillis();

			
			//draws the black end screen
			if(blackEnd >= blackStart + 2000 )
			{
				g2D.setPaint(Color.black); 
				// g2D.setStroke(new BasicStroke(1000) ); 
				g2D.drawRect(0, 0, 800, 1100 ); 
				g2D.fillRect(0, 0, 800, 1100); 
			
			}

			if(tank.won == false)
			{
				if(blackEnd >= blackStart + 2500 )
				{
				try {
					startVoice();//announcer announces elimination
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				}
			}

			//victory end screen
			if(tank.won == true )
			{
				ImageIcon endIcon = new ImageIcon("victory2.png"); 


				 instLabel = new JLabel(); 
				instLabel.setSize(800, 1100 ); 
				instLabel.setIcon(endIcon); 
				instLabel.setVisible(true); 
				instLabel.setOpaque(true); 
				this.add(instLabel); 
				instLabel.setVerticalAlignment(JLabel.CENTER ); 
				instLabel.setHorizontalAlignment(JLabel.CENTER ); 

			
			}
			
				
			
		}
	}


	//announcer announces elimination
	public void startVoice() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		File file3 = new File("eliminatedVoice.wav" );

		AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(file3); 
				Clip clip3 = AudioSystem.getClip();
				clip3.open(audioStream3);

				clip3.start(); 

				try
				{
					Thread.sleep(5000); 
				}
				catch(InterruptedException e)
				{

				}
				System.exit(0); 
	}
}

class Tank
{
	KeyList KL; 
	 static double SPEED = 0.0;//this dictates how fast it will go
	 static  int WIDTH = 40;
	 static int LENGTH = 40; 
	static double acc = 0.005;
	static double maxSPEED = 0.05; 

	boolean won; 
	Blood blood; 
	boolean gameOver; 

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

	
	 ImageIcon leftDown, rightDown, both, down;
	
	public Tank(int x1, int y1, int direction1, KeyList KL1)
	{
		won = false; 
		gameOver = false; 
		x = x1;
		y = y1;
		direction = direction1;
		KL = KL1; 
	
		startDeath = 0; //time when death starts
		endDeath = 0; //time when death ends
		setDirection(direction1);

		shotComplete = false; 
		moved = false; 
		

		//different phases for the player's movement
		leftDown = new ImageIcon("left.png");
		rightDown = new ImageIcon("right.png");
		both = new ImageIcon("both.png");

		down = new ImageIcon("both.png");

		moving = false;
		alive = true; 
		toUse = both; 
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

	//method that makes the legs look like they're running
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
				 
				toUse = new ImageIcon("deadPhase.png"); //switches to looking dead
				g.drawImage(toUse.getImage(), getX()-WIDTH/2, getY()-LENGTH/2, WIDTH, LENGTH, frame);
				blood = new Blood(getX()-WIDTH/2+35, getY()-LENGTH/2+10, 0, KL );//spawns blood
				


					file = new File("gunShot.wav" );
				audioStream = AudioSystem.getAudioInputStream(file); 
				clip = AudioSystem.getClip();
				clip.open(audioStream);


				if(!shotComplete)
					{
						clip.start(); //plays shot sound
						shotComplete = true; 
					}
				gameOver = true; 
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
	
		if(Math.abs(SPEED) > 0.0005)//definition of a player moving
		{
			
			moving = true;
		 
		}
		moving = false; 
	

		if(getDirection() == 0 && (y-LENGTH/2 > minY)  )//if facing up and in bounds
		{

			if( KL.up == true )
			{
				if(Math.abs(SPEED) < maxSPEED - 0.01)
				{
					SPEED+=acc; //speeds up in this direction
				}
				
				y -= SPEED;
				moving = true; 

			}
		
			if(KL.up == false && SPEED >= 0 )
			{
				if(Math.abs(SPEED) < maxSPEED )
				{
					SPEED-=acc/8; 
				}
				
				y-=SPEED; 
				moving = true; 
			}
		
		}

		if(getDirection() == 180 && (y+LENGTH/2 < maxY)  )//if facing down and in bounds
		{
			if( KL.down == true ) 
			{

				if(Math.abs(SPEED) < maxSPEED - 0.01)
				{
					SPEED-=acc; //speeds up in this direction
				}
				
				
				y -= SPEED;
					moving = true; 
			}
			if(KL.down == false && SPEED  <= 0 )
			{

				if(Math.abs(SPEED) <maxSPEED)
				{
					SPEED+=acc/2; 
				}

				y += SPEED;
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