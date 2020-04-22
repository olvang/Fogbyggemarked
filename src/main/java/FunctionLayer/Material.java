package FunctionLayer;

import Components.MaterialLengthComponent;
import Components.MaterialHeightComponent;
import Components.MaterialWidthComponent;

import java.util.ArrayList;


public class Material {
    private int materialID;
    private MaterialHeightComponent height;
    private String description;
    private int costPrice;
    private int category;
    private String helpText;

    //Used by BillCalculator
    private ArrayList<MaterialLengthComponent> materialLengths;
    private ArrayList<MaterialWidthComponent> materialWidths;

    public Material(int materialID, MaterialLengthComponent length, MaterialHeightComponent height, MaterialWidthComponent width, String description, int costPrice, int category, String helpText) {
        materialLengths = new ArrayList<>();
        materialWidths = new ArrayList<>();
        addMaterialLength(length);
        addMaterialWidth(width);

        this.materialID = materialID;
        this.height = height;
        this.description = description;
        this.costPrice = costPrice;
        this.category = category;
        this.helpText = helpText;
    }

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

    public ArrayList<MaterialLengthComponent> getMaterialLengths() {
        return materialLengths;
    }

    public void setMaterialLengths(ArrayList<MaterialLengthComponent> materialsLengths) {
        this.materialLengths = materialsLengths;
    }

    public ArrayList<MaterialWidthComponent> getMaterialWidth() {
        return materialWidths;
    }

    public void setMaterialWidth(ArrayList<MaterialWidthComponent> materialsWidth) {
        this.materialWidths = materialsWidth;
    }

    public void addMaterialWidth(MaterialWidthComponent widthToAdd) {
        materialWidths.add(widthToAdd);
    }

    public void addMaterialLength(MaterialLengthComponent lengthToAdd) {
        materialLengths.add(lengthToAdd);
    }
}




