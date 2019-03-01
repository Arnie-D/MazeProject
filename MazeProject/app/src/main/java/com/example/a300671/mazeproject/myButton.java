package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class myButton
{
    // a button here simply is a rectangle that does something when the controller tells it to


    int x;            // x of top left corner of box
    int y;            // y of top left corner of box

    int width;
    int height;

    int scaledWidth;
    int scaledHeight;

    int myID;
    static int buttonId = 0;

    Character character;
    Controls controller;




    public myButton(Controls aController)
    {
        width = MainActivity.buttonWidth;
        height = MainActivity.buttonHeight;

        scaledWidth = width * 150;
        scaledHeight = height * 150;

        myID = buttonId;
        buttonId += 1;

        controller = aController;

        character = controller.getCharacter();
    }

    public myButton(Controls aController, int tempX, int tempY)
    {
        width = MainActivity.buttonWidth;
        height = MainActivity.buttonHeight;

        x = tempX;
        y = tempY;

        myID = buttonId;
        buttonId += 1;
    }

    public void press()
    {
        System.out.println("myID is equal to: " + myID);

        if(myID == 0)          // move
        {
            if (character.canMoveForward())
            {
                character.moveForward();
            }
            else
            {
                character.resetCharacter();
            }
        }
        else if(myID == 1)     // right
        {
            character.turnRight();
        }
        else if(myID == 2)     // left
        {
            character.turnLeft();
        }
    }

    public void printMyStuff()
    {
        System.out.println(" > My ID is: " + myID);
        System.out.println(" > My X is:  " + x);
        System.out.println(" > My Width is: " + width);
        System.out.println(" > My Y is: " + y);
        System.out.println(" > My Height is: " + height);

    }

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

    public int getScaledWidth()
    {
        return scaledWidth;
    }

    public int getHeight()
    {
        return height;
    }

    public int getScaledHeight()
    {
        return scaledHeight;
    }

    public int getID()
    {
        return myID;
    }

    public void setCharacter(Character aCharacter)
    {
        character = aCharacter;
    }

    public static void resetID()
    {
        buttonId = 0;
    }
}
