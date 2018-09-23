package dataPackages;

import java.io.Serializable;

public class ToFigurePack implements Serializable{
    public String figure;
    public int playerNumber;

    public ToFigurePack(String figure, int playerNumber) {
        this.figure = figure;
        this.playerNumber = playerNumber;
    }
    
    public ToFigurePack(){}

    public String getFigure() {
        return figure;
    }
    
    public int getPlayerNumber(){
        return playerNumber;
    }
}
