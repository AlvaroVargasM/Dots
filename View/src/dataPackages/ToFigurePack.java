package dataPackages;

import java.io.Serializable;

public class ToFigurePack implements Serializable{
    public String figure;

    public ToFigurePack(String figure) {
        this.figure = figure;
    }
    
    public ToFigurePack(){}

    public String getFigure() {
        return figure;
    }
}
