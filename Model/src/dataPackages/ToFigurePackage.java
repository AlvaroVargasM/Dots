package dataPackages;

import gameLogic.LinkedList;


public class ToFigurePackage  {
    private LinkedList toFigure;
    private int playerNumber;
    private int plusScore;
    
    public ToFigurePackage (){
    }

    public ToFigurePackage(LinkedList toFigure, int playerNumber, int plusScore) {
        this.toFigure = toFigure;
        this.playerNumber = playerNumber;
        this.plusScore = plusScore;
    }
    
    public LinkedList getList() {
        return toFigure;
    }
    
    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getPlusScore() {
        return plusScore;
    }
}
