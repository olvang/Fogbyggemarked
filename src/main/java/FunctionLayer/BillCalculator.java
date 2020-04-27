package FunctionLayer;

import FunctionLayer.BillGenerator.CarportGenerator;
import FunctionLayer.BillGenerator.FlatRoofGenerator;
import FunctionLayer.BillGenerator.ShedGenerator;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Exceptions.ValidationFailedException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillCalculator {
    public ArrayList<BillLine> calculateBillFromOrder(Order order) throws SQLException, ValidationFailedException, ClassNotFoundException, GeneratorException, CommandException {
        int[] categoriesNeeded = null;
        int orderType;

        //Check which type of order it is and sets the order type
        //Flat roof, no shed = 1
        //Flat roof, with shed = 2
        //Inclined roof, no shed = 3
        //Inclined roof, with shed = 4
        if(order.getIncline() > 0){
            //Inclined roof
            orderType = 3;
        }else{
            //Flat roof
            orderType = 1;
        }

        //Plus one if order has shed
        if(order.isWithShed()){
            orderType++;
        }

        //Select which categories are needed for the selected order type
        switch (orderType){
            case 1: //Flat roof, no shed
                categoriesNeeded = new int[]{1,2,3,4,5,6,7,11};
                break;
            case 2: //Flat roof, with shed
                categoriesNeeded = new int[]{2,11};
                break;
            case 3: //Inclined roof, no shed
                categoriesNeeded = new int[]{3};
                break;
            case 4: //Inclined roof, with shed
                categoriesNeeded = new int[]{4};
                break;
        }

        //Get each category
        ArrayList<Category> categoriesAvailable = LogicFacade.getTheseCategories(categoriesNeeded);

        //Used to calculate bill lines
        ArrayList<Category> categoriesUsedInGenerator = null;
        int[] categoryIdsUsedInGenerator = null;
        ArrayList<BillLine> billLine = null;

        //Used to hold all BillLines
        ArrayList<BillLine> billLinesFinal = new ArrayList<BillLine>();

        //For each category calculate materials needed
        for(int category : categoriesNeeded){
            //Resets array

            switch(category){
                //TODO Translate to English
                case 1: //understernbrædder til for & bag ende
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.underSternsBredderFrontAndBack(categoriesUsedInGenerator,order.getWidth());
                    break;
                case 2: //understernbrædder til siderne
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.underSternsBredderSides(categoriesUsedInGenerator,order.getDepth());
                    break;
                case 3: //oversternbrædder til forenden
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{3};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.overSternBredderFront(categoriesUsedInGenerator, order);
                    break;
                case 4: //oversternbrædder til siderne
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.overSternBredderSides(categoriesUsedInGenerator);
                    break;
                case 5: //til z på bagside af dør
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.zOnBackOfDoor(categoriesUsedInGenerator);
                    break;
                case 6: //løsholter til skur gavle
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.losholterGabled(categoriesUsedInGenerator);
                    break;
                case 7: //løsholter til skur sider
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.losholterSides(categoriesUsedInGenerator);
                    break;
                case 8: //Remme i sider, sadles ned i stolpe
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepth(), order.getWidth());
                    break;
                case 9: //Remme i sider, sadles ned i stolper (skur del, deles)
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.RemInSidesShed(categoriesUsedInGenerator);
                    break;
                case 10: //Spær, monteres på rem
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.sperOnRem(categoriesUsedInGenerator);
                    break;
                case 11: //Stolper nedgraves 90 cm. i jord
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{11};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.posts(categoriesUsedInGenerator,order);
                    break;
                case 12: //til beklædning af skur 1 på 2
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.boardsForShed(categoriesUsedInGenerator);
                    break;
                case 13: //vandbrædt på stern i sider
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.waterBoardOnSternSides(categoriesUsedInGenerator);
                    break;
                case 14: //vandbrædt på stern i forende
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.waterBoardOnSternFront(categoriesUsedInGenerator);
                    break;
                case 15: //tagplader monteres på spær
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.roofPanels(categoriesUsedInGenerator);
                    break;
                case 16: //Skruer til tagplader
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.screwsForRoofPanels(categoriesUsedInGenerator);
                    break;
                case 17: //Til vindkryds på spær
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.perforatedBand(categoriesUsedInGenerator);
                    break;
                case 18: //Til montering af spær på rem - højre
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.UniversalBeslagRight(categoriesUsedInGenerator);
                    break;
                case 19: //Til montering af spær på rem - venstre
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.UniversalBeslagLeft(categoriesUsedInGenerator);
                    break;
                case 20: //Til montering af stern & vandbrædt
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForSternAndWaterBoard(categoriesUsedInGenerator);
                    break;
                case 21: //Til montering af universalbeslag + hulbånd
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForUniversalBeslagAndPerforatedBand(categoriesUsedInGenerator);
                    break;
                case 22: //Til montering af rem på stolper - bolte
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.boltsForRemOnPost(categoriesUsedInGenerator);
                    break;
                case 23: //Til montering af rem på stolper - skiver
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.skiverForRemOnPost(categoriesUsedInGenerator);
                    break;
                case 24: //til montering af yderste beklædning
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForOuter(categoriesUsedInGenerator);
                    break;
                case 25: //til montering af inderste beklædning
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForInner(categoriesUsedInGenerator);
                    break;
                case 26: //Til lås på dør i skur
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.stalddorsgreb(categoriesUsedInGenerator);
                    break;
                case 27: //Til skurdør
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.hingeForDoor(categoriesUsedInGenerator);
                    break;
                case 28: //Til montering af løsholter i skur
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.vinkelBeslag(categoriesUsedInGenerator);
                    break;
            }
            if(billLine != null){
                billLinesFinal.addAll(billLine);
                categoriesUsedInGenerator.clear();
            }

        }

        return billLinesFinal;
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
