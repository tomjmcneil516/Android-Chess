package com.example.chessapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

    public Context context;
    private SurfaceHolder holder;
    private GameThread gameThread;
    private Board board;


    public GameView (Context context){
        super(context);
        this.context = context;
        gameThread = new GameThread(this);
        this.board = new Board(this);
        this.board.setBoard();
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = false;
                gameThread.setRunning(false);
                while(retry) {
                    try {
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        board.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (holder.getSurface().isValid()) {
                board.RegisterTouch(event.getX(),event.getY());
            }
        }
        return true;
    }
}
