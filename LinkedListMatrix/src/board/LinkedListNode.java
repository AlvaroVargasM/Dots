package board;

public class LinkedListNode<GenericType> {
    private int position;
    private GenericType data;
    private LinkedListNode nextDot;
    private LinkedList connectionsList;
    private boolean visited;
    
    public LinkedListNode(int position, GenericType data){
        this.position = position;
        this.nextDot = null;
        this.connectionsList = new LinkedList(0);
        this.visited = false;
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LinkedListNode getNextDot() {
        return nextDot;
    }

    public void setNextDot(LinkedListNode nextDot) {
        this.nextDot = nextDot;
    }

    public LinkedList getConnectionsList() {
        return connectionsList;
    }

    public void setConnectionsList(LinkedList connectionsList) {
        this.connectionsList = connectionsList;
    }

    public GenericType getData() {
        return data;
    }

    public void setData(GenericType data) {
        this.data = data;
    }
    
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
   
    public String toString(){
        String str = Integer.toString(position);
        return str;
    }
}
