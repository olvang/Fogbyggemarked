package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import org.junit.Test;
import org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CarportGeneratorTest {

    @Test
    public void testOverSternBredderFront() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{3});
        Order order = new Order(new DepthComponent(10), new HeightComponent(10), new WidthComponent(1000), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.overSternBredderFront(categoriesUsedInGenerator, order);


        String expected = "25x125mm. trykimp. Brædt";
        int expectedAmount = 3;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
    }
    @Test
    public void testRemInSidesCarportUnder600() throws Exception {

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{8});
        Order order = new Order(new DepthComponent(500), new HeightComponent(10), new WidthComponent(550), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepth(),order.getWidth());

        int expectedAmount = 2;
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }
    @Test
    public void testRemInSidesCarportAbove600() throws Exception {

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{8});
        Order order = new Order(new DepthComponent(500), new HeightComponent(10), new WidthComponent(780), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepth(),order.getWidth());

        int expectedAmount = 3;
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }
    @Test
    public void testRemInSidesCarportAbove600AndAbove600() throws Exception {

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{8});
        Order order = new Order(new DepthComponent(650), new HeightComponent(10), new WidthComponent(780), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepth(),order.getWidth());

        int expectedAmount = 6;
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }
    @Test
    public void testunderSternsBredderSides() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{2});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(10), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.underSternsBredderSides(categoriesUsedInGenerator, order.getDepth());

        String expected = "25x200 mm. trykimp. Brædt";
        int expectedAmount1 = 6;

        assertEquals(expected, billLine.get(0).getMaterial().getName() );
        assertEquals(expectedAmount1, billLine.get(0).getAmount());

    }
    @Test
    public void underSternsBredderFrontAndBackTest() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{1});
        WidthComponent orderWidth = new WidthComponent(600);
        ArrayList<BillLine> billLine = CarportGenerator.underSternsBredderFrontAndBack(categoriesUsedInGenerator, orderWidth);


        String expected = "25x200 mm. trykimp. Brædt";
        int expectedAmount = 4;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
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
