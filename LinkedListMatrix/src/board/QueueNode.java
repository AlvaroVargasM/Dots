package board;

public class QueueNode {
    private QueueNode nextNode;
    private Player player;
    
    public QueueNode(Player player){
        this.player = player;
    }

    public QueueNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(QueueNode nextNode) {
        this.nextNode = nextNode;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
