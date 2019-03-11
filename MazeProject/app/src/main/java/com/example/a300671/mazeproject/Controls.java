package com.example.a300671.mazeproject;

import android.content.res.Resources;
import android.widget.Button;
import android.widget.Toast;

/*
/ The controls class is the designed set of buttons for the program and the logic associated with them
/ In the program's current state, the Controls class is effectively a static class (though not designed to be static)
/
 */
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

        // calls class constructor of myButton, (Controls aController, int tempX, int tempY)
        moveButton = new myButton(this,
                (int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.moveButtonProportionX),       // sets the x of the button to a set proportion mark of the screen width (screen's width * proportion)
                (int) ((Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.highAxis)) - MainActivity.headerOffSet);                  // sets the y of the button to a set proportion mark of the screen height

        rightButton = new myButton(this,
                (int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.rightButtonProportionX),
                (int) ((Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis)) - MainActivity.headerOffSet);

        leftButton = new myButton(this,
                (int) (Resources.getSystem().getDisplayMetrics().widthPixels * MainActivity.leftButtonProportionX),
                (int) ((Resources.getSystem().getDisplayMetrics().heightPixels * MainActivity.lowAxis)) - MainActivity.headerOffSet);

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

            int leftX = button.getX() - (button.getWidth() / 2);                // the x value of a button is the x value of the center of the button
            int rightX = button.getX() + (button.getWidth() / 2);
            int topY = (button.getY() - (button.getHeight() / 2));
            int bottomY = button.getY() + (button.getHeight() / 2);

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
