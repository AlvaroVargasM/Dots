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
    
    public RegisterPack(){
        
    }
    
    public RegisterPack(String playerIp, String playerName,int playerNumber,
                        String otherPlayerName){
        this.playerIp = playerIp;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.otherPlayerName = otherPlayerName;
    }

    public String getPlayerIp() {
        return playerIp;
    }

    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    public String getOtherPlayerName() {
        return otherPlayerName;
    }

    public void setOtherPlayerName(String otherPlayerName) {
        this.otherPlayerName = otherPlayerName;
    }
}
