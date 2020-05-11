package FunctionLayer.SVGGenerator;

import FunctionLayer.BillGenerator.CarportGenerator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PartBuilder {

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
        int gap;
        if(carportWidth < 100) {
            //The carport is so narrow that just subtracting 35 would create a massive gap
            // instead we make the gap a third of the width, which is still massive, but better
            gap = (int) Math.ceil(carportWidth / 3.0);
        } else {
            gap = 35;
        }
        upperStartingCorner = upperCornerY + gap;
        bottomStartingCorner = upperCornerY + ( carportWidth - gap);
        svg.addRect(upperCornerX, upperStartingCorner, plankHeight, carportWidth);
        svg.addRect(upperCornerX, bottomStartingCorner, plankHeight, carportWidth);
    }

    public static void drawPostsWithoutShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth) {

        throw new NotImplementedException();
    }

    public static void drawSper(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth) {
        int sperWidth = 4;
        int amountOfSper = CarportGenerator.getAmountOfSper(carportDepth);
        System.out.println(amountOfSper);
        for(int i = 1; i <= amountOfSper; i++) {
            int xPosition = i * 55 + cornerX;
            svg.addRect(xPosition, cornerY, carportWidth, sperWidth);
        }
    }

    public static void drawPerforatedBand(SVG svg) {
        throw new NotImplementedException();
    }

    public static void drawDepthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public static void drawInnerWidthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public static void drawOuterWidthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public static void drawSperSpaceArrows(SVG svg) {
        throw new NotImplementedException();
    }


}
