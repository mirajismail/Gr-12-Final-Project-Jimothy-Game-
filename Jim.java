import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*;

public class Jim{
	//constants
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2;

	private int damage;
	private int speed;
	private int x,y;
	private int jumpHeight = 100;
	private double vy;
	private int dir;
	private int WAIT = 7;
	
	Image playerPics[],back, crouch[];
	
	
	private int frame = 0;
	private int delay = 0;
	private int groundLevel;
	
	private boolean onGround = false;
	private boolean standing;
	private boolean swing = false;
	
	private boolean moveLeft, moveRight, moveDown;
	
    public Jim(int x, int y, int speed) {
    	this.x = x;
		this.y = y;
		groundLevel = y;
    	this.speed = speed;
    	
		playerPics = new Image[18];
		crouch = new Image[4];
		
    	
    	for(int i = 0; i<18; i++){
    		Image temp  = new ImageIcon("Assets/Sprites/JimWalk/" + (i+69)+".png").getImage();
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);
			playerPics[i] = temp;
		}
		for(int i = 0; i<4; i++){
    		Image temp  = new ImageIcon("Assets/Sprites/Jim/" + (i+172)+".png").getImage();
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);
			crouch[i] = temp;
		}
    }
    
    public void move(int d){
		if(d == DOWN){
			if(onGround){
				dir = DOWN;
				y = 580;
			}		
		}
		else{
			if (d == RIGHT){
				dir = RIGHT; 
				if(x<1070){
					x += speed;
				}
				delay += 1;
				if (delay%WAIT == 0){
					frame = (frame+1)%9;
				}
			}
			
			else if (d == LEFT){
				dir = LEFT;
				if(x>10){
					x -= speed;
				}
				delay+=1;
				if (delay%WAIT == 0){
					frame = (frame+1)%9;
				}
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
    
    public void attack(boolean jPressed){
    	if(jPressed){
    		swing = true;
    	}
    	swing = false;		
    }
    
    public void draw(Graphics g){
		if(dir == DOWN){
			g.drawImage(crouch[3],x,y,null);	
		}
		else{
			g.drawImage(playerPics[frame+(dir*9)],x,y,null);
		}
    }
    
    public int getHeight(){
    	if(dir == DOWN){
			return crouch[3].getHeight(null);
		}
    	return playerPics[frame+(dir*9)].getHeight(null);
    }
    
    public int getWidth(){
		if(dir == DOWN){
			return crouch[3].getWidth(null);
		}
    	return playerPics[frame+(dir*9)].getWidth(null);
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