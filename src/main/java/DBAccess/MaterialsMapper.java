package DBAccess;

import Components.MaterialHeightComponent;
import Components.MaterialLengthComponent;
import Components.MaterialWidthComponent;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialsMapper {

    /**
     * <p>Gets all categories in the database</p>
     * @return Arraylist of Category objects
     */
    public static ArrayList<Category> getAllCategories() throws DatabaseException {
        ArrayList<Category> listOfCategories = new ArrayList();
        String SQL = "SELECT `materials_id`, `unit`, `length`, `height`, `width`, `amount`, `name`, `category`.`category_id`, `price`, " +
                "`category`.`decription` FROM `materials` " +
                "LEFT JOIN `material_to_category` ON `materials`.`materials_id` = `material_to_category`.`material_id` " +
                "LEFT JOIN `category` on `material_to_category`.`category_id` = `category`.`category_id` " +
                "ORDER BY `category`.`category_id` ";
        try (Connection con = Connector.connection(); PreparedStatement ps = con.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            fillList(listOfCategories, rs);

        } catch (SQLException e) {
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til materiale databasen: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in MaterialMapper: " + e.getMessage());
        } catch (ValidationFailedException e) {
            throw new DatabaseException("Et element i materiale databasen fejlede validering: " + e.getMessage());
        }
        return listOfCategories;
    }


    /**
     * <p>Gets all categories in the database from a list of id's</p>
     * @param idsToGet A int array with the category id's to pull from the database
     * @return Arraylist of Category objects
     */
    public static ArrayList<Category> getTheseCategories(int[] idsToGet ) throws DatabaseException {
        ArrayList<Category> listOfMaterials = new ArrayList(); //To hold the materials

        StringBuilder queryBuilder = new StringBuilder
                ("SELECT `materials_id`, `unit`, `length`, `height`, `width`, `amount`, `name`, `category`.`category_id`, `price`, " +
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
            fillList(listOfMaterials, rs);
        }  catch (SQLException e) {
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til materiale databasen: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in MaterialMapper: " + e.getMessage());
        } catch (ValidationFailedException e) {
            throw new DatabaseException("Et element i materiale databasen fejlede validering: " + e.getMessage());
        }
        return listOfMaterials;
    }

    /**
     * <p>Used to fill the arraylist of categories with the data pulled from the database </p>
     * @param listToFill The Arraylist of categories to fill with data
     * @param rs The resultset object with data from the database
     */
    private static void fillList(ArrayList<Category> listToFill, ResultSet rs) throws SQLException, ValidationFailedException {
        int previousId = -1;
        while(rs.next() ) {
            int categoryId = rs.getInt("category_id");
            int length = rs.getInt("length");
            int width = rs.getInt("width");
            int materialId = rs.getInt("materials_id");
            int height = rs.getInt("height");
            int amount = rs.getInt("amount");
            int price = rs.getInt("price");
            String name = rs.getString("name");
            String description = rs.getString("decription");
            String unit = rs.getString("unit");

            if(previousId != categoryId) {
                Material mat = new Material(
                        materialId, new MaterialLengthComponent(length), new MaterialHeightComponent(height),
                        new MaterialWidthComponent(width), name, price, categoryId,amount, unit);
                listToFill.add(
                        new Category(categoryId, mat, description));
            }else {
                Material mat = new Material(
                        materialId, new MaterialLengthComponent(length), new MaterialHeightComponent(height),
                        new MaterialWidthComponent(width), name, price, categoryId,amount, unit);
                listToFill.get(listToFill.size() - 1).addMaterial(mat);
            }
            previousId = categoryId;
        }
    }
}
