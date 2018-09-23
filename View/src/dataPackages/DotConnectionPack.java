
package dataPackages;

import java.io.Serializable;

/**
 *
 * @author luism
 */
public class DotConnectionPack implements Serializable{
    
    private int initialDot;
    private int finalDot;
    private int playerNumber;

    /**
     *
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
     *
     */
    public DotConnectionPack(){
    }

    /**
     *
     * @return
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

