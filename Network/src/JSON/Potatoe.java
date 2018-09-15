package JSON;

import java.io.Serializable;

/**
 * This an example class used for testing sockets and JSON conversion.
 * @author valva
 */
public class Potatoe implements Serializable {
    private int price;
    private int weight;
    private String type;

    /**
     * 
     * @return 
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}