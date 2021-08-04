package com.example.chessapp;

import java.util.ArrayList;

public class Pawn extends Space {

    private Board board;


    public Pawn(GameView gameView,Board board,String color, String name,int x, int y){
        super(gameView,board,color,name,x,y);
        this.board = board;
    }

    @Override
    public ArrayList<Space> getMoves(){

        ArrayList<Space> Moves = new ArrayList<Space>();
        int i = super.getX(), j = super.getY();

        Space s = this.board.getSpace(i+1,j);
        if(super.getColor().equals("BLACK")&&s.getName().equals("EMPTY")) {
            Moves.add(s);
            if(!super.hasMoved()) {
                s = this.board.getSpace(i + 2, j);
                if (s.getName().equals("EMPTY")){
                Moves.add(s);
                }
            }
        }

        s = this.board.getSpace(i-1,j);
        if(super.getColor().equals("WHITE")&&s.getName().equals("EMPTY")){
            Moves.add(s);
            if(!super.hasMoved()) {
                s = this.board.getSpace(i - 2, j);
                if (s.getName().equals("EMPTY")) {
                    Moves.add(s);
                }
            }
        }

        if(j+1<8){
            s = this.board.getSpace(i+1,j+1);
            if(super.getColor().equals("BLACK")&&s.getColor().equals("WHITE")) {
                Moves.add(s);
            }
            s = this.board.getSpace(i-1,j+1);
            if(super.getColor().equals("WHITE")&&s.getColor().equals("BLACK")){
                Moves.add(s);
            }
        }

        if(j-1>-1){
            s = this.board.getSpace(i+1,j-1);
            if(super.getColor().equals("BLACK")&&s.getColor().equals("WHITE")) {
                Moves.add(s);
            }
            s = this.board.getSpace(i-1,j-1);
            if(super.getColor().equals("WHITE")&&s.getColor().equals("BLACK")){
                Moves.add(s);
            }
        }

        if(this.board.getPlayerTurn().equals(super.getColor())) {
            for (i = 0; i < Moves.size(); i++) {
                Board BoardCopy = this.board.copy();
                Space src = new Space(super.gameView, BoardCopy, "EMPTY", "EMPTY", super.getX(), super.getY());
                Space dst = new Pawn(super.gameView, BoardCopy, super.getColor(), "PAWN", Moves.get(i).getX(), Moves.get(i).getY());
                BoardCopy.setSpace(src);
                BoardCopy.setSpace(dst);
                if (BoardCopy.isInCheck()) {
                    Moves.remove(i);
                    i--;
                }
            }
        }

        return Moves;

    }
}
