
package dataPackages;

import java.io.Serializable;

/**
 *
 * @author luism
 */
public class DotConnectionPack implements Serializable{
    
    /**
     * The number of the initial dot in a dot link.
     */
    private int initialDot;
    
    /**
     * The number of the final dot in a dot link.
     */
    private int finalDot;
    
    /**
     * The number of the player.
     */
    private int playerNumber;

    /**
     * Main constructor of the 
     * @param initialDot
     * @param finalDot
     * @param playerNumber
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

