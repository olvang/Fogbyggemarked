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
        svg.addRect(upperCornerX, upperStartingCorner, plankHeight, carportDepth);
        svg.addRect(upperCornerX, bottomStartingCorner, plankHeight, carportDepth);
    }

    public static void drawPostsWithoutShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth) {

        throw new NotImplementedException();
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

        int gap = 35;
        if(carportWidth < 100) {
            //The carport is so narrow that just subtracting 35 would create a massive gap
            // instead we make the gap a third of the width, which is still massive, but better
            gap = (int) Math.ceil(carportWidth / 3.0);
        }

        //Y = Starts/Ends from the gap calculated above
        //X = Starts/Ends 1 Sper in
        svg.addLine(cornerX+spaceBetweenSper,cornerY + gap,carportDepth-spaceBetweenSper,carportWidth-gap,true);
        svg.addLine(cornerX+spaceBetweenSper,(cornerY + carportWidth)-gap,carportDepth-spaceBetweenSper,gap,true);
    }

    public static void drawPerforatedBandWithShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth,int shedDepth, int shedWidth) {
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
