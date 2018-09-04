package linkedlist;

public class LinkedListNode {
    private int columnNumber;
    private LinkedListNode nextNode;
    
    public LinkedListNode(int columnNumber){
        this.columnNumber = columnNumber;
        this.nextNode = null;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public LinkedListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(LinkedListNode nextNode) {
        this.nextNode = nextNode;
    }
    
    public String toString(){
        String strColumnNumber = Integer.toString(columnNumber);
        return strColumnNumber;
    }
}
