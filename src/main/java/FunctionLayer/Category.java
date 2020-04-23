package FunctionLayer;

import java.util.ArrayList;

public class Category {
    private int categoryId;
    private ArrayList<Material> materials;
    private String description;

    public Category(int categoryId, Material material, String description) {
        materials = new ArrayList<>();

        this.categoryId = categoryId;
        materials.add(material);
        this.description = description;
    }

    //---------//
    // Getters //
    //---------//
    public int getCategoryId() {
        return categoryId;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<Material> getMaterials() {
        return materials;
    }
    public Material getMaterialByMaterialId(int materialId) {
        for(Material mat : materials) {
            if (mat.getMaterialID() == materialId) {
                return mat;
            }
        }
        return null;
    }
    public Material getMaterialAtIndex(int index) {
        return materials.get(index);
    }
    public Material getLongestMaterial() {
        Material longest = materials.get(0);
        for(int i = 1; i < materials.size(); i++) {
            if (materials.get(i).getLength().getLength() > longest.getLength().getLength()) {
                longest = materials.get(i);
            }
        }
        return longest;
    }
    public Material getWidestMaterial() {
        Material widest = materials.get(0);
        for(int i = 1; i < materials.size(); i++) {
            if (materials.get(i).getWidth().getWidth() > widest.getWidth().getWidth()) {
                widest = materials.get(i);
            }
        }
        return widest;
    }
    public Material getHighestMaterial() {
        Material highest = materials.get(0);
        for(int i = 1; i < materials.size(); i++) {
            if (materials.get(i).getHeight().getHeight() > highest.getHeight().getHeight()) {
                highest = materials.get(i);
            }
        }
        return highest;
    }

    //---------//
    // Setters //
    //---------//
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //--------//
    // Adders //
    //--------//
    public void addMaterial(Material material) {
            materials.add(material);
    }


}

