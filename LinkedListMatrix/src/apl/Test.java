package apl;

import linkedlist.LinkedList;
import linkedlist.Matrix;

public class Test {
    
    public static void main(String[] args){
        
        Matrix matrix = new Matrix();
        int pos = 0;
        for (int i = 0; i < 5; i++){
            LinkedList list = new LinkedList(i);
            for(int j = 0; j < 5; j++){
                list.insertNode(pos++);
            }
            matrix.insertList(list);
        }
        System.out.println(matrix.toString());        
    }
}
