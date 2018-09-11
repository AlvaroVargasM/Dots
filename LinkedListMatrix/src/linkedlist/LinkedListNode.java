package linkedlist;

public class LinkedListNode {
    private int data;
    private LinkedListNode nextNode;
    
    public LinkedListNode(int data){
        this.data = data;
        this.nextNode = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LinkedListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(LinkedListNode nextNode) {
        this.nextNode = nextNode;
    }
    
    public String toString(){
        String str = Integer.toString(data);
        return str;
    }
}
