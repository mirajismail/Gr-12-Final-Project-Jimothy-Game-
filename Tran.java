import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*;
import java.util.*;

public class Tran {
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2;
	private int x, y, speed, dir, delay = 0, WAIT = 7, swingWait = 10, frame = 0, swingFrame = 0, timer = 0;
	
	public boolean swing = false;
	Image tranPics[], swingPics[];

	
    public Tran(int x, int y, int speed) {
 	   	this.x = x;
 	   	this.y = y;
		this.speed = speed;
		tranPics = new Image[8];
		swingPics = new Image[4];
    	for(int i = 0; i<8; i++){
    		Image temp  = new ImageIcon("Assets/Sprites/Tran/" + i +".png").getImage();
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);
			tranPics[i] = temp;
		}
		for(int i = 0; i<4; i++){
			Image temp  = new ImageIcon("Assets/Sprites/Tran/" + (i+18) +".png").getImage();
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);
			swingPics[i] = temp;
		}
	}
    
    public void move(int xFinal){
		if(x>xFinal-10 && x<xFinal+10){
			dir = RIGHT;
			return;
		}

    	if (xFinal > x){
			x+=speed;
			dir = RIGHT;
			delay+=1;
			if (delay%WAIT == 0){
				frame = (frame+1)%8;
			}
		}
    	
    	else if (xFinal < x){
			x-=speed;
			dir = LEFT;
			delay+=1;
			if (delay%WAIT == 0){
				frame = (frame+1)%8;
			}
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
			if(dir == RIGHT){
				int w = tranPics[frame].getWidth(null);
				int h = tranPics[frame].getHeight(null);
				g.drawImage(tranPics[frame], x + w, y, -w, h, null);
			}
	        else{
				g.drawImage(tranPics[frame],x,y,null);
			} 
        	
		}
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