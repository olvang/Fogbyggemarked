package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import org.junit.Test;

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


        String expected = "25x125 mm. trykimp. Brædt";
        int expectedAmount = 2;

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
        Order order = new Order(new DepthComponent(1500), new HeightComponent(10), new WidthComponent(10), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.sternsBredderSides(categoriesUsedInGenerator, order.getDepth());

        String expected = "25x200 mm. trykimp. Brædt";
        int expectedAmount1 = 4;
        int exspectedLength1 = 540;

        int expectedAmount2 = 2;
        int exspectedLength2 = 360;

        System.out.println(billLine.get(1).getMaterial().getLength());
        System.out.println(billLine.get(1).getAmount());
        System.out.println(billLine.get(0).getMaterial().getLength());
        System.out.println(billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName());

        assertEquals(expectedAmount1, billLine.get(0).getAmount());
        assertEquals(expectedAmount2, billLine.get(1).getAmount());

        assertEquals(exspectedLength1,billLine.get(1).getMaterial().getLength());
        assertEquals(exspectedLength2,billLine.get(0).getMaterial().getLength());

    }
    @Test
    public void testoverSternsBredderSides() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{4});
        Order order = new Order(new DepthComponent(780), new HeightComponent(10), new WidthComponent(10), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.sternsBredderSides(categoriesUsedInGenerator, order.getDepth());

        String expected = "25x125 mm. trykimp. Brædt";
        int expectedAmount1 = 2;
        int exspectLength1 = 540;

        int exspectedAmount2 = 2;
        int exspectedLength2 = 360;

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount1, billLine.get(0).getAmount());
        assertEquals(exspectLength1, billLine.get(0).getMaterial().getLength());

        assertEquals(exspectedAmount2, billLine.get(1).getAmount());
        assertEquals(exspectedLength2, billLine.get(1).getMaterial().getLength());
    }
    @Test
    public void testSperOnRem() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{10});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(270), 0 ,false);
        BillLine billLine = CarportGenerator.sperOnRem(categoriesUsedInGenerator, order).get(0);

        String expected = "45x195 mm. spærtræ ubh.";
        int expectedAmount = 19;

        assertEquals(expectedAmount, billLine.getAmount());
        assertEquals(expected, billLine.getMaterial().getName());
    }


    @Test
    public void underSternsBredderFrontAndBackTest() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{1});
        WidthComponent orderWidth = new WidthComponent(600);
        ArrayList<BillLine> billLine = CarportGenerator.underSternsBredderFrontAndBack(categoriesUsedInGenerator, orderWidth);


        String expected = "25x200 mm. trykimp. Brædt";
        int expectedAmount = 3;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
    }


    @Test
    public void testPerforatedBand() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{17});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(500), 0 ,false);
        ArrayList<BillLine> billLine = CarportGenerator.perforatedBand(categoriesUsedInGenerator, order);

        String expected = "hulbånd 1x20 mm. 10 mtr.";
        int expectedAmount = 4;

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount, billLine.get(0).getAmount());
    }

    @Test
    public void testboltsForRemOnPost() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{22});
        int amountOfPosts = 6;
        int expectedNoShed = 12;
        int expectedWithShed = 16;
        String expectedName = "bræddebolt 10 x 120 mm.";

        ArrayList<BillLine> billLineNoShed = CarportGenerator.boltsForRemOnPost(categoriesUsedInGenerator, amountOfPosts,false);
        ArrayList<BillLine> billLineWithShed = CarportGenerator.boltsForRemOnPost(categoriesUsedInGenerator, amountOfPosts,true);



        assertEquals(expectedName, billLineNoShed.get(0).getMaterial().getName());
        assertEquals(expectedNoShed, billLineNoShed.get(0).getAmount());
        assertEquals(expectedName, billLineWithShed.get(0).getMaterial().getName());
        assertEquals(expectedWithShed, billLineWithShed.get(0).getAmount());
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
