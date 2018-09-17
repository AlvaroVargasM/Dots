package dataPackages;

/**
 * Class used to register a new player.
 */
public class RegisterPack {
    
    private String playerIp;
    
    private String playerName;
    
    private int playerNumber;
    
    public RegisterPack(String playerIp, String playerName,int playerNumber){
        this.playerIp = playerIp;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
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
}
