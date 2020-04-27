package FunctionLayer;

import Components.HeightComponent;
import Components.MaterialLengthComponent;
import Components.MaterialHeightComponent;
import Components.MaterialWidthComponent;


public class Material {
    private int materialID;
    private MaterialHeightComponent height;
    private MaterialWidthComponent width;
    private MaterialLengthComponent length;
    private String name;
    private int costPrice;
    private int category;
    private int amount;

    //Used by BillCalculator
    //private HashMap<Integer, MaterialLengthComponent> materialLengths;
    //private HashMap<Integer, MaterialWidthComponent> materialWidths;

    public Material(int materialID, MaterialLengthComponent length, MaterialHeightComponent height, MaterialWidthComponent width, String name, int costPrice, int category, int amount) {
        this.materialID = materialID;
        this.height = height;
        this.width = width;
        this.length = length;
        this.name = name;
        this.costPrice = costPrice;
        this.category = category;
        this.amount = amount;
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
}




