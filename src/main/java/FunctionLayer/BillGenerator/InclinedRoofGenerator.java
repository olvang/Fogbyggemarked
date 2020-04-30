package FunctionLayer.BillGenerator;

import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.util.ArrayList;

public class InclinedRoofGenerator {

    public static ArrayList<BillLine> soffit(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material  = null;
        int amountOfSoffitPerEnd = 0;

        //We need to calculate the length of the roof
        //We know A = incline, b = order Width / 2, and C = 90
        //Therefor we can calculate side c
        // c = b / cos(A)
        double roofLength = (order.getWidth().getWidth() / 2) / Math.cos(order.getIncline());

        //We know what the length of one side of the roof, on the front end, so to get both sides of the roof * 2
        roofLength *= 2;

        //Sort the materials in the category
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //Now we can run trough each material to get what best fits
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material =  materialsSortedByLength.get(i);

            //If the the material length is bigger than the roofLength, we use that material
            if(material.getLength() / roofLength >= 1){
                //Fitting Material found, which means we only need 1 per End
                amountOfSoffitPerEnd = 1;
                break;
            }else if(i == 0){
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the roofLength

                //We then keep on adding the material, until the roofLength has been filled
                while (roofLength > 0){
                    amountOfSoffitPerEnd++;
                    roofLength -= material.getLength();
                }
            }
        }


        //Returns the billLine, amountOfSoffitPerEnd * 2 since we need
        billLine = new BillLine(material,amountOfSoffitPerEnd * 2);
        billLines.add(billLine);

        return billLines;
    }
}
