package FunctionLayer.BillGenerator;

import Components.*;
import FunctionLayer.*;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CarportGeneratorTest extends TestDataSetup {

    @Test
    public void testOverSternBredderFront() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{3});
        Order order = new Order(new DepthComponent(10), new HeightComponent(10), new WidthComponent(1000),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.overSternBredderFront(categoriesUsedInGenerator, order);


        String expected = "trykimp. Brædt";
        int expectedAmount = 2;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
    }
    @Test
    public void testUniversalBeslagRight() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        Order order = new Order(new DepthComponent(780), new HeightComponent(10), new WidthComponent(540),
                new InclineComponent(0),false,customer);

        ArrayList<Category> categoriesUsedInGeneratorRemme = getCategoriesAvailable(new int[]{8});
        ArrayList<BillLine> billLineRemme = CarportGenerator.RemInSidesCarport(categoriesUsedInGeneratorRemme, order.getDepthComponent(),order.getWidthComponent());

        ArrayList<Category> categoriesUsedInGeneratorSper = getCategoriesAvailable(new int[]{10});
        ArrayList<BillLine> billLineSper = CarportGenerator.sperOnRem(categoriesUsedInGeneratorSper, order);

        int Remme = billLineRemme.get(0).getAmount();
        int Sper = billLineSper.get(0).getAmount();

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{18});
        ArrayList<BillLine> billLineBeslag = CarportGenerator.UniversalBeslagRight(categoriesUsedInGenerator, Sper, Remme);

        assertEquals( 4, Remme);
        assertEquals(15,Sper);

        int exspectedBeslag = 15;

        assertEquals(exspectedBeslag,billLineBeslag.get(0).getAmount());

    }
    @Test
    public void testRemInSidesCarportUnder600() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{8});
        Order order = new Order(new DepthComponent(500), new HeightComponent(10), new WidthComponent(550),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepthComponent(),order.getWidthComponent());

        int expectedAmount = 2;
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }
    @Test
    public void testRemInSidesCarportAbove600() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{8});
        Order order = new Order(new DepthComponent(500), new HeightComponent(10), new WidthComponent(780),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepthComponent(),order.getWidthComponent());

        int expectedAmount = 3;
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }
    @Test
    public void testRemInSidesCarportAbove600AndAbove600() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{8});
        Order order = new Order(new DepthComponent(650), new HeightComponent(10), new WidthComponent(780),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepthComponent(),order.getWidthComponent());

        int expectedAmount = 6;
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }

    @Test
    public void testunderSternsBredderSides() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{2});
        Order order = new Order(new DepthComponent(1500), new HeightComponent(10), new WidthComponent(10),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.sternsBredderSides(categoriesUsedInGenerator, order.getDepthComponent());

        String expected = "25x150 mm. trykimp. Bræt";
        int expectedAmount = 4;
        int expectedLength = 6000;

        assertEquals(expected, billLine.get(0).getMaterial().getName());

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expectedLength,billLine.get(0).getMaterial().getLength());

    }

    @Test
    public void testoverSternsBredderSides() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{4});
        Order order = new Order(new DepthComponent(780), new HeightComponent(10), new WidthComponent(10),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.sternsBredderSides(categoriesUsedInGenerator, order.getDepthComponent());

        String expected = "trykimp. Brædt";
        int expectedAmount1 = 2;
        int exspectLength1 = 5400;

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount1, billLine.get(0).getAmount());
        assertEquals(exspectLength1, billLine.get(0).getMaterial().getLength());
    }

    @Test
    public void testSperOnRem() throws Exception {
        //These three lines need to be in every test, but change the target method in CarportGenerator,
        // the order to fit your testdata and the categories needed
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{10});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(270),
                new InclineComponent(0) ,false,customer);
        BillLine billLine = CarportGenerator.sperOnRem(categoriesUsedInGenerator, order).get(0);

        String expected = "spærtræ ubh.";
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


        String expected = "25x150 mm. trykimp. Bræt";
        int expectedAmount = 2;

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expected, billLine.get(0).getMaterial().getName() );
    }


    @Test
    public void testPerforatedBand() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{17});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(500),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.perforatedBand(categoriesUsedInGenerator, order);

        String expected = "hulbånd 1x20 mm. 10 mtr.";
        int expectedAmount = 4;

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount, billLine.get(0).getAmount());
    }

    @Test

    public void testScrewsForSternAndWaterboard() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{20,13,14});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(1000),
                new InclineComponent(0) ,false,customer);
        ArrayList<BillLine> billLine = CarportGenerator.screwsForSternAndWaterBoard(categoriesUsedInGenerator, order, new ArrayList<BillLine>());

        String expected = "4,5 x 60 mm. skruer 200 stk.";
        int expectedAmount = 2;
        //total length 3240

        assertEquals(expected, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount, billLine.get(0).getAmount());
    }

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

    @Test
    public void TestskiverForRemOnPost() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{23});
        int amountOfBolts = 28;
        int expected = 28;
        String expectedName = "firkantskiver";

        ArrayList<BillLine> billLineNoShed = CarportGenerator.skiverForRemOnPost(categoriesUsedInGenerator, amountOfBolts);

        assertEquals(expectedName, billLineNoShed.get(0).getMaterial().getName());
        assertEquals(expected, billLineNoShed.get(0).getAmount());
    }

    @Test
    public void testScrewsForUniversalBeslagAndPerforatedBand() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{10,21});
        Order order = new Order(new DepthComponent(1000), new HeightComponent(10), new WidthComponent(580),
                new InclineComponent(0) ,false,customer);

        //If sper is calculated correctly, there should be 38 beslag
        ArrayList<BillLine> billLine =
                CarportGenerator.screwsForUniversalBeslagAndPerforatedBand(categoriesUsedInGenerator, order, 38);

        int expectedAmount = 2;
        //expect about 418 screws

        assertEquals(expectedAmount, billLine.get(0).getAmount());
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
