
package dataPackages;

import java.io.Serializable;

/**
 *
 * @author luism
 */
public class ToFigurePack implements Serializable{

    /**
     *
     */
    public String figure;

    /**
     *
     */
    public int playerNumber;

    /**
     *
     * @param figure
     * @param playerNumber
     */
    public ToFigurePack(String figure, int playerNumber) {
        this.figure = figure;
        this.playerNumber = playerNumber;
    }
    
    /**
     *
     */
    public ToFigurePack(){}

    /**
     *
     * @return
     */
    public String getFigure() {
        return figure;
    }
    
    /**
     *
     * @return
     */
    public int getPlayerNumber(){
        return playerNumber;
    }
}
