package com.example.a300671.mazeproject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    private DemoView demoView;
    
    Character character;
    


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                // not using the default activity_main XML resource
        demoView = findViewById(R.id.signature_canvas);
        character = new Character();
        demoView.setCharacter(character);

        //Listens for when the user touches the screen then executes code
        demoView.setOnTouchListener(new View.OnTouchListener() // this onTouchListener is the basis of the entire program: it is the dedicator of all time and space- every layer of reality, in fact- in the program
        {                                                      // how it works is that, here an "onTouch-Lister" is being constructed with the below characteristics, overridden where we want it to do certain things. This constructed onTouchListener is then assigned to DemoView 
            @Override
            public boolean onTouch(View v, MotionEvent event)  // this override is saying that we want onTouch to respond in a certain way, and the body below is how we want it to respond
            {                                                  // the passed view will be DemoView because we assigned the onTouchListener to DemoView, and the MotionEvent is an event (such as touching down on the screen, mnoving your finger, and lifting up your finger) that is passed to the view by the device itself

                int x = (int) event.getRawX();                 // captures and stores the lateral value of the screen-press
                int y = (int) event.getRawY();                 // captures and stores the vertical value of the screen-press

                switch (event.getAction())                     // sets the "switch" expression's target variable to the action-key of the event see next comment
                {                                                 // switch case expressions are basically if-then-else statements that make more sense to a programmer looking at the code - the target variable is set in switch(variable), and what follows is a set of cases, saying that in the case of the variable being this, do this
                    case (MotionEvent.ACTION_DOWN):            // in this case the action-key is ACTION_DOWN, meaning a finger has pressed down on the screen, and the computer will execute the following if statement
                        if ((x > demoView.getScreenWidth() * 0.65) && (y < demoView.getScreenHeight() * 0.75) && (y > demoView.getScreenHeight() * 0.25)) // if the right side of the screen is pressed
                        {
                            character.turnRight();  // turn clockwise 90 degrees
                            demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection()); // redraw the character in its new location
                        } 
                        else if ((x < demoView.getScreenWidth() * 0.35) && (y < demoView.getScreenHeight() * 0.75) && (y > demoView.getScreenHeight() * 0.25)) // if the left side of the screen is pressed
                        {
                            character.turnLeft(); // turn counter-clockwise 90 degrees
                            demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                        } 
                        else if (y < demoView.getScreenHeight() * 0.45) // if the top of the screen is pressed NOTE: y is inverted on the programming side, so effectively this line is saying if the user presses the top .45 portion of the screen
                        {
                            if (character.canMoveForward()) { 
                                character.moveForward();
                                demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                            }
                            else if (character.getWin())
                            {
                                demoView.drawWin();
                            }
                            else {
                                resetScreen(v);
                            } // end of input description

                        } // end of the long 
                        /*else if (y > demoView.getScreenHeight() * 0.55)  // for the (DROPPED) backstep mechanic
                        {
                            if (character.canMoveBackward()) {
                                character.moveBackward();
                                demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                            }
                            else{
                                resetScreen(v);
                            }
                            demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                        } */
                        else // if a "dead zone" was pressed
                        {
                            /*demoView.action = false;
                            demoView.upCalled = false;
                            demoView.downCalled = false;
                            demoView.leftCalled = false;
                            demoView.rightCalled = false;*/ // this was a different form of engine we were originally using to design the program
                        }
                        break;
            /*case MotionEvent.ACTION_MOVE:
            {
                /*demoView.action = false;
                demoView.upCalled = false;
                demoView.downCalled = false;
                demoView.leftCalled = false;
                demoView.rightCalled = false;
                break;
                character.moveForward();
                demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
            }*/
                    case MotionEvent.ACTION_UP:                  //When the user lifts their finger it sets all movement options to false
                    {
                        /*demoView.action = false;
                        demoView.upCalled = false;
                        demoView.downCalled = false;
                        demoView.leftCalled = false;
                        demoView.rightCalled = false;
                        break;*/ // again, unused form of engine
                    }
                }

                return true;
            }
        });

        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection()); // redraw character for safe measure


    }

    /*@Override
    public void onStart() {
        demoView.run();
    }*/

    public void resetScreen(View v)         // resets the screen and the character along with all of the character states
    {
        demoView.resetScreen();
        character.resetCharacter();
        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        demoView.drawMaze();

    }

   /* public void drawMaze(View v) 
   {
        Button button = findViewById(R.id.startButton);
        button.setVisibility(View.GONE);


        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        demoView.drawMaze();

        //demoView.run();
    }*/
}
