package com.example.calculator;

import java.util.ArrayList;

public class Knight extends Space {

    private Board board;


    public Knight(GameView gameView, Board board,String color, String name,int x, int y){
        super(gameView,board,color,name,x,y);
        this.board = board;
    }

    @Override
    public ArrayList<Space> getMoves(){

        ArrayList<Space> Moves = new ArrayList<Space>();

        int i = super.getX()+1, j = super.getY()+2;
        if(i < 8 && j < 8){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }
        i = super.getX()+1; j = super.getY()-2;
        if(i < 8 && j > -1){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }
        i = super.getX()-1; j = super.getY()+2;
        if(i > -1 && j < 8){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }
        i = super.getX()-1; j = super.getY()-2;
        if(i > -1 && j > -1){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }
        i = super.getX()+2; j = super.getY()+1;
        if(i < 8 && j < 8){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }
        i = super.getX()+2; j = super.getY()-1;
        if(i < 8 && j > -1){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);

            }
        }
        i = super.getX()-2; j = super.getY()+1;
        if(i > -1 && j < 8){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }
        i = super.getX()-2; j = super.getY()-1;
        if(i > -1 && j > -1){
            Space s = this.board.getSpace(i,j);
            if(s.getColor().equals(super.getColor())){
            }
            else if(s.getColor().equals("EMPTY")){
                Moves.add(s);
            }
            else{
                Moves.add(s);
            }
        }

        if(this.board.getPlayerTurn().equals(super.getColor())) {
            for (i = 0; i < Moves.size(); i++) {
                Board BoardCopy = this.board.copy();
                Space src = new Space(super.gameView, BoardCopy, "EMPTY", "EMPTY", super.getX(), super.getY());
                Space dst = new Knight(super.gameView, BoardCopy, super.getColor(), "KNIGHT", Moves.get(i).getX(), Moves.get(i).getY());
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
