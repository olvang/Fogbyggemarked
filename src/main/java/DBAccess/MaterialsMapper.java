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

    public static ArrayList getAllMaterials() {
        ArrayList<Material> listOfMaterials = new ArrayList();
        String SQL = "SELECT materials_id, length, height, width, amount, name FROM materials";
        try (Connection con = Connector.connection(); PreparedStatement ps = con.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next() ) {
                int id = rs.getInt("materials_id");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                int width = rs.getInt("width");
                int amount = rs.getInt("amount");
                String name = rs.getString("name");
                //TODO When price, category and description text can be saved in DB, update this instantiation
                listOfMaterials.add
                        (new Material(id, new MaterialLengthComponent(length), new MaterialHeightComponent(height),
                                new MaterialWidthComponent(width), name, -1, -1, "temp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ValidationFailedException e) {
            e.printStackTrace();
        }
        return listOfMaterials;
    }

    public static ArrayList getTheseMaterials(List<Integer> idsToGet ) {
        ArrayList<Material> listOfMaterials = new ArrayList(); //To hold the materials

        //Goes through list of ids and adds each to the query, separated by a comma-
        String SQL = "SELECT materials_id, length, height, width, amount, name FROM materials WHERE materials_id IN (";
        for(int i = 0; i < idsToGet.size(); i++) {
            if(i == 0) {
                SQL += idsToGet.get(i);
            } else {
                SQL += ", " + idsToGet.get(i);
            }
        }
        SQL += ")"; 

        try (Connection con = Connector.connection(); PreparedStatement ps = con.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next() ) {
                int id = rs.getInt("materials_id");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                int width = rs.getInt("width");
                int amount = rs.getInt("amount");
                String name = rs.getString("name");
                //TODO When price, category and description text can be saved in DB, update this instantiation
                listOfMaterials.add
                        (new Material(id, new MaterialLengthComponent(length), new MaterialHeightComponent(height),
                                new MaterialWidthComponent(width), name, -1, -1, "temp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ValidationFailedException e) {
            e.printStackTrace();
        }
        return listOfMaterials;
    }
}
