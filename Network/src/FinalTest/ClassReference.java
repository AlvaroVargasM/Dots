package FinalTest;

import java.io.Serializable;

public class ClassReference implements Serializable{
    private String reference;
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getReference() {
        return reference;
    }
}