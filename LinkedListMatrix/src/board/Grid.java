package board;

/**
 *
 * @author Erick Barrantes
 */
public class Grid {
    private LinkedList firstRow;
    private int rowSize;
    private int columnSize;

    /**
     *
     * @param rowSize
     * @param columnSize
     */
    public Grid(int rowSize, int columnSize){
        firstRow = null;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }
    
    /**
     *
     * @param newRow
     */
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
    
    /**
     *
     * @param position
     * @return
     */
    public LinkedListNode getNode(int position){
        for(LinkedList row = firstRow; row != null; row = row.getNextRow()){
            LinkedListNode node = row.getNode(position);
            if(node != null) return node;
        }return null;
    }
    
    /**
     *
     * @param initialDotPosition
     * @param finalDotPosition
     * @return
     */
    public LinkedList createConnection(int initialDotPosition, int finalDotPosition){
        LinkedListNode initialDot = this.getNode(initialDotPosition);
        LinkedListNode finalDot = this.getNode(finalDotPosition);
        LinkedList figure = new LinkedList(0);
        figure = searchForFigure(initialDot, finalDot, figure);
        initialDot.getConnectionsList().insertNode(finalDotPosition, null);
        finalDot.getConnectionsList().insertNode(initialDotPosition, null);
        this.resetVisitedBooleans();
        return figure;
    }
    
    /**
     *
     * @param currentDot
     * @param initialDot
     * @param finalDot
     * @param figure
     * @return
     */
    public LinkedList searchForFigure(LinkedListNode currentDot, LinkedListNode finalDot, LinkedList figure){
        if(currentDot != null && !currentDot.isVisited()){
            currentDot.setVisited(true);
            figure.insertNode(currentDot.getPosition(), null);
            LinkedList dotConnections = currentDot.getConnectionsList();
            for(LinkedListNode dot = dotConnections.getFirstNode(); dot != null;
                dot = dot.getNextNode()){
                LinkedListNode connection = this.getNode(dot.getPosition());
                if(connection == finalDot){
                    figure.insertNode(connection.getPosition(), null);
                    return figure;
                }
                LinkedList figure2 = searchForFigure(connection, finalDot, figure);
                if(figure2 != null)return figure;
            }figure.deleteLastNode();
        }
        return null;
    }
    
    /**
     *
     */
    public void resetVisitedBooleans(){
        for(LinkedList row = firstRow; row != null; row = row.getNextRow()){
            for(LinkedListNode dot = row.getFirstNode(); dot != null; dot = dot.getNextNode()){
                dot.setVisited(false);
            }
        }
    }
    
    /**
     *
     * @return
     */
    public LinkedList getFirstRow() {
        return firstRow;
    }

    /**
     *
     * @param firstRow
     */
    public void setFirstRow(LinkedList firstRow) {
        this.firstRow = firstRow;
    }

    /**
     *
     * @return
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     *
     * @param rowSize
     */
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    /**
     *
     * @return
     */
    public int getColumnSize() {
        return columnSize;
    }

    /**
     *
     * @param columnSize
     */
    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    /**
     *
     * @return
     */
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
       