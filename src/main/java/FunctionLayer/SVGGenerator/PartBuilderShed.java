package FunctionLayer.SVGGenerator;


//TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
// These will be passed on to here, from the DrawingGenerator.

public class PartBuilderShed {
    public static void drawShed(SVG svg, int x, int y, int carportDepth, int carportWidth,int shedDepth, int shedWidth) {
        //There is a small gap between the top edge of the carport, and the rem (same on bottom).
        int plank = 5;
        int upperStartingCorner;
        int bottomStartingCorner;
        int gap = PartBuilderCarport.getGapToRem(carportWidth);

        //Vertical
        svg.addRect(x + carportDepth-shedDepth, y + gap , plank, shedDepth);
        svg.addRect(x + carportDepth-shedDepth, y + shedWidth - gap , plank, shedDepth);

        //Horizontal
        svg.addRect(x + carportDepth-shedDepth, y + gap , shedWidth - gap * 2, plank);
        svg.addRect(x + carportDepth-plank, y + gap , shedWidth - gap * 2, plank);

        //ShedLines
        svg.addShedLine(x + carportDepth-shedDepth, y + gap , x + carportDepth, y + gap);
        svg.addShedLine(x + carportDepth-shedDepth, y + shedWidth - gap + plank, x + carportDepth, y + shedWidth - gap + plank);

        //Horizontal
        svg.addShedLine(x + carportDepth-shedDepth, y + gap , x + carportDepth-shedDepth, y+shedWidth-gap + plank);
        svg.addShedLine(x + carportDepth, y + gap , x + carportDepth, y+shedWidth-gap + plank);
    }
}
