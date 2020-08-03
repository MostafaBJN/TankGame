/*** In The Name of Allah ***/
package game.sample.ball;

import Map.MapManager;
import com.company.Server.Game;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Program start.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {
	
    public static void main(String[] args) throws IOException {
		// Initialize the global thread-pool
		ThreadPool.init();
		
		// Show the game menu ...
		String name = "test3";
		MapManager.makeLoadedMap(name, MapManager.loadMap(name));
		MapManager.selectMap(name);
		
		// After the player clicks 'PLAY' ...
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame frame = new GameFrame("Simple Ball !");
				frame.setLocationRelativeTo(null); // put frame at center of screen
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.initBufferStrategy();
				// Create and execute the game-loop
				GameLoop game = new GameLoop(frame);
				game.init();
				ThreadPool.execute(game);
				// and the game starts ...
			}
		});
    }
}