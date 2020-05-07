package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.InclineComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FlatRoofGeneratorTest extends TestDataSetup {

    @Test
    public void testwaterBoardOnSternSides() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{13});
        Order order = new Order(new DepthComponent(780), new HeightComponent(220), new WidthComponent(600),
                new InclineComponent(0) ,false);
        ArrayList<BillLine> billLine = FlatRoofGenerator.waterBoardOnSternSides(categoriesUsedInGenerator, order.getDepth());

        String expected = "trykimp. Brædt";
        int expectedAmount1 = 2;
        int exspectedLength1 = 5400;

        int exspectedAmount2 = 2;
        int exspectedLength2 = 3600;

        assertEquals(expected, billLine.get(0).getMaterial().getName() );

        assertEquals(expectedAmount1, billLine.get(0).getAmount());
        assertEquals(exspectedLength1, billLine.get(0).getMaterial().getLength());

        assertEquals(exspectedAmount2, billLine.get(1).getAmount());
        assertEquals(exspectedLength2, billLine.get(1).getMaterial().getLength());
    }

    @Test
    public void testwaterBoardOnSternFront() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{14});
        Order order = new Order(new DepthComponent(780), new HeightComponent(220), new WidthComponent(600),
                new InclineComponent(0) ,false);
        ArrayList<BillLine> billLine = FlatRoofGenerator.waterBoardOnSternFront(categoriesUsedInGenerator, order);

        String expected = "trykimp. Brædt";
        int expectedAmount1 = 2;

        assertEquals(expected, billLine.get(0).getMaterial().getName() );
        assertEquals(expectedAmount1, billLine.get(0).getAmount());
    }

    @Test
    public void testRoofPanels() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{15});
        Order order = new Order(new DepthComponent(1234), new HeightComponent(220), new WidthComponent(600),
                new InclineComponent(0) ,false);
        ArrayList<BillLine> billLine = FlatRoofGenerator.roofPanels(categoriesUsedInGenerator, order);

        String expected = "Plastmo Ecolite blåtonet";
        int expectedAmountOf600 = 12;
        int expectedAmountOf360 = 6;

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmountOf600, billLine.get(0).getAmount());
        assertEquals(expectedAmountOf360, billLine.get(1).getAmount());
    }

    @Test
    public void testScrewsForRoofPanels() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{16});
        Order order = new Order(new DepthComponent(780), new HeightComponent(220), new WidthComponent(600),
                new InclineComponent(0) ,false);
        ArrayList<BillLine> billLine = FlatRoofGenerator.screwsForRoofPanels(categoriesUsedInGenerator, order);

        String expected = "plastmo bundskruer 200 stk.";
        int expectedAmountOfPacks = 3;

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmountOfPacks, billLine.get(0).getAmount());
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
