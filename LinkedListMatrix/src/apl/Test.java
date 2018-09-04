package apl;

import linkedlist.LinkedList;

public class Test {
    
    public static void main(String[] args){
        LinkedList ls = new LinkedList(0);
        ls.insertNode();
        ls.insertNode();
        ls.insertNode();
        
        System.out.println(ls.toString());
    }
}
