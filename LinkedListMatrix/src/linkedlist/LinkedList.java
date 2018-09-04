package linkedlist;

public class LinkedList{
    
    private LinkedListNode firstNode;
    private LinkedList nextList;
    private int data;
    
    public LinkedList(int data){
        firstNode = null;
        nextList = null;
        this.data = data;
    }

    public void insertNode(int data){
        LinkedListNode newNode = new LinkedListNode(data);
        if(isEmpty()) setFirstNode(newNode);
        else{
            LinkedListNode lastNode = firstNode;
            while(lastNode.getNextNode() != null){
                lastNode = lastNode.getNextNode();
            }
            lastNode.setNextNode(newNode);
        }
    }
    
    public LinkedListNode getNode(int data){
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            if(data == node.getData()){
                return node;
            }
        }return null;
    }
    
    public LinkedListNode getFirstNode(){
        return firstNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setFirstNode(LinkedListNode firstNode) {
        this.firstNode = firstNode;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }
    
    public LinkedList getNextList() {
        return nextList;
    }

    public void setNextList(LinkedList nextList) {
        this.nextList = nextList;
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
