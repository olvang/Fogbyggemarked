package FunctionLayer.BillGenerator;

import Components.*;
import FunctionLayer.*;
import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InclinedRoofGeneratorTest extends TestDataSetup {

    @Test
    public void testRygtsen() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{35});
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.rygsten(categoriesUsedInGenerator,order);

        String expectedName = "B & C Rygsten sort";
        int expectedAmount = 22;

        assertEquals(expectedName, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }

    @Test
    public void roofLathTest() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{31});
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.roofLath(categoriesUsedInGenerator,order);

        String expected = "25x50 mm. trykimp. Bræt";
        int expectedAmount = 3;

        assertEquals(expected, billLine.get(0).getMaterial().getName() );
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }

    @Test
    public void soffitTest() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{29});
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.soffit(categoriesUsedInGenerator,order);

        String expected = "trykimp. Brædt";
        int expectedAmount = 2;

        assertEquals(expected, billLine.get(0).getMaterial().getName() );
        assertEquals(expectedAmount, billLine.get(0).getAmount());

    }
    @Test
    public void testboardsForGabled() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{30});
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.boardsForGabled(categoriesUsedInGenerator,order);

        int exspected = 44;

        assertEquals(exspected,billLine.get(0).getAmount());

    }
    @Test
    public void testroofTiles() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{34});
        Order order = new Order(new DepthComponent(360), new HeightComponent(210), new WidthComponent(730), new InclineComponent(20) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.roofTiles(categoriesUsedInGenerator,order);

        int exspected = 243;

        assertEquals(exspected,billLine.get(0).getAmount());

    }

    @Test
    public void testroofLathOnSper() throws Exception{
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{32});
        Order order = new Order(new DepthComponent(360), new HeightComponent(210), new WidthComponent(730), new InclineComponent(20) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.roofLathOnSper(categoriesUsedInGenerator,order);

        int exspected = 8;
        int exspectedLength = 5400;

        assertEquals(exspected,billLine.get(0).getAmount());
        assertEquals(exspectedLength, billLine.get(0).getMaterial().getLength());

    }
    @Test
    public void testscrewsForRoofLaths() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);

        ArrayList<Category> categoriesUsedInGeneratorTop = getCategoriesAvailable(new int[]{32});
        ArrayList<BillLine> billLineTop = InclinedRoofGenerator.topRoofLathHolder(categoriesUsedInGeneratorTop,order);

        ArrayList<Category> categoriesUsedInGeneratorroof = getCategoriesAvailable(new int[]{33});
        ArrayList<BillLine> billLineroof = InclinedRoofGenerator.roofLathOnSper(categoriesUsedInGeneratorroof,order);

        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{41});
        ArrayList<BillLine> billLine = InclinedRoofGenerator.screwsForRoofLaths(categoriesUsedInGenerator,billLineTop,billLineroof);

        int exspected = 4;

        assertEquals(exspected,billLine.get(0).getAmount());

    }
    @Test
    public void testTopRoofLathHolder() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{36});
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.topRoofLathHolder(categoriesUsedInGenerator,order);

        String expectedName = "B & C Toplægte holder";
        int expectedAmount = 9;

        assertEquals(expectedName, billLine.get(0).getMaterial().getName());
        assertEquals(expectedAmount, billLine.get(0).getAmount());
    }

    @Test
    public void testTopRoofLath() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{33});
        Order order = new Order(new DepthComponent(780), new HeightComponent(210), new WidthComponent(730), new InclineComponent(20) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.topRoofLath(categoriesUsedInGenerator,order);

        int expectedPrice = 2;
        String expectedName = "38x73 mm. taglægte T1";

        assertEquals(expectedPrice, billLine.get(0).getAmount());
        assertEquals(expectedName, billLine.get(0).getMaterial().getName());
    }

    @Test
    public void testRoofTileBinders() throws Exception {
        Customer customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{38});
        Order order = new Order(new DepthComponent(730), new HeightComponent(210), new WidthComponent(360), new InclineComponent(25) ,false,customer);
        ArrayList<BillLine> billLine = InclinedRoofGenerator.roofTileBinders(categoriesUsedInGenerator,300,21);

        int expectedAmount= 2;
        String expectedName = "B & C tagstens bindere & nakkekroge";

        assertEquals(expectedAmount, billLine.get(0).getAmount());
        assertEquals(expectedName, billLine.get(0).getMaterial().getName());
    }

    public void testRygtsenBracket() throws Exception {
        ArrayList<Category> categoriesUsedInGenerator = getCategoriesAvailable(new int[]{37});
        int amount = 32;
        ArrayList<BillLine> billLine = InclinedRoofGenerator.rygstenBracket(categoriesUsedInGenerator, amount);

        String expectedName = "B & C rygstensbeslag";
        int expectedAmount = amount;

        assertEquals(expectedName, billLine.get(0).getMaterial().getName());
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
