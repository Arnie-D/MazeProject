package com.example.a300671.mazeproject;

import android.annotation.SuppressLint;
import android.content.res.Resources;
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

    public static final float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    Character character;
    Controls controller;

    public static final int headerOffSet = 250;

    public static final double leftButtonProportionX = 0.3;       // proportion of the screen width where the button is centered
    public static final double moveButtonProportionX = 0.5;       //
    public static final double rightButtonProportionX = 0.8;

    public static final double highAxis = 0.6;                     // button axis, high is move, low is left and right
    public static final double lowAxis = 0.7;

    public static final int buttonWidth = 2;
    public static final int buttonHeight = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                // not using the default activity_main XML resource
        demoView = findViewById(R.id.signature_canvas);

        character = new Character();
        demoView.setCharacter(character);

        myButton.resetID();
        controller = new Controls();
        controller.setCharacter(character);
        demoView.setController(controller);

        demoView.drawCharacter();
        demoView.drawController();

        //Listens for when the user touches the screen then executes code
        demoView.setOnTouchListener(new View.OnTouchListener() // this onTouchListener is the basis of the entire program: it is the dedicator of all time and space- every layer of reality, in fact- in the program
        {                                                      // how it works is that, here an "onTouch-Lister" is being constructed with the below characteristics, overridden where we want it to do certain things. This constructed onTouchListener is then assigned to DemoView 
            @Override
            public boolean onTouch(View v, MotionEvent event)  // this override is saying that we want onTouch to respond in a certain way, and the body below is how we want it to respond
            {                                                  // the passed view will be DemoView because we assigned the onTouchListener to DemoView, and the MotionEvent is an event (such as touching down on the screen, mnoving your finger, and lifting up your finger) that is passed to the view by the device itself

                int x = (int) event.getRawX();                 // captures and stores the lateral value of the screen-press
                int y = (int) event.getRawY() - headerOffSet;                 // captures and stores the vertical value of the screen-press

                switch (event.getAction())                     // sets the "switch" expression's target variable to the action-key of the event see next comment
                {                                              // switch case expressions are basically if-then-else statements that make more sense to a programmer looking at the code - the target variable is set in switch(variable), and what follows is a set of cases, saying that in the case of the variable being this, do this
                    case (MotionEvent.ACTION_DOWN):            // in this case the action-key is ACTION_DOWN, meaning a finger has pressed down on the screen, and the computer will execute the following if statement
                    {
                        controller.press(x, y);
                        demoView.drawCharacter();
                        demoView.drawController();

                        if(character.getWin())
                        {
                            demoView.drawWin();
                        }
                    }
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

        demoView.drawCharacter(); // redraw character for safe measure
    }

    public void resetScreen(View v)         // resets the screen and the character along with all of the character states
    {
        demoView.resetScreen();
        character.resetCharacter();
        demoView.drawCharacter();
        demoView.drawMaze();
        demoView.drawController();

    }

    public void win()
    {
        demoView.drawWin();
    }
}
