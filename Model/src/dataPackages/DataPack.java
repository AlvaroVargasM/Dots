
package dataPackages;

import java.io.Serializable;

/**
 *
 * @author luism
 */
public class DataPack implements Serializable{
    
    private String winner;
    private int score1;
    private int score2;
    private int playerNumber;
    
    /**
     *
     */
    public DataPack(){
        
    }
    
    /**
     *
     * @param winner
     * @param score1
     * @param score2
     * @param playerNumber
     */
    public DataPack(String winner,int score1,int score2,int playerNumber){
        this.winner = winner;
        this.score1 = score1;
        this.score2 = score2;
        this.playerNumber = playerNumber;
    }
    
    /**
     *
     * @return
     */
    public String getWinner() {
        return winner;
    }

    /**
     *
     * @return
     */
    public int getScore1() {
        return score1;
    }

    /**
     *
     * @return
     */
    public int getScore2() {
        return score2;
    }

    /**
     *
     * @return
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
}
