package com.example.chessapp;

import java.util.ArrayList;

public class Bishop extends Space {

    private Board board;

    public Bishop(GameView gameView,Board board,String color, String name,int x, int y){
        super(gameView,board,color,name,x,y);
        this.board = board;
    }

    @Override
    public ArrayList<Space> getMoves(){
        ArrayList<Space> Moves = new ArrayList<Space>();
        int i = super.getX() +1, j = super.getY()+1;
        while(i < 8 && j < 8){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
                break;
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
                i++;
                j++;
            }
            else{
                Moves.add(s);
                break;
            }
        }
        i = super.getX()+1; j = super.getY()-1;
        while(i < 8 && j > -1){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
                break;
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
                i++;
                j--;
            }
            else{
                Moves.add(s);
                break;
            }
        }
        i = super.getX()-1; j = super.getY()-1;
        while(i > -1 && j > -1){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
                break;
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
                i--;
                j--;
            }
            else{
                Moves.add(s);
                break;
            }
        }
        i = super.getX()-1; j = super.getY()+1;
        while(i > -1 && j < 8){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
                break;
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
                i--;
                j++;
            }
            else{
                Moves.add(s);
                break;
            }
        }
        if(this.board.getPlayerTurn().equals(super.getColor())) {
            for (i = 0; i < Moves.size(); i++) {
                Board BoardCopy = this.board.copy();
                Space src = new Space(super.gameView, BoardCopy, "EMPTY", "EMPTY", super.getX(), super.getY());
                Space dst = new Bishop(super.gameView, BoardCopy, super.getColor(), "BISHOP", Moves.get(i).getX(), Moves.get(i).getY());
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
