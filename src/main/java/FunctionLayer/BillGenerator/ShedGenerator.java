package FunctionLayer.BillGenerator;

import Components.ShedDepthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;

import FunctionLayer.Exceptions.GeneratorException;

import FunctionLayer.Material;
import javafx.scene.layout.BorderWidths;
import FunctionLayer.Order;
import PresentationLayer.Bill;

import java.util.ArrayList;

public class ShedGenerator {
    public static ArrayList<BillLine> zOnBackOfDoor(ArrayList<Category> categoriesUsedInGenerator) {
        //We only need one a material in the category
        //For now there is no calculation so we just return the first material in the category
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine = null;

        //Since there is no calculation for which skiver to select, we just use the first one available to us, and set the amount to 1
        billLine = new BillLine(categoriesUsedInGenerator.get(0).getMaterialAtIndex(0),1);

        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> losholterGabled(ArrayList<Category> categoriesUsedInGenerator, int widthOfShed) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material  = null;
        int losholter = 0;

        //We need to calculate the width of the Gabled
        //We need 3 rows of losHolter for the front and back

        //Then we need to calculate which material that best fit the length, starting with the largest first
        //So first we sort the materials in the category
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //Now we can run trough each material to get what best fits
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material =  materialsSortedByLength.get(i);

            //If the the material length is bigger than the fullWidth, we use that material losholter
            if(material.getLength() / widthOfShed >= 1){
                //Material found, which means we need 6 of them for the front and back, 3 for each Gabled
                losholter = 6;
                break;
            }else if(i == 0){
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the fullWidth
                //The fullWidth is calculated by taking the widthOfShed * 6, since we need 3 for each Gabled
                int fullWidthCalc = widthOfShed * 6;

                //We then keep on adding the material, until the fullLength has been filled
                while (fullWidthCalc > 0){
                    losholter++;
                    fullWidthCalc -= material.getLength();
                }
            }
        }

        //Returns the billLine
        billLine = new BillLine(material,losholter);
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> RemInSidesShed(ArrayList<Category> categoriesUsedInGenerator, ShedDepthComponent sDepthCom) {

        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine = null;
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        int amountUsed = 2;
        int rest = 0;

        int longestMaterialLength = materialsSortedByLength.get(0).getLength();
        int depth = sDepthCom.getDepth();

        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material mat =  materialsSortedByLength.get(i);

            if(mat.getLength() / 2 > depth){
                billLine = new BillLine(mat,1);
                billLines.add(billLine);
                return  billLines;
            }
        }
        amountUsed = depth / longestMaterialLength;
        rest = depth % longestMaterialLength;

        if(amountUsed != 0){
            billLine = new BillLine(materialsSortedByLength.get(0),amountUsed);
            billLines.add(billLine);
        }

        if(rest == 0){
            return billLines;
        }

        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material mat =  materialsSortedByLength.get(i);

            if(rest - mat.getLength() < 0){
                amountUsed = amountUsed + 2;
                billLine = new BillLine(mat,amountUsed);

            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(mat,amountUsed);
            }
        }
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> losholterSides(ArrayList<Category> categoriesUsedInGenerator, int depthOfShed) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material  = null;
        int losholter = 0;

        //We need 3 rows of losHolter for each side

        //Then we need to calculate which material that best fit the depth, starting with the largest first
        //So first we sort the materials in the category
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //Now we can run trough each material to get what best fits
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material =  materialsSortedByLength.get(i);

            //If the the material length is bigger than the depth, we use that material losholter
            if(material.getLength() / depthOfShed >= 1){
                //Material found, which means we need 3 of them per side = 2*3 = 6
                losholter = 6;
                break;
            }else if(i == 0){
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the fullDepth
                //The fullDepth is calculated by taking the depthOfShed * 6, since we need 3 for each side
                int fullDepthCalc = depthOfShed * 6;

                //We then keep on adding the material, until the fullLength has been filled
                while (fullDepthCalc > 0){
                    losholter++;
                    fullDepthCalc -= material.getLength();
                }
            }
        }

        //Returns the billLine
        billLine = new BillLine(material,losholter);
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> boardsForShed(ArrayList<Category> categoriesUsedInGenerator,int orderHeight, int orderShedWidth,int orderShedDepth) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material;
        Material materialToUse  = null;
        orderHeight = orderHeight * 10; //cm to mm
        int amountOfBoardsPerColumn = 1;

        //First we need to get the material to use
        //Check which material that best fit the height
        //When the height has been found, we need the widest material of the materials that has that height
        //So first we sort the materials in the category
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());


        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material =  materialsSortedByLength.get(i);

            //Since the materials are sorted by length, and we are checking from the smallest first
            //We know that the first material that is >= than the height, is the height that best fits
            if( orderHeight / material.getLength() >= 1){
                //We then need to check if any of the materials has the same length as the best fitting height
                //if they do, we need to which of them is widest, and use that material in the bill
                //But only if its not the last element
                if(i > 0){
                    materialToUse = material;

                    while (i > -1) {
                    i--;
                    //We then select the next element while i 0 or bigger
                    material = materialsSortedByLength.get(i);
                    //If the next element has the same length, check the widths
                    if(materialToUse.getLength() == material.getLength()){

                        //If the materialToUse width is smaller than the next material, we can set the marterial To Use to the next material
                        if(materialToUse.getWidth()  < material.getWidth()){
                            materialToUse = material;
                        }
                    }else{
                        //We now know there are no more elements with the same height, so we can exit
                        //or
                        break;
                    }
                    //If the $i points to -1, no more materials to check
                    if(i == 0){
                        break;
                    }
                    }
                }
                break;
            }else if(i == 0){
                //If No fitting material has been found, we use the material anyway as it is the biggest we have
                //We then to check if the element before has the same height as the last element
                //if they do, we need to which of them is widest, and use the widest material in the bill
                //But only if element before is not bigger than the element size
                if(i < materialsSortedByLength.size()){
                    materialToUse = material;
                    //We then select the next element
                    while (i < materialsSortedByLength.size()) {
                        i++;
                        //We then select the next element while i smaller than the materialsSortedByLength.size
                        material = materialsSortedByLength.get(i);
                        //If the element has the same length, check the widths
                        if(materialToUse.getLength() == material.getLength()){

                            //If the materialToUse width is smaller than the next material, we can set the materialToUse to the next material
                            if(materialToUse.getWidth() < material.getWidth()){
                                materialToUse = material;
                            }
                        }else{
                            //We now know there are no more elements with the same height, so we can exit
                            break;
                        }

                        //If the $i points to materialsSortedByLength.size()-1, no more materials to check
                        if(i == materialsSortedByLength.size()-1){
                            break;
                        }
                    }
                }

                //Now we need to calculate how many boards there are needed per column
                int orderHeightCalc = orderHeight;
                orderHeightCalc -= materialToUse.getLength();
                while (orderHeightCalc > 0){
                    amountOfBoardsPerColumn++;
                    orderHeightCalc -= materialToUse.getLength();
                }
                break;
            }
        }

        //We then calculate how many cm two boards in a 1 on 2 formation fills
        //2 boards * width of board material
        //We then need to subtract the minimum coverage = which is 2 * 15 mm = 30 mm
        int twoBoardWidths = 2 * materialToUse.getWidth() - 30;
        //If 2 boards are less than 30 mm, the calculation cannot be made
        if(twoBoardWidths < 1){
            new GeneratorException("Et bræde til skuret skal være mindst 16 mm brede");
        }

        //Now we can calculate the amount of boards needed to cover the shed
        int shedPerimeter = 2 * ((orderShedDepth + orderShedWidth) * 10);

        //(Shed perimeter / twoBoardWidths) * 2 * amountOfBoardsPerColumn
        int amountOfBoards = (shedPerimeter / twoBoardWidths) * 2 * amountOfBoardsPerColumn;

        billLine = new BillLine(materialToUse,amountOfBoards);
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> stalddorsgreb(ArrayList<Category> categoriesUsedInGenerator, int amountOfDoors) {
        //The amount of staldørsgreb to use is based on the amount of doors.
        //There aren't really any calculations to do here.
        ArrayList<BillLine> lineToReturn = new ArrayList<>();
        Material mat = categoriesUsedInGenerator.get(0).getMaterialAtIndex(0);
        BillLine line = new BillLine(mat, amountOfDoors);
        lineToReturn.add(line);

        return lineToReturn;
    }

    public static ArrayList<BillLine> hingeForDoor(ArrayList<Category> categoriesUsedInGenerator, int amountOfDoors) {
        //The amount of staldørsgreb to use is based on the amount of doors.
        //That is 2 per door
        //There aren't really any calculations to do here.
        ArrayList<BillLine> lineToReturn = new ArrayList<>();
        Material mat = categoriesUsedInGenerator.get(0).getMaterialAtIndex(0);
        BillLine line = new BillLine(mat, (amountOfDoors * 2) );
        lineToReturn.add(line);

        return lineToReturn;
    }

    public static ArrayList<BillLine> vinkelBeslag(ArrayList<Category> categoriesUsedInGenerator, int amountOfLosholter) {
        //The amount of vinkelbeslag is just the double of løsholter
        ArrayList<BillLine> lineToReturn = new ArrayList<>();
        Material mat = categoriesUsedInGenerator.get(0).getMaterialAtIndex(0);
        BillLine line = new BillLine(mat, (amountOfLosholter * 2) );
        lineToReturn.add(line);

        return lineToReturn;
    }

    public static ArrayList<BillLine> screwsForOuter(ArrayList<Category> materialsUsedInGenerator, int length, int amount) {
        //Antal beklædning på skur 1 på 2 * længden af dem, divideret med 52.5

        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;
        Material materialToUse;

        ArrayList<Material> screws = materialsUsedInGenerator.get(0).getMaterials();

        double amountOfScrewsUsed = amount * length / 52.5;

        materialToUse = screws.get(0);

        int amountInBox = screws.get(0).getAmount();
        int total = (int) Math.ceil(1.0 * amountOfScrewsUsed / amountInBox);
        billLine = new BillLine(materialToUse, total);
        billLines.add(billLine);
        return billLines;

    }

    public static ArrayList<BillLine> screwsForInner(ArrayList<Category> materialsUsedInGenerator, Order order) {
        //DESC: Antal af løsholter til skur-gavle * længden deraf
        // + Antal af løsholter til skur-sider * længden deraf, divideret med 7

        //Getting løsholter
        ArrayList<BillLine> losholter1 = losholterGabled(
                new ArrayList<Category>() {{add(materialsUsedInGenerator.get(0));}}, order.getShedWidth().getWidth()
        );
        ArrayList<BillLine> losholter2 = losholterSides(
                new ArrayList<Category>() {{add(materialsUsedInGenerator.get(1));}}, order.getShedDepth().getDepth()
        );

        ArrayList<BillLine> listToReturn = new ArrayList<>();
        Material materialToUse;
        BillLine lineToReturn;
        ArrayList<Material> screws = materialsUsedInGenerator.get(2).getMaterials();

        //Calculating screws needed:
        int amountOfScrews = 0;
        int amountOfLosholterForGable = 0;
        int lengthOfLosholterForGable = 0;
        for(BillLine line : losholter1) {
            amountOfLosholterForGable += line.getAmount();
            lengthOfLosholterForGable += line.getMaterial().getLength();
        }
        int amountOfLosholterForSides = 0;
        int lengthOfLosholterForSides = 0;
        for(BillLine line : losholter2) {
            amountOfLosholterForSides += line.getAmount();
            lengthOfLosholterForSides += line.getMaterial().getLength();
        }

        amountOfScrews = ((amountOfLosholterForGable * lengthOfLosholterForGable)
                + (amountOfLosholterForSides * lengthOfLosholterForSides) ) / 7;

        //Finding the most fitting box size
        //If there is only one box size to choose from, we just use that.
        if(screws.size() == 1) {
            materialToUse = screws.get(0);
            int amountInBox = screws.get(0).getAmount();
            int total = (int) Math.ceil(1.0 * amountOfScrews / amountInBox);
            lineToReturn = new BillLine(materialToUse, total);
            listToReturn.add(lineToReturn);
            return listToReturn;
        }

        //Otherwise we have to find the most fitting one
        materialToUse = screws.get(0);
        int boxesNeeded = -1;
        for(Material box : screws) {
            int amountInBox = box.getAmount();
            double total = amountOfScrews / amountInBox;
            if(total < materialToUse.getAmount() && total > 1) {
                materialToUse = box;
                boxesNeeded = (int) Math.ceil(total);
            }
        }

        lineToReturn = new BillLine(materialToUse, boxesNeeded);
        listToReturn.add(lineToReturn);

        return listToReturn;
    }
}
