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

    public static BillLine posts(ArrayList<Material> materialsUsedInGenerator, WidthComponent carpotWidth, DepthComponent carpotDepth) {
        int numberOfPostRows = 2;
        int carportWidth = carpotWidth.getWidth();

        if(carportWidth > 600){
            numberOfPostRows += carportWidth / 600;
        }

        BillLine billLine = new BillLine(materialsUsedInGenerator.get(1),numberOfPostRows);
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
