package FunctionLayer.BillGenerator;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.WidthComponent;
import FunctionLayer.BillLine;
import FunctionLayer.Category;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Material;
import FunctionLayer.Order;

import java.util.ArrayList;

/**
 * Used to Generate / Calculate the BillLines associated with a carport
 */
public class CarportGenerator {

    /**
     * <p>Calculates the amount of under sterns brædder used in front and back</p>
     * <p>Finds the best fitting board based on the length of the board.</p>
     * <p>Starting with the smallest.</p>
     * @param carportWidth The width of the carport
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     * @throws GeneratorException An exception for when a generator fails
     */

    public static ArrayList<BillLine> underSternsBredderFrontAndBack(ArrayList<Category> categoriesUsedInGenerator, WidthComponent carportWidth) throws GeneratorException {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
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
            if((material.getLength() / 10) / carportWidth.getWidth()  >= 1){
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
                    fullWidthCalc -= material.getLength() / 10;
                }
            }
        }


        //If boardAmount is more than 1 a calculation has been made, else something went wrong
        if(boardAmount >= 1){
            billLine = new BillLine(material,boardAmount, categoryDescription);
            billLines.add(billLine);
        }else{
            throw new GeneratorException("understernbrædder til for & bag ende kunne ikke beregnes");
        }
        return billLines;
    }

    /**
     * <p>Calculates the amount of stern bærdder needed in the sides.</p>
     * <p>Find the amount of the biggest board that can be used. Then loops through all</p>
     * <p>materials to find the board that best fits the rest.</p>
     * @param depthCom The depth of the carport
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> sternsBredderSides(ArrayList<Category> categoriesUsedInGenerator, DepthComponent depthCom) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        //Gets the length of the longest material in this category
        int longestMaterialLength = materialsSortedByLength.get(0).getLength() / 10;
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
            billLine = new BillLine(materialsSortedByLength.get(0),amountUsed, categoryDescription);
            billLines.add(billLine);
            return billLines;
        }else{
            //Else we need to find how many times the longest material go into the depth.
            amountUsed = (depth / longestMaterialLength) * 2;
            billLines.add(new BillLine(materialsSortedByLength.get(0),amountUsed, categoryDescription));
            //And then find the rest
            rest = depth % longestMaterialLength;
        }
        //Then loop through all materials in the category to find the best suited for the purpose.
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material material =  materialsSortedByLength.get(i);

            //If the rest - the current lenght is less than 0. We know
            //The current lenght is long enough.
            if(rest - (material.getLength() /10) < 0){
                amountUsed = 2;
                //TODO Change name of material
                billLine = new BillLine(material,amountUsed, categoryDescription);
                billLines.add(billLine);
                return billLines;

            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,amountUsed, categoryDescription);
                billLines.add(billLine);
                return billLines;
            }
        }
        return billLines;
    }

    /**
     * <p>Calculates the amount of over sterns brædder used in the front</p>
     * <p>Finds the best fitting board based on the length of the board.
     * <p>Starting with the smallest. Then checking the rest, and finding
     * the board that will need the least cutting</p>
     * @param order The order object to calculate from
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> overSternBredderFront(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        int carportWidth = order.getWidth();
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
                    widthLeftover -= materials.get(i).getLength() / 10;
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
            toBeReturned = new BillLine(materials.get(indexOfFewest), fewest, categoryDescription);
        } else {
            int widthLeftover = carportWidth;
            int amountNeeded = 0;
            while (widthLeftover > 0) {
                widthLeftover -= materials.get(0).getLength() /10;
                amountNeeded++;
            }
            toBeReturned = new BillLine(materials.get(0), amountNeeded, categoryDescription);
        }

        return new ArrayList<BillLine>() {{add(toBeReturned);}};
    }

    /**
     *<p>Calculates the amount of rems used in the carport</p>
     * <p>Find how many rows based on the width. Then finds the amount
     * of the largest fitting board that can be used. The rest is
     * then calculated with the smallest first.</p>
     * @param depthCom The depth of the carport
     * @param widthCom The width of the carport
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> RemInSidesCarport(ArrayList<Category> categoriesUsedInGenerator, DepthComponent depthCom, WidthComponent widthCom) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine = null;
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());
        int rows = 0;
        int amountUsed = 0;
        int rest = 0;
        int longestMaterialLength = materialsSortedByLength.get(0).getLength() / 10;

        int depth = depthCom.getDepth();
        int width = widthCom.getWidth();

        if(width < 600){
            rows = 2;
        }else{
            rows = 3;
        }

        if(depth < longestMaterialLength){
            for (Material mat: materialsSortedByLength) {
                if(mat.getLength() / 10 - depth > 0){
                    billLine = new BillLine(mat,rows, categoryDescription);
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
            if(rest - material.getLength() / 10 < 0){
                amountUsed = amountUsed + rows;
                billLine = new BillLine(material,amountUsed, categoryDescription);

            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,amountUsed, categoryDescription);
            }
            billLines.add(billLine);
        }
        return billLines;
    }

    /**
     * <p>Calculates the amount of sper used in the carport</p>
     * <p>Looping through all the materials finding the best fitting one
     * that is shorter than or equal to the depth of the carport</p>
     * @param order The order object to calculate on
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     * @throws GeneratorException An exception for when a generator fails
     */

    public static ArrayList<BillLine> sperOnRem(ArrayList<Category> categoriesUsedInGenerator, Order order) throws GeneratorException {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        int carportDepth = order.getDepth();
        int carportWidth = order.getWidth();
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

        amount = getAmountOfSper(carportDepth);
        if(sperToUse != null) {
            line = new BillLine(sperToUse, amount, categoryDescription);
        } else {
            throw new GeneratorException("Something went wrong while calculating the spærs.");
        }

        return new ArrayList<BillLine>() {{add(line);}};
    }

    /**
     *<p>Calculates the amount of posts used in the carport</p>
     * <p>Loops through materials to find the best fitting posts
     * based on the height of the carport.</p>
     * @param order The order object to calculate on
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     * @throws GeneratorException An exception for when a generator fails
     */

    public static ArrayList<BillLine> posts(ArrayList<Category> categoriesUsedInGenerator, Order order) throws GeneratorException {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;

        int numberOfPostRows;
        int numberOfPostPerRow = 2;
        int total = 0;
        int carportWidth = order.getWidth();
        int carportDepth = order.getDepth();
        //Subtract 90 cm, because that is how much the post needs to go down in the ground
        int carportHeight = order.getHeight() - 90;

        //If order has shed, subtract they width of the shed
        //ShedGenerator calculates how many post are needed within the shed
        if(order.isWithShed()){
            carportDepth -= order.getShedDepth();
        }

        numberOfPostRows = getAmountOfPostRows(carportWidth);

        total = getAmountOfPosts(carportDepth, numberOfPostRows);

        //Sorts the materials(posts) by their length, DESC
        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());


        //TODO Remove if the customer selects from a dropdown instead of inputfield
        //Loops through all materials, to find best fits starting with the smallest first
        for (int i = materialsSortedByLength.size()-1; i > -1; i--) {
            Material material =  materialsSortedByLength.get(i);

            //If the the material height can fit within remaining carport heigth
            if(carportHeight / material.getLength() > 0){
                billLine = new BillLine(material,total, categoryDescription);


            } else if(i == 0){
                //If it is the last material(largest) then use that
                billLine = new BillLine(material,total, categoryDescription);
            }



        }
        if(billLine != null){
            billLines.add(billLine);
        }else{
                throw new GeneratorException("Kunne ikke udregne stoplerne");
        }
        return billLines;
    }

    /**
     *<p>Calculates how many perforated band used in carport</p>
     * <p>Find the length needed to be covered. Add bands to cover the
     * diagonal.</p>
     * @param order The order object to calculate on
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> perforatedBand(ArrayList<Category> categoriesUsedInGenerator, Order order) {
        int width = order.getWidth();
        int depth = order.getDepth();
        int diagonal = (int) Math.ceil( Math.sqrt((width*width) + (depth*depth)) );
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();

        Category category = categoriesUsedInGenerator.get(0);
        ArrayList<Material> list = category.getMaterials();
        GeneratorUtilities.sortMaterialsByLength(list);

        Material materialToUse = list.get(0);

        // times 2 because we need one going each diagonal direction
        int amountToBeOrdered = (int) (Math.ceil(1.0 * diagonal / (materialToUse.getLength() / 10))) * 2;


        return new ArrayList<BillLine>() {{add(new BillLine(materialToUse, amountToBeOrdered, categoryDescription));}};
    }

    /**
     * <p>Calculates how many universal beslag of the right handside kind needed in carport
     * sper * (remme / 2) - 1</p>
     * @param sper The amount of sper used
     * @param remme The amount of remme used
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> UniversalBeslagRight(ArrayList<Category> categoriesUsedInGenerator, int sper, int remme) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;

        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        int amountUsed = sper * ((remme/2)-1);

        billLine = new BillLine(materialsSortedByLength.get(0),amountUsed, categoryDescription);
        billLines.add(billLine);

        return billLines;
    }

    /**
     * <p>Calculates how many universal beslag of the left handside kind needed in carport</p>
     * <p>Just needs an amount equals to the amount of sper</p>
     * @param sper The amount of sper used
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> UniversalBeslagLeft(ArrayList<Category> categoriesUsedInGenerator, int sper) {
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<BillLine>();
        BillLine billLine = null;

        ArrayList<Material> materialsSortedByLength = GeneratorUtilities.sortMaterialsByLength(categoriesUsedInGenerator.get(0).getMaterials());

        billLine = new BillLine(materialsSortedByLength.get(0),sper, categoryDescription);
        billLines.add(billLine);

        return billLines;
    }

    /**
     * <p>Calculates the amount of boxes of screws need for stern of water boards</p>
     * <p>First need to make sure we have the amount of waterboards.</p>
     * <p>Desc: Længden af vand brædder / 13,5</p>
     * <p>Loop through all box sizes and find the best fitting one.</p>
     * @param finishedList The lists of waterboards from another method
     * @param order The order object to be calculated on
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> screwsForSternAndWaterBoard(ArrayList<Category> categoriesUsedInGenerator, Order order, ArrayList<BillLine> finishedList) {
        //Desc: Længden af vand brædder / 13,5
        String categoryDescription = categoriesUsedInGenerator.get(0).getDescription();
        ArrayList<Material> waterboards = new ArrayList();
        Category screws = categoriesUsedInGenerator.get(0);
        //<editor-fold desc="Gets the waterboards, one way or another" defaultstate="collapsed">

        //These screws are based on the length of the waterboards. We therefore need to check the results of
        // that calculation.
        for(BillLine line : finishedList) {
            if(line.getMaterial().getCategory() == 13 || line.getMaterial().getCategory() == 14) {
                Material mat = line.getMaterial();
                mat.setAmount(line.getAmount());
                waterboards.add(mat);
            }
        }
        //There is however a chance that this is being calculated before the waterboards have been.
        //If that is the case, we'll have to run the calculation ourselves.
        if(waterboards.size() == 0) {
            ArrayList<BillLine> frontBoards = FlatRoofGenerator.waterBoardOnSternFront(
                    new ArrayList<Category>() {{add(categoriesUsedInGenerator.get(1));}}, order
            );
            for(BillLine line : frontBoards) {
                line.getMaterial().setAmount(line.getAmount());
                waterboards.add(line.getMaterial());
            }
            //This method is called from multiple places, and one of them only doesn't use this category
            if(categoriesUsedInGenerator.size() > 2) {
                ArrayList<BillLine> sideBoards = FlatRoofGenerator.waterBoardOnSternSides(
                        new ArrayList<Category>() {{add(categoriesUsedInGenerator.get(2));}}, order.getDepthComponent()
                );
                for(BillLine line : sideBoards) {
                    line.getMaterial().setAmount(line.getAmount());
                    waterboards.add(line.getMaterial());
                }
            }
        }
        //</editor-fold>

        //At this point we can be sure that we have access to the waterboards.
        int amountOfScrewsNeeded = 0;
        int fullLength = 0;
        for(Material board : waterboards) {
            fullLength += (board.getLength() / 10) * board.getAmount();
        }
        amountOfScrewsNeeded = (int) Math.ceil(fullLength / 13.5);

        //If there is only one box size to choose from, we just use that.
        if(screws.getMaterials().size() == 1) {
            Material materialUsed = screws.getMaterials().get(0);
            int amountInBox = screws.getMaterials().get(0).getAmount();
            int total = (int) Math.ceil(1.0 * amountOfScrewsNeeded / amountInBox);
            return new ArrayList<BillLine>() {
                {
                    add(new BillLine(materialUsed, total, categoryDescription));
                }
            };
        }

        //Otherwise we have to find the most fitting one
        Material fewest = screws.getMaterials().get(0);
        int boxesNeeded = -1;
        for(Material box : screws.getMaterials()) {
            int amountInBox = box.getAmount();
            double total = amountOfScrewsNeeded / amountInBox;
            if(total < fewest.getAmount() && total > 1) {
                fewest = box;
                boxesNeeded = (int) Math.ceil(total);
            }
        }

        Material finalFewest = fewest;
        int finalBoxesNeeded = boxesNeeded;
        return new ArrayList<BillLine>() {
            {
                add(new BillLine(finalFewest, finalBoxesNeeded, categoryDescription));
            }
        };
    }

    /**
     * <p>Calculate the amount of screws used for universals beslag and Perforated band</p>
     * <p>DESC: 3 beslagskruer pr. beslagflade (3) (3*3).
     * + 2 skruer i hvert spær som krydses af hulbåndet</p>
     * <p>Then lopå through all box sizes to find the best fitting</p>
     * @param amountOfbeslag The amount of sper beslags used
     * @param order The order object to be calculated on
     * @param categoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     * @throws GeneratorException An exception for when a generator fails
     */

    public static ArrayList<BillLine> screwsForUniversalBeslagAndPerforatedBand(ArrayList<Category> categoriesUsedInGenerator, Order order, int amountOfbeslag) throws GeneratorException {
        //DESC: 3 beslagskruer pr. beslagflade (3) (3*3).
        String categoryDescription = categoriesUsedInGenerator.get(1).getDescription();
        // + 2 skruer i hvert spær som krydses af hulbåndet
        try {
            Category screws = categoriesUsedInGenerator.get(1);
            int amountOfScrewsNeeded = 0;

            //perforated band on spær:

            //The perforated band doesn't cross the sper above the shed, so those should be removed from the
            //calculation.
            Order orderWithoutShedDepth;
            if(order.isWithShed() == true) {
                //Makes an exact Deep Copy of the order, so we can make changes to it without affecting the actual order
                orderWithoutShedDepth = new Order(
                        new DepthComponent(order.getDepth()),
                        new HeightComponent(order.getHeight()),
                        new WidthComponent(order.getWidth()), order.getInclineComponent(), false
                ,order.getCustomer());
            } else {
                orderWithoutShedDepth = order;
            }
            //calculates the amount of sper not above the shed
            ArrayList<BillLine> sper = CarportGenerator.sperOnRem(
                    new ArrayList<Category>() {{add(categoriesUsedInGenerator.get(0));}},
                    orderWithoutShedDepth);
            int sperAmount = sper.get(0).getAmount();
            //2 screws for each sper, for both perforated bands.
            amountOfScrewsNeeded += (sperAmount * 2) * 2;

            //Besæagflader:
            int skruerPrFlade = 3;
            int beslagFlader = 3;
            amountOfScrewsNeeded += (amountOfbeslag * beslagFlader) * skruerPrFlade;

            //If there is only one box size to choose from, we just use that.
            if(screws.getMaterials().size() == 1) {
                Material materialUsed = screws.getMaterials().get(0);
                int amountInBox = screws.getMaterials().get(0).getAmount();
                int total = (int) Math.ceil(1.0 * amountOfScrewsNeeded / amountInBox);
                return new ArrayList<BillLine>() {
                    {
                        add(new BillLine(materialUsed, total, categoryDescription));
                    }
                };
            }

            //Otherwise we have to find the most fitting one
            Material fewest = screws.getMaterials().get(0);
            int boxesNeeded = -1;
            for(Material box : screws.getMaterials()) {
                int amountInBox = box.getAmount();
                double total = amountOfScrewsNeeded / amountInBox;
                if(total < fewest.getAmount() && total > 1) {
                    fewest = box;
                    boxesNeeded = (int) Math.ceil(total);
                }
            }

            Material finalFewest = fewest;
            int finalBoxesNeeded = boxesNeeded;
            return new ArrayList<BillLine>() {
                {
                    add(new BillLine(finalFewest, finalBoxesNeeded, categoryDescription));
                }
            };

        }catch(ValidationFailedException ex) {
            throw new GeneratorException("Something unknown went wrong in ScrewsForUniversalBeslagAndPerforatedBand");
        }
    }

    /**
     * <p>Calculate the amount of bolts for rems</p>
     * <p>DESC: 2 bolts need for each post</p>
     * <p>DESC: isShed + 4</p>
     * @param amountOfPosts The amount of posts in the carport
     * @param withShed is the order with or without shed
     * @param CategoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> boltsForRemOnPost(ArrayList<Category> CategoriesUsedInGenerator, int amountOfPosts, boolean withShed) {
        String categoryDescription = CategoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine = null;
        //We need 2 bolts for each post
        int boltAmount = amountOfPosts * 2;

        //If there is a shed, we need 4 more
        if(withShed){
            boltAmount += 4;
        }

        //Since there is no calculation for which bolts to select, we just use the first one available to us
        billLine = new BillLine(CategoriesUsedInGenerator.get(0).getMaterialAtIndex(0),boltAmount, categoryDescription);

        billLines.add(billLine);

        return billLines;
    }

    /**
     * <p>Calculate the amount of skiver used for rems</p>
     * <p>DESC: amount of skirver = amount of bolts</p>
     * @param amountOfBolts The amount of posts in the carport
     * @param CategoriesUsedInGenerator the categories used in this generator
     * @return An Arraylist of BillLines with the materials needed
     */

    public static ArrayList<BillLine> skiverForRemOnPost(ArrayList<Category> CategoriesUsedInGenerator,int amountOfBolts) {
        String categoryDescription = CategoriesUsedInGenerator.get(0).getDescription();
        ArrayList<BillLine> billLines = new ArrayList<>();
        BillLine billLine = null;

        //We need 1 skive for each bolt
        int skiveAmount = amountOfBolts;


        //Since there is no calculation for which skiver to select, we just use the first one available to us
        billLine = new BillLine(CategoriesUsedInGenerator.get(0).getMaterialAtIndex(0),skiveAmount, categoryDescription);

        billLines.add(billLine);

        return billLines;
    }


    //----------------------//
    // Getting Information //
    //---------------------//

    /**
     * <p>Takes the carport depth and return how many sper is needed</p>
     * <p>DESC: carport depth / 55</p>
     * @param carportDepth The depth of the carport
     * @return int amount of sper
     */

    public static int getAmountOfSper(int carportDepth) {
        int amount;
        amount = (int) Math.ceil(carportDepth / 55.0);
        return amount;
    }

    /**
     * <p>Takes the width of the carport and return the numbers</p>
     * <p>of rows of posts needed to support the roof.</p>
     * @param carportWidth the width of the carport
     * @return int amount of posts rows
     */

    public static int getAmountOfPostRows(int carportWidth) {
        int numberOfPostRows = 2;
        //For each 600 cm, add another post row
        if(carportWidth > 600){
            numberOfPostRows += carportWidth / 600;
        }
        return numberOfPostRows;
    }

    /**
     * <p>Takes the depth and numbers of rows and returns
     * the amoount of posts needed</p>
     * @param carportDepth the depth of the carport
     * @param numberOfRows the amount of post rows in the carport
     * @return int amount of posts
     */

    public static int getAmountOfPosts(int carportDepth, int numberOfRows) {
        //Starting with 2 posts, front and back
        int numberOfPostPerRow = 2;

        //Subtracts 100 cm from the front, 30 cm from the back = 130 cm
        carportDepth -= 130;

        //For each 310 cm, add another post on that row
        numberOfPostPerRow += carportDepth / getDistanceBetweenPosts();

        int total = numberOfPostPerRow * numberOfRows;
        return total;
    }

    /**
     * <p>The distance between two posts</p>
     * @return int the distance between two posts
     */

    public static int getDistanceBetweenPosts() {
        return 310;
    }

}
