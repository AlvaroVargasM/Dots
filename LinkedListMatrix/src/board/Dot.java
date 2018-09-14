package board;

public class Dot {
    private int position;
    private Dot nextDot;
    private LinkedList connectionsList;
    private boolean visited;
    
    public Dot(int position){
        this.position = position;
        this.nextDot = null;
        this.connectionsList = new LinkedList(0);
        this.visited = false;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Dot getNextDot() {
        return nextDot;
    }

    public void setNextDot(Dot nextDot) {
        this.nextDot = nextDot;
    }

    public LinkedList getConnectionsList() {
        return connectionsList;
    }

    public void setConnectionsList(LinkedList connectionsList) {
        this.connectionsList = connectionsList;
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
