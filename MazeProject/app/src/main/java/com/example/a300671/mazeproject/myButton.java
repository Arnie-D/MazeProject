package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.widget.Button;

public class Controls
{
    static myButton moveButton;
    static myButton rightButton;
    static myButton leftButton;

    public Controls()
    {
        moveButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.45), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.1));
        rightButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.55), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.7));
        leftButton = new myButton((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.15), (int) (Resources.getSystem().getDisplayMetrics().heightPixels * 0.7));
    }

    public static myButton[] getButtons()
    {
        myButton[] set = {moveButton, rightButton, leftButton};
        return set;
    }

    public void pressButton(int num)
    {
        myButton[] set = {moveButton, rightButton, leftButton};
        set[num].press();
    }



}
