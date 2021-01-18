import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.*; 
import java.util.*;
public class MainGame extends JFrame{
	javax.swing.Timer myTimer;
	//CREATING JPanels
	TopDown game;//top down game
	CutScene intro, intro2, Scene2, Scene3, Scene4, Scene5, Scene6, Scene7, Scene8, Scene9, Scene10, Scene11, Scene12, EndGame;//cutscenes
	Badminton badminton;//badminton minigame
	Treb treb;//physics minigame
	DodgeBall dodgeBall;//dodgeball minigame
	Trig trig;//math minigame
	ComSci cs;
	JPanel cards;//a panel that uses CardLayout
	private int currentScene = 0;//indicates which scene the game is on
	private int[] marks = new int[5];//array that holds marks gained in each class
	private boolean[] keys;
    CardLayout cLayout = new CardLayout(); //cardlayout
    public MainGame() {//constructor for Jframe
		super("The Life of Jimothy: Road to Waterloo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,720);

		myTimer = new javax.swing.Timer(10, new TickListener());	 // trigger every 100 ms
		myTimer.start();
		keys = new boolean[KeyEvent.KEY_LAST+1];
		addKeyListener(new moveListener());
		intro = new CutScene(0, 7, true, keys);//declaring all of the cutscenes
		intro2 = new CutScene(1, 2, false, keys);
		Scene2 = new CutScene(2, 10, false, keys);
		Scene3 = new CutScene(3, 15, false, keys);
		Scene4 = new CutScene(4, 6, false, keys);
		Scene5 = new CutScene(5, 3, false, keys);
		Scene6 = new CutScene(6, 4, false, keys);
		Scene7 = new CutScene(7, 7, false, keys);
		Scene8 = new CutScene(8, 5, false, keys);
		Scene9 = new CutScene(9, 5, false, keys);
		Scene10 = new CutScene(10, 4, false, keys);
		Scene11 = new CutScene(11, 3, false, keys);
		Scene12 = new CutScene(12, 4, false, keys);
		EndGame = new CutScene(13, 3, false, keys);
		EndGame.setEnd();//indicates that EndGame willl be the last CutScene

		game = new TopDown(keys);//Declaring all minigames and topdown
		badminton = new Badminton(keys);
		treb = new Treb(keys);
		dodgeBall = new DodgeBall(keys);
		trig = new Trig(keys);
		cs = new ComSci(keys);
		cards = new JPanel(cLayout);

		cards.add(intro, "intro");//adding all scenes to cardlayout
		cards.add(intro2, "intro2");
		cards.add(game, "td");
		cards.add(Scene2, "CutScene2");
		cards.add(Scene3, "CutScene3");
		cards.add(badminton, "badminton");
		cards.add(Scene4, "CutScene4");
		cards.add(Scene5, "CutScene5");
		cards.add(Scene6, "CutScene6");
		cards.add(treb, "treb");
		cards.add(Scene7, "CutScene7");
		cards.add(trig, "trig");
		cards.add(Scene8, "CutScene8");
		cards.add(Scene9, "CutScene9");
		cards.add(dodgeBall, "dodgeball");
		cards.add(Scene10, "CutScene10");
		cards.add(Scene11, "CutScene11");
		cards.add(Scene12, "CutScene12");
		cards.add(cs, "cs");
		cards.add(EndGame, "EndGame");
		
		add(cards);
		setResizable(false);
		setVisible(true);
	}
	class moveListener implements KeyListener{
	    public void keyTyped(KeyEvent e) {
		}
	
	    public void keyPressed(KeyEvent e) {
			keys[e.getKeyCode()] = true;
	    }
	    
	    public void keyReleased(KeyEvent e) {
	        keys[e.getKeyCode()] = false;
	    }
    }
	class TickListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
			if(game!= null && game.ready){
				//scene selector
				//based of the variable 'current scene' a cutscnene or minigame is chosen and displayed in the card layout
				//the functions of that scene are then called
				//when then scene is over the variable 'currentScene' is increased, which then displays the next scene
				if(currentScene == 0){
					cLayout.show(cards, "intro");//cutscene functions same for evey cutscene
					if(intro.sceneFinished()){//if scene finishde
						currentScene = 1;//next scene is displayed
					}
					intro.repaint();
				}
				if(currentScene == 1){
					cLayout.show(cards, "intro2");
					if(intro2.sceneFinished()){
						currentScene = 2;
					}
					intro2.repaint();
				}
				if(currentScene == 2){
					cLayout.show(cards, "td");//all topdown scene functions are same
					game.move();//moves character
					game.checkEntrances();//checks for entrance triggers
					game.repaint();//draws scene
					if(game.checkCutScene()){//checks for cutscnene trigger
						currentScene +=1;//if cutscnene found, next scene is played
					}
				}
				if(currentScene ==  3){
					cLayout.show(cards, "CutScene2");
					if(Scene2.sceneFinished()){
						currentScene++;
						game.setMap("Outside");
					}
					Scene2.repaint();
				}
				if(currentScene == 4){
					cLayout.show(cards, "td");
					game.move();
					game.checkEntrances();
					game.repaint();
					if(game.checkCutScene()){
						currentScene++;
					}	
				}
				if(currentScene == 5){
					cLayout.show(cards, "CutScene3");
					if(Scene3.sceneFinished()){
						currentScene++;
					}
					Scene3.repaint();
				}
				if(currentScene == 6){
					cLayout.show(cards, "badminton");
					badminton.gameController();//controls badminton game
					badminton.move();//moves character
					badminton.repaint();
					if(badminton.gameFinished){//if finished
						marks[0] = 100;//player can only get 100% or retry in this game
						currentScene++;//next scene played when done
					}
				}
				if(currentScene == 7){
					cLayout.show(cards, "CutScene4");
					if(Scene4.sceneFinished()){
						currentScene++;
					}
					Scene4.repaint();
				}
				if(currentScene == 8){
					cLayout.show(cards, "td");
					game.move();
					game.checkEntrances();
					game.repaint();
					if(game.checkCutScene()){
						currentScene++;
					}	
				}
				if(currentScene == 9){
					cLayout.show(cards, "CutScene5");
					if(Scene5.sceneFinished()){
						currentScene++;
					}
					Scene5.repaint();
				}
				if(currentScene == 10){
					cLayout.show(cards, "treb");
					if(!treb.gameOver){//if game isnt over
						if(!treb.levelOver){//if level isnt over
							treb.move();//moves trebuchet
							treb.arrowController();//controls arrows
							treb.arrowCollision();//checks arrow collisions
							treb.gameController();//controls treb game
						}
						else{
							treb.nextLevel();//if level is over, next level is run
						}
					}
					treb.repaint();//drawing game
					if(treb.finished){//if player is finished game
						currentScene++;//the next scene is player
						marks[1] = treb.getMark();
					}
				}
				if(currentScene == 11){
					cLayout.show(cards, "CutScene6");
					if(Scene6.sceneFinished()){
						currentScene++;
						game.resetMap();
					}
					Scene6.repaint();
				}
				if(currentScene == 12){
					cLayout.show(cards, "td");
					game.move();
					game.checkEntrances();
					game.repaint();
					if(game.checkCutScene()){
						currentScene++;
					}	
				}
				if(currentScene == 13){
					cLayout.show(cards, "CutScene7");
					if(Scene7.sceneFinished()){
						currentScene++;
					}
					Scene7.repaint();
				}
				if(currentScene == 14){
					cLayout.show(cards, "trig");
					if (!trig.gameOver){//if game isnt over
						if(!trig.levelOver()){//if level isnt over
							trig.gameController();//runs game
							trig.move();//moves ball
						}
						else{
							trig.changeLevel();//if level is over, changes to next level
						}
					}
					trig.repaint();//draws trig game
					if(trig.gameFinished){//if game is over
						currentScene++;//next scene is played
						marks[2] = trig.getMark();//mark is added to average
					}
				}
				if(currentScene == 15){
					cLayout.show(cards, "CutScene8");
					if(Scene8.sceneFinished()){
						currentScene++;
						game.resetMap();
					}
					Scene8.repaint();
				}
				if(currentScene == 16){
					cLayout.show(cards, "td");
					game.move();
					game.checkEntrances();
					game.repaint();
					if(game.checkCutScene()){
						currentScene++;
					}	
				}
				if(currentScene == 17){
					cLayout.show(cards, "CutScene9");
					if(Scene9.sceneFinished()){
						currentScene++;
					}
					Scene9.repaint();
				}
				if(currentScene == 18){
					cLayout.show(cards, "dodgeball");//dodgeball game
					if(!dodgeBall.gameOver){//is game isnt over
						dodgeBall.controlBalls();//controls balls and moves them
						dodgeBall.checkCollisions();//checks for collisions
						dodgeBall.move();//moves character
						dodgeBall.gameController();//controls dodgeball game
					}
					dodgeBall.repaint();//draws game
					if(dodgeBall.finished){//if game is done
						marks[3] = dodgeBall.getMark();//adds mark to average
						currentScene++;//moves on to next scene
					}
				}
				if(currentScene == 19){
					cLayout.show(cards, "CutScene10");
					if(Scene10.sceneFinished()){
						currentScene++;
						game.resetMap();
					}
					Scene10.repaint();
				}
				if(currentScene == 20){
					cLayout.show(cards, "td");
					game.move();
					game.checkEntrances();
					game.repaint();
					if(game.checkCutScene()){
						currentScene++;
					}
				}
				if(currentScene == 21){
					cLayout.show(cards, "CutScene11");
					if(Scene11.sceneFinished()){
						currentScene++;
						game.resetMap();
					}
					Scene11.repaint();
				}
				if(currentScene == 22){
					cLayout.show(cards, "cs");
					if (!cs.gameOver){
						cs.move();
						cs.collisions();
						cs.attackController();
						cs.controlProjectiles();
						cs.gameController();
						cs.checkMckCollisions();
					}
					cs.repaint();
					if(cs.gameFinished){
						marks[4] = cs.getMark();
						currentScene++;
					}
					
				}
				if(currentScene == 23){
					cLayout.show(cards, "CutScene12");
					if(Scene12.sceneFinished()){
						currentScene++;
						game.resetMap();
						EndGame.setMarks(marks);
					}
					Scene12.repaint();
				}
				if(currentScene == 24){
					cLayout.show(cards, "EndGame");
					EndGame.repaint();
				}
			}
		}
	}
    public static void main(String[] arguments) {
		MainGame frame = new MainGame();		
    }
}

class TopDown extends JPanel{//top down adventure part of game
	public boolean ready=false;
	public final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;
	private MainGameCharacter jim;//top down character class
	private boolean[] keys;
	private int speed = 3, gameState = 0;//speed is character's speed, gameState is the current objective the player is on; maps and triggers will change based on gameState
	private BufferedImage mask;
	private String currentMap = "Outside";//variable that holds current map name
	private Hashtable<Integer, String> entrances = new Hashtable<Integer, String>();//holds mask rgb value for every entrance trigger
	private Hashtable<String, int[]> mapStartLocations = new Hashtable<String, int[]>();
	private String[] cutSceneLocations = new String[]{"BusinessHall", "FrontRow", "TopRow", "MathHall", "TopRow", "BusinessHall"};//locations where all cutscenes are triggered
	Image map = new ImageIcon("Assets/Maps/0/Outside.png").getImage();//loading map of Massey
	public TopDown(boolean[] keyListen){
		try {
    		mask = ImageIO.read(new File("Assets/Maps/"+ gameState + "/OutsideMask.png"));//loads collision mask of current map
		} 
		catch (IOException e) {
			System.out.println(e);
		}
		loadEntrances();//loads the triggers for every entrance
		loadStartLocations();//loads the location the player should be spawned for every entrance
		jim = new MainGameCharacter(630, 620, speed);//delcaring jim character
		keys = keyListen;//sets keys to the boolean array passed through from the jFrame
		setSize(1280,720);
	}
    public void addNotify() {
        super.addNotify();
        ready = true;
	}
	private void loadStartLocations(){//loads the location the player should be spawned for every entrance
		mapStartLocations.put("GuidanceHall", new int[]{1200, 350});
		mapStartLocations.put("FrontRow", new int[]{35, 180});
		mapStartLocations.put("BusinessHall", new int[]{1200, 400});
		mapStartLocations.put("TopRow", new int[]{640, 125});
		mapStartLocations.put("MathHall", new int[]{1180, 585});
	}
	private int[] getMapStartLocation(String prev, String next){//returns the start locations for every entrance
		if(prev == "Outside" && next == "FrontRow"){//these are exceptions for when there are 2 entrances to a location
			return new int[]{640, 630};
		}
		if(prev == "BusinessHall" && next == "Outside"){
			return new int[]{630, 620};
		}
		if(prev == "MathHall" && next == "TopRow"){
			return new int[]{65, 340};
		}
		if(prev == "TopRow" && next == "FrontRow"){
			return new int[]{640, 95};
		}
		else{
			return mapStartLocations.get(next);
		}
	}
	private void loadEntrances(){//loads the triggers (mask colour) for every entrance
		Color c = new Color(0, 255, 0);
		int val = c.getRGB();
		entrances.put(val, "GuidanceHall");
		c = new Color(255, 0, 0);
		val = c.getRGB();
		entrances.put(val, "FrontRow");
		c = new Color(0, 255, 255);
		val = c.getRGB();
		entrances.put(val, "FrontRow");
		c = new Color(100, 255, 0);
		val = c.getRGB();
		entrances.put(val, "BusinessHall");
		c = new Color(100, 255, 100);
		val = c.getRGB();
		entrances.put(val, "TopRow");
		c = new Color(255, 200, 0);
		val = c.getRGB();
		entrances.put(val, "MathHall");
		c = new Color(0, 100, 200);
		val = c.getRGB();
		entrances.put(val, "TopRow");
	}
	private boolean entranceAtLocation(){//checks if there is an entrance in the mask at jim's current location
		int c = mask.getRGB(jim.getX(), jim.getY());
		if (entrances.containsKey(c)){
			return true;
		}
		return false;
	}
	public void checkEntrances(){//if there is an entrance at the current location, the map is changed
		if(entranceAtLocation()){
			String newMap = entrances.get(mask.getRGB(jim.getX(), jim.getY()));
			map = new ImageIcon("Assets/Maps/" + gameState + "/" + newMap + ".png").getImage();//loading new map
			try {
				mask = ImageIO.read(new File("Assets/Maps/" + gameState + "/"+ newMap + "Mask.png"));//loading new mask
			} 
			catch (IOException e) {
				System.out.println(e);
			}
			int[] startLocations = getMapStartLocation(currentMap, newMap);//loads the position jim should start in the new map
			jim.setX(startLocations[0]);
			jim.setY(startLocations[1]);
			currentMap = newMap;//changes the string current map to the new map
		}
	}

	public void setMap(String newMap){//sets the map to the map corolating with the string newMap
			map = new ImageIcon("Assets/Maps/" + gameState + "/" + newMap + ".png").getImage();
			try {
				mask = ImageIO.read(new File("Assets/Maps/" + gameState + "/" + newMap + "Mask.png"));
			} 
			catch (IOException e) {
				System.out.println(e);
			}
			int[] startLocations = getMapStartLocation(currentMap, newMap);
			jim.setX(startLocations[0]);
			jim.setY(startLocations[1]);
			currentMap = newMap;

	}


	public void resetMap(){//resets the map in the case that some changes were made that need to be loaded
		map = new ImageIcon("Assets/Maps/" + gameState + "/" + currentMap + ".png").getImage();
		try {
			mask = ImageIO.read(new File("Assets/Maps/" + gameState + "/" + currentMap + "Mask.png"));
		} 
		catch (IOException e) {
			System.out.println(e);
		}
	}

	public boolean checkCutScene(){//checks if there is a cutScene at the current locations with the current game state
		if(currentMap == cutSceneLocations[gameState]){//if the current cutscene trigger is at the current map
			int c = mask.getRGB(jim.getX(), jim.getY());
			int check = (new Color(255, 255, 0)).getRGB();
			if (c == check){//if jim is on the trigger
				gameState+=1;//gameState is changed for the next objective
				return true;
			}
		}
		return false;
	}

	private boolean clear(int x, int y){//checks for color collision with walls
		Color pink = new Color(246, 0, 255);
		if(x<0 || x>= mask.getWidth(null) || y<0 || y>= mask.getHeight(null)){//if the location is ouside of the boudaries, return false
			return false;
		}
		int WALL = pink.getRGB();
		int c = mask.getRGB(x, y);
		return c != WALL;//reutrns if there is a mask colour collsion
	}

    public void move(){//moves jim after checking for collision in every direction
		if(keys[KeyEvent.VK_RIGHT] && clear(jim.getX() + jim.getWidth() + 2*speed, jim.getY()) && clear(jim.getX() + jim.getWidth() + 2*speed, jim.getY()+jim.getHeight())){
			jim.move(RIGHT);
		}
		else if(keys[KeyEvent.VK_LEFT] && clear(jim.getX() - 2*speed, jim.getY()) && clear(jim.getX() - 2*speed, jim.getY()+jim.getHeight())){
			jim.move(LEFT);
		}
		else if(keys[KeyEvent.VK_UP] && clear(jim.getX(), jim.getY() - 2*speed)){
			jim.move(UP);
		}
		else if(keys[KeyEvent.VK_DOWN] && clear(jim.getX(), jim.getY() + jim.getHeight() + 2*speed)){
			jim.move(DOWN);
		}
	}
    public void paint(Graphics g){//drawing function
		g.drawImage(map, 0, 0, null);//draws current map
        jim.draw(g);//draws jim
    }
}

class MainGameCharacter{//top down character
	public final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3, WAIT = 5;//final ints for direction
	private int x, y, speed, dir, frame = 0, delay = 0;
	Image playerPics[], map;//60-96
	public MainGameCharacter(int x, int y, int speed){//constructor
		this.x = x;
		this.y = y;//sets x and y
		this.speed = speed;//sets speed
		playerPics = new Image[36];
		for(int i = 0; i<36; i++){
			playerPics[i] = new ImageIcon("Assets/Sprites/Jim/" + (i+60) + ".png").getImage();//declaring player pictures
		}
	}
	public void move(int d){//moves player and increases the frame of animation accordingly with a delay
		if(d == UP){
			dir = UP;
			y -= speed;
			delay+=1;
			if(delay%WAIT == 0){
			frame = (frame+1)%9;
			}
		}
		if(d == DOWN){
			dir = DOWN;
			y+=speed;
			delay+=1;
			if(delay%WAIT == 0){
			frame = (frame+1)%9;
			}
		}
		if(d == LEFT){
			dir = LEFT;
			x-=speed;
			delay+=1;
			if(delay%WAIT == 0){
			frame = (frame+1)%9;
			}
		}
		if(d == RIGHT){
			dir = RIGHT;
			x+=speed;
			delay+=1;
			if(delay%WAIT == 0){
			frame = (frame+1)%9;
			}
		}
	}
	public void draw(Graphics g){
		g.drawImage(playerPics[frame+(dir*9)], x, y, null);//draws jim at x and y with current direction
	}
	public int getHeight(){
		return playerPics[frame+(dir*9)].getHeight(null);//returns the height of the current jim sprite
	}
	public int getWidth(){
		return playerPics[frame+(dir*9)].getWidth(null);//returns the width of the current jim sprite
	}
	public int getX(){//getter and setter functions
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int set){
		x = set;
	}
	public void setY(int set){
		y = set;
	}
}

class CutScene extends JPanel {//cutScene Class, runs the cutscenes based of folders of images
	public boolean ready=false;
	private boolean[] keys;
	private Image[] scene;
	private int current = 0, delay =  0, length;
	private double avg;
	private int[] marks;
	private boolean animated, fade = false, finished = false, end = false;
	private float transparent;
	private Image toronto = new ImageIcon("Assets/Acceptances/Toronto.png").getImage();
	private Image waterloo = new ImageIcon("Assets/Acceptances/Water.png").getImage();
	private Image western = new ImageIcon("Assets/Acceptances/Western.png").getImage();
	private Image windsor = new ImageIcon("Assets/Acceptances/Windsor.png").getImage();
	private Image stclair = new ImageIcon("Assets/Acceptances/StClair.png").getImage();//images for final scene
	public CutScene(int set, int length, boolean animated,boolean[] keyListen){//constructor
		keys = new boolean[KeyEvent.KEY_LAST+1];
		keys = keyListen;
		scene = new Image[length+1];//creates an image array the size of the cutscene
		for(int i = 1; i<=length; i++){
			scene[i-1] = new ImageIcon("Scenes/Scene" + set + "/" + i + ".png").getImage();//loads the images for teh current cutscene
		}
		this.length = length;//getting length of scene
		this.animated = animated;//getting whether scene is animated
	}
    public void addNotify() {
        super.addNotify();
        ready = true;
	}
	public boolean sceneFinished(){//returns wheter scene is finished
		if(finished){
			return true;
		}
		if(animated){//if animated scene, adds fade before returning finished
			if(keys[KeyEvent.VK_ENTER]){
				fade = true;
			}
		}
		else{
			if(current == length){//if on last slide of cutscene
				return true;//reutrns true
			}
		}
		return false;
	}

	public void setEnd(){
		end = true;//if indicates whether its the final cutscene
	}

	public void setMarks(int[] marks){//sets marks and averages for final cutscene
		this.marks = marks;
		for(int i = 0; i<5; i++){
			avg+=marks[i];
		}
		avg = avg/5;

	}

	public void paint(Graphics g){
		if(fade){//adds fade with tranparency for animated cutscene
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(new Color(0, 0, 0));
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparent));
			g2d.fillRect(0, 0, 1290, 730);
			transparent+=0.005;
			if(transparent>0.5){
				finished = true;//when transparency is done returns finished;
			}
		}
		else{
			if(animated){//if animated
				delay+=1;//runs the slides with a delay
				if(delay%10 == 0){
					current = (current+1)%length;
				}
			}
			else{
				if(keys[KeyEvent.VK_ENTER] && delay>50){//if player presses enter, moves on to next cutscene, delay so that no spam
					current+=1;
					delay = 0;
				}
				delay+=1;
			}
			g.drawImage(scene[current], 0, 0, null);//draws current scene
		}
		if(end && current == 0){
			drawMarks(g);//if final cutscnene, draws marks 'final stats'
		}
		else if(end && current == 1){
			drawAcceptance(g);//if second slide of last cutscene, draws result of game
		}
	}

	private void drawAcceptance(Graphics g){//draws which university the player gets into at end of game
		if(avg>=90){
			g.drawImage(waterloo, 0, 0, null);
		}
		else if(avg>=80){
			g.drawImage(toronto, 0, 0, null);
		}
		else if(avg>=70){
			g.drawImage(western, 0, 0, null);
		}
		else if(avg>=60){
			g.drawImage(windsor, 0, 0, null);
		}
		else{
			g.drawImage(stclair, 0, 0, null);
		}
	}

	private void drawMarks(Graphics g){//draws 'marks' at the end of the game which are the results and stats of the player's game
		g.setColor(Color.black);
		g.setFont(new Font(null, Font.BOLD, 40));
		g.drawString("Data: " + marks[0] + "%", 550, 200);
		g.drawString("Physics: " + marks[1] + "%", 550, 260);
		g.drawString("Mathematics: " + marks[2] + "%", 550, 320);
		g.drawString("Phys Ed: " + marks[3] + "%", 550, 380);
		g.drawString("Computer Science: " + marks[4] + "%", 550, 440);
		g.setColor(Color.red);
		g.drawString("Average: " + avg + "%", 650, 500);
		if(avg>=90){
			g.setFont(new Font(null, Font.BOLD, 200));
			g.drawString("A", 325, 350);
		}
		else if(avg>=80){
			g.setFont(new Font(null, Font.BOLD, 200));
			g.drawString("B", 325, 350);
		}
		else if(avg>=70){
			g.setFont(new Font(null, Font.BOLD, 200));
			g.drawString("C", 325, 350);
		}
		else if(avg>=60){
			g.setFont(new Font(null, Font.BOLD, 200));
			g.drawString("D", 325, 350);
		}
		else{
			g.setFont(new Font(null, Font.BOLD, 200));
			g.drawString("F", 325, 350);
		}
	}
}


class Badminton extends JPanel {//badminton minigame
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2;//final ints for directions
	//fields
	private int mid = 680, k, h, leftIntercept;
	private int birdieX = 300, birdieY = 670, speed = 3, timer = 0, tranTimer = 0, gameTimer = 0, serverTimer = 0;
	private int hitToWin = randint(2, 5), hits = 0, jimPoints = 0, tranPoints = 0;
	private double a;
	public boolean ready=false, gameOver = false, gameFinished = false;
	private boolean canHit = true, roundOver = true, roundWin = true, tranHit = true, onGround = false, serve = false, tranServe = false;
	private boolean []keys;
	private BadminJim Jim = new BadminJim(1000, 550, 3);
	private Tran Tran = new Tran(100, 550, 3);//declaring players
    private Image BG = new ImageIcon("Assets/BossMaps/badmintonBG.png").getImage();//declaring background
    private Image[] endScreens;
	public Badminton(boolean []keys){
		this.keys = keys;
        setSize(1280,720);

        endScreens = new Image[5];
        endScreens[0] = new ImageIcon("Assets/WinScreens/BadmintonA.png").getImage();
		endScreens[4] = new ImageIcon("Assets/WinScreens/BadmintonF.png").getImage();//delcaring reportcard endscreeens
	}
    public void addNotify(){
        super.addNotify();
        requestFocus();
		ready = true;
	}

	public void move(){//moves jim and lets him jump
        Jim.jump(keys[KeyEvent.VK_UP]);
		if (keys[KeyEvent.VK_RIGHT]){
			Jim.move(RIGHT);
		}
		else if(keys[KeyEvent.VK_LEFT]){
			Jim.move(LEFT);
		}

		Tran.move(leftIntercept);//moves tran to the interception of the birdies trajectory
	}

	private boolean inJimRange(){
		if(Math.sqrt(Math.pow(Jim.getX()-birdieX, 2) + Math.pow(Jim.getY()-birdieY, 2)) < 100){//checks if birdie is in jim's range to hit
			return true;
		}
		return false;
	}

	private boolean inTranRange(){
		if(Math.sqrt(Math.pow(Tran.getX()-birdieX, 2) + Math.pow(Tran.getY()-birdieY, 2)) < 100){//checks if birdie is in trans range to hit
			return true;
		}
		return false;
	}
	
	public void gameController(){//controls the game events
		if(jimPoints>=7 || tranPoints>=7){//if one of the players reaches the goal, that player wins and game is over
			if(!gameOver){
				gameTimer = 0;
			}
			gameOver = true;
			gameTimer++;
		}
		else{
			if(roundOver){//if the round is over
				if(birdieY<624 && !onGround){
					moveBirdie();//let the birdie hit the ground first
				}
				else{
					gameTimer++;
					onGround = true;
					if(roundWin){
						birdieServer(1);//if jim wins the round, he is allowed to serve
					}
					else{
						birdieServer(0);//else tran servers
					}
				}
			}
			else{
				if(hits>=hitToWin){//if the rally has gone on long enough
					roundWin = true;//jim wins
					if(birdieY>=624){//after birdie hits the ground
						roundOver = true;//round is set to over
						jimPoints++;//jim gets a point
						hits = 0;//rally length is set to 0
					}
				}
				if(!serve && keys[KeyEvent.VK_SPACE] && canHit){//if jim isn't serving and space is pressed
					Jim.swingAnim();//swing animation
					canHit = false;//can hit is set to false to avoid spamming
					if(inJimRange()){//if birdie is in jim's range, he hits the birdie
						hitBirdie();
						tranServe = false;
						hits++;
					}
				}
				if(birdieY > 624){//if birdie hits the ground
					hits = 0;//rally length set to 0
					roundOver = true;//round over
					if(birdieX>697){//if on jim's side
						roundWin = false;//tran wins
						tranPoints++;
					}
					else{
						roundWin = true;//else jim wins
						jimPoints++;
					}
				}
				if(!tranServe && inTranRange() && tranHit && !roundWin){//if tran isn't serving and can hit the birdie
					hitBirdie();//tran hits the birdie
					tranHit = false;
					serve = false;
					Tran.swingAnim();
				}
				
				if(!serve){//resets serve boolean with cooldown
					serverTimer++;
					if(serverTimer>100){
						serve = false;
					}
				}

				if(!canHit){//resets canhit boolean with cooldown
					timer++;
					if(timer>75){
						timer = 0;
						canHit = true;
					}
				}
				if(!tranHit){//resets tranhit boolean with cooldown
					tranTimer++;
					if(tranTimer>75){
						tranTimer = 0;
						tranHit = true;
					}
				}
				moveBirdie();//moves birdie
			}
		}
	}

	private void birdieServer(int Server){//if serving
		if(Server == 0){//if tran serving
			birdieX = Tran.getX()+25;//birdie held at trans location
			birdieY = 610;
			if(gameTimer>100){//if timer runs out, tran servers
				tranHit = false;
				gameTimer = 0;
				Tran.swingAnim();
				roundOver = false;
				roundWin = false;
				hits = 0;
				onGround = false;
				hitToWin = randint(2, 5);//resets all variables for round
				if(speed > 0){//if birdie going wrong way, changes direction
					speed = -3;
				}
				hitBirdie();//hits birdie
				tranServe = true;//sets serve to true
			}
		}
		if(Server == 1){
			birdieX = Jim.getX()-5;
			birdieY = Jim.getY()+70;//holds birdie at jim
			if(keys[KeyEvent.VK_SPACE] && gameTimer>100){//if player pressed space
				canHit = false;
				timer = 0;
				gameTimer = 0;
				Jim.swingAnim();
				roundOver = false;
				roundWin = false;
				hits = 0;
				onGround = false;
				hitToWin = randint(2, 5);//resets variables
				if(speed < 0){//if birdie going wrong direction, resets direction
					speed = 3;
				}
				hitBirdie();//hits birdie
				serve = true;//sets serve to true
			}
		}
	}

	private void hitBirdie(){//changes direction and finds new trajectory for birdie
		speed*=-1;
		findPara(birdieX, birdieY);
	}

	private void moveBirdie(){//moves birdie along trajectory
			birdieY = findY(birdieX);
			birdieX+=speed;
	}

	private void findPara(int x1, int y1){//find parabola arc from location hit
		h = mid;
		if(x1>1050){
			h += 100;
		}
		else if(x1>680 && x1<890){
			h-=150;
		}
		else if(x1<260){
			h-=150;
		}
		else if(x1<680 && x1>430){
			h+=100;
		}
		k = randHeight();
		x1+=randint(-10, 10);
		a = (y1-k)/Math.pow(x1-h, 2);
		leftIntercept = (int)(Math.sqrt((600-k)/a)*-1+h);//used math to find parabola, and interception with tran
	}

	private int findY(int x){
		return (int) (a*Math.pow(x-h, 2)+k);//returns the y for any x value on parabola
	}

	private int randHeight(){
		return randint(30, 175);//finds random vertex for parabola
	}

	private void resetGame(){//resets game if player retries
		gameOver = false;
		jimPoints = 0;
		tranPoints = 0;
		roundOver = true;
	}

	private static int randint(int low, int high){
		return (int)(Math.random()*(high-low+1)+low);//randing funcion
	}

    public void paint(Graphics g){
		if(gameOver){//if game is done
			if(gameTimer>150){
				Graphics2D g2d = (Graphics2D)g;
				if(tranPoints>=7){//if tran wins
					g2d.drawImage(endScreens[4], 0, 0, null);
					g2d.setColor(Color.red);
					g2d.setFont(new Font(null, Font.BOLD, 150));
					g2d.drawString("0%", 680, 370);//draws faling report card
					if(keys[KeyEvent.VK_ENTER]){
						resetGame();//lets player retry
					}
				}
				else{
					g2d.drawImage(endScreens[0], 0, 0, null);
					g2d.setColor(Color.red);
					g2d.setFont(new Font(null, Font.BOLD, 150));
					g2d.drawString("100%", 630, 370);;//if jim wins, draws passing report card
					if(keys[KeyEvent.VK_ENTER]){
						gameFinished = true;//lets player continue
					}
				}
			}
			else{
				if(jimPoints>=7){
					g.setColor(Color.yellow);
					g.setFont(new Font(null, Font.BOLD, 75));
					g.drawString("YOU WIN!", 450, 350);//if game is over, draws 'you win' or 'you lose' after last round
				}
				else{
					g.setColor(Color.red);
					g.setFont(new Font(null, Font.BOLD, 75));
					g.drawString("YOU LOST", 450, 350);	
				}
			}
		}
		else{
			g.drawImage(BG, 0, 0, null);//draws background
			g.setColor(Color.white);
			Jim.draw(g);//draws jim
			Tran.draw(g);//draws tran
			g.fillRect(birdieX, birdieY, 10, 10);//draws birdie

			//Drawing Points
			g.setColor(Color.black);
			g.setFont(new Font(null, Font.BOLD, 25));
			g.drawString("Jim: " + jimPoints, 650, 50);
			g.drawString("Mr.Tran: " + tranPoints, 500, 50);//draws point system
		}
	}
}

class Treb extends JPanel {//physics minigame
	public final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;//finals ints for direction
	//fields
    private int timer = 0, level = 1, arrowsRemaining = 3, mark = 100, delay = 0;
    private float transparent = 0;
    public boolean ready=false, gameOver = false, levelOver = false, finished = false;
    private boolean canShoot = true, hurt = false;
    private boolean []keys;
    private ArrayList<Arrow> arrows = new ArrayList<Arrow>();
    private Image BG = new ImageIcon("Assets/Catapult/BG.png").getImage(), canon = new ImageIcon("Assets/target/canon.png").getImage(), nextL = new ImageIcon("Assets/target/NEXT LEVEL.png").getImage();
    private Image[] endScreens;
    private Catapult catapult;
    private Target target;
	public Treb(boolean[] keys){
		this.keys = keys;
        setSize(1280,720);
        catapult = new Catapult(3);
        target = new Target();

        endScreens = new Image[5];
        endScreens[0] = new ImageIcon("Assets/WinScreens/PhysicsA.png").getImage();
        endScreens[1] = new ImageIcon("Assets/WinScreens/PhysicsB.png").getImage();
        endScreens[2] = new ImageIcon("Assets/WinScreens/PhysicsC.png").getImage();
        endScreens[3] = new ImageIcon("Assets/WinScreens/PhysicsD.png").getImage();
        endScreens[4] = new ImageIcon("Assets/WinScreens/PhysicsF.png").getImage();//ends screen report cards
	}
    public void addNotify(){
        super.addNotify();
        requestFocus();
        ready = true;
    }
    public void move(){//moves trebuchet
        if(keys[KeyEvent.VK_UP]){
            catapult.move(UP);
        }
        if(keys[KeyEvent.VK_DOWN]){
            catapult.move(DOWN);
        }
    }
    public void arrowShoot(){//shoots projectiles
        catapult.shootAnim();
        arrows.add(new Arrow(catapult.getX(), catapult.getY()));
        arrowsRemaining--;
    }
    public void arrowController(){
        if(keys[KeyEvent.VK_SPACE] && canShoot && arrowsRemaining>0){//calls projectile shooting when space pressed
            arrowShoot();
            canShoot = false;//shooting turned off to avoid spam
        }
        if(!canShoot){//delay to reset shooting
            timer++;
            if(timer >= 100){
                canShoot = true;
                timer = 0;
            }
        }
        for(Arrow a : arrows){
            a.move();//moves every projectile
        }
    }
    public void arrowCollision(){
        for(int a = arrows.size()-1; a>-1; a--){//if projectile collides with target, arrow is removed
            if(arrows.get(a).getX() >= target.getX() && arrows.get(a).getX() <= target.getX()+60 && arrows.get(a).getY() >= target.getY() && arrows.get(a).getY() <= target.getY()+70){
                arrows.remove(a);
                target.hitAnim();
                target.toggleMove();
				levelOver = true;//next level if played
				if(level == 7){
					gameOver = true;//if level 7 is reached, game is done 
				}
            }
        }
    }
    public void nextLevel(){//loads next level when target is hit
        if(keys[KeyEvent.VK_ENTER]){
            level++;
            arrowsRemaining = 3;
            target.toggleMove();
            target.resetAnim();
            target.increaseSpeed();
            catapult.resetPos();
            levelOver = false;
        }
    }
    public void resetLevel(){//resets revel when run out of preojectiles
        hurt = true;
        arrowsRemaining = 3;
        delay = 0;
    }
    public void resetGame(){//resets game if failed
        arrowsRemaining = 3;
        delay = 0;
        gameOver = false;
        target.resetAnim();
        catapult.resetPos();
        level = 1;
        levelOver = false;
        mark = 100;
    }
    public void gameController(){//moves target and checks for game over
        target.move();
        if(level>7){
            gameOver = true;
        }
        if(arrowsRemaining == 0){
            delay++;
            if(delay>100){
                mark-=5;
                resetLevel();//resets level when projectiles run out
            }
        }
        if(mark<=50){
            gameOver = true;
        }
	}
	
	public int getMark(){//returns mark at the end of minigame
		return mark;
	}

    public void paint(Graphics g){//draws game
        if(gameOver){//if game is over
			Graphics2D g2d = (Graphics2D)g;
			
			//draws the different endscreens with report cards based on how well the player did
            if(mark<=50){
                g2d.drawImage(endScreens[4], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
                g2d.drawString(mark+"%", 680, 370);
                if(keys[KeyEvent.VK_ENTER]){
                    resetGame();
                }
            }
            else if(mark<70){
                g2d.drawImage(endScreens[3], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished = true;
                }
            }
            else if(mark<80){
                g2d.drawImage(endScreens[2], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished = true;
                }
            }
            else if(mark<90){
                g2d.drawImage(endScreens[1], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished = true;
                }
            }
            else{
                g2d.drawImage(endScreens[0], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished = true;
                }
            }
        }
        else{
			//if game isn't over
            g.drawImage(BG, 0, 0, null);//drawing background
            catapult.draw(g);//drawing trebuchet
            target.draw(g);//drawing target
            for(Arrow a : arrows){//draws every projectile
                a.draw(g);
            }
            g.setColor(Color.red);
            for(int i = 0; i<arrowsRemaining; i++){
                g.drawImage(canon, 20+i*60, 70, null);//draws ui for how many arrows remain
            }

            if(levelOver && level!=7){
                g.drawImage(nextL, 0, 0, null);//draws ui for next level popup
            }

            ///Level Counter
            g.setColor(Color.black);
            g.setFont(new Font(null, Font.BOLD, 35));
            g.drawString("Level: " + level + "/7", 680, 30);

            //Health Bar
            g.setColor(Color.black);
            g.fillRect(10, 10, 300, 30);
            g.fillRect(10, 40, 120, 20);
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString("Mark: " + mark + "%", 20, 55);
            g.fillRect(15, 15, 290, 20);
            g.setColor(Color.red);
            g.fillRect(15, 15, (int)((double)((mark-50)/50.0)*290), 20);
            
            //Hurt Effect for when projectiles run out
            if(hurt){
                transparent+=0.05;
                if(transparent>0.5){
                    hurt = false;
                }
            }
            else if(transparent>0.05){
                transparent-=0.05;        
            }
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.red);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparent));
            g2d.fillRect(0, 0, 1290, 730);
        }
    }
}

class Catapult{//trebuchet
	public final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;//final ints for diretion
	//fields
    private int x, y, speed, timer = 0, currentPic = 0;
    boolean shooting = false;
    private Image[] sprites = new Image[11];
    public Catapult(int speed){//constructor
        x = 30;
        y = 300;
        this.speed = speed;
        for(int i = 0; i<11; i++){
            sprites[i] = new ImageIcon("Assets/Catapult/" + i +".png").getImage();//loading treb images
        }
    }
    public void move(int move){//moving treb
        if(move == UP){
            if(y>85){
                y-=speed;
            }
        }
        if(move == DOWN){
            if(y<530){
                y+=speed;
            }
        }
    }
    public void shootAnim(){//sets shooting anim to true
        shooting = true;
    }
    public void draw(Graphics g){
        if(shooting){//if shooting, draws shooting anim with delay
            if(timer%2 == 0){
                currentPic++;
                if(currentPic>10){
                    shooting = false;
                    currentPic = 0;
                }
            }
            timer++;
        }
        g.drawImage(sprites[currentPic], x, y, null);// draws treb at current position
    }
    public void resetPos(){//resets treb position for every round
        x = 30;
        y = 300;
    }
    public int getX(){//getter functions
        return x;
    }
    public int getY(){
        return y;
    }
}

class Arrow{//projectile class
	public final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;//final ints for direction
	//fields
    private int x, y, speed = 15;
    Image sprite = new ImageIcon("Assets/target/canon.png").getImage();
    public Arrow(int x, int y){
        this.x = x;
        this.y = y+76;
    }
    public void move(){//moves projectiles right
        x+=speed;
    }
    public void draw(Graphics g){
        g.drawImage(sprite, x, y, null);//draws projectile 
    }
    public int getX(){//getting functions
        return x;
    }
    public int getY(){
        return y;
    }
}
class Target{//target class
    private int x, y, speed, delay = 0, frame = 0;
    private boolean goingUp = true, moving = true;
    private boolean hit;
    private Image[] sprites = new Image[7];
    
    public Target(){
        speed = 2;
        x = 980;
        y = 350;
        for(int i = 0; i<7; i++){
            sprites[i] = new ImageIcon("Assets/target/" + i +".png").getImage();//loads target images
        }
	}
	
    public void move(){//moves target
        if(moving){
            frame = 0;
            hit = false;
            if(goingUp){
                if(y<85){//if target hits the top of screen
                    goingUp = false;//target moves down
                }
                else{
                    y-=speed;
                }
            }
            else{
                if(y>640){//if target hits bottom of screen
                    goingUp = true;//target moves up
                }
                else{
                    y+=speed;
                }
            }
        }
    }
    public void toggleMove(){
        moving = (moving) ? false:true;//toggles whether the target is moving on and off
    }
    public void increaseSpeed(){
        speed += 1;//inscreases speed to inscrease difficulty
    }
    public void hitAnim(){//if hit, runs animation
        hit = true;
    }
    public void resetAnim(){//resets hit anim
        frame = 0;
    }
    public void draw(Graphics g){//draws target
        if(hit){//if hit, draws hit anim
            if(delay%5 == 0){
                frame++;
            }
            if(frame>6){
                frame = 6;
                hit = false;
            }
            delay++;
        }
        g.drawImage(sprites[frame], x, y, null);//draws target
    }
    public int getX(){//getting methods
        return x;
    }
    public int getY(){
        return y;
    }
}

class DodgeBall extends JPanel {
    public static final int RIGHT = 1, LEFT = 0, DOWN = 2, WAIT = 5;//final ints for direction
	//94x60
	//fields
    private int timer = 0, levelTime = 0, difficulty = 100, mark = 100;
    private double timeRemaining = 40;
    private float transparent = 0;
	public boolean ready=false, hurt = false, gameOver = false, finished = false;
    private boolean []keys;
    private Scarrow scarrow = new Scarrow();
    private Jim jim = new Jim(100, 550, 3);
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Image BG = new ImageIcon("Assets/BossMaps/GymBG.png").getImage();//loading bg
    private Image[] endScreens;
	public DodgeBall(boolean[] keys){
		this.keys = keys;
        setSize(1280,720);

        endScreens = new Image[5];
        endScreens[0] = new ImageIcon("Assets/WinScreens/GymA.png").getImage();
        endScreens[1] = new ImageIcon("Assets/WinScreens/GymB.png").getImage();
        endScreens[2] = new ImageIcon("Assets/WinScreens/GymC.png").getImage();
        endScreens[3] = new ImageIcon("Assets/WinScreens/GymD.png").getImage();
        endScreens[4] = new ImageIcon("Assets/WinScreens/GymF.png").getImage();//loading endscreen report cards

	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        ready = true;
    }
    public void controlBalls(){//controls balls thrown
        if(timer%difficulty==0){//inscreases throwing rate to increase difficulty
            scarrow.throwAnimation();//throwing anim for every time scarrow throws
            addBall();//adds ball after throw
            timer = 0;
        }
        timer++;
        moveBalls();//moves balls
    }
    private void addBall(){//adds ball to ArrayList of balls
        Ball b = new Ball(1170, randint(500, 600), 3);
        balls.add(b);
    }
    private void moveBalls(){
        for(Ball b : balls){
            b.move();//moves every ball in arraylist to the left
        }
    }
    public void checkCollisions(){
        for(int b = balls.size()-1; b>-1; b--){//checks with every ball in arrayList
            if(balls.get(b).getX() <= jim.getX()+jim.getWidth()-10 && balls.get(b).getX() >= jim.getX() && balls.get(b).getY() >= jim.getY()-30 && balls.get(b).getY() <= jim.getY()+jim.getHeight()){
                balls.remove(b);//if ball collides with jim, the ball is removed, mark is deducted, and hurt effect is played
                mark-=5;
                hurt = true;
            }
        }
    }
    public void move(){//moves jim left and right, allows jump and crouch
        jim.jump(keys[KeyEvent.VK_UP]);
        if(keys[KeyEvent.VK_DOWN]){
            jim.move(DOWN);
        }
		else if (keys[KeyEvent.VK_RIGHT]){
			jim.move(RIGHT);
		}
		else if(keys[KeyEvent.VK_LEFT]){
			jim.move(LEFT);
        }
        else if(jim.getDir() == DOWN){
            jim.setDir(RIGHT);
        }
    }
    public void gameController(){//controls difficulty and ends game if lost
        if(mark<=50){
            gameOver = true;//ends game if mark is too low
        }
        if(levelTime>4000){//difficulty (shooting rate) is increased after set amount of time
            difficulty = 50;
        }
        else if(levelTime>2500){
            difficulty = 60;
        }
        else if(levelTime>1000){
            difficulty = 75;
        }
        if(levelTime%10 == 0){
            timeRemaining-= 0.1;//time is decreased
        }
        if(timeRemaining <= 0){
            gameOver = true;//if time runs out, player wins
        }
        levelTime++;//level time increased
    }
    private void resetGame(){//resets game if retrying
        gameOver = false;
        balls = new ArrayList<Ball>();
        jim = new Jim(100, 550, 3);
        scarrow = new Scarrow();
        timeRemaining = 40;
        timer = 0; 
        levelTime = 0;
        difficulty = 100;
        mark = 100;
        transparent = 0;
        hurt = false;
	}
	
	public int getMark(){//returns mark after player wins
		return mark;
	}

    public void paint(Graphics g){
        if(gameOver){//if game over
			Graphics2D g2d = (Graphics2D)g;

			//draws endscreen report card based on player's mark
            if(mark<=50){
                g2d.drawImage(endScreens[4], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
                g2d.drawString(mark+"%", 680, 370);
                if(keys[KeyEvent.VK_ENTER]){
                    resetGame();
                }
            }
            else if(mark<70){
                g2d.drawImage(endScreens[3], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished  = true;
                }
            }
            else if(mark<80){
                g2d.drawImage(endScreens[2], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished  = true;
                }
            }
            else if(mark<90){
                g2d.drawImage(endScreens[1], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished  = true;
                }
            }
            else{
                g2d.drawImage(endScreens[0], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    finished  = true;
                }
            }
        }
        else{
            //Drawing Background
            g.drawImage(BG, 0, 0, null);

            //Drawing Text
            String tempTime = String.format("%.1f", timeRemaining);
            g.setColor(Color.black);
            g.setFont(new Font(null, Font.BOLD, 25));
            g.drawString("Time Remaining: " + tempTime, 896, 100);

            //Drawing HealthBar
            g.setColor(Color.black);
            g.fillRect(10, 10, 300, 30);
            g.fillRect(10, 40, 120, 20);
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString("Mark: " + mark + "%", 20, 55);
            g.fillRect(15, 15, 290, 20);
            g.setColor(Color.red);
            g.fillRect(15, 15, (int)((double)((mark-50)/50.0)*290), 20);
            
            //Drawing Balls
            for(Ball b : balls){
                b.paint(g);
            }

            //Drawing Characters
            scarrow.paint(g);
            jim.draw(g);

            //Hurt Effect
            if(hurt){
                transparent+=0.05;
                if(transparent>0.5){
                    hurt = false;
                }
            }
            else if(transparent>0.05){
                transparent-=0.05;        
            }
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.red);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparent));
            g2d.fillRect(0, 0, 1290, 730);
        }
    }
    private int randint(int low, int high){
        return (int)(Math.random()*(high-low+1)+low);//randint function
    }
}

class Ball{//dodgeballs' class
	//fields
    private int x, y, speed; 
    private static Image ball = new ImageIcon("Assets/Sprites/Dodgeball/ball.png").getImage().getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    public Ball(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    public void move(){
        x -= speed;//moves balls left
    }
    public void paint(Graphics g){
        g.drawImage(ball, x, y, null);//draws balls
    }
    public int getX(){//getter functions
        return x;
    }
    public int getY(){
        return y;
    }
}
class Scarrow{//scarrow class
	//fields
    private int x = 1200, y = 550, currentPic = 0, timer = 0;
    private boolean throwing = false;
    private Image[] scarrowPics = new Image[6];
    public Scarrow(){//constructor
        for(int i = 0; i<6; i++){
            Image temp = new ImageIcon("Assets/Sprites/Scarrow/" + i + ".png").getImage();
            temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            scarrowPics[i] = temp;
        }
    }
    public void throwAnimation(){//thorwing anim for scarrow toggled
       throwing = true;
    }
    public void paint(Graphics g){//draws scarrow
        if(throwing){//thorwing anim played
            if(timer%10 == 0){
                currentPic++;
                if(currentPic>5){
                    throwing = false;
                    currentPic = 0;
                }
            }
            timer++;
        }
        g.drawImage(scarrowPics[currentPic], x, y, null);//draws scarrow and position
    }
}

class Trig extends JPanel {
	public final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;//final ints for direction
	//fields
	private int mark = 100, level = 1, delay = 0;
	private Orb ball;
	private Jim jim;
	private UCircle cir;
	
	public boolean ready=false, gameOver = false, gameFinished = false;
	
	private boolean []keys;
	
	private Image[] endScreens;
	private Image BG = new ImageIcon("Assets/BossMaps/MathBG.png").getImage(), nextL = new ImageIcon("Assets/Math/NEXT LEVEL.png").getImage();
	
	public Trig(boolean[] keys){//constructor
		this.keys = keys;
		ball = new Orb(730,290);
		jim = new Jim(1200,550,3);
		cir = new UCircle();
		setSize(1280,720);
		
        endScreens = new Image[5];
        endScreens[0] = new ImageIcon("Assets/WinScreens/MathA.png").getImage();
        endScreens[1] = new ImageIcon("Assets/WinScreens/MathB.png").getImage();
        endScreens[2] = new ImageIcon("Assets/WinScreens/MathC.png").getImage();
        endScreens[3] = new ImageIcon("Assets/WinScreens/MathD.png").getImage();
        endScreens[4] = new ImageIcon("Assets/WinScreens/MathF.png").getImage();//final report cards loaded for end of minigame
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        ready = true;
    }
    
    
    private void resetGame(){//reset game if player fails
    	gameOver = false;
    	jim = new Jim(1200, 550, 3);
    	cir = new UCircle();
    	mark = 100;
    	level = 1;
    }
    
    public void resetLevel(){
        ball.resetting();//resets ball after level
	}
	
	public void changeLevel(){//goes to next level if player presses enter
		if(keys[KeyEvent.VK_ENTER] && delay>50){//delay to avoid spamming
			nextLevel();//goes to next level
		    resetLevel();//resets ball
			ball.setStop(false);
			delay = 0;
		}
		if(delay<=50){
			delay++;
		}
	}
    
    public void nextLevel(){//method for when you level up
        level++;
        ball.setSpeed(level);
        ball.setLevelUp(false);
    }

    public void move(){//method for stopping the ball when space is entered
    	if (keys[KeyEvent.VK_SPACE] && delay>50){
			ball.stopping();
			delay = 0;
		}
		else if(delay<=50){
			delay++;
		}
    }
    
    public boolean levelOver(){//method that returns when you level up
    	return ball.getLevelUp();
    }
    
    public void gameController(){//controls when the game is done and checks for damage taken
    	ball.setLevelUp(false);
    	if (level>5){
    		gameOver = true;
    	}
    	
    	ball.checkAnswer();
    	if (ball.getTakeDamage()){//take 5 damage if you get the wrong answer
    		mark-=5;
    		ball.setTakeDamage(false);
    		resetLevel();
    	}
  
    	if (mark<=50){
    		gameOver = true;
    	}
    }
	
	public int getMark(){
		return mark;//returns mark at end of game
	}
    
    public void paint(Graphics g){//paint method    	
        if(gameOver){
			Graphics2D g2d = (Graphics2D)g;
			
			//draws report card based on players mark at end of game
            if(mark<=50){
                g2d.drawImage(endScreens[4], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
                g2d.drawString(mark+"%", 680, 370);
                if(keys[KeyEvent.VK_ENTER]){
                    resetGame();
                }
            }
            else if(mark<70){
                g2d.drawImage(endScreens[3], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
					gameFinished = true;
                }
            }
            else if(mark<80){
                g2d.drawImage(endScreens[2], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
					gameFinished = true;
                }
            }
            else if(mark<90){
                g2d.drawImage(endScreens[1], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
					gameFinished = true;
                }
            }
            else{
                g2d.drawImage(endScreens[0], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
					gameFinished = true;
				}
            }
        }

        else{//if the game isn't finished
            g.drawImage(BG, 0, 0, null);
			ball.paint(g);
			cir.paint(g);
			jim.draw(g);


            if(ball.getLevelUp()){//if you level up
                g.drawImage(nextL, 0, 0, null);
            }

            ///Level Counter
            g.setColor(Color.black);
            g.setFont(new Font(null, Font.BOLD, 35));
            g.drawString("Level: " + level + "/5", 680, 30);

            //Health Bar
            g.setColor(Color.black);
            g.fillRect(10, 10, 300, 30);
            g.fillRect(10, 40, 120, 20);
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString("Mark: " + mark + "%", 20, 55);
            g.fillRect(15, 15, 290, 20);
            g.setColor(Color.red);
            g.fillRect(15, 15, (int)((double)((mark-50)/50.0)*290), 20);
        }

    }   
}
class Orb{//class for the object moving aroud the circle

//fields
	private int x,y,timer = 0, currentPos = 0,speed;
	private boolean stop = false;
	private Font fontSys = new Font("Comic Sans MS",Font.PLAIN,32);
	public final int UP = 0, LEFT = 1, DOWN = 2,  RIGHT = 3;
	private double[]angles = {0,Math.PI/6,Math.PI/4,Math.PI/3,Math.PI/2,2*Math.PI/3,3*Math.PI/4,5*Math.PI/6,Math.PI,
							 7*Math.PI/6,5*Math.PI/4,4*Math.PI/3,3*Math.PI/2,5*Math.PI/3,7*Math.PI/4, 11*Math.PI/6};
	private int[]xcoords = new int[16];
	private int[]ycoords = new int[16];
	private int randomPos;
	private int tempPos;
	private boolean levelUp = false,takeDamage = false, reset = false;

	public Orb(int x, int y){//constructor
		this.x = x;
		this.y = y;
		for (int i = 0; i<16; i++){//coords for unit circle
			xcoords[i] = (int)(140*Math.cos(angles[i])+(590));
			ycoords[i] = (int)(140*Math.sin(angles[i])+(290));
		}
		randomPos = randint(0,15);//for picking a random position
		
	}
	
	public static int randint(int low, int high){//random integer method
        return (int)(Math.random()*(high-low+1)+low);
    }
    
    public void checkAnswer(){//method checks if you landed on the correct coordinate to see if you level up or take damage
    	if (stop && levelUp == false){
    		if ((16 - currentPos)%16 == randomPos){
    			levelUp = true;
    		}
    		else{
    			takeDamage = true;
    		}
    	}
    }
	
	
	public void stopping(){//sets stop to true
		stop = true;
	}
	
	public void resetting(){//sets reset to true
		reset = true;
	}
	
	//getter methods
	
	public int getSpeed(){
		return speed;
	}
	
	public boolean getStop(){
		return stop;
	}
	
	public boolean getTakeDamage(){
		return takeDamage;
	}
	
	public boolean getLevelUp(){
		return levelUp;
	}
	 
	
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    //setter methods
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setLevelUp(boolean a){
		this.levelUp = a;
	}
	
	public void setTakeDamage(boolean a){
		this.takeDamage = a;
	}
	
	public void setStop(boolean a){
		this.stop = a;
	}
	
	public void setSpeed(int s){
		this.speed = s;
	}
	
	public void paint(Graphics g){//paint component
		Graphics2D ga = (Graphics2D)g;
		ga.setPaint(Color.black);
		ga.fillOval(xcoords[currentPos]-5,ycoords[currentPos]-5,10,10);//draws orb object at each position
		if (reset){//resets the orb
			currentPos = 0;
			stop = false;
			reset = false;
			randomPos = randint(0,15);
		}
		if (stop == false){
			timer++;
			if (timer%(20-3*speed)== 0){//gradually gets faster after each level
				currentPos = (currentPos+1)%16;
					
			}
		}
	
		g.setFont(fontSys);//drawing the degrees
		g.drawString(String.format("%d Degrees",Math.round(Math.toDegrees(angles[randomPos]))),525,100);
		g.drawLine(425,290,755,290);
		g.drawLine(590,125,590,455);
	}
}

class UCircle{//unit circle class

//fields
	private int x = 450, y = 150,l = 280, w = 280, timer = 0, currentPos = 0;
	private boolean stop = false;
	private double[]angles = {0,Math.PI/6,Math.PI/4,Math.PI/3,Math.PI/2,2*Math.PI/3,3*Math.PI/4,5*Math.PI/6,Math.PI,
							 7*Math.PI/6,5*Math.PI/4,4*Math.PI/3,3*Math.PI/2,5*Math.PI/3,7*Math.PI/4, 11*Math.PI/6};

	
	public void stopping(){
		stop = true;
	}
	
	public void paint(Graphics g){//draws circle and 16 points representing the points on the unit circle
		g.drawOval(x,y,l,w);
		
		for (int i = 0; i<16; i++){
			g.drawOval((int)(140*Math.cos(angles[i])+(x+135)),(int)(140*Math.sin(angles[i])+(y+135)),10,10);
		}
	}
}

class ComSci extends JPanel {//computer science mini game
	//fields
	
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2, SHOOT = 3;//constants
	
	public boolean ready = false, gameOver = false, gameFinished = false;
	private boolean []keys;
	
	private int timer = 0, mark = 100,health = 100, delay = 0,counter = 0;
	private float transparent = 0;
	private boolean hurt = false;
	private ArrayList<Projectile> pencils = new ArrayList<Projectile>();
	private McKenzie mck;
	private JimSci jim;
	private Image BG = new ImageIcon("Assets/BossMaps/ComSciBG.png").getImage();
    private Image[] endScreens;
	
	public ComSci(boolean[] keys){//constructor
		this.keys = keys;
		mck = new McKenzie(1000, 500);
		jim = new JimSci(100,550,3);
		setSize(1280,720);

//winscreens
        endScreens = new Image[5];
        endScreens[0] = new ImageIcon("Assets/WinScreens/CSA.png").getImage();
        endScreens[1] = new ImageIcon("Assets/WinScreens/CSB.png").getImage();
        endScreens[2] = new ImageIcon("Assets/WinScreens/CSC.png").getImage();
        endScreens[3] = new ImageIcon("Assets/WinScreens/CSD.png").getImage();
        endScreens[4] = new ImageIcon("Assets/WinScreens/CSF.png").getImage();
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        ready = true;
	}
	
	private void addProjectile(){//adds a projectile object to the arraylist of projectiles
        Projectile p = new Projectile(jim.getX()+10, jim.getY()+30, 7);
        pencils.add(p);
    }
	
	private void moveProjectiles(){//moving the projectiles from left to right
        for(Projectile p : pencils){
            p.move();
        }
    }
    
    public void controlProjectiles(){//throws projectile at appropriate time
    	if(jim.getIsThrowing()){
    		if(delay>50){
    			addProjectile();
    			delay = 0;
    		}
    	}
    	else{
    		delay++;
    	}
    	moveProjectiles();
    }
    
    public void checkMckCollisions(){//checks collision between projectile and mckenzie
        for(int p = pencils.size()-1; p>-1; p--){
            if(pencils.get(p).getX() <= mck.getX()+mck.getWidth()-10 && pencils.get(p).getX() >= mck.getX() && pencils.get(p).getY() >= mck.getY()-30 && pencils.get(p).getY() <= mck.getY()+mck.getHeight()){//hit box
                pencils.remove(p);
                health-=5;
            }
        }
    }

    public void checkJimCollisions(int x, int y){//checks collision between jim and mckenzie's projectiles
		if(x <= jim.getX()+jim.getWidth() && x+40 >= jim.getX() && y+40 >= jim.getY() && y <= jim.getY()+jim.getHeight()){//hit box
			mark -= 1;
			hurt = true;
		}
	}
	
	public int getMark(){//gets the mark
		return mark;
	}
	
	public void move(){//move method
		jim.jump(keys[KeyEvent.VK_UP]);
        if(keys[KeyEvent.VK_DOWN]){
            jim.move(DOWN);
        }
		else if (keys[KeyEvent.VK_RIGHT]){
			jim.move(RIGHT);
		}
		else if(keys[KeyEvent.VK_LEFT]){
			jim.move(LEFT);
        }
        else if(jim.getDir() == DOWN){
            jim.setDir(RIGHT);
		}
		
		jim.setIsThrowing(keys[KeyEvent.VK_SPACE]);
       
    }
	
	public void collisions(){//handles all unique collisions between jimand mckenzie's projectiles
		if (mck.getOctoShot()){
    		int[][] octo = mck.getOcto();
    		for (int i = 0; i < 8; i++){
    			checkJimCollisions(octo[i][0],octo[i][1]);
    		}
    	}
    	if (mck.getPhrase()){
    	
    		checkJimCollisions(mck.getPx(),mck.getPy());
    	}
    	if(mck.getZigzag()){
    	
    		checkJimCollisions(mck.getZx(),mck.getZy());
    	}
	}

	
	public void attackController(){//controls mckenzies attacks
		if(counter>100){
			mck.pickingAttack();
			counter=0;
		}
		counter ++;
		mck.shotController();
	}
	
    public void gameController(){//if you win or lose the game ends
        if(mark<=50 || health<=1){
            gameOver = true;
        }    
    }
    
    private void resetGame(){//resetting the game
        gameOver = false;
        pencils = new ArrayList<Projectile>();
        jim = new JimSci(100, 550, 3);
        mck = new McKenzie(1000,500);
        timer = 0; 
        mark = 100;
        health = 100;
        transparent = 0;
        hurt = false;
    }
    
    public void paint(Graphics g){//paint method
        if(gameOver){
            Graphics2D g2d = (Graphics2D)g;
            if(mark<=50){
                g2d.drawImage(endScreens[4], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
                g2d.drawString(mark+"%", 680, 370);
                if(keys[KeyEvent.VK_ENTER]){
                    resetGame();
                }
            }
            
            //final marks
            else if(mark<70){
                g2d.drawImage(endScreens[3], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    gameFinished = true;
                }
            }
            else if(mark<80){
                g2d.drawImage(endScreens[2], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    gameFinished = true;
                }
            }
            else if(mark<90){
                g2d.drawImage(endScreens[1], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    gameFinished = true;
                }
            }
            else{
                g2d.drawImage(endScreens[0], 0, 0, null);
                g2d.setColor(Color.red);
                g2d.setFont(new Font(null, Font.BOLD, 150));
				g2d.drawString(mark+"%", 680, 370);
				if(keys[KeyEvent.VK_ENTER]){
                    gameFinished = true;
                }
            }
        }

        else{
            //Drawing Background
            g.drawImage(BG, 0, 0, null);

            //Drawing HealthBar for jim
            g.setColor(Color.black);
            g.fillRect(10, 10, 300, 30);
            g.fillRect(10, 40, 120, 20);
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString("Mark: " + mark + "%", 20, 55);
            g.fillRect(15, 15, 290, 20);
            g.setColor(Color.red);
            g.fillRect(15, 15, (int)((double)((mark-50)/50.0)*290), 20);
            
            
            //Drawing Healthbar for McKenzie
            g.setColor(Color.black);
            g.fillRect(910, 10, 300, 30);
            g.fillRect(910, 40, 120, 20);
            g.setColor(Color.white);
            g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString("Health: " + health + "%", 920, 55);
            g.fillRect(915, 15, 290, 20);
            g.setColor(Color.red);
            g.fillRect(915, 15, (int)((double)((health)/100.0)*290), 20);
            
            //Drawing Balls
            for(Projectile p : pencils){
                p.paint(g);
            }

            //Drawing Characters
            mck.draw(g);
            jim.draw(g);

            //Hurt Effect
            if(hurt){
                transparent+=0.05;
                if(transparent>0.5){
                    hurt = false;
                }
            }
            else if(transparent>0.05){
                transparent-=0.05;        
            }
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.red);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparent));
            g2d.fillRect(0, 0, 1290, 730);
        }
    }
}

class McKenzie{//mckenzie class

//fields
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2;
	public static final int P = 0, O = 1, Z = 2;
	private int x, y;
	private boolean octoShot = false, phrase = false, zigzag = false;
	private int[][] octo;
	private int[][] dirs = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	private int projSpeed = 3, zigDir = -3;
	private int px = 900, py, zx = 1100, zy = 550;
	private String[] phrases = {"public static void main(String[] args)", "O(n)", "LinkedList"};
	private Image computer = new ImageIcon("Assets/ComSci/computer1.png").getImage();
	private Image java = new ImageIcon("Assets/ComSci/javaLogo1.png").getImage();
	private Image mckPic = new ImageIcon("Assets/Sprites/McKenzie/MCK1.png").getImage();
	private String currentPhrase = "";
	public McKenzie(int x, int y){//constructor
		this.x = x;
		this.y = y;
		
	}
	
	public void octoShot(){//makes computers fly in all directions dealing damage
		octo = new int[8][2];
		for(int i = 0; i<8; i++){
			octo[i][0] = x+20;
			octo[i][1] = 340;
		}
		octoShot = true;
	}

	public void phrase(){//a phrase moves in a straight line horizontally dealing damage
		phrase = true;
		py = randint(400,600);
		currentPhrase = phrases[randint(0, phrases.length-1)];
	}

	public void zigzag(){//java logo that moves in a zig zag across the screen
		zigzag = true;
	}

	public void shotController(){//controls movement of shots
		if(octoShot){
			if(y>300){
				y-=2;
			}
			else{
				for(int i = 0; i<8; i++){
					octo[i][0] += dirs[i][0]*projSpeed*3;//moves 8 shots in 8 different directions
					octo[i][1] += dirs[i][1]*projSpeed;
					if(octo[i][0]<-100){
						octoShot = false;
					}
				}
			}
		}
		else if(y<500){
			y+=2;
		}

		if(phrase){
			px-=projSpeed*3;
			if(px<-500){
				phrase = false;//moves phrase across screen
				px = 1100;
				phrase();
			}
		}

		if(zigzag){
			zx -= projSpeed;
			zy += zigDir*4;//moves java logo acrosss screen in zig zag
			if(zy<10){
				zigDir = 3;//moves down when hit the top
			}
			if(zy>680){
				zigDir = -3;//moves up when hit the bottom
			}
			if(zx<-100){
				zigzag = false;//if shot is off screen, it is cancelled
				zx = 1100;
				zy= 550;
			}
		}
	}
	
		
	public void pickAttack(int att){//runs the attack passed through
		if (att == P){
			phrase();
		}
		else if(att == O){
			octoShot();
		}
		else if(att == Z){
			zigzag();
		}
	}
	
	public void pickingAttack(){
		pickAttack(randint(0,2));//picks random attack to shoot
	}
	

	
	public void draw(Graphics g){
		g.drawImage(mckPic, x, y, null);//draws mr.mckenzie

		//drawing the 3 different shots
		if(octoShot){
			for(int i = 0; i<8; i++){
				g.drawImage(computer, octo[i][0], octo[i][1],null);
			}
		}
		if(phrase){
			g.setColor(Color.black);
			g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString(currentPhrase, px, py);
		}
		if(zigzag){
			g.drawImage(java, zx, zy, null);
		}
	}
	private static int randint(int low, int high){
		return (int)(Math.random()*(high-low+1)+low);//randint
	}

	//getter and setter
	public int getHeight(){
    	return mckPic.getHeight(null);
    }
    public int getWidth(){
    	return mckPic.getWidth(null);
    }
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getPx(){
		return px;
	}
	
	public int getPy(){
		return py;
	}
	
	public int getZx(){
		return zx;
	}
	
	public int getZy(){
		return zy;
	}
	
	public int[][] getOcto(){
		return octo;
	}
	
	public boolean getOctoShot(){
		return octoShot;
	}
	
	public boolean getPhrase(){
		return phrase;
	}
	
	public boolean getZigzag(){
		return zigzag;
	}
	
	public void setPx(int set){
		px = set;
	}
	
	public void setZx(int set){
		zx = set;
	}
	
	public void setZy(int set){
		zy = set;
	}
	
	public void setPy(int set){
		py = set;
	}
	
}

class JimSci{//jim class for comsci scene
	//constants
	public static final int RIGHT = 1, LEFT = 0, DOWN = 2;//final ints for directions

	//fields
	private int damage;
	private int speed;
	private int x,y;
	private int jumpHeight = 100;
	private double vy;
	private int dir = RIGHT;
	private int WAIT = 7;
	
	Image playerPics[],back, crouch[];
	
	
	private int frame = 0;
	private int delay = 0, delay2 = 0;
	private int groundLevel;
	
	private boolean onGround = false;
	private boolean standing;
	private boolean swing = false;
	
	private boolean moveLeft, moveRight, moveDown, isThrowing = false;
	
    public JimSci(int x, int y, int speed) {
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
			temp = temp.getScaledInstance(temp.getWidth(null)*2, temp.getHeight(null)*2,  java.awt.Image.SCALE_SMOOTH);//loading images of jim
			crouch[i] = temp;
		}
    }
    
    public void move(int d){//moving jim left and right, allowing crouch
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
    
	public void jump(boolean upPressed){//makes jim jump off ground
		onGround = (y == groundLevel);//checks if jim is on the ground
		y+=vy;//y is increased by its velocity
		if (upPressed && onGround){
            vy = -7;//velocity changed to allow upwards movement
        }
        if (onGround == false){
			if(y < groundLevel-jumpHeight){//if jim reaches jump height, he start accelerating dowards
	            vy += 0.5;
			}
        }
        if(y >= groundLevel){//makes sure jim doesnt fall through ground
			y = groundLevel;
			onGround = true;
		}
    }
    
    public void draw(Graphics g){//draws jim
		if(dir == DOWN){
			g.drawImage(crouch[3],x,y,null);//crouching
		}
		else{
			g.drawImage(playerPics[frame+(dir*9)],x,y,null);//regular
		}
    }
    //getter and setter methods
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
	
	public boolean getIsThrowing(){
		return isThrowing;
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
	
	public void setIsThrowing(boolean set){
		isThrowing = set;
	}
  
}

class Projectile{
    private int x, y, speed; 
    private static Image ball = new ImageIcon("Assets/ComSci/pencil.png").getImage();//loads image of projectile
    public Projectile(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;//sets speed and position
    }
    public void move(){
        x += speed;//moves projectiles to the left
    }
    public void paint(Graphics g){
        g.drawImage(ball, x, y, null);//draws image of projectile at its position
	}
	//getter methods
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}