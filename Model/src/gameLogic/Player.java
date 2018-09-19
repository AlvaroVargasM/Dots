package gameLogic;

/**
 * Class that stores all the data needed for the players of the game. 
 * @author Erick Barrantes
 */
public class Player {
    private String nickname;
    private String playerIp;    
    private int playerNumber;
    private int score;
    
    /**
     * Player Constructor. Receives a string to assign it to the nickname attribute
     * and gives the initial value of 0 to the score.
     * @param nickname
     */
    public Player(String nickname, String playerIp, int playerNumber) {
        this.nickname = nickname;
        this.playerIp = playerIp;
        this.playerNumber = playerNumber;
        this.score = 0;
    }

    /**
     * Getter for nickname attribute.
     * @return nickname: String
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setter for nickname attribute.
     * @param nickname 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter for score attribute.
     * @return score: int
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for score attribute.
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerIp() {
        return playerIp;
    }

    public void setPlayerIp(String playerIp) {
        this.playerIp = playerIp;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    
}