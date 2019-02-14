package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.widget.Button;

public class Controls
{
    static Character character;

    static myButton moveButton;
    static myButton rightButton;
    static myButton leftButton;

    public Controls()
    {
        moveButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.leftButtonProportionX), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.highAxis));
        rightButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.rightButtonProportionX), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis));
        leftButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.moveButtonProportionX), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis));
    }

    public static myButton[] getButtons()
    {
        myButton[] set = {moveButton, rightButton, leftButton};
        return set;
    }

    /*public void pressButton(int num)
    {
        myButton[] set = {moveButton, rightButton, leftButton};
        set[num].press();
    }*/

    public static void win()
    {

    }

    public static Character getCharacter()
    {
        return character;
    }



}
