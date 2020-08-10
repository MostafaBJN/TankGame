/*** In The Name of Allah ***/
package Game.Play;

import Game.GUIMenu.GUIBase;
import Thing.Bullet;
import Thing.Map.Ground;
import Thing.Map.MapManager;
import Thing.PlayingTank;
import Thing.Prize;
import Thing.Tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Thing.Prize.*;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering, 
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends GUIBase {
	
	public static final int GAME_HEIGHT = MapManager.getSelectedMap().getVisualHeight() + (31 + 7);                  // 720p game resolution
	public static final int GAME_WIDTH = MapManager.getSelectedMap().getVisualWidth() + (6 + 7);// wide aspect ratio
	public static final int FRAME_HEIGHT = GAME_HEIGHT + (46 + 10 + 4 *(10));// wide aspect ratio
	public static final int FRAME_WIDTH = GAME_WIDTH;// wide aspect ratio

	//uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
	/*private BufferedImage image;*/ 

	private long lastRender;
	private ArrayList<Float> fpsHistory;

	private BufferStrategy bufferStrategy;
	
	public GameFrame(String title) {
		super(title);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		lastRender = -1;
		fpsHistory = new ArrayList<>(100);

	/*	try{
			image = ImageIO.read(new File("Icon.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}*/
	}
	
	/**
	 * This must be called once after the JFrame is shown:
	 *    frame.setVisible(true);
	 * and before any rendering is started.
	 */
	public void initBufferStrategy() {
		// Triple-buffering
		createBufferStrategy(3);
		bufferStrategy = getBufferStrategy();
	}

	
	/**
	 * Game rendering with triple-buffering using BufferStrategy.
	 */
	public void render(GameState state) {
		// Render single frame
		do {
			// The following loop ensures that the contents of the drawing buffer
			// are consistent in case the underlying surface was recreated
			do {
				// Get a new graphics context every time through the loop
				// to make sure the strategy is validated
				Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
				try {
					doRendering(graphics, state);
				} finally {
					// Dispose the graphics
					graphics.dispose();
				}
				// Repeat the rendering if the drawing buffer contents were restored
			} while (bufferStrategy.contentsRestored());

			// Display the buffer
			bufferStrategy.show();
			// Tell the system to do the drawing NOW;
			// otherwise it can take a few extra ms and will feel jerky!
			Toolkit.getDefaultToolkit().sync();

		// Repeat the rendering if the drawing buffer was lost
		} while (bufferStrategy.contentsLost());
	}
	
	/**
	 * Rendering all game elements based on the game state.
	 */
	//TODO change
	private void doRendering(Graphics2D g2d, GameState state) {
		for(ArrayList<Ground> listGround:state.getGameMap().getMap().getGrounds()){
			for(Ground ground:listGround){
				ground.styleFinder();
				g2d.drawImage(ground.getStyleImage(), ground.getStartHorizontalVisualPointInMap()+6, ground.getStartVerticalVisualPointInMap()+31,null);
			}
		}
		int strWidth;
		int strHeight;
		int namesWidthScale = (GAME_WIDTH - 10)/state.getGameMap().getPlayers().size();
		int playerNumber = 0;
		for(PlayingTank playingTank:state.getGameMap().getPlayingTanks()){
			//playingTank.styleFinder();
			strHeight = g2d.getFontMetrics().getHeight();
			String name = playingTank.getName();
			strWidth = g2d.getFontMetrics().stringWidth(name);
			g2d.drawString(name, playerNumber * namesWidthScale + 15 , GAME_HEIGHT + 51 + 2);

			String health;
			if(!playingTank.death()){
				g2d.drawImage(playingTank.getStyleImage(), playingTank.getX() - (playingTank.getArea().getWidth()/2), (playingTank.getY() - playingTank.getArea().getHeight()/2), null);

				g2d.drawString(name, playingTank.getX() - strWidth/2, (playingTank.getY() + Tank.heightOfTank/2 + 2));
				//g2d.drawString(name, GAME_WIDTH, (playingTank.getY() - Tank.heightOfTank/2));

				health = String.valueOf(playingTank.getHealth());
				strWidth = g2d.getFontMetrics().stringWidth(health);
				g2d.drawString(health, playingTank.getX() - strWidth/2, (playingTank.getY() + Tank.heightOfTank/2 + 2 + (strHeight+2)));

			}

			if(!playingTank.death()) {
				health = "Health = " + String.valueOf(playingTank.getHealth());
			}
			else {
				health = "DIED";
			}


			g2d.drawImage(playingTank.getStyleImage(),playerNumber * namesWidthScale + 15 , GAME_HEIGHT + 5, null);

			strWidth = g2d.getFontMetrics().stringWidth(health);
			g2d.drawString(health, playerNumber * namesWidthScale + 15 , GAME_HEIGHT + 51 + strHeight + 2 + 2);

			String kills = "Kills = " + String.valueOf(playingTank.getKills());
			strWidth = g2d.getFontMetrics().stringWidth(kills);
			g2d.drawString(health, playerNumber * namesWidthScale + 15 , GAME_HEIGHT + 51 + (strHeight+2)*2);

			String deaths = "Deaths = " + String.valueOf(playingTank.getDeaths());
			strWidth = g2d.getFontMetrics().stringWidth(deaths);
			g2d.drawString(health, playerNumber * namesWidthScale + 15 , GAME_HEIGHT + 51 + (strHeight+2)*3);

			Prize prize = playingTank.getCurrentPrize();
			if(prize != null) {
				String prizeName = "Prize = ";
				switch (prize.getStyle()) {
					case SHIELD -> prizeName += "SHIELD";
					case HEALTH_INCREASE -> prizeName += "HEALTH_INCREASE";
					case LASER -> prizeName += "LASER";
					case POWER_SHOT_TWICE -> prizeName += "BULLET_POWER_DOUBLE";
					case POWER_SHOT_TRIPLE -> prizeName += "BULLET_POWER_TRIPLE";
				}
				strWidth = g2d.getFontMetrics().stringWidth(prizeName);
				g2d.drawString(prizeName, playerNumber * namesWidthScale + 15, GAME_HEIGHT + 51 + (strHeight + 2) * 4);
			}

			for(Bullet bullet:playingTank.getBullets()){
				BufferedImage image = playingTank.getStyleImage();
				g2d.drawImage(image, playingTank.getX() - (image.getWidth()/2), (playingTank.getY() - image.getHeight()/2), null);
			}

			playerNumber++;
		}


/*		g2d.drawImage(image,state.locX,state.locY,null);*/


		// Print FPS info
		long currentRender = System.currentTimeMillis();
		if (lastRender > 0) {
			fpsHistory.add(1000.0f / (currentRender - lastRender));
			if (fpsHistory.size() > 100) {
				fpsHistory.remove(0); // remove oldest
			}
			float avg = 0.0f;
			for (float fps : fpsHistory) {
				avg += fps;
			}
			avg /= fpsHistory.size();
			//TODO WORDS O SCREEN
			String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
					avg, (currentRender - lastRender));
			g2d.setColor(Color.RED);
			g2d.setFont(g2d.getFont().deriveFont(18.0f));
			strWidth = g2d.getFontMetrics().stringWidth(str);
			strHeight = g2d.getFontMetrics().getHeight();
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight+50);
		}
		lastRender = currentRender;
		// Print user guide
//		String userGuide
//				= "Use the MOUSE or ARROW KEYS to move the BALL. "
//				+ "Press ESCAPE to end the game.";
//		g2d.setFont(g2d.getFont().deriveFont(18.0f));
//		g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
		// Draw GAME OVER

        //TODO our part
		if (state.gameOver) {
			String str = "GAME OVER";
			g2d.setColor(Color.RED);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
		}
	}
	
}
