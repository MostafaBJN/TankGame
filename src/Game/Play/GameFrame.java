/*** In The Name of Allah ***/
package Game.Play;

import Game.GUIMenu.GUIBase;
import Map.Ground;
import Map.MapManager;
import Thing.PlayingTank;
import Thing.Tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

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
	
	public static final int GAME_HEIGHT = MapManager.getSelectedMap().getVisualHeight() + 31 + 7;                  // 720p game resolution
	public static final int GAME_WIDTH = MapManager.getSelectedMap().getVisualWidth() + 6 + 7;  // wide aspect ratio

	//uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
	/*private BufferedImage image;*/ 

	private long lastRender;
	private ArrayList<Float> fpsHistory;

	private BufferStrategy bufferStrategy;
	
	public GameFrame(String title) {
		super(title);
		setSize(GAME_WIDTH, GAME_HEIGHT);
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
	 * G rendering with triple-buffering using BufferStrategy.
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
		// Draw background
//		g2d.setColor(Color.GRAY);
//		g2d.fillRect(0, 0, GAME_WIDTH +5 +5, GAME_HEIGHT+30 +5);
//		// Draw ball
//		g2d.setColor(Color.BLACK);
//		g2d.fillOval(state.locX, state.locY, state.diam, state.diam);
		for(ArrayList<Ground> listGround:state.getGameMap().getMap().getGrounds()){
			for(Ground ground:listGround){
				g2d.drawImage(ground.getStyleImage(), ground.getStartHorizontalVisualPointInMap()+6, ground.getStartVerticalVisualPointInMap()+31,null);
			}
		}
		for(PlayingTank playingTank:state.getGameMap().getPlayingTanks()){
			g2d.drawImage(playingTank.getModelImage(), playingTank.getX() - (Tank.widthOfTank/2), (playingTank.getY() - Tank.heightOfTank/2), null);
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
			g2d.setColor(Color.CYAN);
			g2d.setFont(g2d.getFont().deriveFont(18.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			int strHeight = g2d.getFontMetrics().getHeight();
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
			g2d.setColor(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
		}
	}
	
}
