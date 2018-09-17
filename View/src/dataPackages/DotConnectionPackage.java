
package dataPackages;

import java.awt.Point;

public class DotConnectionPackage {
    
    private Point InitialDot;
    private Point FinalDot;
    
    public DotConnectionPackage(Point InitialDot, Point finalDot){
        this.InitialDot = InitialDot;
        this.FinalDot = FinalDot;
    }
    
    public Point getInitialDot() {
        return InitialDot;
    }

    public Point getFinalDot() {
        return FinalDot;
    }
}

