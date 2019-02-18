package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.widget.Button;

public class Controls
{
    static Character character;

    static myButton moveButton;
    static myButton rightButton;
    static myButton leftButton;

    static myButton[] buttonSet = {moveButton, rightButton, leftButton};

    public Controls()
    {
        moveButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.leftButtonProportionX), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.highAxis));
        rightButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.rightButtonProportionX), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis));
        leftButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.moveButtonProportionX), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis));
    }

    public static myButton[] getButtons()
    {
        return buttonSet;
    }

    public static void press(int x, int y)
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

    public static Character getCharacter()
    {
        return character;
    }



}
