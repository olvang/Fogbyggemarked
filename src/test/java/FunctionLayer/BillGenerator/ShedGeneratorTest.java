package FunctionLayer.BillGenerator;

import Components.*;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
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
    public void losholterGabledTest() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{6});
        WidthComponent orderWidth = new WidthComponent(600);
        ArrayList<BillLine> billLine = ShedGenerator.losholterGabled(categoriesUsedInGenerator, orderWidth.getWidth());


        String expected = "45x95 mm. Reglar ub.";
        int expectedAmount = 14;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
    }

    @Test
    public void losholterSidesTest() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{6});
        int orderShedDepth = 225;

        ArrayList<BillLine> billLine = ShedGenerator.losholterSides(categoriesUsedInGenerator, orderShedDepth);


        String expected = "45x95 mm. Reglar ub.";
        int expectedAmount = 6;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
    }



    @Test
    public void testHingeForDoor() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{27});
        ArrayList<BillLine> lines = ShedGenerator.hingeForDoor(categoriesUsedInGenerator, 3);

        String expectedName = "t hængsel 390 mm";
        int expectedAmount = 6;
        
        assertEquals(expectedAmount, lines.get(0).getAmount());
        assertEquals(expectedName, lines.get(0).getMaterial().getName());
    }

    @Test
    public void testStaldorsgreb() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{26});
        ArrayList<BillLine> lines = ShedGenerator.stalddorsgreb(categoriesUsedInGenerator, 3);

        String expectedName = "stalddørsgreb 50x75";
        int expectedAmount = 3;


        assertEquals(expectedAmount, lines.get(0).getAmount());
        assertEquals(expectedName, lines.get(0).getMaterial().getName());
    }

    @Test
    public void testVinkelBeslag() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{28});
        ArrayList<BillLine> lines = ShedGenerator.vinkelBeslag(categoriesUsedInGenerator, 3);

        String expectedName = "vinkelbeslag 35";
        int expectedAmount = 6;


        assertEquals(expectedAmount, lines.get(0).getAmount());
        assertEquals(expectedName, lines.get(0).getMaterial().getName());
    }

    @Test
    public void testScrewsForInner() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{6, 7, 25});
        Order order = new Order(new DepthComponent(700), new HeightComponent(200), new WidthComponent(700),
                new ShedDepthComponent(225, new DepthComponent(600)),
                new ShedWidthComponent(600, new WidthComponent(700)), 0, true);
        ArrayList<BillLine> lines = ShedGenerator.screwsForInner(categoriesUsedInGenerator, order);

        //LøsholterForGable should contain 14
        //LøsholterForSides should contain 6
        //Total should be 20
        //Lengths should be 270 and 240
        //The calculation should be:
        // ( (14 * 270) + (6 * 240) ) / 7 = ~745
        //When each box contains 300 screws, that should be 3 boxes

        int expectedAmount = 3;
        String expectedName = "4,5 x 50 mm. Skruer 300 stk.";

        assertEquals(expectedAmount, lines.get(0).getAmount());
        assertEquals(expectedName, lines.get(0).getMaterial().getName());
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
