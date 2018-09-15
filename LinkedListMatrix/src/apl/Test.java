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
                list.insertNode(pos++, null);
            }
            grid.insertList(list);
        }
        
        LinkedList test1 = grid.createConnection(24, 18);
        LinkedList test2 = grid.createConnection(23, 18);
        LinkedList test3 = grid.createConnection(22, 18);
        LinkedList test4 = grid.createConnection(17, 18);
        LinkedList test5 = grid.createConnection(22, 21);
        LinkedList test6 = grid.createConnection(16, 21);
        LinkedList test7 = grid.createConnection(16, 11);
        LinkedList test8 = grid.createConnection(11, 10);
        LinkedList test9 = grid.createConnection(15, 10);
        LinkedList test10 = grid.createConnection(15, 20);
        LinkedList test11 = grid.createConnection(19, 14);
        LinkedList test12 = grid.createConnection(13, 14);
        LinkedList test13 = grid.createConnection(13, 18);
        LinkedList test14 = grid.createConnection(19, 18);
        LinkedList test15 = grid.createConnection(13, 9);
        LinkedList test16 = grid.createConnection(9, 14);
        
        if(test1 != null)System.out.println(test1.toString()); 
        if(test2 != null)System.out.println(test2.toString()); 
        if(test3 != null)System.out.println(test3.toString()); 
        if(test4 != null)System.out.println(test4.toString()); 
        if(test5 != null)System.out.println(test5.toString()); 
        if(test6 != null)System.out.println(test6.toString()); 
        if(test7 != null)System.out.println(test7.toString()); 
        if(test8 != null)System.out.println(test8.toString()); 
        if(test9 != null)System.out.println(test9.toString()); 
        if(test10 != null)System.out.println(test10.toString()); 
        if(test11 != null)System.out.println(test11.toString()); 
        if(test12 != null)System.out.println(test12.toString()); 
        if(test13 != null)System.out.println(test13.toString()); 
        if(test14 != null)System.out.println(test14.toString()); 
        if(test15 != null)System.out.println(test15.toString()); 
        if(test16 != null)System.out.println(test16.toString()); 
    }
}