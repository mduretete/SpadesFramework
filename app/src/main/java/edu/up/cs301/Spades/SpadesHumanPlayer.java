package edu.up.cs301.Spades;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * @author Ryan Morrison, Jin Mok, Nick Wagner, Maddy Duretete
 * @version Dec. 2015 RELEASE
 *
 * Class that extends GameHumanPlayer and specifies the actions
 *      the player can make and values that the player object
 *      holds
 */
public class SpadesHumanPlayer extends GameHumanPlayer implements View.OnDragListener, View.OnTouchListener, View.OnClickListener {

    // Widgets to be used and modified during play
    private TextView playerBidTextView;
    private TextView partnerBidTextView;
    private TextView LBidTextView;
    private TextView RBidTextView;
    private TextView playerTrickTextView;
    private TextView partnerTrickTextView;
    private TextView LTrickTextView;
    private TextView RTrickTextView;
    private TextView p1name;
    private TextView p2name;
    private TextView p3name;
    private TextView p0bid;
    private TextView p1bid;
    private TextView p2bid;
    private TextView p3bid;
    private TextView p0tricks;
    private TextView p1tricks;
    private TextView p2tricks;
    private TextView p3tricks;
    private ImageView p0card;
    private ImageView p1card;
    private ImageView p2card;
    private ImageView p3card;
    private ImageView c0;
    private ImageView c1;
    private ImageView c2;
    private ImageView c3;
    private ImageView c4;
    private ImageView c5;
    private ImageView c6;
    private ImageView c7;
    private ImageView c8;
    private ImageView c9;
    private ImageView c10;
    private ImageView c11;
    private ImageView c12;

    private EditText bidView;
    private Button bidConfirm;
    private Button nextRound;

    private TextView t1Score;
    private TextView t2Score;
    private TextView winnerText;

    SpadesState myGameState;
    private GameMainActivity myActivity;

    static boolean gameHasBeenWon = false;

    /**
     * SpadesHumanPlayer():ctor for the human player
     *
     * @param name
     */
    public SpadesHumanPlayer(String name) {
        super(name);
    }

    /**
     * getTopView(): returns the GUI's top object
     *
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * recieveInfo(): recieve info from the local game, set the GUI
     *
     * @param info
     */
    public void receiveInfo(GameInfo info) {
        if (info instanceof SpadesState) {

            //make the GUI
            myGameState = (SpadesState) info;
            if (myGameState.getPlayerBids(0) != -1) {
                playerBidTextView.setText("" + myGameState.getPlayerBids(0));
            }
            if (myGameState.getPlayerBids(1) != -1) {
                LBidTextView.setText("" + myGameState.getPlayerBids(1));
            }
            if (myGameState.getPlayerBids(2) != -1) {
                partnerBidTextView.setText("" + myGameState.getPlayerBids(2));
            }
            if (myGameState.getPlayerBids(3) != -1) {
                RBidTextView.setText("" + myGameState.getPlayerBids(3));
            }

            playerTrickTextView.setText("" + myGameState.getPlayerTricks(0));
            LTrickTextView.setText("" + myGameState.getPlayerTricks(1));
            partnerTrickTextView.setText("" + myGameState.getPlayerTricks(2));
            RTrickTextView.setText("" + myGameState.getPlayerTricks(3));

            if (myGameState.cardsPlayed == 51) {
                nextRound.setEnabled(true);
            }

            //if not our turn, we can't play
            if (myGameState.getCurrentPlayer() > 0) {
                c0.setEnabled(false);
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);
                c4.setEnabled(false);
                c5.setEnabled(false);
                c6.setEnabled(false);
                c7.setEnabled(false);
                c8.setEnabled(false);
                c9.setEnabled(false);
                c10.setEnabled(false);
                c11.setEnabled(false);
                c12.setEnabled(false);
            }
            else { //we can play our cards
                c0.setEnabled(true);
                c1.setEnabled(true);
                c2.setEnabled(true);
                c3.setEnabled(true);
                c4.setEnabled(true);
                c5.setEnabled(true);
                c6.setEnabled(true);
                c7.setEnabled(true);
                c8.setEnabled(true);
                c9.setEnabled(true);
                c10.setEnabled(true);
                c11.setEnabled(true);
                c12.setEnabled(true);
            }
            setCardsPlayable();
                if (myGameState.getPlayer1Hand().get(0) != null) {
                    c0.setImageResource(myGameState.getPlayer1Hand().get(0).imageId);
                }
                if (myGameState.getPlayer1Hand().get(1) != null) {
                    c1.setImageResource(myGameState.getPlayer1Hand().get(1).imageId);
                }
                if (myGameState.getPlayer1Hand().get(2) != null) {
                    c2.setImageResource(myGameState.getPlayer1Hand().get(2).imageId);
                }
                if (myGameState.getPlayer1Hand().get(3) != null) {
                    c3.setImageResource(myGameState.getPlayer1Hand().get(3).imageId);
                }
                if (myGameState.getPlayer1Hand().get(4) != null) {
                    c4.setImageResource(myGameState.getPlayer1Hand().get(4).imageId);
                }
                if (myGameState.getPlayer1Hand().get(5) != null) {
                    c5.setImageResource(myGameState.getPlayer1Hand().get(5).imageId);
                }
                if (myGameState.getPlayer1Hand().get(6) != null) {
                    c6.setImageResource(myGameState.getPlayer1Hand().get(6).imageId);
                }
                if (myGameState.getPlayer1Hand().get(7) != null) {
                    c7.setImageResource(myGameState.getPlayer1Hand().get(7).imageId);
                }
                if (myGameState.getPlayer1Hand().get(8) != null) {
                    c8.setImageResource(myGameState.getPlayer1Hand().get(8).imageId);
                }
                if (myGameState.getPlayer1Hand().get(9) != null) {
                    c9.setImageResource(myGameState.getPlayer1Hand().get(9).imageId);
                }
                if (myGameState.getPlayer1Hand().get(10) != null) {
                    c10.setImageResource(myGameState.getPlayer1Hand().get(10).imageId);
                }
                if (myGameState.getPlayer1Hand().get(11) != null) {
                    c11.setImageResource(myGameState.getPlayer1Hand().get(11).imageId);
                }
                if (myGameState.getPlayer1Hand().get(12) != null) {
                    Log.i("Human","Drawing last card");
                    c12.setImageResource(myGameState.getPlayer1Hand().get(12).imageId);
                }


            //get the trick cards for the human player to see

               // p0card.setImageResource(android.R.color.transparent);

            if (myGameState.currentPlayer == 0 && myGameState.cardsInTrick == 4 && myGameState.cardsPlayed != -1) { //my turn and I (human) won
                try {
                    Thread.sleep(200); //update slowly
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (myGameState.showPlayer0) {
                    game.sendAction(new EndTrickAction(this));
                }
            }

            if (!myGameState.showPlayer0) {
                p0card.setImageResource(R.mipmap.blank);
            }

            if (!myGameState.showPlayer1) {
                p1card.setImageResource(R.mipmap.blank2);
            }
            else if (myGameState.getTrickCards().get(1) != null) {
                p1card.setImageResource(myGameState.getTrickCards().get(1).imageId);
            }

            if (!myGameState.showPlayer2) {
                p2card.setImageResource(R.mipmap.blank3);
            }
            else if (myGameState.getTrickCards().get(2) != null) {
                p2card.setImageResource(myGameState.getTrickCards().get(2).imageId);
            }

            if (!myGameState.showPlayer3) {
                p3card.setImageResource(R.mipmap.blank4);
            }
            else if (myGameState.getTrickCards().get(3) != null) {
                p3card.setImageResource(myGameState.getTrickCards().get(3).imageId);
            }

            //update team score
            t1Score.setText("T1 Score:       " + myGameState.team1Score);
            t2Score.setText("T2 Score:      " + myGameState.team2Score);

            //posts which team "wins"/is winning at the end of a round
            if(myGameState.winningTeam == 0) winnerText.setText("Team 1 winning!");
            else if(myGameState.winningTeam == 1) winnerText.setText("Team 2 winning!");
            else if(myGameState.winningTeam == 2) winnerText.setText("It's tied!");


        }
    }

    /**
     * setAsGui(): Sets this player as the one attached to the GUI, saves the
     * activity, then invokes subclass-specific method
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;
        activity.setContentView(R.layout.activity_main);

        AssetManager am = activity.getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Adequate-ExtraLight.ttf"));

        playerBidTextView = (TextView) activity.findViewById(R.id.p0bid);
        partnerBidTextView = (TextView) activity.findViewById(R.id.p2bid);
        LBidTextView = (TextView) activity.findViewById(R.id.p1bid);
        RBidTextView = (TextView) activity.findViewById(R.id.p3bid);
        playerTrickTextView = (TextView) activity.findViewById(R.id.p0trick);
        partnerTrickTextView = (TextView) activity.findViewById(R.id.p2trick);
        LTrickTextView = (TextView) activity.findViewById(R.id.p1trick);
        RTrickTextView = (TextView) activity.findViewById(R.id.p3trick);

        //unedited views
        p1name = (TextView) activity.findViewById(R.id.p1name);
        p2name = (TextView) activity.findViewById(R.id.p2name);
        p3name = (TextView) activity.findViewById(R.id.p3name);
        p0bid = (TextView) activity.findViewById(R.id.p0bidfield);
        p1bid = (TextView) activity.findViewById(R.id.p1bidfield);
        p2bid = (TextView) activity.findViewById(R.id.p2bidfield);
        p3bid = (TextView) activity.findViewById(R.id.p3bidfield);
        p0tricks = (TextView) activity.findViewById(R.id.p0trickfield);
        p1tricks = (TextView) activity.findViewById(R.id.p1trickfield);
        p2tricks = (TextView) activity.findViewById(R.id.p2trickfield);
        p3tricks = (TextView) activity.findViewById(R.id.p3trickfield);
        p1name.setTypeface(typeface);
        p2name.setTypeface(typeface);
        p3name.setTypeface(typeface);
        p0bid.setTypeface(typeface);
        p1bid.setTypeface(typeface);
        p2bid.setTypeface(typeface);
        p3bid.setTypeface(typeface);
        p0tricks.setTypeface(typeface);
        p1tricks.setTypeface(typeface);
        p2tricks.setTypeface(typeface);
        p3tricks.setTypeface(typeface);

        bidView = (EditText) activity.findViewById(R.id.bidview);
        bidConfirm = (Button) activity.findViewById(R.id.bidconfirm);
        nextRound = (Button) activity.findViewById(R.id.nextRound);

        t1Score = (TextView) activity.findViewById(R.id.team1score);
        t2Score = (TextView) activity.findViewById(R.id.team2score);
        winnerText = (TextView) activity.findViewById(R.id.winnertext);

        playerBidTextView.setTypeface(typeface);
        partnerBidTextView.setTypeface(typeface);
        LBidTextView.setTypeface(typeface);
        RBidTextView.setTypeface(typeface);
        playerTrickTextView.setTypeface(typeface);
        partnerTrickTextView.setTypeface(typeface);
        LTrickTextView.setTypeface(typeface);
        RTrickTextView.setTypeface(typeface);

        bidView.setTypeface(typeface);
        t1Score.setTypeface(typeface);
        t2Score.setTypeface(typeface);
        winnerText.setTypeface(typeface);

        c0 = (ImageView) activity.findViewById(R.id.c0);
        c1 = (ImageView) activity.findViewById(R.id.c1);
        c2 = (ImageView) activity.findViewById(R.id.c2);
        c3 = (ImageView) activity.findViewById(R.id.c3);
        c4 = (ImageView) activity.findViewById(R.id.c4);
        c5 = (ImageView) activity.findViewById(R.id.c5);
        c6 = (ImageView) activity.findViewById(R.id.c6);
        c7 = (ImageView) activity.findViewById(R.id.c7);
        c8 = (ImageView) activity.findViewById(R.id.c8);
        c9 = (ImageView) activity.findViewById(R.id.c9);
        c10 = (ImageView) activity.findViewById(R.id.c10);
        c11 = (ImageView) activity.findViewById(R.id.c11);
        c12 = (ImageView) activity.findViewById(R.id.c12);

        p0card = (ImageView) activity.findViewById(R.id.p0card);
        p1card = (ImageView) activity.findViewById(R.id.p1card);
        p2card = (ImageView) activity.findViewById(R.id.p2card);
        p3card = (ImageView) activity.findViewById(R.id.p3card);

        TextView p0Name = (TextView) activity.findViewById(R.id.p0name);
        p0Name.setTypeface(typeface);

        c0.setOnTouchListener(this);
        c1.setOnTouchListener(this);
        c2.setOnTouchListener(this);
        c3.setOnTouchListener(this);
        c4.setOnTouchListener(this);
        c5.setOnTouchListener(this);
        c6.setOnTouchListener(this);
        c7.setOnTouchListener(this);
        c8.setOnTouchListener(this);
        c9.setOnTouchListener(this);
        c10.setOnTouchListener(this);
        c11.setOnTouchListener(this);
        c12.setOnTouchListener(this);

        p0card.setOnDragListener(this);

        bidConfirm.setOnClickListener(this);
        nextRound.setOnClickListener(this);
        nextRound.setEnabled(false);

        p0Name.setText(this.name.toUpperCase());
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if(!bidView.isEnabled()) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    view.setVisibility(View.INVISIBLE);
                    //cast passed area to drop in
                    ImageView dropSpace = (ImageView) v;
                    //cast active button being dragged
                    ImageView dropped = (ImageView) view;
                    //drop the card
                    //dropped.setDrawingCacheEnabled(true);
                    //dropSpace.setImageBitmap(dropped.getDrawingCache());

                    //update played card
                    if (dropped == c0) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(0).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 0));
                    } else if (dropped == c1) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(1).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 1));
                    } else if (dropped == c2) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(2).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 2));
                    } else if (dropped == c3) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(3).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 3));
                    } else if (dropped == c4) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(4).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 4));
                    } else if (dropped == c5) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(5).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 5));
                    } else if (dropped == c6) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(6).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 6));
                    } else if (dropped == c7) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(7).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 7));
                    } else if (dropped == c8) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(8).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 8));
                    } else if (dropped == c9) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(9).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 9));
                    } else if (dropped == c10) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(10).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 10));
                    } else if (dropped == c11) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(11).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 11));
                    } else if (dropped == c12) {
                        dropSpace.setImageResource(myGameState.getPlayer1Hand().get(12).imageId);
                        game.sendAction(new SpadesPlayCardAction(this, 12));
                    }
                    dropped = null;

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //only act if the bid has been confirmed already
        if(!bidView.isEnabled()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * write a better comment here
     * @param v
     */
    @Override
    public void onClick(View v){
        if(v == bidConfirm){
            //default bid to zero if the user
            int bid = 0; //init bid
            try { //in a try-catch so that parsing something other than an int doesn't break the game
                bid = Integer.parseInt(bidView.getText().toString());
                if((bid>=0)&&(bid<=13)) {
                    //myGameState.playerBids[0] = bid;
                    playerBidTextView.setText(bidView.getText().toString());

                    bidView.setEnabled(false);
                    bidConfirm.setEnabled(false);

                    game.sendAction(new SpadesBidAction(this, bid));
                    Log.i("Send bid","Sent bid");
                    return;
                }
            }catch(Exception e){}

            //in correspondence with default
            game.sendAction(new SpadesBidAction(this, 1));

            //disables bidView and bidConfirm so players can't change their bids
            bidView.setEnabled(false);
            bidConfirm.setEnabled(false);

            //if there is an error, show it, for both the Programmer and the user
            if(bidView.getText().toString().equals("")){
                bidView.setText("ERROR: No Entry");
            } else{
                bidView.setText("ERROR: " + bidView.getText().toString());
            }
        }
        else if (v == nextRound) {
            nextRound.setEnabled(false);
                bidView.setText("");
                bidView.setEnabled(true);
                bidConfirm.setEnabled(true);

                c0.setVisibility(View.VISIBLE);
                c1.setVisibility(View.VISIBLE);
                c2.setVisibility(View.VISIBLE);
                c3.setVisibility(View.VISIBLE);
                c4.setVisibility(View.VISIBLE);
                c5.setVisibility(View.VISIBLE);
                c6.setVisibility(View.VISIBLE);
                c7.setVisibility(View.VISIBLE);
                c8.setVisibility(View.VISIBLE);
                c9.setVisibility(View.VISIBLE);
                c10.setVisibility(View.VISIBLE);
                c11.setVisibility(View.VISIBLE);
                c12.setVisibility(View.VISIBLE);

                game.sendAction(new NextRoundAction(this));
                Log.i("Send bid", "Sent new round");

        }
    }

    /**
     * choose which cards the user can play and can't play
     */
    private void setCardsPlayable(){
        int card;

        Card leadCard; // if card has been led with, this is it
        int leadPlayer = myGameState.getLeadTrick(); // who led
        String leadSuit; // if card has been led with, this is its suit
        ArrayList<Card> playerHand = myGameState.getPlayer1Hand(); // player's hand
        ArrayList<ImageView> cardsToDisable = new ArrayList<>();
        ArrayList<ImageView> myCards = new ArrayList<>(); //putting all the card buttons in a list
        myCards.add(c0);
        myCards.add(c1);
        myCards.add(c2);
        myCards.add(c3);
        myCards.add(c4);
        myCards.add(c5);
        myCards.add(c6);
        myCards.add(c7);
        myCards.add(c8);
        myCards.add(c9);
        myCards.add(c10);
        myCards.add(c11);
        myCards.add(c12);

        for(card=0;card<13;card++) { //check each card in the player's hand
            if(playerHand.get(card)!=null) { //make sure it's an existing card
                if ((leadPlayer != -1)) { //if not lead player, there are restrictions
                    leadCard = myGameState.getTrickCards().get(leadPlayer); //store leading card info
                    leadSuit = leadCard.getSuit();

                    if (!leadSuit.equals(playerHand.get(card).getSuit())) { //if can't play the chosen card
                        cardsToDisable.add(myCards.get(card));
                    }
                } //if the lead player, but spades have not been broken, spades can't played
                else if ((playerHand.get(card).getSuit().equals("S")) && ((!myGameState.spadesBroken))) {
                    if (playerHand.get(card).getSuit().equals("S"))
                        cardsToDisable.add(myCards.get(card));
                }
            }else cardsToDisable.add(myCards.get(card));
        }

        if(cardsToDisable.size()<13)
            for(card=0;card<cardsToDisable.size();card++)
                if(cardsToDisable.get(card)!=null)
                    cardsToDisable.get(card).setEnabled(false);
    }
}