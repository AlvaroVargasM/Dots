

package dataPackages;

import java.util.LinkedList;

public class toFigurePackage  {
    
    private LinkedList toFigure;
    private int playerNumber;
    private int plusScore;
    
    public toFigurePackage (){
    }
    
    public toFigurePackage (LinkedList toFigure){
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
