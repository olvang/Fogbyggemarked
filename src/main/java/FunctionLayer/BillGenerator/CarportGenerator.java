package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Material;

import java.util.ArrayList;

public class CarportGenerator {

    public static BillLine underSternsBredderFrontAndBack(ArrayList<Material> materialsUsedInGenerator, WidthComponent carpotWidth) {
        return null;
    }

    public static BillLine underSternsBredderSides(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine overSternBredderFront(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine overSternBredderSides(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }
    

    public static BillLine RemInSidesCarport(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine sperOnRem(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine posts(ArrayList<Material> materialsUsedInGenerator, WidthComponent carportWidthComponent, DepthComponent carportDepthComponent) {
        //TODO take shed into account or create another category for post calculation with shed
        int numberOfPostRows = 2;
        int numberOfPostPerRow = 2;
        int total = 0;
        int carportWidth = carportWidthComponent.getWidth();
        int carportDepth = carportDepthComponent.getDepth();

        //For each 600 cm, add another post row
        if(carportWidth > 600){
            numberOfPostRows += carportWidth / 600;
        }

        //Subtracts 100 cm from the front, 30 cm from the back = 130 cm
        carportDepth -= 130;

        //For each 310 cm, add another post on that row
        numberOfPostPerRow += carportDepth / 310;

        total = numberOfPostPerRow * numberOfPostRows;

        //TODO calculate variant

        BillLine billLine = new BillLine(materialsUsedInGenerator.get(1),total);
        return billLine;
    }

    public static BillLine perforatedBand(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine UniversalBeslagRight(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForSternAndWaterBoard(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForUniversalBeslagAndPerforatedBand(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine boltsForRemOnPost(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine skiverForRemOnPost(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForOuter(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine screwsForInner(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }

    public static BillLine UniversalBeslagLeft(ArrayList<Material> materialsUsedInGenerator) {
        return null;
    }
}
