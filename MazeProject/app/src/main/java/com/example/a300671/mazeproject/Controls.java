package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.widget.Button;
import android.widget.Toast;

public class Controls
{
    Character character;

    myButton moveButton;
    myButton rightButton;
    myButton leftButton;

    myButton[] buttonSet;

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
        System.out.println("screen press at- X: " + x + ", Y: " + y);

        for(myButton button: buttonSet)
        {

            int leftX = button.getX();
            int rightX = button.getX() + button.getWidth();
            int topY = button.getY();
            int bottomY = button.getY() + button.getHeight();

            if((x >= leftX && x <= rightX) || (x <= rightX && x >= leftX))
            {
                if((y >= topY && y <= bottomY) || (y <= bottomY && y >= topY))
                {
                    button.press();
                    System.out.println("button press at- X: " + x + ", Y: " + y);
                    System.out.println("in range: X - " + leftX + ", " + rightX + " | Y - " + topY + ", " + bottomY);
                    button.printMyStuff();
                    System.out.println(leftX);
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
