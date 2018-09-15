package board;

/**
 *
 * @author Erick Barrantes
 */
public class Player {
    private String nickname;
    private int score;
    
    /**
     *
     * @param nickname
     */
    public Player(String nickname){
        this.nickname = nickname;
        this.score = 0;
    }

    /**
     *
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
}