package dataPackages;

public class DotConnectionPack {
    
    private int initialDot;
    private int finalDot;
    private int playerNumber;

    public DotConnectionPack(int initialDot, int finalDot, int playerNumber) {
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

