package edu.up.cs301.spadestest;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * @author Ryan Morrison, Jin Mok, Nick Wagner, Maddy Duretete
 * @version Nov. 2015
 *
 * Class that allows the bid action to be recognized
 */
public class SpadesBidAction extends GameAction {

    /**
     * SpadesBidAction(): calls parent player object making the action
     * @param player
     */
    public SpadesBidAction(GamePlayer player){
        super(player);
    }
}
