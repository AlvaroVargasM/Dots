
package dataPackages;

import java.awt.Point;

public class DotConnectionPackage {
    
    private Point initialDot;
    private Point finalDot;
    
    public DotConnectionPackage(Point initialDot, Point finalDot){
        this.initialDot = initialDot;
        this.finalDot = finalDot;
    }
    
    public Point getInitialDot() {
        return initialDot;
    }

    public Point getFinalDot() {
        return finalDot;
    }
}

