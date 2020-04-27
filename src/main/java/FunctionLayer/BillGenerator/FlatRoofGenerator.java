package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Material;
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

    public static ArrayList<BillLine> roofPanels(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        int depth = order.getDepth().getDepth();
        Category category = categoriesUsedInGenerator.get(0);
        ArrayList<Material>  listOfMaterials = category.getMaterials();

        GeneratorUtilities.sortMaterialsByLength(listOfMaterials);
        ArrayList<BillLine> listToBeReturned = new ArrayList();
        int[] amountForEach = new int[listOfMaterials.size()];

        int remainingDepth = depth;

        while ( remainingDepth > 0 ) {
            for(int i = 0; i < listOfMaterials.size(); i++) {
                if(remainingDepth - listOfMaterials.get(i).getLength() > 0) {
                    amountForEach[i]++;
                    remainingDepth -= listOfMaterials.get(i).getLength();
                    break;
                }
            }
            if(remainingDepth < listOfMaterials.get(listOfMaterials.size() - 1).getLength()) {
                amountForEach[listOfMaterials.size() - 1]++;
                remainingDepth -= listOfMaterials.get(listOfMaterials.size() - 1).getLength();
            }
        }

        for(int i = 0; i < amountForEach.length; i++) {
            if(amountForEach[i] > 0) {
                listToBeReturned.add(new BillLine(listOfMaterials.get(i), amountForEach[i]));
            }
        }
        return listToBeReturned;
    }

    public static ArrayList<BillLine> screwsForRoofPanels(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }
}
