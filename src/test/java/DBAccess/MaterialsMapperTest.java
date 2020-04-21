package DBAccess;


import FunctionLayer.Materials;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MaterialsMapperTest {

    @Test
    public void testGetAllMaterialsByContent() {
        int indexToCheck = 5;
        String expected = "45x95 mm. Reglar ub.";
        ArrayList<Materials> test = MaterialsMapper.getAllMaterials();

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

        ArrayList<Materials> test = MaterialsMapper.getTheseMaterials(ids);
        for (Materials mat : test) {
            System.out.println(mat.getDescription());
        };
        assertEquals(ids.size(), test.size());
    }
}
