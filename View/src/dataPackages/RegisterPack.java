
package dataPackages;

import java.io.Serializable;

/**
 * Class used to register a new player.
 */
public class RegisterPack implements Serializable{
    
    private String playerIp;
    
    private String playerName;
    
    private int playerNumber;
    
    private String otherPlayerName;
    
    /**
     *
     */
    public RegisterPack(){
        
    }
    
    /**
     *
     * @param playerIp
     * @param playerName
     * @param playerNumber
     */
    public RegisterPack(String playerIp, String playerName,int playerNumber){
        this.playerIp = playerIp;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
    }

    /**
     *
     * @return
     */
    public String getPlayerIp() {
        return playerIp;
    }

    /**
     *
     * @param playerIp
     */
    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    /**
     *
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
     * @param playerNumber
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    /**
     *
     * @return
     */
    public String getOtherPlayerName() {
        return otherPlayerName;
    }

    /**
     *
     * @param otherPlayerName
     */
    public void setOtherPlayerName(String otherPlayerName) {
        this.otherPlayerName = otherPlayerName;
    }
}
