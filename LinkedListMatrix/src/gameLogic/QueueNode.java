package gameLogic;

/**
 * Node for Queue class. Its main purpose is to store the data needed for 
 * the Queue and point to the next node in the queue.
 * @author Erick Barrantes
 */
public class QueueNode {
    private QueueNode nextNode;
    private Player player;
    
    /**
     * Constructor for QueueNode class. Receives a Player attribute and assigns 
     * it to the player attribute.
     * @param player
     */
    public QueueNode(Player player){
        this.player = player;
    }

    /**
     * Getter for nextNode attribute.
     * @return nextNode: QueueNode
     */
    public QueueNode getNextNode() {
        return nextNode;
    }

    /**
     * Setter for nextNode attribute
     * @param nextNode
     */
    public void setNextNode(QueueNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Getter for player attribute.
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter for player attribute.
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }   
}