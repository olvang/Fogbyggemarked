package FunctionLayer.BillGenerator;

import Components.InclineComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Material;
import java.util.*;

public class GeneratorUtilities {

    public static double calculateRoofLength(InclineComponent incline, WidthComponent carportWidth) {
        //Divide by 2 because the input is the entire width of the roof, and we only need to calculate one side
        //Math.cos uses radians, so we need to convert the angle first
        double cosineOfA = Math.cos(Math.toRadians(incline.getIncline()));
        double halfOfWidth = carportWidth.getWidth() / 2;

        double result =  halfOfWidth / cosineOfA;
        return result;
    }

    public static double calculateRoofHeight(InclineComponent incline, WidthComponent carportWidth) {
        //Divide by 2 because the input is the entire width of the roof, and we only need to calculate one side
        //Math.tan uses radians, so we need to convert the angle first
        double result = ( carportWidth.getWidth() / 2) * Math.tan(Math.toRadians(incline.getIncline()));
        return result;
    }

    public static ArrayList<Material> sortMaterialsByLength(ArrayList<Material> categoriesUsedInGenerator){
        //Sort the array by length
        categoriesUsedInGenerator.sort(new lengthSorter());
        return categoriesUsedInGenerator;
    }

    public static ArrayList<Material> sortMaterialsByWidth(ArrayList<Material> categoriesUsedInGenerator){
        //Sort the array by width
        categoriesUsedInGenerator.sort(new widthSorter());
        return categoriesUsedInGenerator;
    }

    public static ArrayList<Material> sortMaterialsByAmount(ArrayList<Material> categoriesUsedInGenerator){
        //Sort the array by width
        categoriesUsedInGenerator.sort(new amountSorter());
        return categoriesUsedInGenerator;
    }

    public static int searchForAmountInACategoryFromBillLines(int categoryID, ArrayList<BillLine> billLines){
        int amount = 0;
        //Goes trough each billLine to find billLines with the categoryID.
        for (BillLine billLine : billLines) {
            if(billLine.getMaterial().getCategory() == categoryID ){
                //When found, adds the amount
                amount += billLine.getAmount();
            }
        }
        return amount;
    }

}


class lengthSorter implements Comparator<Material>
{
    //Compares two lengths and returns a Integer used by .sort
    @Override
    public int compare(Material o1, Material o2) {
        return Integer.compare(o2.getLength(),o1.getLength());
    }
}

class widthSorter implements Comparator<Material>
{
    //Compares two widths and returns a Integer used by .sort
    @Override
    public int compare(Material o1, Material o2) {
        return Integer.compare(o2.getWidth(),o1.getWidth());
    }
}

class amountSorter implements Comparator<Material>
{
    //Compares two widths and returns a Integer used by .sort
    @Override
    public int compare(Material o1, Material o2) {
        return Integer.compare(o2.getAmount(),o1.getAmount());
    }
}