package FunctionLayer.BillGenerator;

import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Material;
import javafx.scene.layout.BorderWidths;

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

    public static ArrayList<BillLine> RemInSidesShed(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
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
            if(material.getLength() / orderHeight >= 1){
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
                        if(materialToUse.getWidth() < material.getWidth()){
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
        int shedPerimeter = 2 * (orderShedDepth + (orderShedWidth));

        //(Shed perimeter / twoBoardWidths) * 2 * amountOfBoardsPerColumn
        int amountOfBoards = (shedPerimeter / twoBoardWidths) * 2 * amountOfBoardsPerColumn;

        billLine = new BillLine(materialToUse,amountOfBoards);
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> stalddorsgreb(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> hingeForDoor(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> vinkelBeslag(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }
}
