package FunctionLayer;

import FunctionLayer.BillGenerator.*;
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
                categoriesNeeded = new int[]{1,2,3,4,8,10,11,13,14,15,16,17,18,19,20,21,22,23};
                break;
            case 2: //Flat roof, with shed
                categoriesNeeded = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
                break;
            case 3: //Inclined roof, no shed
                categoriesNeeded = new int[]{29,2,10,11,8,14,30,31,32,33,34,35,36,37,38,18,19,39,40,41,22,23};
                break;
            case 4: //Inclined roof, with shed
                categoriesNeeded = new int[]{29,2,10,11,8,9,6,7,14,30,12,31,5,32,33,34,35,36,37,38,18,19,26,27,28,39,40,41,22,23,24,25};
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
                    billLine = CarportGenerator.underSternsBredderFrontAndBack(categoriesUsedInGenerator,order.getWidthComponent());
                    break;
                case 2: //understernbrædder til siderne
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{2};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.sternsBredderSides(categoriesUsedInGenerator,order.getDepthComponent());
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
                    categoryIdsUsedInGenerator = new int[]{4};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.sternsBredderSides(categoriesUsedInGenerator,order.getDepthComponent());
                    break;
                case 5: //til z på bagside af dør
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{5};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.zOnBackOfDoor(categoriesUsedInGenerator);
                    break;
                case 6: //løsholter til skur gavle
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{6};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.losholterGabled(categoriesUsedInGenerator,order.getShedWidth());
                    break;
                case 7: //løsholter til skur sider
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{7};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.losholterSides(categoriesUsedInGenerator,order.getShedDepth());
                    break;
                case 8: //Remme i sider, sadles ned i stolpe
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{8};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.RemInSidesCarport(categoriesUsedInGenerator, order.getDepthComponent(), order.getWidthComponent());
                    break;
                case 9: //Remme i sider, sadles ned i stolper (skur del, deles)
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{9};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.RemInSidesShed(categoriesUsedInGenerator,order.getShedDepthComponent());
                    break;
                case 10: //Spær, monteres på rem
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{10};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.sperOnRem(categoriesUsedInGenerator, order);
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
                    categoryIdsUsedInGenerator = new int[]{12};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.boardsForShed(categoriesUsedInGenerator,order.getHeight(), order.getShedWidth(),order.getShedDepth());
                    break;
                case 13: //vandbrædt på stern i sider
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{13};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.waterBoardOnSternSides(categoriesUsedInGenerator,order.getDepthComponent());
                    break;
                case 14: //vandbrædt på stern i forende
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{14};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.waterBoardOnSternFront(categoriesUsedInGenerator,order);
                    break;
                case 15: //tagplader monteres på spær
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{15};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.roofPanels(categoriesUsedInGenerator, order);
                    break;
                case 16: //Skruer til tagplader
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{16};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = FlatRoofGenerator.screwsForRoofPanels(categoriesUsedInGenerator,order);
                    break;
                case 17: //Til vindkryds på spær
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{17};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.perforatedBand(categoriesUsedInGenerator, order);
                    break;
                case 18: //Til montering af spær på rem - højre
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{18};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Gets the amount of spær and remme used
                    int sper = GeneratorUtilities.searchForAmountInACategoryFromBillLines(10, billLinesFinal);
                    int remme = GeneratorUtilities.searchForAmountInACategoryFromBillLines(8, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.UniversalBeslagRight(categoriesUsedInGenerator, sper, remme);
                    break;
                case 19: //Til montering af spær på rem - venstre
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{19};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Get the amount of spær used
                    sper = GeneratorUtilities.searchForAmountInACategoryFromBillLines(10, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.UniversalBeslagLeft(categoriesUsedInGenerator, sper);
                    break;
                case 20: //Til montering af stern & vandbrædt
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{20,13,14};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForSternAndWaterBoard(categoriesUsedInGenerator, order, billLinesFinal);
                    break;
                case 21: //Til montering af universalbeslag + hulbånd
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{10,21};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    int amountOfBeslag = 0;
                    amountOfBeslag += GeneratorUtilities.searchForAmountInACategoryFromBillLines(18, billLinesFinal);
                    amountOfBeslag += GeneratorUtilities.searchForAmountInACategoryFromBillLines(19, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForUniversalBeslagAndPerforatedBand(categoriesUsedInGenerator, order, amountOfBeslag);
                    break;
                case 22: //Til montering af rem på stolper - bolte
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{22};

                    //We need the amount of posts calculated
                    int amountOfPosts = GeneratorUtilities.searchForAmountInACategoryFromBillLines(11,billLinesFinal);

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.boltsForRemOnPost(categoriesUsedInGenerator,amountOfPosts,order.isWithShed());
                    break;
                case 23: //Til montering af rem på stolper - skiver
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{23};

                    //We need the amount of posts calculated
                    int amountOfBolts = GeneratorUtilities.searchForAmountInACategoryFromBillLines(22,billLinesFinal);

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.skiverForRemOnPost(categoriesUsedInGenerator,amountOfBolts);
                    break;
                case 24: //til montering af yderste beklædning
                    //This case needs the result of boardsForShed
                    //Therefore we calculate that first:

                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{12};
                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
                    //Gets the length and amount of boards used for the shed.
                    billLine = ShedGenerator.boardsForShed(categoriesUsedInGenerator,order.getHeight(), order.getShedWidth(),order.getShedDepth());

                    int length = billLine.get(0).getMaterial().getLength();
                    int amount = billLine.get(0).getAmount();

                    //Now we can continue the calculation of case 24:

                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{24};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.screwsForOuter(categoriesUsedInGenerator,length,amount);
                    break;
                case 25: //til montering af inderste beklædning
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{6, 7, 25};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.screwsForInner(categoriesUsedInGenerator, order);
                    break;
                case 26: //Til lås på dør i skur
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{26};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    int amountOfDoors = GeneratorUtilities.searchForAmountInACategoryFromBillLines(5, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.stalddorsgreb(categoriesUsedInGenerator, amountOfDoors);
                    break;
                case 27: //Til skurdør
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{27};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    int numberOfDoors = GeneratorUtilities.searchForAmountInACategoryFromBillLines(5, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.hingeForDoor(categoriesUsedInGenerator, numberOfDoors);
                    break;
                case 28: //Til montering af løsholter i skur
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{28};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    int amountOfLosholter = 0;
                    amountOfLosholter += GeneratorUtilities.searchForAmountInACategoryFromBillLines(6, billLinesFinal);
                    amountOfLosholter += GeneratorUtilities.searchForAmountInACategoryFromBillLines(7, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = ShedGenerator.vinkelBeslag(categoriesUsedInGenerator, amountOfLosholter);
                    break;
                case 29: //Vindskeder på rejsning
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{29};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.soffit(categoriesUsedInGenerator,order);
                    break;
                case 30: //Bekældning på gavl
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{30};
                
                //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
                
                //Calls the generator and returns the BillLine
                billLine = InclinedRoofGenerator.boardsForGabled(categoriesUsedInGenerator,order);
                    break;
                case 31: //Vindskeder på rejsning
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{31};
                
                //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
                
                //Calls the generator and returns the BillLine
                billLine = InclinedRoofGenerator.roofLath(categoriesUsedInGenerator,order);
                    break;

                case 32: //Taglægter på spær
                    categoryIdsUsedInGenerator = new int[]{32};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.roofLathOnSper(categoriesUsedInGenerator, order);
                    break;
                case 33: //Toplægte
                    categoryIdsUsedInGenerator = new int[]{33};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.topRoofLath(categoriesUsedInGenerator, order);
                    break;
                case 34:
                    categoryIdsUsedInGenerator = new int[]{34};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.roofTiles(categoriesUsedInGenerator, order);
                    break;
                case 35: //Rygsten
                    //The material categories needed in the generator method
                    categoryIdsUsedInGenerator = new int[]{35};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.rygsten(categoriesUsedInGenerator,order);
                    break;
                case 36: //Toplægte holder
                    categoryIdsUsedInGenerator = new int[]{36};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.topRoofLathHolder(categoriesUsedInGenerator, order);
                    break;
                case 37: //Rygstensbeslag
                    categoryIdsUsedInGenerator = new int[]{35,37};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    int amountOfRygtsen = GeneratorUtilities.searchForAmountInACategoryFromBillLines(35, billLinesFinal);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.rygstenBracket(categoriesUsedInGenerator, amountOfRygtsen);
                    break;
                case 38: //Tagstens bindere & nakkekroge
                    //We need the amounts calculated in category 34 and 35
                    int roofTilesAmount = 0;
                    int rygstenAmount = 0;
                    try{
                        roofTilesAmount = GeneratorUtilities.searchForAmountInACategoryFromBillLines(34,billLinesFinal);
                        rygstenAmount = GeneratorUtilities.searchForAmountInACategoryFromBillLines(35,billLinesFinal);
                    }catch (Exception e){
                        //could not get the amounts, so the case cannot be calculated
                        throw new GeneratorException("Kan ikke udregne Tagstens bindere & nakkekroge");
                    }



                    categoryIdsUsedInGenerator = new int[]{38};
                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.roofTileBinders(categoriesUsedInGenerator,roofTilesAmount,rygstenAmount);
                    break;
                case 39: //Skruer, samme som 20
                    categoryIdsUsedInGenerator = new int[]{39,14};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    //Calls the generator and returns the BillLine
                    billLine = CarportGenerator.screwsForSternAndWaterBoard(categoriesUsedInGenerator, order, billLinesFinal);
                    billLine.get(0).setAmount(billLine.get(0).getAmount() + 1); //A box more is needed for this one
                    break;

                case 40: //Screws, same as 21 + 1 one more
                    categoryIdsUsedInGenerator = new int[]{10,40};

                    //Gets a list with only the categories needed
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    int numberOfBeslag = 0;
                    numberOfBeslag += GeneratorUtilities.searchForAmountInACategoryFromBillLines(18, billLinesFinal);
                    numberOfBeslag += GeneratorUtilities.searchForAmountInACategoryFromBillLines(19, billLinesFinal);


                    if(numberOfBeslag == 0){
                        //numberOfBeslag will equal 0 if category 18 and 19 has not be calculated
                        throw new GeneratorException("Kan ikke beregne skruer til montering af universalbeslag + toplægte");
                    }else{
                        //Calls the generator and returns the BillLine
                        billLine = CarportGenerator.screwsForUniversalBeslagAndPerforatedBand(categoriesUsedInGenerator, order, numberOfBeslag);
                        billLine.get(0).setAmount(billLine.get(0).getAmount() + 1); //A box more is needed for this one
                    }
                    break;
                case 41: // 5,0 x 100 mm. skruer 100 stk.
                    categoryIdsUsedInGenerator = new int[]{41};
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);

                    categoryIdsUsedInGenerator = new int[]{33};
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
                    ArrayList<BillLine> topRoofLath = InclinedRoofGenerator.topRoofLath(categoriesUsedInGenerator, order);

                    categoryIdsUsedInGenerator = new int[]{32};
                    categoriesUsedInGenerator = getCategoriesUsedInGenerator(categoryIdsUsedInGenerator, categoriesAvailable);
                    ArrayList<BillLine> roofLathOnSper = InclinedRoofGenerator.roofLathOnSper(categoriesUsedInGenerator, order);

                    //Calls the generator and returns the BillLine
                    billLine = InclinedRoofGenerator.screwsForRoofLaths(categoriesUsedInGenerator, topRoofLath,roofLathOnSper);
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
