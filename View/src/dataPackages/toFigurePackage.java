

package dataPackages;

import java.util.LinkedList;

public class ToFigurePackage  {
    
    private LinkedList toFigure;
    private int playerNumber;
    private int plusScore;
    
    public ToFigurePackage (){
    }
    
    public ToFigurePackage (LinkedList toFigure){
        this.toFigure = toFigure;
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
