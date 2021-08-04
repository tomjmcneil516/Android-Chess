package com.example.calculator;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Space{

    private String name;
    private String color;
    private boolean Touched = false;
    private boolean Moved = false;
    private int x;
    private int y;
    public GameView gameView;
    private Board board;


    public Space(GameView gameView, Board board, String color, String name, int x, int y) {
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
        this.gameView = gameView;
        this.board = board;
    }


    public void draw(Canvas canvas, int left, int top) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bmp = BitmapFactory.decodeResource(gameView.context.getResources(),R.drawable.bitmapchess,options);
        Rect src = new Rect(left,top,left+180,top+180);   //+180 for king
        Rect dst = new Rect(145+y*100,145+x*100,235+y*100,235+x*100);  //+90 for king
        if(Touched == true){
            dst = new Rect(130+y*100,130+x*100,250+y*100,250+x*100);

            ArrayList<Space> a = this.getMoves();

            Paint paint = new Paint();
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.rgb(0, 170, 0));

            for(int k = 0; k < a.size(); k++){
                canvas.drawRect(150+a.get(k).getY()*100,150+a.get(k).getX()*100,230+a.get(k).getY()*100,230+a.get(k).getX()*100,paint);
            }

        }
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        Moved = true;
        this.x = x;
    }

    public boolean hasMoved(){
        return Moved;
    }

    public void setY(int y){
        this.y = y;
    }

    public boolean isTouched(){
        return Touched;
    }

    public void setTouched(){
        Touched = !Touched;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Space> getMoves() {
        ArrayList<Space> Empty = new ArrayList<Space>();

        int i = x+1, j = y-1;
        if(i < 8 && j > -1) {
            Space s = new Space(this.gameView, board,"", "", i, j);
            Empty.add(s);
        }
        i = x+1; j = y+1;
        if(i < 8 && j < 8) {
            Space s = new Space(this.gameView, board,"", "", i, j);
            Empty.add(s);
        }
        i = x-1; j = y+1;
        if(i > -1 && j < 8) {
            Space s = new Space(this.gameView, board,"", "", i, j);
            Empty.add(s);
        }
        i = x-1; j = y-1;
        if(i > -1 && j > -1) {
            Space s = new Space(this.gameView, board,"", "", i, j);
            Empty.add(s);
        }



        return Empty;
    }

}




