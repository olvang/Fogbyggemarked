package DBAccess;


import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Material;
import org.junit.Test;

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

    @Test public void testGetTheseMaterialsBySize() throws SQLException, ValidationFailedException, ClassNotFoundException {
        int[] ids = new int[] {14,1,17,3,24,5,15,13,8,28};

        ArrayList<Material> test = MaterialsMapper.getTheseMaterials(ids);
        for (Material mat : test) {
            System.out.println(mat.getDescription());
        };
        assertEquals(ids.length, test.size());
    }

    
}
