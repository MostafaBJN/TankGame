/*** In The Name of Allah ***/
package Game.Play;


import Game.Run.Run;
import Service.Player;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods 
 * in the while loop (update() and render()) should be 
 * long running! Both must execute very quickly, without 
 * any waiting and blocking!
 * 
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class GameLoop implements Runnable {
	
	/**
	 * PlayMultiPlayerMenu Per Second.
	 * Higher is better, but any value above 24 is fine.
	 */
	public static final int FPS = 90;
	
	private GameFrame canvas;
	private GameState state;

	public GameLoop(GameFrame frame) {
		canvas = frame;
	}

	public GameLoop() {
		canvas = null;
	}
	
	/**
	 * This must be called before the game loop starts.
	 */
	public void init(GameMap gameMap, Player activePlayer) {
		state = new GameState(gameMap, activePlayer);
		canvas.addKeyListener(state.getKeyListener());
		canvas.addMouseListener(state.getMouseListener());
		canvas.addMouseMotionListener(state.getMouseMotionListener());
	}

	public void init(GameMap gameMap, Player activePlayer, boolean computer){
		state = new GameState(gameMap, activePlayer, computer);
		canvas.addKeyListener(state.getKeyListener());
		canvas.addMouseListener(state.getMouseListener());
		canvas.addMouseMotionListener(state.getMouseMotionListener());
	}

	/**
	 * for offline
	 */
	public void init(GameMap gameMap) {
		state = new GameState(gameMap);
		if(canvas != null) {
			canvas.addKeyListener(state.getKeyListener());
			canvas.addMouseListener(state.getMouseListener());
			canvas.addMouseMotionListener(state.getMouseMotionListener());
		}
	}

	@Override
	public void run() {
		boolean gameOver = false;
		while (!gameOver) {
			try {
				long start = System.currentTimeMillis();
				state.update();
				if(canvas != null) {
					canvas.render(state);
				}
				gameOver = state.gameOver;
				//stabling fps
				long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
				if (delay > 0)
					Thread.sleep(delay);
			} catch (InterruptedException ex) {
			}
		}
		if(canvas != null) {
			canvas.render(state);
		}
	}
}
