package board;

/**
 * Node for LinkedList class. Its main purpose is to store the data needed for the
 * LinkedList and point to the next Node in the list.
 * @author Erick Barrantes
 * @param <GenericType>
 */
public class LinkedListNode<GenericType> {
    private int position;
    private GenericType data;
    private LinkedListNode nextNode;
    private LinkedList connectionsList;
    private boolean visited;
    
    /**
     * LinkedListNode constructor. Receives an integer that indicates its position
     * in the list. Also, it can store a data with any kind of type (GenericType).
     * Assigns all the values to the attributes.
     * @param position
     * @param data
     */
    public LinkedListNode(int position, GenericType data){
        this.position = position;
        this.nextNode = null;
        this.connectionsList = new LinkedList(0);
        this.visited = false;
        this.data = data;
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
    public LinkedListNode getNextNode() {
        return nextNode;
    }

    /**
     *
     * @param nextNode
     */
    public void setNextNode(LinkedListNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     *
     * @return
     */
    public LinkedList getConnectionsList() {
        return connectionsList;
    }

    /**
     *
     * @param connectionsList
     */
    public void setConnectionsList(LinkedList connectionsList) {
        this.connectionsList = connectionsList;
    }

    /**
     *
     * @return
     */
    public GenericType getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(GenericType data) {
        this.data = data;
    }
    
    /**
     *
     * @return
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     *
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
   
    public String toString(){
        String str = Integer.toString(position);
        return str;
    }
}
