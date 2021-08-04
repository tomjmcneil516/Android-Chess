package com.example.calculator;

import android.graphics.Canvas;

public class GameThread extends Thread {

    private GameView view;
    private boolean isRunning = false;

    public GameThread( GameView view){
        this.view = view;

    }

    public void setRunning(boolean isRunning){

        this.isRunning = isRunning;
    }


    @Override
    public void run() {
        while (isRunning) {
            Canvas canvas = null;
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

