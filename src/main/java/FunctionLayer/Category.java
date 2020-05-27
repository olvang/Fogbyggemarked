package FunctionLayer;

import java.util.ArrayList;

/**
 * Represents a Category
 */
public class Category {
    private int categoryId;
    private ArrayList<Material> materials;
    private String description;

    /**
     * <p>Constructor for a Category </p>
     * @param categoryId The category ID
     * @param material The material as a material object (added to the arraylist of materials on the Category object)
     * @param description The description of the material
     */
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

    /**
     * <p>Gets a material from a material ID within the Arraylist of materials</p>
     * @param materialId The material id to find
     * @return Returns a material if found, else returns Null
     */
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

