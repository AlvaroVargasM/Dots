package board;

/**
 * Class that stores all the data needed for the players of the game. 
 * @author Erick Barrantes
 */
public class Player {
    private String nickname;
    private int score;
    
    /**
     * Player Constructor. Receives a string to assign it to the nickname attribute
     * and gives the initial value of 0 to the score.
     * @param nickname
     */
    public Player(String nickname){
        this.nickname = nickname;
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
}