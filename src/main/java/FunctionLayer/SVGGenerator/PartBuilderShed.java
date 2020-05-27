package FunctionLayer.SVGGenerator;


//TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
// These will be passed on to here, from the DrawingGenerator.

import FunctionLayer.BillGenerator.CarportGenerator;
import FunctionLayer.BillGenerator.ShedGenerator;


public class PartBuilderShed {
    public static void drawShed(SVG svg, int x, int y, int carportDepth, int carportWidth,int shedDepth, int shedWidth) {
        //There is a small gap between the top edge of the carport, and the rem (same on bottom).
        int plank = 5;
        int upperStartingCorner;
        int bottomStartingCorner;
        int gap = PartBuilderCarport.getGapToRem(carportWidth);
        //Right gap found on the original drawing from fog
        int rightGap = 30;

        //Vertical
        svg.addRect(x + carportDepth-shedDepth-rightGap, y + gap , plank, shedDepth);
        svg.addRect(x + carportDepth-shedDepth-rightGap, y + shedWidth - gap , plank, shedDepth);

        //Horizontal
        svg.addRect(x + carportDepth-shedDepth-rightGap, y + gap , shedWidth - gap * 2, plank);
        svg.addRect(x + carportDepth-plank-rightGap, y + gap , shedWidth - gap * 2, plank);

        //ShedLines
        //Vertical
        svg.addShedLine(x + carportDepth-shedDepth-rightGap, y + gap , x + carportDepth-rightGap, y + gap);
        svg.addShedLine(x + carportDepth-shedDepth-rightGap, y + shedWidth - gap + plank, x + carportDepth-rightGap, y + shedWidth - gap + plank);

        //Horizontal
        svg.addShedLine(x + carportDepth-shedDepth-rightGap, y + gap , x + carportDepth-shedDepth-rightGap, y+shedWidth-gap + plank);
        svg.addShedLine(x + carportDepth-rightGap, y + gap , x + carportDepth-rightGap, y+shedWidth-gap + plank);
    }

    public static void drawPerforatedBandWithShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth, int shedDepth, int shedWidth) {
        int amountOfSper = CarportGenerator.getAmountOfSper(carportDepth);
        int spaceBetweenSper = carportDepth / amountOfSper;

        int gap = PartBuilderCarport.getGapToRem(carportWidth);
        int startXa, startYa, endXa, endYa;
        int startXb, startYb, endXb, endYb;

        startXa = cornerX + spaceBetweenSper;
        startXb = cornerX + spaceBetweenSper;
        startYa = cornerY + gap;
        startYb = (cornerY + carportWidth) - gap;
        endYa = cornerY + carportWidth - gap;
        endYb = cornerY + gap;
        if (carportWidth * 0.4 <= shedWidth && carportDepth * 0.7 >= shedDepth) {
            System.out.println(1);
            endXa = (+cornerX + carportDepth) - shedDepth - spaceBetweenSper;
            endXb = (cornerX + carportDepth) - shedDepth - spaceBetweenSper;
        } else if (carportWidth * 0.4 > shedWidth && carportDepth * 0.7 >= shedDepth) {
            System.out.println(2);
            endXa = (+cornerX + carportDepth) - spaceBetweenSper;
            endXb = (cornerX + carportDepth) - shedDepth - spaceBetweenSper;
        } else if (carportWidth * 0.4 <= shedWidth && carportDepth * 0.7 < shedDepth) {
            System.out.println(3);
            endXa = (cornerX + carportDepth) - spaceBetweenSper;
            endXb = (cornerX + carportDepth) - spaceBetweenSper;
            endYa = cornerY + carportWidth - gap;
            endYb = (cornerY) + shedWidth;

            startYa = cornerY + (shedWidth);
        } else {
            System.out.println(4);
            endXa = (cornerX + carportDepth) - spaceBetweenSper;
            endXb = (cornerX + carportDepth) - spaceBetweenSper;
            endYa = cornerY + carportWidth - gap;
            endYb = (cornerY) + shedWidth;

            startYa = cornerY + (shedWidth);
        }

        //Y = Starts/Ends from the gap calculated above
        //X = Starts/Ends 1 Sper in
        svg.addLine(startXa, startYa, endXa, endYa, true);
        svg.addLine(startXb, startYb, endXb, endYb, true);
    }

    public static void drawPostsWithShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth,int shedDepth, int shedWidth) {
        int postSides = 6;
        int cornerYOriginal = cornerY;
        int amountOfRows = CarportGenerator.getAmountOfPostRows(carportWidth);
        int rowCounter = amountOfRows;
        int amountOfPosts = ShedGenerator.getAmountOfPostsWithShed(carportDepth,shedDepth,amountOfRows);
        int postsInEachRow = amountOfPosts / amountOfRows;
        int postCounter;


        int gap = PartBuilderCarport.getGapToRem(carportWidth);
        int rightGap = 30; //Right gap found on the original drawing from fog
        int frontGap = 100; //Decided upon beforehand. Same as in CarportGenerator.
        boolean firstInRow;
        boolean firstWithInShed;

        int spaceBetweenEachRow = (carportWidth - gap * 2) / (amountOfRows-1);
        int spaceBetweenEachPost = (carportDepth - rightGap - frontGap) / (postsInEachRow-1);

        int postXShedFront = cornerX + carportDepth - rightGap - shedDepth;
        int postXShedBack =  cornerX + carportDepth - rightGap - postSides;

        //For checking if a post is within the shed parameter
        //bottom left corner of shed
        int shedX1 = cornerX + carportDepth-shedDepth-rightGap;
        int shedY1 = cornerY + shedWidth - gap + postSides;

        //Top right corner of shed
        int shedX2 = cornerX + carportDepth - rightGap;
        int shedY2 = cornerY + gap;


        //We start with placing the first 2 post on the bottom of the shed
        // Left bottom corner
        svg.addRect(postXShedFront, cornerY + shedWidth - gap, postSides, postSides);
        // Right bottom right
        svg.addRect(postXShedBack, cornerY + shedWidth - gap, postSides, postSides);

        //For each row
        for (int i = 0; i < amountOfRows; i++) {
            //For each post in a row
            firstInRow = true;
            firstWithInShed = true;
            //Minus one since the last post in each row, is calculated separately
            postCounter = postsInEachRow;
            for (int j = 0; j < postsInEachRow; j++) {
                if(firstInRow){
                    if(rowCounter == amountOfRows && firstInRow ){
                        //First row and first post
                        svg.addRect(cornerX + frontGap, cornerY + gap, postSides, postSides);
                    }else{
                        //First post in a row
                        svg.addRect(cornerX + frontGap, cornerY + gap, postSides, postSides);
                    }

                    firstInRow = false;
                }else if(j == postsInEachRow -1){
                    //Last row on a row
                    svg.addRect(cornerX + frontGap + spaceBetweenEachPost * j - postSides, cornerY + gap, postSides, postSides);
                }else if(isPostWithinShed(shedX1,shedX2,shedY1,shedY2,cornerX + frontGap + spaceBetweenEachPost * j,cornerY + gap)){
                    // Post is within a shed
                    if(firstWithInShed){
                        //First post within a shed on a row
                        svg.addRect(cornerX + carportDepth - shedDepth - rightGap, cornerY + gap, postSides, postSides);
                        firstWithInShed = false;
                    }else{
                        //Within shed not first or last
                        svg.addRect(cornerX + carportDepth - shedDepth - rightGap + (shedDepth / postCounter), cornerY + gap, postSides, postSides);
                    }
                }else{
                    svg.addRect(cornerX + frontGap + spaceBetweenEachPost * j, cornerY + gap, postSides, postSides);
                }
                postCounter--;
            }
            //Calculate the next row y position
            cornerY += spaceBetweenEachRow;

            rowCounter--;
        }


    }

    //Used to check if a post x, y is within the shed parameter
    private static boolean isPostWithinShed(int shedX1,int shedX2,int shedY1,int shedY2, int postX, int postY){
        //Checks if the post is within the shed
        if (postX >= shedX1 && postX <= shedX2 &&
                postY <= shedY1 && postY >= shedY2){
            return true;
        }
        return false;
    }
}
