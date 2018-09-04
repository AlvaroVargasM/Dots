package apl;

import linkedlist.LinkedList;
import linkedlist.Matrix;

public class Test {
    
    public static void main(String[] args){
        
        Matrix matrix = new Matrix();
        for (int i = 0; i < 3; i++){
            LinkedList list = new LinkedList(i);
            for(int j = 0; j < 3; j++){
                list.insertNode(j);
            }
            matrix.insertList(list);
        }
        System.out.println(matrix.toString());
    }
}
