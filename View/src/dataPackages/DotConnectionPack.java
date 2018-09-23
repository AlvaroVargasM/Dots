
package dataPackages;

import java.io.Serializable;

/**
 * Holds the data relative to a dot connection in the grid.
 */
public class DotConnectionPack implements Serializable{
    
    /**
     * Number of the initial dot in a dot link.
     */
    private int initialDot;
    
    /**
     * Number of the final dot in a dot link.
     */
    private int finalDot;
    
    /**
     * Number of the player.
     */
    private int playerNumber;

    /**
     * Main constructor of the class DotConnectionPack.
     * @param initialDot {@link DotConnectionPack#initialDot}
     * @param finalDot {@link DotConnectionPack#finalDot}
     * @param playerNumber {@link DotConnectionPack#playerNumber}
     */
    public DotConnectionPack(int initialDot, int finalDot, int playerNumber) {
        this.initialDot = initialDot;
        this.finalDot = finalDot;
        this.playerNumber = playerNumber;
    }
    
    /**
     * Default consturctor of the class DotConnectionPack. 
     */
    public DotConnectionPack(){
    }

    /**
     *
     * @return {@link DotConnectionPack#initialDot}
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
    
    /**
     * Returns the initialDot variable.
     * @return {@link DotConnectionPack#initialDot}
     */
    public int getInitialDot() {
        return initialDot;
    }

    /**
     * Returns the finalDot variable.
     * @return {@link DotConnectionPack#finalDot}
     */
    public int getFinalDot() {
        return finalDot;
    }
}

