
package dataPackages;

import java.io.Serializable;

/**
 * Class that holds the data needed to create a new figure.
 */
public class ToFigurePack implements Serializable{

    /**
     * A concatenated string that contains a series of dots numbers.
     */
    public String figure;

    /**
     * Number of the player who connect the figure.
     */
    public int playerNumber;

    /**
     * Constructor of the class ToFigurePack.
     * @param figure {@link ToFigurePack#figure}
     * @param playerNumber {@link ToFigurePack#playerNumber}
     */
    public ToFigurePack(String figure, int playerNumber) {
        this.figure = figure;
        this.playerNumber = playerNumber;
    }
    
    /**
     * Default constructor of the class ToFigurePack.
     */
    public ToFigurePack(){}

    /**
     * Returns the variable figure.
     * @return {@link ToFigurePack#figure}
     */
    public String getFigure() {
        return figure;
    }
    
    /**
     * Returns the variable playerNumber.
     * @return {@link ToFigurePack#playerNumber}
     */
    public int getPlayerNumber(){
        return playerNumber;
    }
}
