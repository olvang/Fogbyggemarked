package FunctionLayer.BillGenerator;

import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.LogicFacade;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ShedGeneratorTest {
    @Test
    public void TestzOnBackOfDoor() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{5});
        int expected = 1;
        String expectedName = "38x73 mm. Lægte ubh.";

        ArrayList<BillLine> billLineNoShed = ShedGenerator.zOnBackOfDoor(categoriesUsedInGenerator);



        assertEquals(expectedName, billLineNoShed.get(0).getMaterial().getName());
        assertEquals(expected, billLineNoShed.get(0).getAmount());
    }

    private ArrayList<Category> getCategoriesAvailable(int[] categoryIdsUsedInGenerator) throws Exception{
        ArrayList<Category> categoriesAvailable = LogicFacade.getTheseCategories(categoryIdsUsedInGenerator);

        //Gets a list with only the categories needed
        return getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
    }

    private ArrayList<Category> getCategoriesUsedInGenerator(int[] categoryIdsUsedInGenerator, ArrayList<Category> categoriesAvailable) {
        ArrayList<Category> categoriesUsedInGenerator = new ArrayList<>();

        //Loop all categories needed in Generator
        for(int categoryID : categoryIdsUsedInGenerator){

            //Loop all materials available to search for the categoryID
            for(Category category : categoriesAvailable){
                if(category != null && category.getCategoryId() == categoryID){
                    categoriesUsedInGenerator.add(category);

                    //When the material has been found exit
                    break;
                }
            }
        }
        return categoriesUsedInGenerator;
    }
}
