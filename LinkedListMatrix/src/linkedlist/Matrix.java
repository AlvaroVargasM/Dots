package linkedlist;

public class Matrix {
    private LinkedList firstRow;
    private boolean empty;
    private int size;

    public Matrix(){
        firstRow = null;
        empty = true;
        size = 0;
    }
    
    
    
    public LinkedList getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(LinkedList firstRow) {
        this.firstRow = firstRow;
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
    
    
    
}
