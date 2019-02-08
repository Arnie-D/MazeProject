package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class myButton
{
    // a button here simply is a rectangle
    //


    int x;            // x of top left corner of box
    int y;            // y of top left corner of box

    int width;
    int height;

    int buttonId;






    public myButton()
    {
        width = 400;
        height = 200;
    }

    public myButton(int tempX, int tempY)
    {
        width = 400;
        height = 200;

        x = tempX;
        y = tempY;
    }

    public void press()
    {
        Controls.pressButton(buttonId);
    }

    public void draw()
    {

    }
}
