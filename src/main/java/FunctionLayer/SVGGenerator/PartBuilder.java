package FunctionLayer.SVGGenerator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PartBuilder {

    //TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
    // These will be passed on to here, from the DrawingGenerator.

    public void drawOuterBox(SVG svg, int x, int y, int carportDepth, int carportWidth) {
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

    public void drawPillars(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawSper(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawPerforatedBand(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawDepthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawInnerWidthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawOuterWidthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawSperSpaceArrows(SVG svg) {
        throw new NotImplementedException();
    }


}
