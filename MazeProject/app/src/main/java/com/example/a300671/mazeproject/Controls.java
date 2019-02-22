package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.widget.Button;

public class Controls
{
    Character character;

    static myButton moveButton;
    static myButton rightButton;
    static myButton leftButton;

    static myButton[] buttonSet;

    public Controls()
    {
        buttonSet = new myButton[3];

        moveButton = new myButton(this,
                (int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.moveButtonProportionX),
                (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.highAxis));

        rightButton = new myButton(this,
                (int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.rightButtonProportionX),
                (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis));

        leftButton = new myButton(this,
                (int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.leftButtonProportionX),
                (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis));

        setButtonSet(new myButton[] {moveButton, rightButton, leftButton});
    }

    public myButton[] getButtons()
    {
        return buttonSet;
    }

    public void press(int x, int y)
    {
        for(myButton button: buttonSet)
        {
            if(x > button.getX() && x < button.getX() + button.getWidth())
            {
                if(y > button.getY() && y < button.getY() + button.getHeight())
                {
                    button.press();
                }
            }
        }
    }

    public void setButtonSet(myButton[] aButtonSet)
    {

        for(int i = 0; i < 3; i++)
        {
            buttonSet[i] = aButtonSet[i];
        }
    }

    public void setCharacter(Character aCharacter)
    {
        character = aCharacter;

        for(int i = 0; i < 3; i++)
        {
            buttonSet[i].setCharacter(character);
        }
    }

    public Character getCharacter()
    {
        return character;
    }



}
