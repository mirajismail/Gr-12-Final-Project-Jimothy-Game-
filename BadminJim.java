import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 

public class BadminJim {
	//constants
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2;

	private int damage;
	private int speed;
	private int x,y;
	private int jumpHeight = 100;
	private double vy;
	private int dir;
	private int WAIT = 7;
	private int swingWait = 10;
	private int timer = 0;
	
	Image playerPics[], swingPics[];
	
	
	private int frame = 0;
	private int swingFrame = 0;
	private int delay = 0;
	private int groundLevel;
	
	private boolean onGround = false;
	private boolean standing;
	private boolean swing = false;
	
	private boolean moveLeft, moveRight, moveDown;
	
    public BadminJim(int x, int y, int speed) {
    	this.x = x;
		this.y = y;
		groundLevel = y;
    	this.speed = speed;
		playerPics = new Image[8];
		swingPics = new Image[4];
    	//69-77
    	for(int i = 0; i<8; i++){
    		Image temp  = new ImageIcon("Assets/Sprites/badminJim/" + (i+69)+".png").getImage();
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);
			playerPics[i] = temp;
		}
		for(int i = 0; i<4; i++){
			Image temp  = new ImageIcon("Assets/Sprites/badminJim/" + (i+89)+".png").getImage();
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);
			swingPics[i] = temp;
		}
    }
    
    public void move(int d){
		if (d == RIGHT){
			dir = RIGHT;
			x += speed;
			delay += 1;
			if (delay%WAIT == 0){
				frame = (frame+1)%8;
			}
		}	
		else if (d == LEFT){
			dir = LEFT;
			if(x>710){
				x -= speed;
			}
			delay+=1;
			if (delay%WAIT == 0){
				frame = (frame+1)%8;
			}
		}
	}
    
	public void jump(boolean upPressed){
		onGround = (y == groundLevel);
		y+=vy;
		if (upPressed && onGround){
            vy = -7;
        }
        if (onGround == false){
			if(y < groundLevel-jumpHeight){
	            vy += 0.5;
			}
        }
        if(y >= groundLevel){
			y = groundLevel;
			onGround = true;
		}
	}
	
	public void swingAnim(){
		swing = true;
	}
    
    public void draw(Graphics g){
		if(swing){
			g.drawImage(swingPics[swingFrame], x, y, null);
			timer++;
			if(timer%swingWait == 0){
				swingFrame++;
				if(swingFrame>3){
					swing = false;
					swingFrame = 0;
				}
			}
		}
		else{
	        if(dir == LEFT){		
				g.drawImage(playerPics[frame],x,y,null);
			} 
        	else{
				int w = playerPics[frame].getWidth(null);
				int h = playerPics[frame].getHeight(null);
				g.drawImage(playerPics[frame], x + w, y, -w, h, null);
			}
		}
	}
    
    public int getHeight(){
        return playerPics[frame].getHeight(null);
    }
    
    public int getWidth(){
    	return playerPics[frame].getWidth(null);
    }
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }

	public int getDir(){
		return dir;
	}
	
    public void setX(int set){
    	x = set;
    }
    
    public void setY(int set){
    	y = set;
    }
    public void setDir(int set){
		dir = set;
	}
  
}