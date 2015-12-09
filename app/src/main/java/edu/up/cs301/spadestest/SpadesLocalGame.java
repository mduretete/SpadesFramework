package edu.up.cs301.spadestest;

import android.os.Message;

import java.util.ArrayList;
import java.util.logging.Handler;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.util.GameTimer;
import edu.up.cs301.game.util.Tickable;

/**
 * @author Ryan Morrison, Jin Mok, Nick Wagner, Maddy Duretete
 * @version Dec. 2015 ALPHA
 *
 * Class that extends LocalGame and handles the in-game conditions, such as turns,
 *      winning, and valid moves.
 */
public class SpadesLocalGame extends LocalGame {

    private SpadesState spadesGameState; //gameState used in the local game

    /**
     * SpadesLocalGame(): ctor for the new game state
     */
    protected SpadesLocalGame() {
        spadesGameState = new SpadesState();
    }//ctor

    /**
     * sendUpdatedStateTo(): send updated state to a player
     * @param p gamePlayer to send the copied game state to
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        SpadesState copy = new SpadesState(spadesGameState);
        p.sendInfo(copy);
    }//sendUpdatedStateTo()

    /**
     * canMove(): can a player make a move/action?
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return boolean
     */
    @Override
    public boolean canMove(int playerIdx) {
        return playerIdx == spadesGameState.getCurrentPlayer();
    }//canMove()


    /**
     * checkIfGameOver(): has the win condition been met?
     * @return String
     */
    @Override
    public String checkIfGameOver() {
        if(spadesGameState.getTeam1Score() >= 200){
            return "TEAM 1 WINS";
        } else if (spadesGameState.getTeam2Score() >= 200) {
            return "TEAM 2 WINS";
        }
        return null;
    }//checkIfGameOver()

    /**
     * makeMove(): is called when a new action arrives from a player
     * @param action the move that the player has sent to the game
     * @return boolean
     */
    @Override
    public boolean makeMove(GameAction action) {
        if(!canMove(getPlayerIdx(action.getPlayer()))){
            return false;
        }
        if(action instanceof SpadesBidAction){
            spadesGameState.placeBid(((SpadesBidAction) action).getBid());
        }
        else if(action instanceof EndTrickAction) {
            spadesGameState.noShowCard();

        }
        else if(action instanceof SpadesPlayCardAction){
            spadesGameState.playCard(((SpadesPlayCardAction) action).getCardIndex());
        }

        return true;
    }//makeMove()






}//SpadesLocalGame.java