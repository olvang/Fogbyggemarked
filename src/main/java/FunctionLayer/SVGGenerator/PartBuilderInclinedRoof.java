package FunctionLayer.SVGGenerator;

import FunctionLayer.BillGenerator.InclinedRoofGenerator;

//TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
    // These will be passed on to here, from the DrawingGenerator.
public class PartBuilderInclinedRoof {

        public static void drawInclineInnerWidthArrow(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
            svg.addLineWithArrow(cornerX + carportDepth + 30,cornerY+20,cornerX + carportDepth + 30,(cornerY + carportWidth)-30);
            svg.addText(cornerX + carportDepth + 35,carportWidth / 2 + cornerY,90,(carportWidth - 40) + " cm");


            //The small lines in each end
            svg.addLine(cornerX + carportDepth + 10,cornerY+20,cornerX + carportDepth + 40,cornerY+20,false);
            svg.addLine(cornerX + carportDepth + 10,(cornerY + carportWidth)-30,cornerX + carportDepth + 40,(cornerY + carportWidth)-30,false);
        }

        public static void drawSterns(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
            //Top
            svg.addRect(cornerX, cornerY + (carportWidth / 2), 7, carportDepth);

            //Front + back
            svg.addRect(cornerX, cornerY, carportWidth, 5);
            svg.addRect(cornerX + carportDepth, cornerY, carportWidth, 5);

            int halfwaypoint = cornerY + (carportWidth / 2) + 3;
            svg.addLine(cornerX, halfwaypoint, cornerX + 5, halfwaypoint, false);
            svg.addLine(cornerX + carportDepth, halfwaypoint, cornerX + carportDepth + 5, halfwaypoint, false);

            //Sides
            svg.addRect(cornerX, cornerY, 4, carportDepth);
            svg.addRect(cornerX, cornerY + carportWidth - 5, 4, carportDepth);
            svg.addRect(cornerX, cornerY, 2, carportDepth);
            svg.addRect(cornerX, cornerY + carportWidth - 5, 2, carportDepth);
        }

        public static void drawLaths(SVG svg,int cornerX, int cornerY, int carportDepth, int carportWidth) {
            int numberOfRows = InclinedRoofGenerator.getAmountOfRowsOnSper(carportDepth);

            //We don't draw the final row, since it's supposed to be drawn underneath the bottom edge,
            // but the bottom edge is a bit above where it's supposed to be, so it can be seen
            // hanging out when it shouldn't be visible.
            for(int row = 1; row < numberOfRows; row++) {
                int yPlacement = cornerY + (carportWidth / numberOfRows) * row;
                svg.addRect(cornerX, yPlacement, 4, carportDepth);
                svg.addRect(cornerX, yPlacement, 1, carportDepth);

            }
        }

    public static void drawRemsInclined(SVG svg, int upperCornerX, int upperCornerY, int carportDepth, int carportWidth) {
        //There is a small gap between the top edge of the carport, and the rem (same on bottom).
        //The gap is 35cm on either side.
        int plankHeight = 5;
        int upperStartingCorner;
        int bottomStartingCorner;
        int gap = PartBuilderCarport.getGapToRem(carportWidth);

        upperStartingCorner = upperCornerY + gap;
        bottomStartingCorner = upperCornerY + ( carportWidth - gap);
        svg.addRect(upperCornerX, upperStartingCorner, plankHeight, carportDepth);
        svg.addRect(upperCornerX, bottomStartingCorner, plankHeight, carportDepth);
    }

}
