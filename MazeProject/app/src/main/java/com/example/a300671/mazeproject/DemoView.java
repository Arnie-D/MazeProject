package com.example.a300671.mazeproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

class DemoView extends View
{

    private int width;             // width of screen
    private int height;            // height of screen
    private Bitmap bitmap;         // defines the canvas screen
    private Canvas canvas;         // the canvas used in this instance of DemoView

    private Path path;             // this "path" here is the lines which make up the body of the screen, both the maze and the player
    private Path controlsPath;       // controls path
    private Path playerPath;

    Context context;               //for context
    Controls controller;

    private Paint paintSand;       // colors
    private Paint paintBlack;
    private Paint paintRed;
    private Paint paintWhite;

    int offsetX;
    int offsetY;
                
    public boolean action;
    
    Character character;

    float stretchValue;
    float buttonStretch;
    
    

    public DemoView(Context c, AttributeSet attributeSet)
    {
        super(c, attributeSet);
        context = c;

        path = new Path();
        controlsPath = new Path();
        playerPath = new Path();

        stretchValue = (float)(MainActivity.stretchFactor); //this stretches the maze points to fit the lenovo tablets' screen

        paintSand = new Paint();
        paintSand.setColor(0xffffd417);
        paintSand.setStyle(Paint.Style.STROKE);  // creating a "pen" that does not fill in regions
        paintSand.setStrokeWidth(10);            // width of the pen's stroke
        paintSand.setAntiAlias(true);

        paintBlack = new Paint();                //we don't use this but it is here for customization
        paintBlack.setColor(Color.BLACK);
        paintBlack.setStyle(Paint.Style.STROKE);  // creating a "pen" that does not fill in regions
        paintBlack.setStrokeWidth(10);            // width of the pen's stroke
        paintBlack.setAntiAlias(true);

        paintRed = new Paint();
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(10);

        paintWhite = new Paint();                //we don't use this but it is here for customization
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.FILL);
        paintWhite.setStrokeWidth(10);

        offsetX = (int) ((MainActivity.screenWidth - Maze.getMazeWidth() * stretchValue) / 2);
        offsetY = (int) ((MainActivity.screenHeight - Maze.getMazeHeight() * stretchValue) / 2) - MainActivity.headerOffSet;
                
        action = false;

        
    }
    
    

    @Override protected
    void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }


    @Override protected
    void onDraw(Canvas canvas) // renders the screen
    {
        super.onDraw(canvas);
        canvas.drawPath(path, paintRed);
        canvas.drawPath(controlsPath, paintBlack);
        canvas.drawPath(playerPath, paintBlack);
        //resetScreen();

    }





    void resetScreen() { // clears the screen of what has been drawn by "path", so the walls of the maze and the player character
        path.reset();
        controlsPath.reset();
        playerPath.reset();
    }

    void drawCharacter() { // draws the body of the player character (NOTE: in the process of redrawing the character in this method, everything drawn by path is erased and character is redrawn)
        float x = character.getX();
        float y = character.getY();

        float refX = x;        // reference point of triangle
        float refY = y;        // ^
        int direction = character.getDirection();
        float xSlant;          //how much the base moves from left to right
        float ySlant;
        float topX;            //coordinates of the top of the triangle
        float topY;

        if (direction == 0) {  // the location of the reference point is dependent on the direction of the character
            refX = x - 10f;
            refY = y - 10f;

            xSlant = 0f;
            ySlant = 20f;
            topX = refX + 25f;
            topY = refY+ 10f;
        }
        else if (direction == 1) {
            refX = x - 10f;
            refY = y + 10f;

            xSlant = 20f;
            ySlant = 0f;
            topX = refX + 10f;
            topY = refY - 25f;
        }
        else if (direction == 2) {
            refX = x + 10f;
            refY = y - 10f;

            xSlant = 0f;
            ySlant = 20f;
            topX = refX - 25f;
            topY = refY + 10f;
        }
        else if (direction == 3) {
            refX = x - 10f;
            refY = y - 10f;

            xSlant = 20f;
            ySlant = 0f;
            topX = refX + 10f;
            topY = refY + 25f;
        }
        else {
            return;
        }

        path.reset();
        controlsPath.reset();
        playerPath.reset();

        drawMaze();   // redraws the maze walls
        drawController(); // redraws the controller buttons


        playerPath.moveTo(refX * stretchValue + offsetX, refY * stretchValue + offsetY);                       //draws base
        playerPath.lineTo((refX + xSlant) * stretchValue + offsetX, (refY + ySlant) * stretchValue + offsetY); //bottom right of triangle


        playerPath.moveTo(refX * stretchValue + offsetX, refY * stretchValue + offsetY);                       //draws line from left base to top
        playerPath.lineTo(topX * stretchValue + offsetX, topY * stretchValue + offsetY);

        playerPath.moveTo((refX + xSlant) * stretchValue + offsetX, (refY + ySlant) * stretchValue + offsetY); //draws line from right base to top
        playerPath.lineTo(topX * stretchValue + offsetX, topY * stretchValue + offsetY);


        invalidate();

    }

    void drawMaze() // tells the path to cover the walls of the maze
    {
        path.reset();
        int[][][] walls = Maze.getAllWalls();
        int numWalls = Maze.getNumWalls();

        int[] winCoords = Maze.getWinCoords();
        int winX = (int) ((winCoords[0] * stretchValue) + offsetX);
        int winY = (int) ((winCoords[1] * stretchValue) + offsetY);

        int[][] wall;
        int[] point1;
        int[] point2;



        for (int i = 0; i < numWalls; i += 1) { //just a simple loop that goes through each wall of the maze and draws it
            wall = walls[i];
            point1 = wall[0];
            point2 = wall[1];

            path.moveTo(point1[0] * stretchValue + offsetX, point1[1] * stretchValue + offsetY);
            path.lineTo(point2[0] * stretchValue + offsetX, point2[1] * stretchValue + offsetY);
        }

        path.moveTo(winX - 10 * stretchValue, winY - 10 * stretchValue);
        path.lineTo(winX + 10 * stretchValue, winY - 10 * stretchValue);
        path.moveTo(winX + 10 * stretchValue, winY - 10 * stretchValue);
        path.lineTo(winX + 10 * stretchValue, winY + 10 * stretchValue);
        path.moveTo(winX + 10 * stretchValue, winY + 10 * stretchValue);
        path.lineTo(winX - 10 * stretchValue, winY + 10 * stretchValue);
        path.moveTo(winX - 10 * stretchValue, winY + 10 * stretchValue);
        path.lineTo(winX - 10 * stretchValue, winY - 10 * stretchValue);

        invalidate(); //calls onDraw()
    }

    void drawController()
    {
        controlsPath.reset();

        myButton[] set = controller.getButtons();

        for(int i = 0; i < set.length; i++)
        {
            int tempX = set[i].getX() - (set[i].getWidth() / 2);  // since the x value is at the center of the button, and since we want the x value of the top right point of the button, subtract half the width from x
            int tempY = set[i].getY() - (set[i].getHeight() / 2);
            int tempWidth = (int) (set[i].getWidth());
            int tempHeight = (int) (set[i].getHeight());

            controlsPath.moveTo(tempX, tempY);
            controlsPath.lineTo((tempX + tempWidth), tempY);
            controlsPath.moveTo((tempX + tempWidth), tempY);
            controlsPath.lineTo((tempX + tempWidth), (tempY + tempHeight));
            controlsPath.moveTo((tempX + tempWidth), (tempY + tempHeight));
            controlsPath.lineTo(tempX, (tempY + tempHeight));
            controlsPath.moveTo(tempX, (tempY + tempHeight));
            controlsPath.lineTo(tempX, tempY);
            controlsPath.moveTo(tempX, tempY);

            controlsPath.moveTo(tempX + tempWidth, tempY);
            controlsPath.lineTo(tempX + tempWidth, tempY + tempHeight);
        }

    }

    void drawWin() { // win-state visuals -- underwhelming
        Toast toast = Toast.makeText(context, "YOU WIN!!!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void setControls(Controls controls)
    {
        controller = controls;
    }

    public int getScreenWidth(){ // accessor
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    
    public int getScreenHeight(){ // accessor
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    
    public void setCharacter(Character character)  // sets the view's character object to the passed character object
    {
        this.character = character;
    }

    public void setController(Controls aController)
    {
        controller = aController;
    }
     
   
}
