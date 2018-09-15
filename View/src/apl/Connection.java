
package apl;

public class Connection {
    public int initialDotPosition;
    public int finalDotPosition;

    public Connection(int initialDotPosition, int finalDotPosition) {
        this.initialDotPosition = initialDotPosition;
        this.finalDotPosition = finalDotPosition;
    }

    public int getInitialDotPosition() {
        return initialDotPosition;
    }

    public void setInitialDotPosition(int initialDotPosition) {
        this.initialDotPosition = initialDotPosition;
    }

    public int getFinalDotPosition() {
        return finalDotPosition;
    }

    public void setFinalDotPosition(int finalDotPosition) {
        this.finalDotPosition = finalDotPosition;
    }
}