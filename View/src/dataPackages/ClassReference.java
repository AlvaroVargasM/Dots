package dataPackages;

import java.io.Serializable;

public class ClassReference implements Serializable{
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}