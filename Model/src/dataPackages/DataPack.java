/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataPackages;

import java.io.Serializable;

public class DataPack implements Serializable{
    
    private String winner;
    private int score1;
    private int score2;
    private int playerNumber;
    
    public DataPack(){
        
    }
    
    public DataPack(String winner,int score1,int score2,int playerNumber){
        this.winner = winner;
        this.score1 = score1;
        this.score2 = score2;
        this.playerNumber = playerNumber;
    }
    
    public String getWinner() {
        return winner;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
