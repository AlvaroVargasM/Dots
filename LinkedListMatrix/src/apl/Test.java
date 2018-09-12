package apl;

import board.LinkedList;
import board.Grid;

public class Test {
    
    public static void main(String[] args){
        
        Grid grid = new Grid();
        int pos = 0;
        for (int i = 0; i < 5; i++){
            LinkedList list = new LinkedList(i);
            for(int j = 0; j < 5; j++){
                list.insertNode(pos++);
            }
            grid.insertList(list);
        }
        System.out.println(grid.toString());        
    }
}
