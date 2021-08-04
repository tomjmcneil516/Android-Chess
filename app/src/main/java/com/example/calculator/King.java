package com.example.calculator;

import java.util.ArrayList;

public class King extends Space {

    private Board board;

    public King(GameView gameView,Board board,String color, String name,int x, int y){
        super(gameView,board,color,name,x,y);
        this.board = board;
    }

    public ArrayList<Space> getMoves(){
        ArrayList<Space> Moves = new ArrayList<Space>();

        int i = super.getX()+1, j = super.getY();
        if(i < 8) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX()+1; j = super.getY()+1;
        if(i < 8 && j < 8) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX()+1; j = super.getY()-1;
        if(i < 8 && j > -1) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX(); j = super.getY()-1;
        if(j > -1) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX(); j = super.getY()+1;
        if(j < 8) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX()-1; j = super.getY()+1;
        if(i > -1 && j < 8) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX()-1; j = super.getY();
        if(i > -1) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        i = super.getX()-1; j = super.getY()-1;
        if(i > -1 && j > -1) {
            Space s = this.board.getSpace(i, j);
            if (!s.getColor().equals(super.getColor())) {
                Moves.add(s);
            }
        }

        if(this.board.getPlayerTurn().equals(super.getColor())){
            for(i = 0; i < Moves.size(); i++){
                Board BoardCopy = this.board.copy();
                Space src = new Space(super.gameView,BoardCopy,"EMPTY","EMPTY",super.getX(),super.getY());
                Space dst = new King(super.gameView,BoardCopy,super.getColor(),"KING", Moves.get(i).getX(), Moves.get(i).getY());
                BoardCopy.setSpace(src);
                BoardCopy.setSpace(dst);
                if(BoardCopy.isInCheck()){
                    Moves.remove(i);
                    i--;
                }
            }
        }

        if(super.getColor().equals("WHITE")){
            if(canCastleKingSide(7)) {
                Moves.add(this.board.getSpace(7, 6));
            }
            if(canCastleQueenSide(7)){
                Moves.add(this.board.getSpace(7, 2));
            }
        }

        if(super.getColor().equals("BLACK")){
            if(canCastleKingSide(0)) {
                Moves.add(this.board.getSpace(0, 6));
            }
            if(canCastleQueenSide(0)){
                Moves.add(this.board.getSpace(0, 2));
            }
        }

        return Moves;
    }

    private boolean canCastleKingSide(int row) {
        if (super.hasMoved()) {
            return false;
        }
        if (board.getSpace(row, 7).hasMoved()) {
            return false;
        }
        for (int i = 5; i < 7; i++) {
            if(!board.getSpace(row,i).getName().equals("EMPTY")) {
                return false;
            }
        }
        Board BoardCopy = this.board.copy();
        for (int i = 4; i < 8; i++) {
            Space dst = new King(super.gameView, BoardCopy, "WHITE", "KING", row, i);
            BoardCopy.setSpace(dst);
            }
        if (BoardCopy.isInCheck()) {
            return false;
        }
        return true;
    }


    private boolean canCastleQueenSide(int row){
        if (super.hasMoved()) {
            return false;
        }
        if (board.getSpace(row, 0).hasMoved()) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if(!board.getSpace(row,i).getName().equals("EMPTY")) {
                return false;
            }
        }
        Board BoardCopy = this.board.copy();
        for (int i = 0; i < 5; i++) {
            Space dst = new King(super.gameView, BoardCopy, "WHITE", "KING", row, i);
            BoardCopy.setSpace(dst);
        }
        if (BoardCopy.isInCheck()) {
            return false;
        }
        return true;
    }

}
