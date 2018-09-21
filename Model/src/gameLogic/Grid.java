package gameLogic;

/**
 * Grid points to a first LinkedList, meaning that Grid is a LinkedList, were 
 * each node is another LinkedList. In other words, it's a matrix were each row
 * is a LinkedList that contains LinkedListNodes.
 * This class allows to insert new rows or LinkedList to the structure and get 
 * an specific node in a position.
 * Besides, this structure allows to create connection with nodes in the same
 * row or even in different rows, and validate if a figure is made with this 
 * connections.
 * @author Erick Barrantes
 */
public class Grid {
    private LinkedList firstRow;
    private int rowSize;
    private int columnSize;
    private static Grid singletonGrid;

    /**
     * Grid constructor. Receives the size of the grid or matrix and assigns 
     * the initial values to the attributes.
     * @param rowSize
     * @param columnSize
     */
    private Grid(int rowSize, int columnSize){
        firstRow = null;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }
    
    public static Grid getGrid(int rowSize, int columnSize){
        if(singletonGrid == null){
            singletonGrid = new Grid(rowSize, columnSize);
            int pos = 1;
            for (int i = 0; i < singletonGrid.getRowSize(); i++){
                LinkedList list = new LinkedList();
                for(int j = 0; j < singletonGrid.getColumnSize(); j++){
                    list.add(pos++, null);
                }
                singletonGrid.insertList(list);
            }
        }return singletonGrid;
    }
    
    /**
     * Receives a LinkedList and inserts it to this structure.
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
     * Searches and returns the node in the specified position.
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
     * This function receives to integers indicating the positions of the nodes
     * that want to be connected. Before connecting this lines, this values are
     * sent to searchForFigure function to validate if this connection creates 
     * a figure or not.
     * @param initialDotPosition
     * @param finalDotPosition
     * @return figure: LinkedList. Null if there was no figure created. On the
     * contrary, returns a LinkedList with all the positions that shape the 
     * figure.
     */
    public LinkedList createConnection(int initialDotPosition, int finalDotPosition){
        LinkedListNode initialDot = this.getNode(initialDotPosition); //Obtain initialDot in the grid structure.
        LinkedListNode finalDot = this.getNode(finalDotPosition);//Obtain finalDot in the grid structure.
        LinkedList<Integer> figure = new LinkedList<Integer>(); //LinkedList that will have dots that shape figure.
        figure = searchForFigure(initialDot, finalDot, figure); //Validate if figure is created.
        initialDot.getConnectionsList().add(finalDotPosition, null);//Connect initialDot with finalDot
        finalDot.getConnectionsList().add(initialDotPosition, null);//Connect finalDot with initialDot
        this.resetVisitedBooleans();
        return figure;
    }
    
    /**
     * Receives a new connection and validates if this connection creates a figure.
     * The currentDot starts as the initial dot of the connection. This function
     * will obtain the LinkedList of connections of each node and travel through 
     * the dots connected directly or indirectly to the initial dot. If currentDot
     * is equal to finalDot in some moment of the recursion, this means that a figure
     * was created. If this happens, the LinkedList figure will be storing all the
     * correct dots positions that shape the figure. If a shape its not created,
     * it will return null
     * @param currentDot
     * @param finalDot
     * @param figure
     * @return figure: LinkedList with the dots the shape the figure.
     */
    public LinkedList searchForFigure(LinkedListNode currentDot, LinkedListNode finalDot, LinkedList figure){
        if(currentDot != null && !currentDot.isVisited()){
            currentDot.setVisited(true);
            figure.add(currentDot.getPosition()); //Insert currentNode to LinkedList figure.
            LinkedList dotConnections = currentDot.getConnectionsList(); //Obtain list of connections
            for(LinkedListNode dot = dotConnections.getFirstNode(); dot != null;
                dot = dot.getNextNode()){ //For each node.
                LinkedListNode connection = this.getNode(dot.getPosition());//Obtain connected dot from grid.
                if(connection == finalDot){
                    figure.add(connection.getPosition(), connection.getPosition()); //Insert finalDot to figure list
                    return figure;
                }
                LinkedList finalFigure = searchForFigure(connection, finalDot, figure);
                if(finalFigure != null)return figure;//If figure was made, return list with dots that shape figure
            }figure.deleteLastNode(); //If finalDot was not found in the list of connections of an specific node, delete this last node.
        }
        return null;
    }
    
    /**
     * Resets all visitedBoleans of each node to false.
     */
    public void resetVisitedBooleans(){
        for(LinkedList row = firstRow; row != null; row = row.getNextRow()){
            for(LinkedListNode dot = row.getFirstNode(); dot != null; dot = dot.getNextNode()){
                dot.setVisited(false);
            }
        }
    }
    
    /**
     * Getter for firstRow attribute
     * @return firstRow: LinkedList
     */
    public LinkedList getFirstRow() {
        return firstRow;
    }

    /**
     * Setter for firstRow attribute
     * @param firstRow
     */
    public void setFirstRow(LinkedList firstRow) {
        this.firstRow = firstRow;
    }

    /**
     * Getter for rowSize attribute
     * @return rowSize: int
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     * Setter for rowSize attribute
     * @param rowSize
     */
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    /**
     * Getter for columnSize attribute
     * @return columnSize: int
     */
    public int getColumnSize() {
        return columnSize;
    }

    /**
     * Setter for columnSize attribute
     * @param columnSize
     */
    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    /**
     * Indicates if the grid its empty(true) or not(false).
     * @return boolean
     */
    public boolean isEmpty() {
        return firstRow == null;
    }
    
    public String toString(){
        String str = "Matrix:\n";
        int i = 0;
        for(LinkedList list = firstRow; list != null; list = list.getNextRow()){
            str += i + ": " + list.toString() + "\n";
            i++;
        } return str;
    } 
}