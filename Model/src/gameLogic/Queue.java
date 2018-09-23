package gameLogic;

/**
 * Queue main purpose is to point to the firstNode of the queue and dequeue the 
 * node that was inserted first. It allows to insert a new QueueNode to this Queue 
 * and store any amount of data in a lineal structure.
 * @author Erick Barrantes
 */
public class Queue{
    private QueueNode firstNode;
    private int size;
    
    /**
     * Queue constructor. Assigns initial values to the attributes.
     */
    public Queue(){
        firstNode = null;
        size = 0;
    }

    /**
     * Receives a player class to create a new node an insert it in the queue.
     * @param player
     */
    public void enqueue(Player player){
        QueueNode newNode = new QueueNode(player);
        if(isEmpty()) setFirstNode(newNode);
        else{
            QueueNode lastNode = firstNode;
            while(lastNode.getNextNode() != null){
                lastNode = lastNode.getNextNode();
            }
            lastNode.setNextNode(newNode);
        }size++;
    }
    
    /**
     * Deletes an returns the first node in the queue.
     * @return
     */
    public QueueNode dequeue(){
        QueueNode node = firstNode;
        this.setFirstNode(firstNode.getNextNode());
        size--;
        return node;
    }
    
    /**
     * Returns the first node in the queue.
     * @return
     */
    public QueueNode peek(){
        return firstNode;
    }
    
    public boolean contains(String playerIp){
        for(QueueNode node = firstNode; node != null; node = node.getNextNode()){
            if(node.getPlayer().getPlayerIp() == playerIp) return true;
        }return false;
    }
   
    /**
     * Indicates if the queue its empty (true) or not (false).
     * @return boolean
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * Getter for firstNode attribute.
     * @return firstNode: QueueNode
     */
    public QueueNode getFirstNode() {
        return firstNode;
    }

    /**
     * Setter for firstNode attribute.
     * @param firstNode
     */
    public void setFirstNode(QueueNode firstNode) {
        this.firstNode = firstNode;
    }

    /**
     * Getter for size attribute.
     * @return size: int
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter for size attribute.
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }
}