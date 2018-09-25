package gameLogic;

/**
 * LinkedList main purpose is to point to the firstNode of the list. It allows to
 * insert a new LinkedListNode to this list and store any amount of data in a 
 * lineal structure. It also has some methods to delete the last node of the list
 * or find an specific node in a position.
 * This LinkedList also points to a next LinkedList, meaning that it could also
 * be a node of a LinkedList with LinkedLists as nodes.
 * @author Erick Barrantes
 * @param <GenericType>
 */
public class LinkedList<GenericType>{
    
    private LinkedListNode firstNode;
    private LinkedList nextRow;
    private int size;
    
    /**
     * LinkedList constructor. Assigns the initial values to the attributes.
     */
    public LinkedList(){
        firstNode = null;
        nextRow = null;
        this.size = 0;
    }

    /**
     * Receives the data necessary to create a node and insert it in the list.
     * @param position
     * @param data
     */
    public void add(int position, GenericType data){
        LinkedListNode newDot = new LinkedListNode(position, data);
        if(isEmpty()) setFirstNode(newDot);
        else{
            LinkedListNode lastDot = firstNode;
            while(lastDot.getNextNode() != null){
                lastDot = lastDot.getNextNode();
            }
            lastDot.setNextNode(newDot);
        }size++;
    }
    
     /**
     * Receives the data necessary to create a node and insert it in the list.
     * @param data
     */
    public void add(GenericType data){
        this.add(size, data);
    }
    
    /**
     * Searches and returns the node in the specified position.
     * @param position
     * @return 
     * @return: node in position: LinkedListNode
     */
    public LinkedListNode getNode(int position){
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            if(position == node.getPosition()){
                return node;
            }
        }return null;
    }
    
    /**
     * Deletes the last inserted node of the list.
     */
    public void deleteLastNode(){
        if(firstNode != null && firstNode.getNextNode() != null){
            LinkedListNode node = firstNode;
            while(node.getNextNode().getNextNode() != null)node = node.getNextNode();
            node.setNextNode(null);
        }
    }
    
    /**
     * Getter for FirstNode attribute.
     * @return firstNode: LinkedListNode
     */
    public LinkedListNode getFirstNode(){
        return firstNode;
    }
    
    /**
     * Setter for firstNode attribute.
     * @param firstNode
     */
    public void setFirstNode(LinkedListNode firstNode) {
        this.firstNode = firstNode;
    }
    
    /**
     * Indicates if the list its empty (true) or not (false)
     * @return boolean
     */
    public boolean isEmpty() {
        return firstNode == null;
    }
    
    /**
     * Getter for nextRow attribute.
     * @return nextRow: LinkedList
     */
    public LinkedList getNextRow() {
        return nextRow;
    }

    /**
     * Setter for nextRow attribute.
     * @param nextRow
     */
    public void setNextRow(LinkedList nextRow) {
        this.nextRow = nextRow;
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
    
    public String toString(){
        String str = "[ ";
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            str += node.toString() + " -> ";
        }
        str += "null ]";
        return str;
    }
}
