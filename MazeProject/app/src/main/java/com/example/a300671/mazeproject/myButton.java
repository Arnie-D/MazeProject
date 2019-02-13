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

    static int buttonId = 0;






    public myButton()
    {
        width = 400;
        height = 200;

        buttonId = buttonId;
        buttonId += 1;
    }

    public myButton(int tempX, int tempY)
    {
        width = 300;
        height = 200;

        x = tempX;
        y = tempY;

        buttonId = buttonId;
        buttonId += 1;
    }


    public void draw(DemoView aView)
    {

    }

    public void press()
    {
        if (this.buttonId == 0)         // move
        {

        }
        else if (this.buttonId == 1)    // left
        {

        }
        else if (this.buttonId == 2)    // right
        {

        }
    }
}
