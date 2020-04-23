package DBAccess;

import Components.MaterialHeightComponent;
import Components.MaterialLengthComponent;
import Components.MaterialWidthComponent;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialsMapper {

    public static ArrayList getAllMaterials() throws ValidationFailedException, SQLException, ClassNotFoundException {
        ArrayList<Material> listOfMaterials = new ArrayList();
        String SQL = "SELECT `materials_id`, `length`, `height`, `width`, `amount`, `name`, `category`.`category_id`, `price`, " +
                "`category`.`decription` FROM `materials` " +
                "LEFT JOIN `material_to_category` ON `materials`.`materials_id` = `material_to_category`.`material_id` " +
                "LEFT JOIN `category` on `material_to_category`.`category_id` = `category`.`category_id` " +
                "ORDER BY `category`.`category_id` ";
        try (Connection con = Connector.connection(); PreparedStatement ps = con.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            int previousId = -1;
            while(rs.next() ) {
                int categoryId = rs.getInt("category_id");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int id = rs.getInt("materials_id");

                //If the current material is of same category as the previous one,
                //all the information needed is already present and there is no need to pull it again
                //On the other hand, we pull the length and width and add them to the list of this category's different
                //available sizes. This will always be the most recent material in the list.
                if(categoryId == previousId) {
                    listOfMaterials.get(listOfMaterials.size() - 1).addMaterialWidth(id, new MaterialWidthComponent(width));
                    listOfMaterials.get(listOfMaterials.size() - 1).addMaterialLength(id, new MaterialLengthComponent(length));
                }else {
                    int height = rs.getInt("height");
                    int amount = rs.getInt("amount");
                    int price = rs.getInt("price");
                    String name = rs.getString("name");
                    String description = rs.getString("decription");
                    //TODO When price, category and description text can be saved in DB, update this instantiation
                    listOfMaterials.add
                            (new Material(id, new MaterialLengthComponent(length), new MaterialHeightComponent(height),
                                    new MaterialWidthComponent(width), name, price, categoryId, description));
                }
                previousId = categoryId;
            }
        } catch (SQLException e) {
            throw new SQLException("SQLError in MaterialMapper " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("ClassNotFoundError in MaterialMapper " + e.getMessage());
        } catch (ValidationFailedException e) {
            throw new ValidationFailedException("Validation failed: " + e.getMessage());
        }
        return listOfMaterials;
    }

    
    public static ArrayList getTheseMaterials(int[] idsToGet ) throws SQLException, ValidationFailedException, ClassNotFoundException {
        ArrayList<Material> listOfMaterials = new ArrayList(); //To hold the materials

        StringBuilder queryBuilder = new StringBuilder
                ("SELECT `materials_id`, `length`, `height`, `width`, `amount`, `name`, `category`.`category_id`, `price`, " +
                        "`category`.`decription` FROM `materials` " +
                        "LEFT JOIN `material_to_category` ON `materials`.`materials_id` = `material_to_category`.`material_id` " +
                        "LEFT JOIN `category` on `material_to_category`.`category_id` = `category`.`category_id` " +
                        "WHERE `category`.`category_id` IN (");

        //Goes through list of ids and adds each to the query, separated by a comma-
        for(int i = 0; i < idsToGet.length; i++) {
            if(i == 0) {
                queryBuilder.append(idsToGet[i]);
            } else {
                queryBuilder.append(", " + idsToGet[i]);
            }
        }
        queryBuilder.append(") ORDER BY `category`.`category_id`");
        String SQL = queryBuilder.toString();

        try (Connection con = Connector.connection(); PreparedStatement ps = con.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            int previousId = -1;
            while(rs.next() ) {
                int categoryId = rs.getInt("category_id");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int id = rs.getInt("materials_id");

                //If the current material is of same category as the previous one,
                //all the information needed is already present and there is no need to pull it again
                //On the other hand, we pull the length and width and add them to the list of this category's different
                //available sizes. This will always be the most recent material in the list.
                if(categoryId == previousId) {
                    listOfMaterials.get(listOfMaterials.size() - 1).addMaterialWidth(id, new MaterialWidthComponent(width));
                    listOfMaterials.get(listOfMaterials.size() - 1).addMaterialLength(id, new MaterialLengthComponent(length));
                }else {
                    int height = rs.getInt("height");
                    int amount = rs.getInt("amount");
                    int price = rs.getInt("price");
                    String name = rs.getString("name");
                    String description = rs.getString("decription");
                    //TODO When price, category and description text can be saved in DB, update this instantiation
                    listOfMaterials.add
                            (new Material(id, new MaterialLengthComponent(length), new MaterialHeightComponent(height),
                                    new MaterialWidthComponent(width), name, price, categoryId, description));
                }
                previousId = categoryId;
            }
        } catch (SQLException e) {
            throw new SQLException("SQLError in MaterialMapper " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("ClassNotFoundError in MaterialMapper " + e.getMessage());
        } catch (ValidationFailedException e) {
            throw new ValidationFailedException("Validation failed: " + e.getMessage());
        }
        return listOfMaterials;
    }
}
