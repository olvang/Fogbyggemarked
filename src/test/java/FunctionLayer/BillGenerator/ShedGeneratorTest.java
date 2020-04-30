package FunctionLayer.BillGenerator;

import Components.*;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
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
    @Test
    public void testRemInSidesShedShort() throws Exception {

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{9});
        Order order = new Order(new DepthComponent(500),
                new HeightComponent(10),
                new WidthComponent(550),
                new ShedDepthComponent("200","500"),
                new ShedWidthComponent("200","500"),
                0 ,false);
        ArrayList<BillLine> billLine = ShedGenerator.RemInSidesShed(categoriesUsedInGenerator, order.getShedDepth());

        int exspectedAmount = 1;
        int exspectedLength = 480;

        assertEquals(exspectedAmount,billLine.get(0).getAmount());
        assertEquals(exspectedLength,billLine.get(0).getMaterial().getLength());
    }

    @Test
    public void testRemInSidesShedLong() throws Exception {

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{9});
        Order order = new Order(new DepthComponent(500),
                new HeightComponent(10),
                new WidthComponent(550),
                new ShedDepthComponent("500","550"),
                new ShedWidthComponent("500","550"),
                0 ,false);
        ArrayList<BillLine> billLine = ShedGenerator.RemInSidesShed(categoriesUsedInGenerator, order.getShedDepth());

        int exspectedAmount = 2;
        int exspectedLength = 600;

        assertEquals(exspectedAmount,billLine.get(0).getAmount());
        assertEquals(exspectedLength,billLine.get(0).getMaterial().getLength());
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
