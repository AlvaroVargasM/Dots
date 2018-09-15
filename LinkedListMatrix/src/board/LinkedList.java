package board;

/**
 *
 * @author Erick Barrantes
 * @param <GenericType>
 */
public class LinkedList<GenericType>{
    
    private LinkedListNode firstNode;
    private LinkedList nextRow;
    private int position;
    private int size;
    
    /**
     *
     * @param position
     */
    public LinkedList(int position){
        firstNode = null;
        nextRow = null;
        this.position = position;
        this.size = 0;
    }

    /**
     *
     * @param position
     * @param data
     */
    public void insertNode(int position, GenericType data){
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
     *
     * @param position
     * @return
     */
    public LinkedListNode getNode(int position){
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            if(position == node.getPosition()){
                return node;
            }
        }return null;
    }
    
    /**
     *
     */
    public void deleteLastNode(){
        if(firstNode != null && firstNode.getNextNode() != null){
            LinkedListNode node = firstNode;
            while(node.getNextNode().getNextNode() != null)node = node.getNextNode();
            node.setNextNode(null);
        }
    }
    
    /**
     *
     * @return
     */
    public LinkedListNode getFirstNode(){
        return firstNode;
    }
    
    /**
     *
     * @param firstNode
     */
    public void setFirstNode(LinkedListNode firstNode) {
        this.firstNode = firstNode;
    }

    /**
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
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
    public LinkedList getNextRow() {
        return nextRow;
    }

    /**
     *
     * @param nextRow
     */
    public void setNextRow(LinkedList nextRow) {
        this.nextRow = nextRow;
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
    
    public String toString(){
        String str = "[ ";
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            str += node.toString() + " -> ";
        }
        str += "null ]";
        return str;
    }
}
