package linkedlist;

public class Matrix {
    private LinkedList firstList;
    private boolean empty;

    public Matrix(){
        firstList = null;
        empty = true;
    }
    
    public void insertList(LinkedList newList){
        if(isEmpty()) setFirstList(newList);
        else{
            LinkedList lastList = firstList;
            while(lastList.getNextList() != null){
                lastList = lastList.getNextList();
            }
            lastList.setNextList(newList);
        }
    }
    
    public LinkedList getFirstRow() {
        return firstList;
    }

    public void setFirstList(LinkedList firstList) {
        this.firstList = firstList;
    }

    public boolean isEmpty() {
        return firstList == null;
    }
    
    public String toString(){
        String str = "Matrix:\n";
        for(LinkedList list = firstList; list != null; list = list.getNextList()){
            str += Integer.toString(list.getData()) + ": " + list.toString() + "\n";
        } return str;
    }
    
}
