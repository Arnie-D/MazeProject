package com.example.a300671.mazeproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
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

    Context context;               //for context

    private Paint paintSand;       // colors
    private Paint paintBlack;
    private Paint paintWhite;
    
    
    public boolean rightCalled; // see initialization of DemoView
    public boolean leftCalled;
    public boolean upCalled;
    public boolean downCalled;

    int offsetX;
    int offsetY;
                
    public boolean action;
    
    Character character;

    private float stretchValue;
    
    

    public DemoView(Context c, AttributeSet attributeSet)
    {
        super(c, attributeSet);
        context = c;
        //canvas = new Canvas();

        path = new Path();
        controlsPath = new Path();

        stretchValue = (float)(Resources.getSystem().getDisplayMetrics().widthPixels / com.example.a205037.maze.Maze.getMazeWidth()); //this stretches the maze points to fit the lenovo tablets' screen

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

        paintWhite = new Paint();                //we don't use this but it is here for customization
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.FILL);
        paintWhite.setStrokeWidth(10);

        offsetX = getScreenWidth() / 8;
        offsetY = getScreenHeight() / 8;
        
        
        
        rightCalled = false;                     //these variables tell us if the character is moving forward/back or left/right - these aren't used in the current program but could be used in a future version of the prohram
        leftCalled = false;
        upCalled = false;
        downCalled = false;
                
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
        canvas.drawPath(path, paintSand);
        canvas.drawPath(controlsPath, paintBlack);
        //resetScreen();

    }





    void resetScreen() { // clears the screen of what has been drawn by "path", so the walls of the maze and the player character
        path.reset();
        controlsPath.reset();
        //invalidate();
    }

    void drawCharacter(float x, float y, float oldx, float oldy, int direction) { // draws the body of the player character (NOTE: in the process of redrawing the character in this method, everything drawn by path is erased and character is redrawn)


        float refX = x;        // reference point of triangle
        float refY = y;        // ^
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

        path.reset();  // clears all of the built path that has already been drawn, including the maze walls and character

        drawMaze();   // redraws the maze walls
        drawController(); // redraws the controller buttons


        path.moveTo(refX * stretchValue + offsetX, refY * stretchValue + offsetY);                       //draws base
        path.lineTo((refX + xSlant) * stretchValue + offsetX, (refY + ySlant) * stretchValue + offsetY); //bottom right of triangle


        path.moveTo(refX * stretchValue + offsetX, refY * stretchValue + offsetY);                       //draws line from left base to top
        path.lineTo(topX * stretchValue + offsetX, topY * stretchValue + offsetY);

        path.moveTo((refX + xSlant) * stretchValue + offsetX, (refY + ySlant) * stretchValue + offsetY); //draws line from right base to top
        path.lineTo(topX * stretchValue + offsetX, topY * stretchValue + offsetY);


        invalidate();

        /*CharSequence text = x + ", " + y + "";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        toast.show();*/

    }

    void drawMaze() { // tells the path to cover the walls of the maze
        int[][][] walls = com.example.a205037.maze.Maze.getAllWalls();
        int numWalls = com.example.a205037.maze.Maze.getNumWalls();

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


        invalidate(); //calls onDraw()
    }

    void drawController()
    {
        width = getScreenWidth();
        height = getScreenHeight();

        controlsPath.reset();

        controlsPath.moveTo(width * 0.8f, height * 0.8f);   // bottom right button
        controlsPath.lineTo(width * 0.55f, height * 0.8f);

        controlsPath.moveTo(width * 0.55f, height * 0.8f);
        controlsPath.lineTo(width * 0.55f, height * 0.7f);

        controlsPath.moveTo(width * 0.55f, height * 0.7f);
        controlsPath.lineTo(width * 0.8f, height * 0.7f);

        controlsPath.moveTo(width * 0.8f, height * 0.7f);
        controlsPath.lineTo(width * 0.8f, height * 0.8f);




        controlsPath.moveTo(width * 0.2f, height * 0.8f);   // bottom left button
        controlsPath.lineTo(width * 0.45f, height * 0.8f);

        controlsPath.moveTo(width * 0.45f, height * 0.8f);
        controlsPath.lineTo(width * 0.45f, height * 0.7f);

        controlsPath.moveTo(width * 0.45f, height * 0.7f);
        controlsPath.lineTo(width * 0.2f, height * 0.7f);

        controlsPath.moveTo(width * 0.2f, height * 0.7f);
        controlsPath.lineTo(width * 0.2f, height * 0.8f);

    }
    void drawWin() { // win-state visuals -- underwhelming
        path.reset();
        invalidate();
        Toast toast = Toast.makeText(context, "YOU WIN!!!", Toast.LENGTH_LONG);
        toast.show();
    }

    // the below method is a designed "game loop" for running the maze program
    void run() //we never got to use, it would make the game much better but ran out of time, call it from onStart() if you want to utilize it, not bug-tested yet
    {          // note from Derek: the method is based on logical boolean variables - input makes a variable true, and the program seeing that it is true reacts accordingly, then resets the respective variable to false and awaits next input
        boolean running = true;
        
        while(running)
        {

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                System.exit(0);
            }


            rightCalled = false;
            leftCalled = false;
            upCalled = false;
            downCalled = false;
            action = false;

            /*if(action == true)
            {
                if(upCalled)
                {
                    if(character.canMoveForward()) {
                        character.moveForward();
                    }
                    upCalled = false;
                }
                else if(downCalled){
                    if(character.canMoveBackward()) {
                        character.moveBackward();
                        downCalled = false;
                    }

                }
                else if(rightCalled){
                    character.turnRight();
                    rightCalled = false;
                }
                else if(leftCalled){
                    character.turnLeft();
                    leftCalled = false;
                }


            }
            else
            {
                // nothing happens
            }*/

            drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
            drawMaze();
            invalidate();
        }
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
     
   
}
