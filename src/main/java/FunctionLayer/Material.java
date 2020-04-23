package FunctionLayer;

import Components.MaterialLengthComponent;
import Components.MaterialHeightComponent;
import Components.MaterialWidthComponent;

import java.util.ArrayList;
import java.util.HashMap;


public class Material {
    private int materialID;
    private MaterialHeightComponent height;
    private String description;
    private int costPrice;
    private int category;
    private String helpText;

    //Used by BillCalculator
    private HashMap<Integer, MaterialLengthComponent> materialLengths;
    private HashMap<Integer, MaterialWidthComponent> materialWidths;

    public Material(int materialID, MaterialLengthComponent length, MaterialHeightComponent height, MaterialWidthComponent width, String description, int costPrice, int category, String helpText) {
        materialLengths = new HashMap<>();
        materialWidths = new HashMap<>();
        addMaterialLength(materialID, length);
        addMaterialWidth(materialID, width);

        this.materialID = materialID;
        this.height = height;
        this.description = description;
        this.costPrice = costPrice;
        this.category = category;
        this.helpText = helpText;
    }

    //---------//
    // Getters //
    //---------//
    public int getMaterialID() {
        return materialID;
    }

    public MaterialHeightComponent getHeight() {
        return height;
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

    public HashMap<Integer, MaterialLengthComponent> getMaterialLengths() {
        return materialLengths;
    }

    public HashMap<Integer, MaterialWidthComponent> getMaterialWidths() {
        return materialWidths;
    }

    //---------//
    // Setters //
    //---------//
    public void setMaterialLengths(HashMap<Integer, MaterialLengthComponent> materialsLengths) {
        this.materialLengths = materialsLengths;
    }
    public void setMaterialWidths(HashMap<Integer, MaterialWidthComponent> materialsWidth) {
        this.materialWidths = materialsWidth;
    }

    //--------//
    // Adders //
    //--------//
    public void addMaterialWidth(int materialID, MaterialWidthComponent widthToAdd) {
        materialWidths.put(materialID, widthToAdd);
    }
    public void addMaterialLength(int materialID, MaterialLengthComponent lengthToAdd) {
        materialLengths.put(materialID, lengthToAdd);
    }
}




