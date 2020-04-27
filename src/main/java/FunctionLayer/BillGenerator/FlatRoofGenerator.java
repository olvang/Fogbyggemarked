package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Order;

import java.util.ArrayList;

public class FlatRoofGenerator {
    public static ArrayList<BillLine> waterBoardOnSternSides(ArrayList<Category> categoriesUsedInGenerator, DepthComponent depth) {
        //Calculated same way as Stern for the sides
        return CarportGenerator.sternsBredderSides(categoriesUsedInGenerator,depth);
    }

    public static ArrayList<BillLine> waterBoardOnSternFront(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        //Calculated same way as Stern for the front
        return CarportGenerator.overSternBredderFront(categoriesUsedInGenerator,order);
    }

    public static ArrayList<BillLine> roofPanels(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> screwsForRoofPanels(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }
}
