package apl;

import linkedlist.LinkedList;
import linkedlist.Matrix;

public class Test {
    
    public static void main(String[] args){
        LinkedList ls1 = new LinkedList(0);
        ls1.insertNode(0);
        ls1.insertNode(1);
        ls1.insertNode(2);
        
        LinkedList ls2 = new LinkedList(1);
        ls2.insertNode(3);
        ls2.insertNode(4);
        ls2.insertNode(5);
        
        LinkedList ls3 = new LinkedList(2);
        ls3.insertNode(6);
        ls3.insertNode(7);
        ls3.insertNode(8);
        
        Matrix matrix = new Matrix();
        matrix.insertList(ls1);
        matrix.insertList(ls2);
        matrix.insertList(ls3);
        
        System.out.println(matrix.toString());
    }
}
