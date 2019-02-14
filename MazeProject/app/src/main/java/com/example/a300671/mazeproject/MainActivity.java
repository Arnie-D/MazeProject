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
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    private DemoView demoView;
    
    Character character;

    public static final double leftButtonProportionX = 0.15;       // proportion of the screen width where the button starts
    public static final double moveButtonProportionX = 0.35;
    public static final double rightButtonProportionX = 0.55;

    public static final double highAxis = 0.1;                     // button axis, high is move, low is left and right
    public static final double lowAxis = 0.7;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                // not using the default activity_main XML resource
        demoView = findViewById(R.id.signature_canvas);
        character = new Character();
        demoView.setCharacter(character);
        demoView.drawController();




        final Toast toast = Toast.makeText(demoView.context, "This Happened", Toast.LENGTH_LONG);

        //Listens for when the user touches the screen then executes code
        demoView.setOnTouchListener(new View.OnTouchListener() // this onTouchListener is the basis of the entire program: it is the dedicator of all time and space- every layer of reality, in fact- in the program
        {                                                      // how it works is that, here an "onTouch-Lister" is being constructed with the below characteristics, overridden where we want it to do certain things. This constructed onTouchListener is then assigned to DemoView 
            @Override
            public boolean onTouch(View v, MotionEvent event)  // this override is saying that we want onTouch to respond in a certain way, and the body below is how we want it to respond
            {                                                  // the passed view will be DemoView because we assigned the onTouchListener to DemoView, and the MotionEvent is an event (such as touching down on the screen, mnoving your finger, and lifting up your finger) that is passed to the view by the device itself

                int x = (int) event.getRawX();                 // captures and stores the lateral value of the screen-press
                int y = (int) event.getRawY();                 // captures and stores the vertical value of the screen-press

                switch (event.getAction())                     // sets the "switch" expression's target variable to the action-key of the event see next comment
                {                                              // switch case expressions are basically if-then-else statements that make more sense to a programmer looking at the code - the target variable is set in switch(variable), and what follows is a set of cases, saying that in the case of the variable being this, do this
                    case (MotionEvent.ACTION_DOWN):            // in this case the action-key is ACTION_DOWN, meaning a finger has pressed down on the screen, and the computer will execute the following if statement
                        Controls.press(x, y);
                        
                        if ((x > demoView.getScreenWidth() * 0.55 && x < demoView.getScreenWidth() * 0.8) && (y > demoView.getScreenHeight() * 0.7 && y < demoView.getScreenHeight() * 0.8)) // if the right side of the screen is pressed
                        {
                            character.turnRight();  // turn clockwise 90 degrees
                            toast.show();
                        }
                        else if ((x > demoView.getScreenWidth() * 0.2 && x < demoView.getScreenWidth() * 0.45) && (y > demoView.getScreenHeight() * 0.7 && y < demoView.getScreenHeight() * 0.95)) // if the left side of the screen is pressed
                        {
                            character.turnLeft();               // turn counter-clockwise 90 degrees
                        } 
                        else if (y < demoView.getScreenHeight() * 0.45) // if the top of the screen is pressed NOTE: y is inverted on the programming side, so effectively this line is saying if the user presses the top .45 portion of the screen
                        {
                            if (character.canMoveForward()) { 
                                character.moveForward();
                            }
                            else if (character.getWin())
                            {
                                demoView.drawWin();
                            }
                            else {
                                resetScreen(v);
                            } // end of input description

                        } // end of the forward step

                        else // if a "dead zone" was pressed
                        {

                        }
                        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                        demoView.drawController();
                        break;
                    case MotionEvent.ACTION_MOVE:
                    {

                    }
                    case MotionEvent.ACTION_UP:
                    {

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
        demoView.drawController();

    }

   /* public void drawMaze(View v) 
   {
        Button button = findViewById(R.id.startButton);
        button.setVisibility(View.GONE);


        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        demoView.drawMaze();

        //demoView.run();
    }*/

   public void win()
   {
       demoView.drawWin();
   }
}
