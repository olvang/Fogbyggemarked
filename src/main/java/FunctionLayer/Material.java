package FunctionLayer;

import Components.MaterialLengthComponent;
import Components.MaterialHeightComponent;
import Components.MaterialWidthComponent;

import java.util.ArrayList;


public class Material {
    private int materialID;
    private MaterialLengthComponent length;
    private MaterialHeightComponent height;
    private MaterialWidthComponent width;
    private String description;
    private int costPrice;
    private int category;
    private String helpText;

    //Used by BillCalculator
    private ArrayList<MaterialLengthComponent> materialsLengths;
    private ArrayList<MaterialHeightComponent> materialsHeight;

    public Material(int materialID, MaterialLengthComponent length, MaterialHeightComponent height, MaterialWidthComponent width, String description, int costPrice, int category, String helpText) {
        this.materialID = materialID;
        this.length = length;
        this.height = height;
        this.width = width;
        this.description = description;
        this.costPrice = costPrice;
        this.category = category;
        this.helpText = helpText;
    }

    public int getMaterialID() {
        return materialID;
    }

    public MaterialLengthComponent getLength() {
        return length;
    }

    public MaterialHeightComponent getHeight() {
        return height;
    }

    public MaterialWidthComponent getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public int getCategory() {
        return category;
    }

    public String getHelpText() {
        return helpText;
    }

    public ArrayList<MaterialLengthComponent> getMaterialsLengths() {
        return materialsLengths;
    }

    public void setMaterialsLengths(ArrayList<MaterialLengthComponent> materialsLengths) {
        this.materialsLengths = materialsLengths;
    }

    public ArrayList<MaterialHeightComponent> getMaterialsHeight() {
        return materialsHeight;
    }

    public void setMaterialsHeight(ArrayList<MaterialHeightComponent> materialsHeight) {
        this.materialsHeight = materialsHeight;
    }
}




