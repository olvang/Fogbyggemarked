package FunctionLayer;

/**
 * Represents a BillLine
 */
public class BillLine {
    private Material material;
    private int amount;
    private String description;

    /**
     * <p>Constructor for an order with shed </p>
     * @param material The material associated with the Billline
     * @param amount The amount of the material
     * @param description The Description of the BillLine
     */
    public BillLine(Material material, int amount, String description) {
        this.material = material;
        this.amount = amount;
        this.description = description;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
