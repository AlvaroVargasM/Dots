package board;

public class Queue{
    private QueueNode firstNode;
    private int size;
    
    public Queue(){
        firstNode = null;
        size = 0;
    }

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
    
    public QueueNode dequeue(){
        QueueNode node = firstNode;
        this.setFirstNode(firstNode.getNextNode());
        size--;
        return node;
    }
    
    public QueueNode peek(){
        return firstNode;
    }
    
    public boolean isEmpty() {
        return firstNode == null;
    }

    public QueueNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(QueueNode firstNode) {
        this.firstNode = firstNode;
    }
}
