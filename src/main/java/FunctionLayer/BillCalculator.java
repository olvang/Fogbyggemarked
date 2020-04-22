package FunctionLayer;

import java.util.ArrayList;

public class BillCalculator {
    public ArrayList<BillLine> calculateBillFromOrder(Order order){
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
                categoriesNeeded = new int[]{1,2,3,4,5,6,7};
                break;
            case 2: //Flat roof, with shed
                categoriesNeeded = new int[]{2};
                break;
            case 3: //Inclined roof, no shed
                categoriesNeeded = new int[]{3};
                break;
            case 4: //Inclined roof, with shed
                categoriesNeeded = new int[]{4};
                break;
        }

        //Get the materials available within each category
        ArrayList<Material> materialsAvailable = LogicFacade.getTheseMaterials(categoriesNeeded);

        //Used to calculate bill lines
        ArrayList<Material> materialsUsedInGenerator = null;
        int[] categoriesUsedInGenerator = null;
        BillLine billLine;

        //Used to hold all BillLines
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();

        //For each category calculate materials needed
        for(int category : categoriesNeeded){
            //Resets array
            materialsUsedInGenerator.clear();

            switch(category){
                //TODO Translate to English
                case 1: //understernbrædder til for & bag ende
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 2: //understernbrædder til siderne
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 3: //oversternbrædder til forenden
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 4: //oversternbrædder til siderne
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 5: //til z på bagside af dør
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 6: //løsholter til skur gavle
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 7: //løsholter til skur sider
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 8: //Remme i sider, sadles ned i stolpe
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 9: //Remme i sider, sadles ned i stolper (skur del, deles)
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 10: //Spær, monteres på rem
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 11: //Stolper nedgraves 90 cm. i jord
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 12: //til beklædning af skur 1 på 2
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 13: //vandbrædt på stern i sider
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 14: //vandbrædt på stern i forende
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 15: //tagplader monteres på spær
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 16: //Skruer til tagplader
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 17: //Til vindkryds på spær
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 18: //Til montering af spær på rem - højre
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 19: //Til montering af spær på rem - venstre
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 20: //Til montering af stern & vandbrædt
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 21: //Til montering af universalbeslag + hulbånd
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 22: //Til montering af rem på stolper - bolte
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 23: //Til montering af rem på stolper - skiver
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 24: //til montering af yderste beklædning
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 25: //til montering af inderste beklædning
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 26: //Til lås på dør i skur
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 27: //Til skurdør
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
                case 28: //Til montering af løsholter i skur
                    //The material categories needed in the generator method
                    categoriesUsedInGenerator = new int[]{1,2};

                    //Gets a list with only the categories needed
                    materialsUsedInGenerator = getMaterialsUsedInGenerator(categoriesUsedInGenerator);

                    //Calls the generator and returns the BillLine
                    billLine = carportGenerator.calculateUnderSternsBrædder(materialsUsedInGenerator);
                    break;
            }
            billLines.add(billLine);
        }

        return null;
    }

    private ArrayList<Material> getMaterialsUsedInGenerator(int[] categoriesUsedInGenerator,ArrayList<Material> materialsAvailable) {
        ArrayList<Material> materialsUsedInGenerator = null;

        //Loop all categories needed in Generator
        for(int categoryID : categoriesUsedInGenerator){

            //Loop all materials available to search for the categoryID
            for(Material material : materialsAvailable){
                if(material.getCategory() == categoryID){
                    materialsUsedInGenerator.add(material);

                    //When the material has been found exit
                    break;
                }
            }
        }
        return materialsUsedInGenerator;
    }
}
