package com.example.a300671.mazeproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

public class Maze
{
    public static int numWalls = 36;                // number of "walls" in the whole maze
    public static int[] winCoords = {180,380};
    public static int[][][] walls = {                                          // constructed array, clone of desired walls[][][]
    {{0, 0}, {400, 0}},             // W1                    //
    {{400, 0}, {400, 400}},         // W2                    // overall capsule {} is the array of walls, each wall is its own capsule, containing two point coordinates as arrays
    {{400,400}, {0, 400}},          // W3                    // {wall 1, wall 2...}  ->  {  {W1 point A , W1 point B}, {W2 Point A , W2 Point B} ...}  ->  {   { {Xval A, Yval A} , {Xval B, Yval B} }   }
    {{0,400}, {0,0}},               // W4
    {{40,0}, {40,160}},                       // W5
    {{40,160}, {80,160}},                       // W6
    {{80,160}, {80,40}},                       // W7
    {{80,40}, {120,40}},                       // W8
    {{120,40}, {120,160}},                       // W9
    {{160,160}, {160,0}},                       // W10
    {{200,200}, {200,40}},                       // W11
    {{200,40}, {280,40}},                       // W12
    {{240,80}, {240,200}},                       // W13
    {{280,40}, {280,160}},                       // W14
    {{320,160}, {320,40}},                       // W15
    {{320,40}, {400,40}},                       // W16
    {{360,80}, {360,200}},                       // W17
    {{360,200}, {360,360}},                       // W18
    {{360,360}, {240,360}},                       // W19
    {{240,360}, {240,320}},                       // W20
    {{240,320}, {320,320}},                       // W21
    {{320,320}, {320,240}},                       // W22
    {{360,200}, {240,200}},                       // W23
    {{280,240}, {280,280}},                       // W24
    {{280,280}, {240,280}},                       // W25
    {{240,280}, {240,200}},                       // W26
    {{240,200}, {200,200}},                       // W27
    {{200,200}, {0,200}},                       // W28
    {{200,240}, {40,240}},                       // W29
    {{40,240}, {40,360}},                       // W30
    {{40,360}, {120,360}},                       // W31
    {{120,360}, {120,320}},                       // W32
    {{80,320}, {80,280}},                       // W33
    {{80,280}, {160,280}},                       // W34
    {{160,280}, {160,400}},                       // W35
    {{200,400}, {200,240}}                       // W36
    };             // array of walls[][], wall is an array of points[], a point is an array with [xVal, yVal]

    public static int[][] oddWallPoints = { // in essence, because of the way the maze is designed, all points that have x values that are multiples of 40 and y values that are multiples of 40 are always wall point, or wall spaces
            {20,200},                       // similarly, all points that have x values that are multiples of 20 and y values that are multiples of 20 are always open to the player
            {40,20},                        // points that are 20-40 mixes (odd points), [x is multiple of 40 and y is only a multiple of 20] or [y is a multiple of 40 and x is only a multiple of 20], have the potential to be either player accessible or a wall
            {40,60},                        // these 20-40 mix tiles that are designed to wall the player are recorded here as "odd wall points"
            {40,100},                       // the 220 column's odd wall points are not recorded, so that the 220 column can be a secret shortcut to the end (mostly for bug-testing purposes but left in for fun)
            {40,140},
            {40,260},
            {40,200},
            {40,300},
            {40,340},
            {60,160},
            {60,240},
            {60,360},
            {80,60},
            {80,100},
            {80,140},
            {80,300},
            {100,40},
            {100,200},
            {100,240},
            {100,280},
            {100,360},
            {120,60},
            {120,100},
            {120,140},
            {120,340},
            {140, 200},
            {140, 240},
            {140,280},
            {160,20},
            {160,60},
            {160,100},
            {160,140},
            {160,300},
            {160,340},
            {160,380},
            {200,60},
            {200,100},
            {200,140},
            {200,180},
            {200,260},
            {200,300},
            {200,340},
            {200,380},
            {220,40},
            {220,200},
            {240,100},
            {240,140},
            {240,180},
            {240,220},
            {240,260},
            {240,340},
            {260,40},
            {260,200},
            {260,280},
            {260,320},
            {260,360},
            {280,60},
            {280,100},
            {280,140},
            {280,260},
            {300,200},
            {300,320},
            {300,360},
            {320,60},
            {320,100},
            {320,140},
            {320,260},
            {320,300},
            {340,40},
            {340,200},
            {340,360},
            {360,100},
            {360,140},
            {360,180},
            {360,220},
            {360,260},
            {360,300},
            {360,340},
            {380,40}
    };



    public static int mazeWidth = 400;
    public static int mazeHeight = 400;

    public int[][] getWall(int wallIndex)                   // returns array of points given the index of the wall
    {
        return walls[wallIndex];
    }

    public int[] getWallPoint(int wallIndex, int pointIndex)     // returns point given index of wall and index of point (0 is point A,  is point B)
    {
        return walls[wallIndex][pointIndex];
    }

    public int[] getWallPointA(int wallIndex)     // returns point A of a given wall (wall index)
    {
        return walls[wallIndex][0];
    }

    public int[] getWallPointB(int wallIndex)     // returns point B of a given wall (wall index)
    {
        return walls[wallIndex][1];
    }

    public static int[][][] getAllWalls()
    {
        return walls;
    }

    public static int getMazeWidth()
    {
        return mazeWidth;
    }

    public static int getMazeHeight()
    {
        return mazeHeight;
    }

    public static int[] getWinCoords()
    {
        return winCoords;
    }

    public static int getNumWalls() {
        return numWalls;
    }
    
    /*public static int[][] getAllWallX(){
        for(int[][] wall : walls)
            return wall;
    }*/

    public static boolean checkCollision(int x, int y) { //returns true if character is colliding w/ maze
        boolean collision = false;                       // "bruteForceMatch" would work fine for the purpose of this method, but that is slower, so this method speeds up the process to see first if the given point is a 40-40 point or a 20-20 point
        boolean xMatch = false;
        boolean yMatch = false;

        if((x > 0) && (x < 400) && (y > 0) && (y < 400)){ // sees if the given point is within the boundaries of the maze itself
            if((x % 40 == 0) && (y % 40 == 0)) // sees if the values of the point are multiples of 40 -- 40-40 spaces are always wall spaces
                collision = true;
            else{
                if(((x % 40 == 20) && (y % 40 == 20))) { // sees if the given point has values that are multiples of 20 -- 20-20 spaces are always player spaces
                    collision = false;
                }
                else{
                    if(((x % 40 == 20) && (y % 40 == 0)) || ((x % 40 == 0) &&(y % 40 == 20))){ // checks that the given point is a 20-40 or 40-20 "mixed" space

                        if(bruteForceMatch(x, y) == true){ // dedicates the rest of the calculations to bruteForceMatch method
                            collision = true;
                        }
                        else{
                            collision = false;
                        }
                    }
                }
            }
        }
        else{
            collision = true;
        }

        /*for(int wall = 0; wall < numWalls; wall++){
            if(getWallPointA(wall)[0] == getWallPointB(wall)[0]){
                int wallX = getWallPointA(wall)[0];

            }
        }*/


        return collision;
    }

    public static boolean bruteForceMatch(int x, int y){ // cross references the given point's values with the values of all the "odd" wall spaces and returns if the point is on a wall space
        int[] point = {x, y};
        for(int[] vals : oddWallPoints){
            if((vals[0] == point[0]) && (vals[1] == point[1])) { // if both and x and y are equivalent to an x and y of an odd wall point, then the given point is on a wall space
                return true;
            }
        }
        return false;
    }

    public static boolean checkWin(int x, int y) { // checks to see if the player character is in the win space
        boolean win = false;

        if ((x > 160 && x < 200) && (y > 360 && y < 400)) //Basically it only wins if it is in one position so we only need one if statement to check
        {
            win = true;
        }


        return win;

    }

}
