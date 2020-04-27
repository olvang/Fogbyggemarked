package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Material;
import FunctionLayer.Order;
import PresentationLayer.Bill;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarportGenerator {

    public static ArrayList<BillLine> underSternsBredderFrontAndBack(ArrayList<Category> categoriesUsedInGenerator, WidthComponent carportWidth) throws GeneratorException {
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine;
        Material material  = null;
        int boardAmount = 0;

        //* 2 (2x because front & back)
        //5 cm for each end, 5 cm * 2 ends * 2 sides = 20 cm extra
        int fullWidth = carportWidth.getWidth() * 2 + 20;


        //Sorts the materials(underSternsBredder) by their length, DESC
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());


        //Loops through all underSternsBredder, to find best fits starting with the smallest first
        //The first board that is higher than the width will be used
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            material =  materialsSortedByLength.get(i);

            //If the the material length is bigger than the width of the carport, we use that material
            if(material.getLength() / carportWidth.getWidth()  >= 1){
                //Material found
                //Count two as we need one of the material for front and back of carport
                boardAmount = 2;
                break;
            }else if(i == 0){
                int fullWidthCalc = fullWidth;
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the full width of the carport
                while (fullWidthCalc > 0){
                    boardAmount++;
                    fullWidthCalc -= material.getLength();
                }
            }
        }


        //If boardAmount is more than 1 a calculation has been made, else something went wrong
        if(boardAmount >= 1){
            billLine = new BillLine(material,boardAmount);
            billLines.add(billLine);
        }else{
            throw new GeneratorException("understernbrædder til for & bag ende kunne ikke beregnes");
        }
        return billLines;
    }

    public static ArrayList<BillLine> sternsBredderSides(ArrayList<Category> categoriesUsedInGenerator, DepthComponent depthCom) {

        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //Gets the length of the longest material in this category
        int longestMaterialLength = materialsSortedByLength.get(0).getLength();
        //It's required we add 2.5cm on each end of the carport
        final int CONSTANT_ADD_EACH_SIDE = 5;

        int amountUsed = 0;
        int rest = 0;
        //The total depth of a carport
        int depth = depthCom.getDepth() + CONSTANT_ADD_EACH_SIDE;

        //If the carport is shorter than the longest material
        if(depth<longestMaterialLength){
            //Only two of the longest material is needed. Might need a for loop here to find the best material
            amountUsed = 2;
            billLine = new BillLine(materialsSortedByLength.get(0),amountUsed);
            billLines.add(billLine);
            return billLines;
        }else{
            //Else we need to find how many times the longest material go into the depth.
            amountUsed = (depth / longestMaterialLength) * 2;
            //And then find the rest
            rest = depth % longestMaterialLength;
        }
        //Then loop through all materials in the category to find the best suited for the purpose.
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material material =  materialsSortedByLength.get(i);

            //If the rest - the current lenght is less than 0. We know
            //The current lenght is long enough.
            if(rest - material.getLength() < 0){
                amountUsed = amountUsed + 2;
                billLine = new BillLine(material,amountUsed);

            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,amountUsed);
            }
        }
        billLines.add(billLine);
        return billLines;
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
                    widthLeftover -= materials.get(i).getLength();
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
                widthLeftover -= materials.get(0).getLength();
                amountNeeded++;
            }
            toBeReturned = new BillLine(materials.get(0), amountNeeded);
        }

        return new ArrayList<BillLine>() {{add(toBeReturned);}};
    }

    public static ArrayList<BillLine> overSternBredderSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }
    

    public static ArrayList<BillLine> RemInSidesCarport(ArrayList<Category> categoriesUsedInGenerator, DepthComponent depthCom, WidthComponent widthCom) {
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine = null;
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());
        int rows = 0;
        int amountUsed = 0;
        int rest = 0;
        int longestMaterialLength = materialsSortedByLength.get(materialsSortedByLength.size()-1).getLength();

        int depth = depthCom.getDepth();
        int width = widthCom.getWidth();

        if(width < 600){
            rows = 3;
        }else{
            rows = 2;
        }

        if(depth < longestMaterialLength){
            for (Material mat: materialsSortedByLength) {
                if(mat.getLength() - depth > 0){
                    billLine = new BillLine(mat,rows);
                    billLines.add(billLine);
                    return billLines;
                }
            }
        }else{
            //Else we need to find how many times the longest material go into the depth.
            amountUsed = (depth / longestMaterialLength) * rows;
            //And then find the rest
            rest = depth % longestMaterialLength;
        }

        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material material =  materialsSortedByLength.get(i);

            //If the rest - the current lenght is less than 0. We know
            //The current lenght is long enough.
            if(rest - material.getLength() < 0){
                amountUsed = amountUsed + rows;
                billLine = new BillLine(material,amountUsed);

            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,amountUsed);
            }
            billLines.add(billLine);
        }
        return billLines;
    }

    public static ArrayList<BillLine> sperOnRem(ArrayList<Category> categoriesUsedInGenerator, Order order) throws GeneratorException {
        int carportDepth = order.getDepth().getDepth();
        int carportWidth = order.getWidth().getWidth();
        Category category = categoriesUsedInGenerator.get(0);
        ArrayList<Material> list = category.getMaterials();

        Material sperToUse = null;
        int amount;
        BillLine line;

        GeneratorUtilities.sortMaterialsByLength(list);

        //If carport is wider than the longest plank, it cannot be built.
        if(carportWidth > list.get(0).getLength() ) {
            throw new GeneratorException("Carport is too wide");
        }


        if(list.size() == 1) {
            sperToUse = list.get(0);
        } else {
            //Loop through the list, largest to smallest, and each time a plank is longer than the carport, it
            // is added as the sperToUse. As soon as a plank is shorter than the carport, the loop is broken and
            // we know the previous plank is the shortest possible one that is still longer than the carport.
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).getLength() >= carportWidth) {
                    sperToUse = list.get(i);
                } else {
                    break;
                }
            }
        }

        amount = (int) Math.ceil(carportDepth / 55.0);
        if(sperToUse != null) {
            line = new BillLine(sperToUse, amount);
        } else {
            throw new GeneratorException("Something went wrong while calculating the spærs.");
        }

        return new ArrayList<BillLine>() {{add(line);}};
    }

    public static ArrayList<BillLine> posts(ArrayList<Category> categoriesUsedInGenerator, Order order) throws GeneratorException {
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;

        int numberOfPostRows = 2;
        int numberOfPostPerRow = 2;
        int total = 0;
        int carportWidth = order.getWidth().getWidth();
        int carportDepth = order.getDepth().getDepth();
        //Subtract 90 cm, because that is how much the post needs to go down in the ground
        int carportHeight = order.getHeight().getHeight() - 90;

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
            if(carportHeight / material.getLength() > 0){
                billLine = new BillLine(material,total);


            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,total);
            }



        }
        if(billLine != null){
            billLines.add(billLine);
        }else{
                throw new GeneratorException("Kunne ikke udregne stoplerne");
        }
        return billLines;
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
