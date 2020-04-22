package DBAccess;


import FunctionLayer.Material;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MaterialMapperTest {

    @Test
    public void testGetAllMaterialsByContent() {
        int indexToCheck = 5;
        String expected = "45x95 mm. Reglar ub.";
        ArrayList<Material> test = MaterialsMapper.getAllMaterials();

        assertEquals(expected, test.get(5).getDescription());
    }

    @Test public void testGetTheseMaterialsBySize() {
        ArrayList<Integer> ids = new ArrayList<Integer>() {
            {
                add(14);
                add(1);
                add(17);
                add(3);
                add(24);
                add(5);
                add(15);
                add(13);
                add(8);
                add(28);
            }
        };

        ArrayList<Material> test = MaterialsMapper.getTheseMaterials(ids);
        for (Material mat : test) {
            System.out.println(mat.getDescription());
        };
        assertEquals(ids.size(), test.size());
    }
}
