package dataPackages;

import java.util.LinkedList;

public class ToFigurePack  {
    private LinkedList toFigure;
    private int playerNumber;
    private int plusScore;
    
    public ToFigurePack (){
    }

    public ToFigurePack(LinkedList toFigure, int playerNumber, int plusScore) {
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
