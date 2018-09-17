package FinalTest;

import java.io.Serializable;

public class ClassReference implements Serializable{
    private String reference;
    
    public ClassReference(String reference){
        this.reference = reference;
    }
    
    public String getReference() {
        return reference;
    }
}