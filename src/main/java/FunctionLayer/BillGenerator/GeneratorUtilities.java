package FunctionLayer.BillGenerator;

import Components.InclineComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Material;
import java.util.*;

/**
 * Used to Generate / Calculate the BillLines associated with a shed
 */
public class GeneratorUtilities {

    /**
     * <p>Calculates and returns the length of the roof </p>
     * @param incline The incline of the roof
     * @param carportWidth the width of the carport
     * @return A double with the calculated roof length
     */
    public static double calculateRoofLength(InclineComponent incline, WidthComponent carportWidth) {
        //Divide by 2 because the input is the entire width of the roof, and we only need to calculate one side
        //Math.cos uses radians, so we need to convert the angle first
        double cosineOfA = Math.cos(Math.toRadians(incline.getIncline()));
        double halfOfWidth = carportWidth.getWidth() / 2;

        double result =  halfOfWidth / cosineOfA;
        return result;
    }

    /**
     * <p>Calculates and returns the height of the roof </p>
     * @param incline The incline of the roof
     * @param carportWidth the width of the carport
     * @return A double with the calculated roof height
     */
    public static double calculateRoofHeight(InclineComponent incline, WidthComponent carportWidth) {
        //Divide by 2 because the input is the entire width of the roof, and we only need to calculate one side
        //Math.tan uses radians, so we need to convert the angle first
        double result = ( carportWidth.getWidth() / 2) * Math.tan(Math.toRadians(incline.getIncline()));
        return result;
    }

    /**
     * <p>Sorts arraylist of materials by their lengths</p>
     * @param materialsUsedInGenerator The arraylist of categories to be sorted
     * @return Arraylist of materials sorted by the material length
     */
    public static ArrayList<Material> sortMaterialsByLength(ArrayList<Material> materialsUsedInGenerator){
        //Sort the array by length
        materialsUsedInGenerator.sort(new lengthSorter());
        return materialsUsedInGenerator;
    }

    /**
     * <p>Sorts arraylist of materials by their widths</p>
     * @param materialsUsedInGenerator The arraylist of categories to be sorted
     * @return Arraylist of materials sorted by the material widths
     */
    public static ArrayList<Material> sortMaterialsByWidth(ArrayList<Material> materialsUsedInGenerator){
        //Sort the array by width
        materialsUsedInGenerator.sort(new widthSorter());
        return materialsUsedInGenerator;
    }

    /**
     * <p>Sorts arraylist of materials by the amount on each material</p>
     * @param materialsUsedInGenerator The arraylist of categories to be sorted
     * @return Arraylist of materials sorted by the material amount
     */
    public static ArrayList<Material> sortMaterialsByAmount(ArrayList<Material> materialsUsedInGenerator){
        //Sort the array by width
        materialsUsedInGenerator.sort(new amountSorter());
        return materialsUsedInGenerator;
    }

    /**
     * <p>Finds the amount's on a BillLine with a specific category id</p>
     * <p>Used by categories, that needs to get the amount from other calculated categories</p>
     * @param categoryID The category ID to search for
     * @param  billLines The Arraylist of BillLines to search within
     * @return The amount calculated, if none is found returns 0
     */
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