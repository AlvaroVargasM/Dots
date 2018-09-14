package apl;

import board.LinkedList;
import board.Grid;

public class Test {
    
    public static void main(String[] args){
        
        Grid grid = new Grid(5, 5);
        int pos = 0;
        for (int i = 0; i < grid.getRowSize(); i++){
            LinkedList list = new LinkedList(i);
            for(int j = 0; j < grid.getColumnSize(); j++){
                list.insertDot(pos++, null);
            }
            grid.insertList(list);
        }
        
        
        boolean test1 = grid.makeConnection(0, 1);
        boolean test2 = grid.makeConnection(1, 2);
        boolean test3 = grid.makeConnection(1, 6);
        boolean test4 = grid.makeConnection(6, 7);
        boolean test5 = grid.makeConnection(0, 5);
        boolean test6 = grid.makeConnection(5, 6);
         
        if(test6)System.out.println("si"); 
    }
}
