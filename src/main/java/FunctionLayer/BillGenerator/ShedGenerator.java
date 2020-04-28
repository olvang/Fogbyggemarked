package FunctionLayer.BillGenerator;

import FunctionLayer.BillLine;
import FunctionLayer.Category;

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

    public static ArrayList<BillLine> RemInSidesShed(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
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
