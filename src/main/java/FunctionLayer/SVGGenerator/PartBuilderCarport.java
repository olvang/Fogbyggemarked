package FunctionLayer.SVGGenerator;

import FunctionLayer.BillGenerator.CarportGenerator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PartBuilderCarport {

    //TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
    // These will be passed on to here, from the DrawingGenerator.

    public static void drawOuterBox(SVG svg, int x, int y, int carportDepth, int carportWidth) {
        svg.addRect(x,  y, carportWidth, carportDepth);
    }

    public static void drawRems(SVG svg, int upperCornerX, int upperCornerY, int carportDepth, int carportWidth) {
        //There is a small gap between the top edge of the carport, and the rem (same on bottom).
        //The gap is 35cm on either side.
        int plankHeight = 5;
        int upperStartingCorner;
        int bottomStartingCorner;
        int gap = getGapToRem(carportWidth);

        upperStartingCorner = upperCornerY + gap;
        bottomStartingCorner = upperCornerY + ( carportWidth - gap);
        svg.addRect(upperCornerX, upperStartingCorner, plankHeight, carportDepth);
        svg.addRect(upperCornerX, bottomStartingCorner, plankHeight, carportDepth);
    }

    public static void drawPostsWithoutShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth) {
        int postSides = 6;
        int amountOfRows = CarportGenerator.getAmountOfPostRows(carportWidth);
        int amountOfPosts = CarportGenerator.getAmountOfPosts(carportDepth,amountOfRows);
        int postsInEachRow = amountOfPosts / amountOfRows;

        int topGap = getGapToRem(carportWidth);
        int leftGap = 100; //Decided upon beforehand. Same as in CarportGenerator.

        int postX = cornerX + leftGap;
        int postY = 0;

        for(int row = 0; row < amountOfRows; row++) {
            if(row == 0) {
                //Topmost row
                postY = cornerY + topGap;
            }else if (row == 1) {
                //Bottom row
                postY = cornerY + (carportWidth - topGap);
            } else {
                //All middle rows
                // subtract 2 because we've already placed top and bottom at this point
                postY = cornerY + (((carportWidth - topGap * 2)) / (amountOfRows - 1)) * (row - 1);
            }

            int firstPosition = 0;
            int lastPosition = 0;
            for(int post = 0; post < postsInEachRow; post++) {
                if(post == 0) {
                    //place first post
                    postX = cornerX + leftGap;
                    firstPosition = postX;
                } else if (post == 1) {
                    //place last post
                    postX = cornerX + carportDepth - leftGap;
                    lastPosition = postX;
                } else {
                    //Subtract 2 because we've already placed first and last
                    //Don't tell anyone, but I'm honestly not sure why it suddenly works when subtracting only 1 lol
                    // maybe it's because we're counting from 0? I honestly don't know anymore. 
                    int distanceBetween = (lastPosition - firstPosition) / (postsInEachRow - 1);
                    postX = firstPosition + (distanceBetween * (post - 1) );
                }
                svg.addRect(postX, postY, postSides, postSides);
            }
        }

    }

    public static void drawSper(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth) {
        int sperWidth = 4;
        int amountOfSper = CarportGenerator.getAmountOfSper(carportDepth);
        int spaceBetweenSper = carportDepth / amountOfSper;
        for(int i = 0; i <= amountOfSper; i++) {
            int xPosition = i * spaceBetweenSper + cornerX;
            svg.addRect(xPosition, cornerY, carportWidth, sperWidth);
        }
    }

    public static void drawPerforatedBandWithoutShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth) {
        //We need the space between each sper, as that is where it starts / ends
        int amountOfSper = CarportGenerator.getAmountOfSper(carportDepth);
        int spaceBetweenSper = carportDepth / amountOfSper;

        int gap = getGapToRem(carportWidth);

        //Y = Starts/Ends from the gap calculated above
        //X = Starts/Ends 1 Sper in
        svg.addLine(cornerX+spaceBetweenSper,cornerY + gap,+cornerX+carportDepth-spaceBetweenSper,cornerY+carportWidth-gap,true);
        svg.addLine(cornerX+spaceBetweenSper,(cornerY + carportWidth)-gap,cornerX+carportDepth-spaceBetweenSper,cornerY+gap,true);
    }

    public static void drawPerforatedBandWithShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth,int shedDepth, int shedWidth) {
        throw new NotImplementedException();
    }

    public static void drawDepthArrow(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
        svg.addLineWithArrow(cornerX,(cornerY + carportWidth)+30,cornerX+carportDepth,(cornerY + carportWidth)+30);
        svg.addText(carportDepth / 2 + cornerX,(cornerY + carportWidth)+45,0,carportDepth + " cm");
    }

    public static void drawInnerWidthArrow(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
        svg.addLineWithArrow(cornerX + carportDepth + 30,cornerY+35,cornerX + carportDepth + 30,(cornerY + carportWidth)-30);
        svg.addText(cornerX + carportDepth + 35,carportWidth / 2 + cornerY,90,(carportWidth - 70) + " cm");


        //The small lines in each end
        svg.addLine(cornerX + carportDepth + 10,cornerY+35,cornerX + carportDepth + 40,cornerY+35,false);
        svg.addLine(cornerX + carportDepth + 10,(cornerY + carportWidth)-30,cornerX + carportDepth + 40,(cornerY + carportWidth)-30,false);
    }

    public static void drawOuterWidthArrow(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
        //Arrows
        svg.addLineWithArrow(cornerX + carportDepth + 60,cornerY,cornerX + carportDepth + 60,(cornerY + carportWidth));

        //text
        svg.addText(cornerX + carportDepth + 75,carportWidth / 2 + cornerY,90,carportWidth + " cm");

        //The small lines in each end
        svg.addLine(cornerX + carportDepth + 30,cornerY,cornerX + carportDepth + 70,cornerY,false);
        svg.addLine(cornerX + carportDepth + 30,(cornerY + carportWidth),cornerX + carportDepth + 70,(cornerY + carportWidth),false);
    }

    public static void drawSperSpaceArrows(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
        int sperWidth = 4;
        int amountOfSper = CarportGenerator.getAmountOfSper(carportDepth);
        int spaceBetweenSper = carportDepth / amountOfSper;
        for(int i = 0; i < amountOfSper; i++) {
            int xPosition = i * spaceBetweenSper + cornerX;

            //Arrows
            svg.addLineWithArrow(xPosition, cornerY -40, xPosition+spaceBetweenSper+sperWidth, cornerY -40);

            //text
            svg.addText(xPosition + (spaceBetweenSper / 2), cornerY -50,0, String.valueOf((double)spaceBetweenSper / 10));

            //The small lines
            svg.addLine(xPosition+sperWidth/2,cornerY -50,xPosition+sperWidth/2,cornerY -20,false);

            if(i == amountOfSper -1){
                xPosition = 15 * spaceBetweenSper + cornerX;
                //Add the last line
                svg.addLine(xPosition+sperWidth/2,cornerY -50,xPosition+sperWidth/2,cornerY -20,false);
            }
        }
    }

    public static int getGapToRem(int carportWidth) {
        int gap;
        if(carportWidth < 100) {
            //The carport is so narrow that just subtracting 35 would create a massive gap
            // instead we make the gap a third of the width, which is still massive, but better
            gap = (int) Math.ceil(carportWidth / 3.0);
        } else {
            gap = 35;
        }
        return gap;
    }

}
