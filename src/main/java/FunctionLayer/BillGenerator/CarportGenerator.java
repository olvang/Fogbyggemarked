package FunctionLayer.BillGenerator;

import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarportGenerator {

    public static ArrayList<BillLine> underSternsBredderFrontAndBack(ArrayList<Category> categoriesUsedInGenerator, WidthComponent carpotWidth) {
        return null;
    }

    public static ArrayList<BillLine> underSternsBredderSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> overSternBredderFront(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        int carportWidth = order.getWidth().getWidth();
        BillLine toBeReturned;
        Category cat = categoriesUsedInGenerator.get(0);
        ArrayList<Material> materials = cat.getMaterials();

        //We need to find the size that can
        if(materials.size() > 1) {
            int fewest = Integer.MAX_VALUE;
            int leftoverOfFewest = Integer.MIN_VALUE;
            int indexOfFewest = -1;

            for(int i = 0; i < materials.size(); i++) {
                int widthLeftover = carportWidth;
                int amountNeeded = 0;
                while (widthLeftover > 0) {
                    widthLeftover -= materials.get(i).getLength().getLength();
                    amountNeeded++;
                }

                if( amountNeeded < fewest && amountNeeded != 0 ) {
                    //if current amount needed is the lowest
                    fewest = amountNeeded;
                    leftoverOfFewest = widthLeftover;
                    indexOfFewest = i;
                }else if(amountNeeded == fewest) {
                    //If the same number of materials are needed, we check which one
                    // has to be cut the least
                    if(widthLeftover > leftoverOfFewest) {
                        fewest = amountNeeded;
                        leftoverOfFewest = widthLeftover;
                        indexOfFewest = i;
                    }
                }
            }
            toBeReturned = new BillLine(materials.get(indexOfFewest), fewest);
        } else {
            int widthLeftover = carportWidth;
            int amountNeeded = 0;
            while (widthLeftover > 0) {
                widthLeftover -= materials.get(0).getLength().getLength();
                amountNeeded++;
            }
            toBeReturned = new BillLine(materials.get(0), amountNeeded);
        }

        return new ArrayList<BillLine>() {{add(toBeReturned);}};
    }

    public static ArrayList<BillLine> overSternBredderSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }
    

    public static ArrayList<BillLine> RemInSidesCarport(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> sperOnRem(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> posts(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;

        int numberOfPostRows = 2;
        int numberOfPostPerRow = 2;
        int total = 0;
        int carportWidth = order.getWidth().getWidth();
        int carportDepth = order.getDepth().getDepth();
        int carportHeight = order.getHeight().getHeight();

        //If order has shed, subtract they width of the shed
        //ShedGenerator calculates how many post are needed within the shed
        if(order.isWithShed()){
            carportDepth -= order.getShedDepth().getDepth();
        }


        //For each 600 cm, add another post row
        if(carportWidth > 600){
            numberOfPostRows += carportWidth / 600;
        }

        //Subtracts 100 cm from the front, 30 cm from the back = 130 cm
        carportDepth -= 130;

        //For each 310 cm, add another post on that row
        numberOfPostPerRow += carportDepth / 310;

        total = numberOfPostPerRow * numberOfPostRows;

        //Sorts the materials(posts) by their length, DESC
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());


        //TODO Remove if the customer selects from a dropdown instead of inputfield
        //Loops through all materials, to find best fits starting with the smallest first
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material material =  materialsSortedByLength.get(i);

            //If the the material height can fit within remaining carport heigth
            if(carportHeight / material.getLength().getLength() > 0){
                billLine = new BillLine(material,total);


            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,total);
            }

            if(billLine != null){
                billLines.add(billLine);
                return billLines;
            }else{
                //TODO Throw correct exception
                try {
                    throw new CommandException("Kunne ikke udregne stoplerne");
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static ArrayList<BillLine> perforatedBand(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> UniversalBeslagRight(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> screwsForSternAndWaterBoard(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> screwsForUniversalBeslagAndPerforatedBand(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> boltsForRemOnPost(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> skiverForRemOnPost(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> screwsForOuter(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> screwsForInner(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> UniversalBeslagLeft(ArrayList<Category> materialsUsedInGenerator) {
        return null;
    }
}
