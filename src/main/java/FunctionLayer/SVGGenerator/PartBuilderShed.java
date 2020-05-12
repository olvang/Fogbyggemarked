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
        //Right gap found on the original drawing from fog
        int rightGap = 30;

        //Vertical
        svg.addRect(x + carportDepth-shedDepth-rightGap, y + gap , plank, shedDepth);
        svg.addRect(x + carportDepth-shedDepth-rightGap, y + shedWidth - gap , plank, shedDepth);

        //Horizontal
        svg.addRect(x + carportDepth-shedDepth-rightGap, y + gap , shedWidth - gap * 2, plank);
        svg.addRect(x + carportDepth-plank-rightGap, y + gap , shedWidth - gap * 2, plank);

        //ShedLines
        svg.addShedLine(x + carportDepth-shedDepth-rightGap, y + gap , x + carportDepth-rightGap, y + gap);
        svg.addShedLine(x + carportDepth-shedDepth-rightGap, y + shedWidth - gap + plank, x + carportDepth-rightGap, y + shedWidth - gap + plank);

        //Horizontal
        svg.addShedLine(x + carportDepth-shedDepth-rightGap, y + gap , x + carportDepth-shedDepth-rightGap, y+shedWidth-gap + plank);
        svg.addShedLine(x + carportDepth-rightGap, y + gap , x + carportDepth-rightGap, y+shedWidth-gap + plank);
    }
}
