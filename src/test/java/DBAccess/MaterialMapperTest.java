package DBAccess;


import Components.MaterialLengthComponent;
import Components.MaterialWidthComponent;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Material;
import org.junit.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MaterialMapperTest {

    @Test
    public void testGetAllMaterialsByContent() throws SQLException, ValidationFailedException, ClassNotFoundException {
        int indexToCheck = 5;
        String expected = "45x95 mm. Reglar ub.";
        ArrayList<Material> test = MaterialsMapper.getAllMaterials();

        assertEquals(expected, test.get(5).getDescription());
    }

    @Test
    public void testGetTheseMaterialsBySize() throws SQLException, ValidationFailedException, ClassNotFoundException {
        int[] ids = new int[] {14,1,17,3,24,5,15,13,8,28};

        ArrayList<Material> test = MaterialsMapper.getTheseMaterials(ids);
        //For visualisation purposes
       /* for (Material mat : test) {
            System.out.println(mat.getDescription());
        };*/
        assertEquals(ids.length, test.size());
    }

    @Test
    public void testGetTheseMaterialsBySizeVariations() throws SQLException, ValidationFailedException, ClassNotFoundException {
        int expectedLength1 = 600;
        int expectedLength2 = 360;
        int expectedWidth = 109;

        int[] ids = new int[] {3,8,15,6};

        ArrayList<Material> test = MaterialsMapper.getTheseMaterials(ids);

        //For visualisation purposes
/*        for (Material mat : test) {
            System.out.println(mat.getDescription() + ": ");
            for(MaterialLengthComponent len : mat.getMaterialLengths()) {
                System.out.println("length: " + len.getLength());
            }
            for(MaterialWidthComponent wid : mat.getMaterialWidth()) {
                System.out.println("width: " + wid.getWidth());
            }
            System.out.println("");
        }*/

        assertEquals(expectedLength1, test.get(3).getMaterialLengths().get(0).getLength());
        assertEquals(expectedLength2, test.get(3).getMaterialLengths().get(1).getLength());
        assertEquals(expectedWidth, test.get(3).getMaterialWidth().get(0).getWidth());
        assertEquals(expectedWidth, test.get(3).getMaterialWidth().get(1).getWidth());
    }

    
}
