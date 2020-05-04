package FunctionLayer.BillGenerator;

import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.util.ArrayList;

public class InclinedRoofGenerator {

    public static ArrayList<BillLine> soffit(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material = null;
        int amountOfSoffitPerEnd = 0;

        //We need to calculate the length of the roof
        //We know A = incline, b = order Width / 2, and C = 90
        //Therefor we can calculate side c
        // c = b / cos(A)
        double roofLength = GeneratorUtilities.calculateRoofLength(order.getInclineComponent(), order.getWidth());

        //We know what the length of one side of the roof, on the front end, so to get both sides of the roof * 2
        roofLength *= 2;

        //Sort the materials in the category
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //Now we can run trough each material to get what best fits
        for (int i = materialsSortedByLength.size() - 1; i > -1; i--) {
            material = materialsSortedByLength.get(i);

            //If the the material length is bigger than the roofLength, we use that material
            if (material.getLength() / roofLength >= 1) {
                //Fitting Material found, which means we only need 1 per End
                amountOfSoffitPerEnd = 1;
                break;
            } else if (i == 0) {
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the roofLength

                //We then keep on adding the material, until the roofLength has been filled
                while (roofLength > 0) {
                    amountOfSoffitPerEnd++;
                    roofLength -= material.getLength();
                }
            }
        }


        //Returns the billLine, amountOfSoffitPerEnd * 2 since we need
        billLine = new BillLine(material, amountOfSoffitPerEnd * 2);
        billLines.add(billLine);

        return billLines;
    }


    public static ArrayList<BillLine> roofLath(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material = null;
        //We * 2, since we then calculate both sides at the same time
        int roofDepth = order.getDepth().getDepth() * 2;
        int amountOfBoards = 0;

        //Sort the materials in the category
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //We calculate which and how many materials that best fit the depth of the carport
        for (int i = materialsSortedByLength.size() - 1; i > -1; i--) {
            material = materialsSortedByLength.get(i);

            //If the the material length is bigger than the roofLength, we use that material
            if ((material.getLength() / 10) / roofDepth >= 1) {
                //Fitting Material found, which means we only need 1 per End
                amountOfBoards = 1;
                break;
            } else if (i == 0) {
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the roofLength

                //We then keep on adding the material, until the roofLength has been filled
                while (roofDepth > 0) {
                    amountOfBoards++;
                    roofDepth -= material.getLength() / 10;
                }
                break;
            }
        }

        //Returns the billLine, amountOfBoards * 2 since we need
        billLine = new BillLine(material, amountOfBoards);
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> boardsForGabled(ArrayList<Category> categoriesUsedInGenerator, Order order){

        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;

        Material material;
        Material materialToUse  = null;

        double height = GeneratorUtilities.calculateRoofHeight(order.getInclineComponent(),order.getWidth()) * 10;
        double width = (order.getWidth().getWidth() / 2.0) * 10;

        //The area of the front facing Gabled
        double area = height * width;

        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material = materialsSortedByLength.get(i);

            if(material.getLength() > height){
                materialToUse = material;
                break;
            }
        }

        //We then need to subtract the minimum coverage = which is 2 * 15 mm = 30 mm
        int twoBoardWidths = 2 * materialToUse.getWidth() - 30;

        //This is one side
        double amountOfBoardsToUse = (width / twoBoardWidths) * 2;

        //We need to the back aswell. Plus a error magin of .05
        int amountOfBoards = (int) Math.ceil(amountOfBoardsToUse * 2.05);

        billLine = new BillLine(materialToUse,amountOfBoards );
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> rygsten(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        //Amount is calculated by taking depth in m * 3
        ArrayList<BillLine> list = new ArrayList<>();

        double depth = order.getDepth().getDepth();
        depth = depth / 100; //since the depth is stored in cm
        int amount = (int) Math.ceil(depth * 3.0);

        BillLine line = new BillLine(categoriesUsedInGenerator.get(0).getMaterials().get(0), amount);
        list.add(line);

        return list;
    }
}
