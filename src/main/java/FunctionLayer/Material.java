package FunctionLayer;

import Components.HeightComponent;
import Components.MaterialLengthComponent;
import Components.MaterialHeightComponent;
import Components.MaterialWidthComponent;

/**
 * Represents a material
 */
public class Material {
    private int materialID;
    private MaterialHeightComponent height;
    private MaterialWidthComponent width;
    private MaterialLengthComponent length;
    private String name;
    private int costPrice;
    private int category;
    private int amount;
    private String unit;


    /**
     * <p>Constructor for a material </p>
     * @param materialID The Material ID
     * @param length The Material length in cm
     * @param height The Material height in cm
     * @param width The Material width in cm
     * @param name The Material name
     * @param costPrice The the price that it costs without a profit being added
     * @param category The category ID number
     * @param amount How many amounts per material (Usually 1)
     * @param unit The unit of the material (Stk, pakke, rulle)
     */
    public Material(int materialID, MaterialLengthComponent length, MaterialHeightComponent height, MaterialWidthComponent width, String name, int costPrice, int category, int amount, String unit) {
        this.materialID = materialID;
        this.height = height;
        this.width = width;
        this.length = length;
        this.name = name;
        this.costPrice = costPrice;
        this.category = category;
        this.amount = amount;
        this.unit = unit;
    }

    //---------//
    // Getters //
    //---------//
    public int getMaterialID() {
        return materialID;
    }
    public int getHeight() {
        return height.getHeight();
    }
    public int getWidth() {
        return width.getWidth();
    }
    public int getLength() {
        return length.getLength();
    }
    public MaterialHeightComponent getHeightComponent() {
        return height;
    }
    public MaterialWidthComponent getWidthtComponent() {
        return width;
    }
    public MaterialLengthComponent getLengthComponent() {
        return length;
    }
    public String getName() {
        return name;
    }
    public int getCostPrice() {
        return costPrice;
    }
    public int getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}




