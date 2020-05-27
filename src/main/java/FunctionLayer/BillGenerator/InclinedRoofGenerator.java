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
        double roofLength = GeneratorUtilities.calculateRoofLength(order.getInclineComponent(), order.getWidthComponent());

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
        billLine = new BillLine(material, amountOfSoffitPerEnd * 2, categoriesUsedInGenerator.get(0).getDescription());
        billLines.add(billLine);

        return billLines;
    }


    public static ArrayList<BillLine> roofLath(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;
        Material material = null;
        //We * 2, since we then calculate both sides at the same time
        int roofDepth = order.getDepth() * 2;
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
        billLine = new BillLine(material, amountOfBoards, categoriesUsedInGenerator.get(0).getDescription());
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> boardsForGabled(ArrayList<Category> categoriesUsedInGenerator, Order order){

        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;

        Material material;
        Material materialToUse  = null;

        double height = GeneratorUtilities.calculateRoofHeight(order.getInclineComponent(),order.getWidthComponent()) * 10;
        double width = (order.getWidth() / 2.0) * 10;

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

        billLine = new BillLine(materialToUse,amountOfBoards, categoriesUsedInGenerator.get(0).getDescription());
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> rygsten(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        //Amount is calculated by taking depth in m * 3
        ArrayList<BillLine> list = new ArrayList<>();

        double depth = order.getDepth();
        depth = depth / 100; //since the depth is stored in cm
        int amount = (int) Math.ceil(depth * 3.0);

        BillLine line = new BillLine(categoriesUsedInGenerator.get(0).getMaterials().get(0), amount, categoriesUsedInGenerator.get(0).getDescription());
        list.add(line);

        return list;
    }

    public static ArrayList<BillLine> roofLathOnSper(ArrayList<Category> categoriesUsedInGenerator, Order order) {

        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        Material material = materialsSortedByLength.get(0);
        int longestMaterialLength = material.getLength() / 10;
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;

        int depth = order.getDepth();

        int rows = getAmountOfRowsOnSper(depth);

        double needToBeCoveredDepth = depth * 1.8;

        int amountUsed = (int) needToBeCoveredDepth / longestMaterialLength;

        if(needToBeCoveredDepth < longestMaterialLength){
            billLine = new BillLine(material,rows, categoryDescription);
            billLines.add(billLine);
            return billLines;
        }



        billLines.add(new BillLine(material,amountUsed * rows, categoryDescription));

        //The answer is already more or less correct, adding this wildly increases the amount used.
        /*double rest = needToBeCoveredDepth % material.getLength();

        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material = materialsSortedByLength.get(i);

            if(material.getLength() / 100 > rest){
                billLines.add(new BillLine(material,amountUsed * rows, categoryDescription));
                return billLines;
            }else if(i == 0){
                billLines.add(new BillLine(material,amountUsed * rows, categoryDescription));
                return billLines;
            }
        }*/

        return billLines;
    }

    public static ArrayList<BillLine> topRoofLath(ArrayList<Category> categoriesUsedInGenerator, Order order) throws GeneratorException {
        ArrayList<Material> possibleMaterials = categoriesUsedInGenerator.get(0).getMaterials();
        int orderDepth = order.getDepth();
        Material materialToUse = null;
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();

        int lowestAmount = Integer.MAX_VALUE;
        for(Material mat : possibleMaterials) {
            double length = mat.getLength() / 10.0; //divided by 10 because length is stored in mm
            double result = orderDepth / length;
            int amount = (int) Math.ceil(result);
            if(amount < lowestAmount) {
                lowestAmount = amount;
                materialToUse = mat;
            }
        }
        if(materialToUse == null) {
            throw new GeneratorException("No material was selected in topRooFlath");
        }
        BillLine line = new BillLine(materialToUse, lowestAmount, categoryDescription);
        ArrayList<BillLine> list = new ArrayList<>();
        list.add(line);

        return list;
    }

    public static ArrayList<BillLine> topRoofLathHolder(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        //Amount is calculated by taking depth in m / 0.9
        ArrayList<BillLine> list = new ArrayList<>();
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();

        double depth = order.getDepth();
        depth = depth / 100; //since the depth is stored in cm
        int amount = (int) Math.ceil(depth / 0.9);

        BillLine line = new BillLine(categoriesUsedInGenerator.get(0).getMaterials().get(0), amount, categoryDescription);
        list.add(line);

        return list;

    }

    public static ArrayList<BillLine> rygstenBracket(ArrayList<Category> categoriesUsedInGenerator, int amountOfRygsten) {
        Material materialToUse = categoriesUsedInGenerator.get(0).getMaterials().get(0);
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        BillLine line = new BillLine(materialToUse, amountOfRygsten, categoryDescription);
        ArrayList<BillLine> list = new ArrayList<>();
        list.add(line);
        return list;
    }

    public static ArrayList<BillLine> roofTileBinders(ArrayList<Category> categoriesUsedInGenerator, int roofTilesAmount, int rygstenAmount) {
        ArrayList<Material> possibleMaterials = GeneratorUtilities.sortMaterialsByAmount(categoriesUsedInGenerator.get(0).getMaterials());
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();

        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;

        Material material;
        Material materialToUse = null;

        int amountOfPackages = 0;

        //we need two for each stone, so we * 2
        int roofTileBindersNeeded = (roofTilesAmount + rygstenAmount) * 2;

        //Then we need to check which material best fits
        for (int i = possibleMaterials.size()-1; i > -1; i--) {
            material = possibleMaterials.get(i);

            //First we check if any of the materials has enough amounts
            //we start with the smallest, if one has enough amounts we use that material
            if(material.getAmount() > roofTileBindersNeeded){
                materialToUse = material;
                amountOfPackages = 1;
                break;
            }else if(i == 0){
                //If it is the last material, we use it anyways and calculates how many we need
                materialToUse = material;

                while (roofTileBindersNeeded > 0) {
                    amountOfPackages++;
                    roofTileBindersNeeded -= materialToUse.getAmount();
                }
            }
        }


        billLine = new BillLine(materialToUse,amountOfPackages, categoryDescription);
        billLines.add(billLine);

        return billLines;
    }

    public static ArrayList<BillLine> screwsForRoofLaths(ArrayList<Category> categoriesUsedInGenerator, ArrayList<BillLine> topRoofLath, ArrayList<BillLine> roofLathOnSper) {

        Material materialToUse = categoriesUsedInGenerator.get(0).getMaterials().get(0);
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();

        double total = 0;

        for (BillLine billline: topRoofLath) {
            total = total + billline.getAmount() * (billline.getMaterial().getLength() / 10);
        }

        for (BillLine billline: roofLathOnSper) {
            total = total + billline.getAmount() * (billline.getMaterial().getLength() / 10);
        }

        int packsOfScrews = (int) Math.ceil(total / 60 / materialToUse.getAmount());

        ArrayList<BillLine> billLines = new ArrayList<>();

        billLines.add(new BillLine(materialToUse,packsOfScrews,categoryDescription));

        return billLines;
    }

    public static ArrayList<BillLine> roofTiles(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());
        Material material = materialsSortedByLength.get(0);
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine;

        double depth = order.getDepth() / 100.0;
        double length = GeneratorUtilities.calculateRoofLength(order.getInclineComponent(),order.getWidthComponent()) / 100;

        double area = depth * length * 2;

        //there is used 9 rooftiles pr. m^2
        int amountOfRootTilesUsed = (int) area * 9;

        billLine = new BillLine(material,amountOfRootTilesUsed, categoryDescription);

        billLines.add(billLine);

        return billLines;

    }

    public static int getAmountOfRowsOnSper(int orderDepth) {
        //One rooftile needs one lath pr. 40 cm
        return (int) Math.ceil(orderDepth / 50.0);
    }
}
