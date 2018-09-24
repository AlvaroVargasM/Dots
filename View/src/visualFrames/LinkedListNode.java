package visualFrames;

import java.awt.Point;
import visualFrames.LinkedList;

/**
 * Node for LinkedList class. Its main purpose is to store the data needed for 
 * the LinkedList and point to the next Node in the list.
 * Each node also contains a LinkedList with all the positions of the dots that 
 * this node is connected to.
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
     * Assigns all the initial values to the attributes.
     * @param position position of new node.
     * @param data generic data
     */
    public LinkedListNode(int position, GenericType data){
        this.position = position;
        this.nextNode = null;
        this.connectionsList = new LinkedList();
        this.visited = false;
        this.data = data;
    }

    /**
     * Getter for position attribute.
     * @return position: Integer.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter for position attribute.
     * @param position new value for position attribute.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Getter for nextNode attribute.
     * @return nextNode: LinkedListNode
     */
    public LinkedListNode getNextNode() {
        return nextNode;
    }

    /**
     * Setter for nextNode attribute.
     * @param nextNode
     */
    public void setNextNode(LinkedListNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Getter for connectionsList attribute.
     * @return connectionsList: LinkedList
     */
    public LinkedList getConnectionsList() {
        return connectionsList;
    }

    /**
     * Setter for connectionsList attribute.
     * @param connectionsList
     */
    public void setConnectionsList(LinkedList connectionsList) {
        this.connectionsList = connectionsList;
    }

    /**
     * Getter for data attribute (GenericType).
     * @return data: GenericType
     */
    public GenericType getData() {
        return data;
    }

    /**
     * Setter for data attribute.
     * @param data
     */
    public void setData(GenericType data) {
        this.data = data;
    }
    
    /**
     * Getter for visited attribute.
     * @return visited: boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Setter for visited attribute.
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
   
    public String toString(){
        String str;
        Point data = (Point) this.data;
        str = data.toString();
        return str;
    }
}
