package Nickname;

public class PlayerName {
    private String nickname;

    // Constructors
    public PlayerName(String nickname) {
        if( nickname.length() <= 8){
            this.nickname = nickname;
        }
        else {
            this.nickname = null;
        }
    }
    public PlayerName() {
        this.nickname = System.getProperty("user.name");
    }
    
    // Setter & Getter
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    } 
}

 class Main {
     public static void main (String[] args){
         PlayerName myName = new PlayerName();
         System.out.println(myName.getNickname());
     }
}