
package dataPackages;

import java.io.Serializable;

/**
 * Auxiliary class used to specify the class of a packet sent through sockets.
 */
public class ClassReference implements Serializable{
    
    /**
     * The name of a data class.
     */
    private String reference;

    /**
     * Returns the reference variable.
     * @return {@link ClassReference#refence}
     */
    public String getReference() {
        return reference;
    }
    
    /**
     * Default consturctor of the class ClassReference. 
     */
    public ClassReference() {
    }
    
    /**
     * Sets the value of the variable reference.
     * @param reference {@link ClassReference#}
     */
    public ClassReference(String reference) {
        this.reference = reference;
    }
}