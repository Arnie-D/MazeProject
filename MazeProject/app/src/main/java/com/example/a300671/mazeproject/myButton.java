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

    Character character;






    public myButton()
    {
        width = 300;
        height = 200;

        buttonId = buttonId;
        buttonId += 1;

        character = Controls.getCharacter();
    }

    public myButton(int tempX, int tempY)
    {
        width = 300;
        height = 200;

        x = tempX;
        y = tempY;

        buttonId = buttonId;
        buttonId += 1;

        character = Controls.getCharacter();
    }

    /*public void press()
    {
        if(buttonId == 0)          // move
        {
            if (character.canMoveForward()) {
                character.moveForward();
            }
            else if (character.getWin())
            {
                Controls.win();
            }
            else
            {
                resetScreen(v);
            }
        }
        else if(buttonId == 1)     // right
        {

        }
        else if(buttonId == 2)     // left
        {

        }
    }*/

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
