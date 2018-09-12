package board;

public class Player {
    private String nickname;
    private int score;
    
    public Player(String nickname){
        this.nickname = nickname;
        this.score = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
