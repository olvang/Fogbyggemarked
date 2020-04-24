package FunctionLayer.BillGenerator;

import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.util.ArrayList;

public class CarportGenerator {

    public static ArrayList<BillLine> underSternsBredderFrontAndBack(ArrayList<Category> categoriesUsedInGenerator, WidthComponent carportWidth) throws GeneratorException {
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine;
        Material material  = null;
        int count = 0;
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

            //If the the material height is bigger than the width of the carport, we use that material
            if(fullWidth / material.getLength().getLength() >= 1){
                //Material found
                count = 1;
                break;
            }else if(i == 0){
                int fullWidthCalc = fullWidth;
                //If No material found, we use the material anyway as it is the biggest we have
                //We then calculate how many are needed to fill out the full width of the carport
                while (fullWidthCalc / material.getLength().getLength() > 0){
                    count++;
                    fullWidthCalc -= material.getLength().getLength();
                }
            }
        }

        //We can now use the material from above
        //Width of carport * 2 (2x because front & back) / length of underSternsBredder (Calculate best fit)
        boardAmount = (fullWidth / material.getLength().getLength()) * count;


        //If boardAmount is more than 1 a calculation has been made, else something went wrong
        if(boardAmount >= 1){
            billLine = new BillLine(material,boardAmount);
            billLines.add(billLine);
        }else{
            throw new GeneratorException("understernbrædder til for & bag ende kunne ikke beregnes");
        }
        return billLines;
    }

    public static ArrayList<BillLine> underSternsBredderSides(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
    }

    public static ArrayList<BillLine> overSternBredderFront(ArrayList<Category> categoriesUsedInGenerator) {
        return null;
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
            if(carportHeight / material.getLength().getLength() > 0){
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
