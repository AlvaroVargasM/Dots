package dataPackages;

import java.io.Serializable;

public class ClassReference implements Serializable{
    private String reference;

    public String getReference() {
        return reference;
    }
    
    public ClassReference() {
    }
    
    public ClassReference(String reference) {
        this.reference = reference;
    }
}