package FunctionLayer.BillGenerator;

import Components.ShedDepthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Material;

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

    public static ArrayList<BillLine> losholterGabled(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
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

    public static ArrayList<BillLine> losholterSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> boardsForShed(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
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
