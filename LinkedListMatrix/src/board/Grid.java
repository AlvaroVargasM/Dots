package board;

public class Grid {
    private LinkedList firstRow;
    private int rowSize;
    private int columnSize;

    public Grid(int rowSize, int columnSize){
        firstRow = null;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }
    
    public void insertList(LinkedList newRow){
        if(isEmpty()) setFirstRow(newRow);
        else{
            LinkedList lastRow = firstRow;
            while(lastRow.getNextRow() != null){
                lastRow = lastRow.getNextRow();
            }
            lastRow.setNextRow(newRow);
        }
    }
    
    public Dot getDot(int position){
        LinkedList row = firstRow;
        int rowPosition = position/rowSize;
        int i = 0;
        while(i++ < rowPosition){
            row = row.getNextRow();
        }
        Dot dot = row.getDot(position);
        return dot;
    }
    
    public boolean makeConnection(int initialDotPosition, int finalDotPosition){
        Dot initialDot = this.getDot(initialDotPosition);
        Dot finalDot = this.getDot(finalDotPosition);
        LinkedList f = new LinkedList(0);
        boolean figure = searchForFigure(initialDot, initialDot, finalDot, f);
        initialDot.getConnectionsList().insertDot(finalDotPosition);
        finalDot.getConnectionsList().insertDot(initialDotPosition);
        this.resetVisitedBooleans();
        return figure;
    }
    
    public boolean searchForFigure(Dot currentDot, Dot initialDot, Dot finalDot, LinkedList f){
        if(currentDot != null && !currentDot.isVisited()){
            currentDot.setVisited(true);
            f.insertDot(currentDot.getPosition());
            LinkedList dotConnections = currentDot.getConnectionsList();
            for(Dot dot = dotConnections.getFirstDot(); dot != null;
                dot = dot.getNextDot()){
                Dot connection = this.getDot(dot.getPosition());
                if(connection == finalDot)
                    if(currentDot != initialDot){
                        f.insertDot(connection.getPosition());
                        System.out.println(f.toString());
                        return true;
                    }
                boolean figure = searchForFigure(connection, initialDot, finalDot, f);
                if(figure)return figure;
            }
        }return false;
    }
    
    public void resetVisitedBooleans(){
        for(LinkedList row = firstRow; row != null; row = row.getNextRow()){
            for(Dot dot = row.getFirstDot(); dot != null; dot = dot.getNextDot()){
                dot.setVisited(false);
            }
        }
    }
    
    public LinkedList getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(LinkedList firstRow) {
        this.firstRow = firstRow;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public boolean isEmpty() {
        return firstRow == null;
    }
    
    public String toString(){
        String str = "Matrix:\n";
        for(LinkedList list = firstRow; list != null; list = list.getNextRow()){
            str += Integer.toString(list.getPosition()) + ": " + list.toString() + "\n";
        } return str;
    } 
}
       