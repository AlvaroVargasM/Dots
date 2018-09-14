package board;

public class LinkedList<GenericType>{
    
    private LinkedListNode firstDot;
    private LinkedList nextRow;
    private int position;
    private int size;
    
    public LinkedList(int position){
        firstDot = null;
        nextRow = null;
        this.position = position;
        this.size = 0;
    }

    public void insertDot(int position, GenericType data){
        LinkedListNode newDot = new LinkedListNode(position, data);
        if(isEmpty()) setFirstDot(newDot);
        else{
            LinkedListNode lastDot = firstDot;
            while(lastDot.getNextDot() != null){
                lastDot = lastDot.getNextDot();
            }
            lastDot.setNextDot(newDot);
        }size++;
    }
    
    public LinkedListNode getDot(int position){
        for(LinkedListNode node = firstDot; node != null; node = node.getNextDot()){
            if(position == node.getPosition()){
                return node;
            }
        }return null;
    }
    
    public LinkedListNode getFirstDot(){
        return firstDot;
    }
    
    public void setFirstDot(LinkedListNode firstDot) {
        this.firstDot = firstDot;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public boolean isEmpty() {
        return firstDot == null;
    }
    
    public LinkedList getNextRow() {
        return nextRow;
    }

    public void setNextRow(LinkedList nextRow) {
        this.nextRow = nextRow;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public String toString(){
        String str = "[ ";
        for(LinkedListNode node = firstDot; node != null; node = node.getNextDot()){
            str += node.toString() + " -> ";
        }
        str += "null ]";
        return str;
    }
}
