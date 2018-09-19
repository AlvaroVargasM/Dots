package dataPackages;

import gameLogic.LinkedList;

public class ToFigurePack  {
    private LinkedList toFigure;
    
    public ToFigurePack (){
    }

    public ToFigurePack(LinkedList toFigure) {
        this.toFigure = toFigure;
    }
    
    public LinkedList getList() {
        return toFigure;
    }

}
