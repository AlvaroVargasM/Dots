package board;

public class Queue{
    private QueueNode firstNode;
    
    public Queue(){
        firstNode = null;
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
        }
    }
    
    public QueueNode dequeue(){
        QueueNode node = firstNode;
        this.setFirstNode(firstNode.getNextNode());
        return node;
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
