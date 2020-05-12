package FunctionLayer.SVGGenerator;


//TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
// These will be passed on to here, from the DrawingGenerator.

import FunctionLayer.BillGenerator.CarportGenerator;

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

    public static void drawPerforatedBandWithShed(SVG svg, int cornerX, int cornerY, int carportDepth, int carportWidth, int shedDepth, int shedWidth) {
        int amountOfSper = CarportGenerator.getAmountOfSper(carportDepth);
        int spaceBetweenSper = carportDepth / amountOfSper;

        int gap = PartBuilderCarport.getGapToRem(carportWidth);
        int startXa, startYa, endXa, endYa;
        int startXb, startYb, endXb, endYb;

        startXa = cornerX+spaceBetweenSper;
        startXb = cornerX+spaceBetweenSper;
        startYa = cornerY + gap;
        startYb = (cornerY + carportWidth)-gap;
        endYa = cornerY+carportWidth-gap;
        endYb = cornerY+gap;
        if(carportWidth * 0.4 <= shedWidth && carportDepth * 0.7 >= shedDepth) {
            System.out.println(1);
            endXa = (+cornerX+carportDepth)-shedDepth-spaceBetweenSper;
            endXb = (cornerX+carportDepth)-shedDepth-spaceBetweenSper;
        } else if (carportWidth * 0.4 > shedWidth && carportDepth * 0.7 >= shedDepth){
            System.out.println(2);
            endXa = (+cornerX+carportDepth)-spaceBetweenSper;
            endXb = (cornerX+carportDepth)-shedDepth-spaceBetweenSper;
        } else if ( carportWidth * 0.4 <= shedWidth && carportDepth * 0.7 < shedDepth ) {
            System.out.println(3);
            endXa = (cornerX+carportDepth)-spaceBetweenSper;
            endXb = (cornerX+carportDepth)-spaceBetweenSper;
            endYa = cornerY+carportWidth-gap;
            endYb = (cornerY) + shedWidth;

            startYa = cornerY + (shedWidth);
        } else {
            System.out.println(4);
            endXa = (cornerX+carportDepth)-spaceBetweenSper;
            endXb = (cornerX+carportDepth)-spaceBetweenSper;
            endYa = cornerY+carportWidth-gap;
            endYb = (cornerY) + shedWidth;

            startYa = cornerY + (shedWidth);
        }

        //Y = Starts/Ends from the gap calculated above
        //X = Starts/Ends 1 Sper in
        svg.addLine( startXa, startYa, endXa, endYa,true);
        svg.addLine(startXb, startYb, endXb, endYb,true);
    }
}
