package edu.up.cs301.Spades;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * @author Ryan Morrison, Jin Mok, Nick Wagner, Maddy Duretete
 * @version Dec. 2015 RELEASE
 *
 * Main Activity, primarily to handle the menu screen
 *
 * KNOWN BUG: Sometimes the game will start up without any cards on screen; the app must be
 *  restarted. Waiting for a few seconds before hitting "start game" seems to make it less
 *  likely, possibly a problem with interaction with the game framework.
 */

public class MainActivity extends GameMainActivity {

    private static final int PORT_NUMBER = 5555;

    /**
     * Create the default configuration for this game:
     * - one human player vs. three computer players
     *
     * @return the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Spades has three player types:  human, computer, and advanced computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) { return new SpadesHumanPlayer(name);}});

        playerTypes.add(new GamePlayerType("Local Computer Player") {
            public GamePlayer createPlayer(String name) { return new SpadesComputerPlayer(name);}});

        playerTypes.add(new GamePlayerType("Local Advanced Computer Player") {
            public GamePlayer createPlayer(String name) { return new SpadesComputerPlayerAdv(name);}});

        GameConfig defaultConfig = new GameConfig(playerTypes,4,4,"Spades",PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.addPlayer("Computer", 2); // player 3: a computer player
        defaultConfig.addPlayer("Computer", 3); // player 4: a computer player
        defaultConfig.setRemoteData("Remote Player", "", 0); // WE DO NOT SUPPORT NETWORK PLAY

        return defaultConfig;
    }

    public LocalGame createLocalGame() {
        return new SpadesLocalGame();
    }
}
