package FunctionLayer.BillGenerator;

import Components.ShedDepthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;

import FunctionLayer.Exceptions.GeneratorException;

import FunctionLayer.Material;
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

    public static ArrayList<BillLine> boardsForShed(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
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

    public static ArrayList<BillLine> screwsForOuter(ArrayList<Category> materialsUsedInGenerator) {
        return null;
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
