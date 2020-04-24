package FunctionLayer.BillGenerator;

import FunctionLayer.Material;
import java.util.*;

public class GeneratorUtilities {

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

}


class lengthSorter implements Comparator<Material>
{
    //Compares two lengths and returns a Integer used by .sort
    @Override
    public int compare(Material o1, Material o2) {
        return Integer.compare(o2.getLength().getLength(),o1.getLength().getLength());
    }
}

class widthSorter implements Comparator<Material>
{
    //Compares two widths and returns a Integer used by .sort
    @Override
    public int compare(Material o1, Material o2) {
        return Integer.compare(o2.getWidth().getWidth(),o1.getWidth().getWidth());
    }
}