package dataPackages;

public class DotConnectionPackage {
    
    private int initialDot;
    private int finalDot;
    private int playerNumber;

    public DotConnectionPackage(int initialDot, int finalDot, int playerNumber) {
        this.initialDot = initialDot;
        this.finalDot = finalDot;
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
    
    public int getInitialDot() {
        return initialDot;
    }

    public int getFinalDot() {
        return finalDot;
    }
}

