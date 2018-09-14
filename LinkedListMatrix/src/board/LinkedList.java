package board;

public class LinkedList{
    
    private Dot firstDot;
    private LinkedList nextRow;
    private int position;
    
    public LinkedList(int position){
        firstDot = null;
        nextRow = null;
        this.position = position;
    }

    public void insertDot(int position){
        Dot newDot = new Dot(position);
        if(isEmpty()) setFirstDot(newDot);
        else{
            Dot lastDot = firstDot;
            while(lastDot.getNextDot() != null){
                lastDot = lastDot.getNextDot();
            }
            lastDot.setNextDot(newDot);
        }
    }
    
    public Dot getDot(int position){
        for(Dot node = firstDot; node != null; node = node.getNextDot()){
            if(position == node.getPosition()){
                return node;
            }
        }return null;
    }
    
    public Dot getFirstDot(){
        return firstDot;
    }
    
    public void setFirstDot(Dot firstDot) {
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
    
    public String toString(){
        String str = "[ ";
        for(Dot node = firstDot; node != null; node = node.getNextDot()){
            str += node.toString() + " -> ";
        }
        str += "null ]";
        return str;
    }
}
