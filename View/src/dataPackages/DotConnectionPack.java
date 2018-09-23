
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
     *
     * @return
     */
    public int getInitialDot() {
        return initialDot;
    }

    /**
     *
     * @return
     */
    public int getFinalDot() {
        return finalDot;
    }
}

