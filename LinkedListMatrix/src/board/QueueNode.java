package board;

/**
 *
 * @author Erick Barrantes
 */
public class QueueNode {
    private QueueNode nextNode;
    private Player player;
    
    /**
     *
     * @param player
     */
    public QueueNode(Player player){
        this.player = player;
    }

    /**
     *
     * @return
     */
    public QueueNode getNextNode() {
        return nextNode;
    }

    /**
     *
     * @param nextNode
     */
    public void setNextNode(QueueNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
