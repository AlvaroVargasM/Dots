package linkedlist;

public class LinkedList{
    
    private LinkedListNode firstNode;
    private boolean empty;
    private int size;
    private LinkedList nextRow;
    private int rowNumber;
    
    public LinkedList(int rowNumber){
        size = 0;
        empty = true;
        firstNode = null;
        nextRow = null;
        this.rowNumber = rowNumber;
    }
    
    public boolean contains(int data){
        if(!isEmpty()){
            for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
                if(data == node.getColumnNumber()) return true;
            }
        }return false;
    }
    
    public void insertNode(){
        LinkedListNode newNode = new LinkedListNode(size);
        if(isEmpty()){
            setFirstNode(newNode);
            setEmpty(false);
        }else{
            LinkedListNode lastNode = firstNode;
            while(lastNode.getNextNode() != null){
                lastNode = lastNode.getNextNode();
            }
            lastNode.setNextNode(newNode);
        }increaseSize();
    }
    
    public void delete(int data){
        if(contains(data)){
            LinkedListNode nodeToDelete = getNode(data);
            if(firstNode == nodeToDelete){
                setFirstNode(firstNode.getNextNode());
                firstNode = null;
            }else{
                LinkedListNode node = firstNode;
                while(node.getNextNode() != nodeToDelete){
                    node = node.getNextNode();
                }
                node.setNextNode(nodeToDelete.getNextNode());
                nodeToDelete = null;
            }decreaseSize();
            if(getSize() == 0) setEmpty(true);
        }
    }
    
    public LinkedListNode getNode(int data){
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            if(data == node.getColumnNumber()){
                return node;
            }
        }return null;
    }
    
    public void sortLinkedList(){
	LinkedListNode tempHeader = firstNode;
        for (int j=0; j<size; j++){
            while (tempHeader.getNextNode() != null){
                if (tempHeader.getColumnNumber() < tempHeader.getNextNode().getColumnNumber()){
                    LinkedListNode temp = tempHeader.getNextNode();
                    if(tempHeader == firstNode) firstNode = temp;
                    else getPreviousNode(tempHeader.getColumnNumber()).setNextNode(temp);
                    tempHeader.setNextNode(tempHeader.getNextNode().getNextNode());
                    temp.setNextNode(tempHeader);
                }else tempHeader = tempHeader.getNextNode();
            }tempHeader = firstNode;
        }
    }
    
    public LinkedListNode getPreviousNode(int data){
        LinkedListNode node = null;
        for(LinkedListNode currentNode = firstNode ; currentNode != null; currentNode = currentNode.getNextNode()){
            if (data == currentNode.getNextNode().getColumnNumber()) {
                node = currentNode;
                break;
            }
        }return node;
    }
    
    public LinkedListNode getFirstNode(){
        return firstNode;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setFirstNode(LinkedListNode firstNode) {
        this.firstNode = firstNode;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void increaseSize() {
        this.size++;
    }
    
    public void decreaseSize() {
        this.size++;
    }

    public LinkedList getNextRow() {
        return nextRow;
    }

    public void setNextRow(LinkedList nextRow) {
        this.nextRow = nextRow;
    }
    
    public String toString(){
        String str = " F: ";
        for(LinkedListNode node = firstNode; node != null; node = node.getNextNode()){
            str += node.toString() + " -> ";
        }
        str += "null";
        return str;
    }
}
