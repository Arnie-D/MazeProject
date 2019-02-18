package com.example.a300671.mazeproject;

public class Character
{

    float x;  // lateral location of the player character, not scaled to fit the screen
    float y;  // vertical location of the characer, not scaled to fit the screen
    float oldx; // these are for covering up the player's last position
    float oldy; // ^
    int direction;  //0 (right), 1 (up), 2 (left), 3 (down)

    boolean win; // used to judge whether or not the character is in win-state

    public Character() {
        x = 20f; //These x and y values work on any sized screen b/c when we draw the character they are multiplied by DemoView.stretchValue
        y = 20f;
        oldx = 20f;
        oldy = 20f;
        direction = 3; // initialized to be in the top left, facing down
        win = false;
    }

    public void resetCharacter() {  // resets the character to the top left of screen
        x = 20f;
        y = 20f;
        oldx = 20f;
        oldy = 20f;
        direction = 3;
    }

    public boolean canMoveForward()  // a step in the process of collision detection - checks to see if, based on where the player is and where the player is facing (fields of class, not parameters of method), the player can or cannot move forward
    {
        int tempX = (int) x;
        int tempY = (int) y;
        
        if (direction == 0) { // if the player is facing right
            if(com.example.a205037.maze.Maze.checkCollision((tempX + 20),tempY)) //checks to see if there is wall-collision in the space in front of the character
            {                                                                    // 20 is the move rate, the program is hard-wired to work with 20 - think of the maze as a board of spaces the character can be at, each space is 20 units away from the adjacent space
                return false; // if the collision in the next space is true, then it is false that the player can move forward
            }
            else
            {
                return true; // if the collision in the next space is false, then it is true that the player can move forward
            }
        }
        else if (direction == 1) { // up
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY - 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 2) {  // left
            if(com.example.a205037.maze.Maze.checkCollision((tempX - 20), tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 3) {  // down
            if (com.example.a205037.maze.Maze.checkWin(tempX, (tempY + 20)))
            {
                win = true;
                return false;
            }
            else if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY + 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            System.out.println("Logical infeasibility in canMoveForward"); //Derek added these, idk why
            return false;                                                  // Derek added these because it is good style
        }
    }  // end of canMoveForward
    
    public boolean canMoveBackward() // Dropped player movement mechanic, it was working to move the character back but wasn't working with collision detection
    {
        int tempX = (int) x;
        int tempY = (int) y;
        
        if (direction == 0) {
            if(com.example.a205037.maze.Maze.checkCollision((tempX + 20),tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 1) {
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY - 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 2) {
            if(com.example.a205037.maze.Maze.checkCollision((tempX - 20), tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 3) {
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY + 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            System.out.println("Logical infeasibility in canMoveBackward");
            return false;
        }
    }
    
    public void moveForward() {

        if (direction == 0) {
            oldx = x;
            x += 20;                   // make a "speed" variable to replace magic "20" ?  -- EDIT: the program is designed as a board, with spaces' center points being 20 units apart
        } else if (direction == 1) {
            oldy = y;
            y -= 20;
        } else if (direction == 2) {
            oldx = x;
            x -= 20;
        } else if (direction == 3) {
            oldy = y;
            y += 20;
        }

        //oldx = 0;
        //oldy = 400;
    }
    
    public void moveBackward() { // abandoned mechanic, see "canMoveForward()" method


        if (direction == 0) {
            oldx = x;
            x -= 20;
        } else if (direction == 1) {
            oldy = y;
            y += 20;
        } else if (direction == 2) {
            oldx = x;
            x += 20;
        } else if (direction == 3) {
            oldy = y;
            y -= 20;
        }
    }


    public void turnLeft() { // turns the character counter-clockwise
        oldx = x;
        oldy = y;
        direction = (direction + 1) % 4;
    }
    
    public void turnRight() { // turns the character clockwise
        oldx = x;
        oldy = y;

        if(direction >= 0)
            direction -= 1;
        else
            direction = 3;
    }

    public float getX() // accessor method to get character's x, though in this version of the program the variable is not private
    {
        return x;
    }

    public float getY() // accessor
    {
        return y;
    }

    public float getOldx() // accessor
    {
        return oldx;
    }

    public float getOldy() // accessor
    {
        return oldy;
    }

    public int getDirection() // accessor
    {
        return direction;
    }

    public boolean getWin() { // accessor
        return win;
    }
}
