package com.example.chessapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class Board {

    private Space[][] BoardPosition;
    private String playerTurn = "WHITE";
    private Space selectedSpace;
    private GameView gameView;


    public Board(GameView gameView) {
        this.gameView = gameView;
    }


    public void RegisterTouch(float x, float y) {
        int i = (int) ((y - 140) / 100);
        int j = (int) ((x - 140) / 100);

        if (i > 7 || i < 0 || j > 7 || j < 0) {
            return;
        }

        if (BoardPosition[i][j].getColor().equals(playerTurn)) {
            if(selectedSpace == null){
                selectedSpace = BoardPosition[i][j];
                selectedSpace.setTouched();
            }
            else if(selectedSpace.equals(BoardPosition[i][j])){
                selectedSpace.setTouched();
                selectedSpace = null;
            }
            else{
                selectedSpace.setTouched();
                selectedSpace = BoardPosition[i][j];
                selectedSpace.setTouched();
            }
            return;
        }

        if(selectedSpace == null){
            return;
        }

        for(int k = 0; k < selectedSpace.getMoves().size(); k++){
            if(selectedSpace.getMoves().get(k).getX() == i && selectedSpace.getMoves().get(k).getY() == j){
                Space temp = new Space(gameView,this,"EMPTY","EMPTY",selectedSpace.getX(),selectedSpace.getY());
                selectedSpace.setX(i);
                selectedSpace.setY(j);
                BoardPosition[temp.getX()][temp.getY()] = temp;
                BoardPosition[i][j] = selectedSpace;
                selectedSpace.setTouched();
                if(selectedSpace.getName().equals("PAWN")&&(i==7 || i==0)){
                    BoardPosition[i][j] = new Queen(gameView,this,playerTurn,"QUEEN",i,j);
                }

                if(selectedSpace.getName().equals("KING")){
                    if(selectedSpace.getY()-temp.getY()==2){
                        BoardPosition[selectedSpace.getX()][7].setY(5);
                        this.setSpace(BoardPosition[selectedSpace.getX()][7]);
                        temp = new Space(gameView,this,"EMPTY","EMPTY",selectedSpace.getX(),7);
                        this.setSpace(temp);
                    }
                    else if(selectedSpace.getY()-temp.getY()==-2){
                        BoardPosition[selectedSpace.getX()][0].setY(3);
                        this.setSpace(BoardPosition[selectedSpace.getX()][0]);
                        temp = new Space(gameView,this,"EMPTY","EMPTY",selectedSpace.getX(),0);
                        this.setSpace(temp);
                    }
                }

                selectedSpace = null;
                if(playerTurn.equals("WHITE")){
                    playerTurn = "BLACK";
                }
                else{
                    playerTurn = "WHITE";
                }

                if(this.isInCheck()){
                    if(getPlayerMoves(playerTurn).size()==0){
                        this.setBoard();
                        playerTurn = "WHITE";
                    }
                }

                return;
            }
        }



    }


    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1)
                    paint.setColor(Color.rgb(120, 70, 40));
                else {
                    paint.setColor(Color.rgb(200, 160, 130));
                }
                canvas.drawRect(140 + 100 * i, 140 + 100 * j, 240 + 100 * i, 240 + 100 * j, paint);
            }
        }


        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(BoardPosition[i][j].getColor().equals("BLACK") && BoardPosition[i][j].getName().equals("KING")){
                    BoardPosition[i][j].draw(canvas, -10, 0);
                }
                if(BoardPosition[i][j].getColor().equals("BLACK") && BoardPosition[i][j].getName().equals("QUEEN")){
                    BoardPosition[i][j].draw(canvas, 320, 0);
                }
                if(BoardPosition[i][j].getColor().equals("BLACK") && BoardPosition[i][j].getName().equals("ROOK")){
                    BoardPosition[i][j].draw(canvas, 655, 0);
                }
                if(BoardPosition[i][j].getColor().equals("BLACK") && BoardPosition[i][j].getName().equals("BISHOP")){
                    BoardPosition[i][j].draw(canvas, 985, 0);
                }
                if(BoardPosition[i][j].getColor().equals("BLACK") && BoardPosition[i][j].getName().equals("KNIGHT")){
                    BoardPosition[i][j].draw(canvas, 1320, 0);
                }
                if(BoardPosition[i][j].getColor().equals("BLACK") && BoardPosition[i][j].getName().equals("PAWN")){
                    BoardPosition[i][j].draw(canvas, 1650, 0);
                }
                if(BoardPosition[i][j].getColor().equals("WHITE") && BoardPosition[i][j].getName().equals("KING")){
                    BoardPosition[i][j].draw(canvas, -10, 280);
                }
                if(BoardPosition[i][j].getColor().equals("WHITE") && BoardPosition[i][j].getName().equals("QUEEN")){
                    BoardPosition[i][j].draw(canvas, 320, 280);
                }
                if(BoardPosition[i][j].getColor().equals("WHITE") && BoardPosition[i][j].getName().equals("ROOK")){
                    BoardPosition[i][j].draw(canvas, 655, 280);
                }
                if(BoardPosition[i][j].getColor().equals("WHITE") && BoardPosition[i][j].getName().equals("BISHOP")){
                    BoardPosition[i][j].draw(canvas, 985, 280);
                }
                if(BoardPosition[i][j].getColor().equals("WHITE") && BoardPosition[i][j].getName().equals("KNIGHT")){
                    BoardPosition[i][j].draw(canvas, 1320, 280);
                }
                if(BoardPosition[i][j].getColor().equals("WHITE") && BoardPosition[i][j].getName().equals("PAWN")){
                    BoardPosition[i][j].draw(canvas, 1650, 280);
                }
            }
        }

    }

    public void setBoard(){
        BoardPosition = new Space[8][8];
        for(int i = 0; i < 8; i++) {
            BoardPosition[1][i] = new Pawn(gameView,this,"BLACK", "PAWN",1,i);
            BoardPosition[6][i] = new Pawn(gameView,this,"WHITE", "PAWN",6,i);
        }

        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                BoardPosition[i][j] = new Space(gameView,this,"EMPTY", "EMPTY",i,j);
            }
        }
        BoardPosition[0][0] = new Rook(gameView,this,"BLACK", "ROOK",0,0);
        BoardPosition[0][7] = new Rook(gameView,this,"BLACK", "ROOK",0,7);
        BoardPosition[0][1] = new Knight(gameView,this,"BLACK", "KNIGHT",0,1);
        BoardPosition[0][6] = new Knight(gameView,this,"BLACK", "KNIGHT",0,6);
        BoardPosition[0][2] = new Bishop(gameView,this,"BLACK", "BISHOP",0,2);
        BoardPosition[0][5] = new Bishop(gameView,this,"BLACK", "BISHOP",0,5);
        BoardPosition[0][3] = new Queen(gameView,this,"BLACK", "QUEEN",0,3);
        BoardPosition[0][4] = new King(gameView,this,"BLACK", "KING",0,4);

        BoardPosition[7][0] = new Rook(gameView,this,"WHITE", "ROOK",7,0);
        BoardPosition[7][7] = new Rook(gameView,this,"WHITE", "ROOK",7,7);
        BoardPosition[7][1] = new Knight(gameView,this,"WHITE", "KNIGHT",7,1);
        BoardPosition[7][6] = new Knight(gameView,this,"WHITE", "KNIGHT",7,6);
        BoardPosition[7][2] = new Bishop(gameView,this,"WHITE", "BISHOP",7,2);
        BoardPosition[7][5] = new Bishop(gameView,this,"WHITE", "BISHOP",7,5);
        BoardPosition[7][3] = new Queen(gameView,this,"WHITE", "QUEEN",7,3);
        BoardPosition[7][4] = new King(gameView,this,"WHITE", "KING",7,4);

    }

    public boolean isInCheck() {
        String oppositeColor = "WHITE";
        if (playerTurn.equals("WHITE")) {
            oppositeColor = "BLACK";
        }
        ArrayList<Space> underAttack = getPlayerMoves(oppositeColor);
        for(int i = 0; i < underAttack.size(); i++){
            if(underAttack.get(i).getName().equals("KING")){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Space> getPlayerMoves(String color){
        ArrayList<Space> s = new ArrayList<Space>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(BoardPosition[i][j].getColor().equals(color)){
                    s.addAll(BoardPosition[i][j].getMoves());
                }
            }
        }
        return s;
    }


    public Space getSelectedSpace() {
        return selectedSpace;
    }

    public void clearSelectedSpace(){
        selectedSpace = null;
    }

    public void setPlayerTurn(String playerTurn){
        this.playerTurn = playerTurn;
    }

    public String getPlayerTurn() {
        return playerTurn;
    }

    public void setSelectedSpace(Space s){
        selectedSpace = s;
    }

    public Space getSpace(int x, int y){
        return BoardPosition[x][y];
    }

    public Space[][] getBoardPosition(){
        return BoardPosition;
    }

    public void setSpace(Space s){
        BoardPosition[s.getX()][s.getY()] = s;
    }

    public void setSpace(Space space, int x, int y){
        BoardPosition[x][y] = space;
    }

    public Board copy() {
        Board copy = new Board(this.gameView);
        Space[][] BoardCopy = new Space[8][8];
        copy.setPlayerTurn(this.playerTurn);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(BoardPosition[i][j].getName().equals("EMPTY")){
                    BoardCopy[i][j] = new Space(gameView, copy, "EMPTY","EMPTY", i, j);
                }
                else if(BoardPosition[i][j].getName().equals("ROOK")) {
                    BoardCopy[i][j] = new Rook(gameView, copy, BoardPosition[i][j].getColor(),"ROOK", i, j);
                }
                else if(BoardPosition[i][j].getName().equals("BISHOP")) {
                    BoardCopy[i][j] = new Bishop(gameView, copy, BoardPosition[i][j].getColor(),"BISHOP", i, j);
                }
                else if(BoardPosition[i][j].getName().equals("KNIGHT")) {
                    BoardCopy[i][j] = new Knight(gameView, copy, BoardPosition[i][j].getColor(),"KNIGHT", i, j);
                }
                else if(BoardPosition[i][j].getName().equals("QUEEN")) {
                    BoardCopy[i][j] = new Queen(gameView, copy, BoardPosition[i][j].getColor(),"QUEEN", i, j);
                }
                else if(BoardPosition[i][j].getName().equals("KING")) {
                    BoardCopy[i][j] = new King(gameView, copy, BoardPosition[i][j].getColor(),"KING", i, j);
                }
                else if(BoardPosition[i][j].getName().equals("PAWN")) {
                    BoardCopy[i][j] = new Pawn(gameView, copy, BoardPosition[i][j].getColor(),"PAWN", i, j);
                }
            }
        }
        copy.BoardPosition = BoardCopy;
        return copy;
    }

}
