package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.util.ArrayList;

public class CarportGenerator {

    public static BillLine underSternsBredderFrontAndBack(ArrayList<Category> categoriesUsedInGenerator, WidthComponent carpotWidth) {
        return null;
    }

    public static BillLine underSternsBredderSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine overSternBredderFront(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine overSternBredderSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }
    

    public static BillLine RemInSidesCarport(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine sperOnRem(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine posts(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        int numberOfPostRows = 2;
        int numberOfPostPerRow = 2;
        int total = 0;
        int carportWidth = order.getWidth().getWidth();
        int carportDepth = order.getDepth().getDepth();

        //If order has shed, subtract they width of the shed
        //ShedGenerator calculates how many post are needed within the shed
        if(order.isWithShed()){
            carportDepth -= order.getShedDepth().getDepth();
        }


        //For each 600 cm, add another post row
        if(carportWidth > 600){
            numberOfPostRows += carportWidth / 600;
        }

        //Subtracts 100 cm from the front, 30 cm from the back = 130 cm
        carportDepth -= 130;

        //For each 310 cm, add another post on that row
        numberOfPostPerRow += carportDepth / 310;

        total = numberOfPostPerRow * numberOfPostRows;

        //TODO calculate best material fit

        BillLine billLine = new BillLine(categoriesUsedInGenerator.get(0),total);
        return billLine;
    }

    public static BillLine perforatedBand(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine UniversalBeslagRight(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForSternAndWaterBoard(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForUniversalBeslagAndPerforatedBand(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static BillLine boltsForRemOnPost(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine skiverForRemOnPost(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForOuter(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForInner(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine UniversalBeslagLeft(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }
}
