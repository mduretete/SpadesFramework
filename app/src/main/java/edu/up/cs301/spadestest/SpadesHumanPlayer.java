package edu.up.cs301.spadestest;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * @author Ryan Morrison, Jin Mok, Nick Wagner, Maddy Duretete
 * @version Nov. 2015
 *
 * Class that extends GameHumanPlayer and specifies the actions
 *      the player can make and values that the player object
 *      holds
 */
public class SpadesHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    // Widgets to be used and modified during play
    private TextView playerBidTextView;
    private TextView partnerBidTextView;
    private TextView LBidTextView;
    private TextView RBidTextView;
    private TextView playerTrickTextView;
    private TextView partnerTrickTextView;
    private TextView LTrickTextView;
    private TextView RTrickTextView;
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

    SpadesState myGameState;
    private GameMainActivity myActivity;

    private String[] cardNames;
    private ArrayList<Bitmap> deck;


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
     * recieveInfo(): Callback method, called when player gets a message
     *
     * @param info
     */
    public void receiveInfo(GameInfo info) {
        if (info instanceof SpadesState) {
            myGameState = (SpadesState) info;
            playerBidTextView.setText("" + myGameState.getPlayerBids(0));
            LBidTextView.setText("" + myGameState.getPlayerBids(1));
            partnerBidTextView.setText("" + myGameState.getPlayerBids(2));
            RBidTextView.setText("" + myGameState.getPlayerBids(3));
            playerTrickTextView.setText("" + myGameState.getPlayerTricks(0));
            LTrickTextView.setText("" + myGameState.getPlayerTricks(1));
            partnerTrickTextView.setText("" + myGameState.getPlayerTricks(2));
            RTrickTextView.setText("" + myGameState.getPlayerTricks(3));
            c0.setImageResource(myGameState.getPlayer1Hand()[0].imageId);
            c1.setImageResource(myGameState.getPlayer1Hand()[1].imageId);
            c2.setImageResource(myGameState.getPlayer1Hand()[2].imageId);
            c3.setImageResource(myGameState.getPlayer1Hand()[3].imageId);
            c4.setImageResource(myGameState.getPlayer1Hand()[4].imageId);
            c5.setImageResource(myGameState.getPlayer1Hand()[5].imageId);
            c6.setImageResource(myGameState.getPlayer1Hand()[6].imageId);
            c7.setImageResource(myGameState.getPlayer1Hand()[7].imageId);
            c8.setImageResource(myGameState.getPlayer1Hand()[8].imageId);
            c9.setImageResource(myGameState.getPlayer1Hand()[9].imageId);
            c10.setImageResource(myGameState.getPlayer1Hand()[10].imageId);
            c11.setImageResource(myGameState.getPlayer1Hand()[11].imageId);
            c12.setImageResource(myGameState.getPlayer1Hand()[12].imageId);
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

        playerBidTextView = (TextView) activity.findViewById(R.id.p0bid);
        partnerBidTextView = (TextView) activity.findViewById(R.id.p2bid);
        LBidTextView = (TextView) activity.findViewById(R.id.p1bid);
        RBidTextView = (TextView) activity.findViewById(R.id.p3bid);
        playerTrickTextView = (TextView) activity.findViewById(R.id.p0trick);
        partnerTrickTextView = (TextView) activity.findViewById(R.id.p0trick);
        LTrickTextView = (TextView) activity.findViewById(R.id.p0trick);
        RTrickTextView = (TextView) activity.findViewById(R.id.p0trick);

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

        // create arrayList to hold the deck
        cardNames = activity.getResources().getStringArray(R.array.card_names);
        deck = new ArrayList<Bitmap>();
        TypedArray cardIds = activity.getResources().obtainTypedArray(R.array.cardIdArray); //don't use "activity?"
        for (int i = 0; i < cardNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = cardIds.getResourceId(i, 0);
            if (id == 0) id = cardIds.getResourceId(0, 0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(activity.getResources(), id);
            deck.add(img);
        }

        c0.setOnTouchListener(new ChoiceTouchListener());
        c1.setOnTouchListener(new ChoiceTouchListener());
        c2.setOnTouchListener(new ChoiceTouchListener());
        c3.setOnTouchListener(new ChoiceTouchListener());
        c4.setOnTouchListener(new ChoiceTouchListener());
        c5.setOnTouchListener(new ChoiceTouchListener());
        c6.setOnTouchListener(new ChoiceTouchListener());
        c7.setOnTouchListener(new ChoiceTouchListener());
        c8.setOnTouchListener(new ChoiceTouchListener());
        c9.setOnTouchListener(new ChoiceTouchListener());
        c10.setOnTouchListener(new ChoiceTouchListener());
        c11.setOnTouchListener(new ChoiceTouchListener());
        c12.setOnTouchListener(new ChoiceTouchListener());

        p0card.setOnDragListener(new ChoiceDragListener());
    }

    /**
     * onClick(): handles buttons being clicked
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v == c0) {
            playerBidTextView.setText("BLAH");
        } else if (v == c1) {

        } else if (v == c2) {

        } else if (v == c3) {

        } else if (v == c4) {

        } else if (v == c5) {

        } else if (v == c6) {

        } else if (v == c7) {

        } else if (v == c8) {

        } else if (v == c9) {

        } else if (v == c10) {

        } else if (v == c11) {

        } else if (v == c12) {

        }
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }

        }


    }
    @TargetApi(11)
    private class ChoiceDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
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
                    dropped.setDrawingCacheEnabled(true);
                    //cheaty temporary way to set the card because we can't use "dropped" yet
                    dropSpace.setImageBitmap(dropped.getDrawingCache());
                    //need to update played cards and turn
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}