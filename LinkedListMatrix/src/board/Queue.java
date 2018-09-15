package board;

/**
 *
 * @author Erick Barrantes
 */
public class Queue{
    private QueueNode firstNode;
    private int size;
    
    /**
     *
     */
    public Queue(){
        firstNode = null;
        size = 0;
    }

    /**
     *
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
     *
     * @return
     */
    public QueueNode dequeue(){
        QueueNode node = firstNode;
        this.setFirstNode(firstNode.getNextNode());
        size--;
        return node;
    }
    
    /**
     *
     * @return
     */
    public QueueNode peek(){
        return firstNode;
    }
    
    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     *
     * @return
     */
    public QueueNode getFirstNode() {
        return firstNode;
    }

    /**
     *
     * @param firstNode
     */
    public void setFirstNode(QueueNode firstNode) {
        this.firstNode = firstNode;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }
}
