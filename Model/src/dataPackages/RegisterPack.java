
package dataPackages;

import java.io.Serializable;

/**
 * Class used to register a new player.
 */
public class RegisterPack implements Serializable{
    
    /**
      * Ip adress of the player.
      */
    private String playerIp;
    
    /**
      * Nickname of the player.
      */
    private String playerName;
    
    /**
      * Number of the player.
      */
    private int playerNumber;
    
    /**
      * Name of the other player.
      */
    private String otherPlayerName;
    
    /**
     * Default constructor of the class RegisterPack.
     */
    public RegisterPack(){}
    
    /**
     * Constructor of the class RegisterPack.
     * @param playerIp {@link RegisterPack#playerIp}
     * @param playerName {@link RegisterPack#playerName}
     * @param playerNumber {@link RegisterPack#playerNumber}
     */
    public RegisterPack(String playerIp, String playerName,int playerNumber){
        this.playerIp = playerIp;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
    }

    /**
     * Returns the variable playerIp.
     * @return {@link RegisterPack#playerIp}
     */
    public String getPlayerIp() {
        return playerIp;
    }

    /**
     * Sets the variable playerIp.
     * @param playerIp {@link RegisterPack#playerIp}
     */
    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    /**
     * Returns the variable playerName.
     * @return {@link RegisterPack#playerName}
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the variable playerName.
     * @param playerName {@link RegisterPack#playerName}
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns the variable playerNumber.
     * @return {@link RegisterPack#playerNumber}
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Sets the variable playerNumber.
     * @param playerNumber {@link RegisterPack#playerNumber}
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    /**
     * Returns the variable playerNumber.
     * @return {@link RegisterPack#otherPlayerName}
     */
    public String getOtherPlayerName() {
        return otherPlayerName;
    }

    /**
     * Sets the variable otherPlayerName.
     * @param otherPlayerName
     */
    public void setOtherPlayerName(String otherPlayerName) {
        this.otherPlayerName = otherPlayerName;
    }
}
