package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.util.ArrayList;

/**
 * Used to Generate / Calculate the BillLines associated with a flatroof
 */
public class FlatRoofGenerator {

    /**
     *<p>Calculates amount of waterboards on sides</p>
     *Uses sternbredderSidees from carport generator
     * @param depth The depth of the carport
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return ArrayList<BilLine> An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> waterBoardOnSternSides(ArrayList<Category> categoriesUsedInGenerator, DepthComponent depth) {
        //Calculated same way as Stern for the sides
        return CarportGenerator.sternsBredderSides(categoriesUsedInGenerator,depth);
    }

    /**
     *<p>Calculates amount of waterboards on sides</p>
     *Uses oversternbredderFront from carport generator
     * @param order the order object to calculate on
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return ArrayList<BilLine> An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> waterBoardOnSternFront(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        //Calculated same way as Stern for the front
        return CarportGenerator.overSternBredderFront(categoriesUsedInGenerator,order);
    }

    public static ArrayList<BillLine> roofPanels(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        int depth = order.getDepth();
        int width = order.getWidth();
        Category category = categoriesUsedInGenerator.get(0);
        ArrayList<Material>  listOfMaterials = category.getMaterials();

        GeneratorUtilities.sortMaterialsByLength(listOfMaterials);
        ArrayList<BillLine> listToBeReturned = new ArrayList();
        int[] amountUsedForEachMaterial = new int[listOfMaterials.size()];
        //Since the carport will be wider than 109cm, we need to multiply each by the width of the
        // carport, divided by the width of the roof panels. For now we assume that the width doesn't vary.
        int multiplier = (int) Math.ceil((double) width / listOfMaterials.get(0).getWidth());

        int remainingDepth = depth;

        //Continuing until there is no more depth to cover, add roof panel
        while ( remainingDepth > 0 ) {
            //From longest to shortest, check the length of the roof panel until one short enough to fit in
            // the remaining area is found. Once it is found, add it to the amount, subtract it's length from the
            // remaining depth and stop the for loop.
            for(int i = 0; i < listOfMaterials.size(); i++) {
                if(remainingDepth - (listOfMaterials.get(i).getLength() / 10) > 0) {
                    amountUsedForEachMaterial[i] += multiplier;
                    remainingDepth -= (listOfMaterials.get(i).getLength()/ 10);
                    break;
                }
            }
            //At some point none of the tiles will fit, while still keeping remainingDepth above 0,
            // so we need to check if the shortest (final entry in list since we sorted it) panel is longer than
            // the remaining depth. If it is, we add it to the list and subtract it's length. This gets remainingDepth
            // below 0, ending the loop.
            if(remainingDepth < (listOfMaterials.get(listOfMaterials.size() - 1).getLength() / 10)) {
                amountUsedForEachMaterial[listOfMaterials.size() - 1] += multiplier;
                remainingDepth -= listOfMaterials.get(listOfMaterials.size() - 1).getLength() / 10;
            }
        }

        //Then we convert the int array to actual BillLines, containing the amounts we need.
        for(int i = 0; i < amountUsedForEachMaterial.length; i++) {
            if(amountUsedForEachMaterial[i] > 0) {
                listToBeReturned.add(new BillLine(listOfMaterials.get(i), amountUsedForEachMaterial[i], categoryDescription));
            }
        }
        return listToBeReturned;
    }

    public static ArrayList<BillLine> screwsForRoofPanels(ArrayList<Category> categoriesUsedInGenerator,Order order) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        //divided by 100 to convert to meter
        int orderWidth = order.getWidth() / 100;
        int orderDepth = order.getDepth() / 100;
        int amountOfScrewPacks = 0;
        //As of now, we use the first screw pack in the category
        Material screwPackMaterial = categoriesUsedInGenerator.get(0).getMaterialAtIndex(0);
        int screwsPerPack = screwPackMaterial.getAmount();

        //square meter
        double squareMeter = orderWidth * orderDepth;

        //We need 12 screws per square meter
        int neededScrews = (int) (squareMeter * 12);

        //Calculate how many packs are needed
        while(neededScrews > 0){
            neededScrews -= screwsPerPack;
            amountOfScrewPacks++;
        }

        BillLine billLine = new BillLine(screwPackMaterial,amountOfScrewPacks, categoryDescription);
        billLines.add(billLine);

        return billLines;
    }
}
