package FunctionLayer.SVGGenerator;

import FunctionLayer.Exceptions.DrawingFailedException;
import FunctionLayer.Order;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DrawingGenerator {

    //Calls the required method, each of which handles their own drawing process.
    public static SVG createCarportDrawing(Order order) throws DrawingFailedException {
        System.out.println("Is with shed: " + order.isWithShed() + ", incline: " + order.getIncline());
        if(!order.isWithShed() && order.getIncline() == 0 ) {
            //If the order is without shed and has flat roof
            return createNoShedFlatRoofDrawing(order.getDepth(), order.getWidth());

        } else if (order.isWithShed() && order.getIncline() == 0) {
            //If the order is with shed and has flat roof
            return createWithShedFlatRoofDrawing(order.getDepth(),
                    order.getWidth(), order.getShedDepth(), order.getShedWidth());

        } else if( (!order.isWithShed() && order.getIncline() > 0)) {
            //If the order is without shed and has raised roof
            return createNoShedRaisedRoofDrawing(order.getDepth(), order.getWidth(), order.getIncline());

        } else if (order.isWithShed() && order.getIncline() > 0 ) {
            //If the order is with shed and has raised roof
            return createWithShedRaisedRoofDrawing(order.getDepth(), order.getWidth(),
                     order.getShedDepth(), order.getShedWidth(), order.getIncline());

        } else {
            throw new DrawingFailedException("Order " + order.getOrderId() + " does not fit expected pattern for drawing.");
        }
    }


    private static SVG createNoShedFlatRoofDrawing(int depth, int width) {
        SVG svg = createSVGObject(depth,width);
        int startingX = 100;
        int startingY = 100;
        PartBuilderCarport.drawOuterBox(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawRems(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawSper(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawPerforatedBandWithoutShed(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawPostsWithoutShed(svg, startingX,startingY, depth, width);

        PartBuilderCarport.drawDepthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawInnerWidthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawOuterWidthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawSperSpaceArrows(svg, startingX,startingY, depth, width);
        return svg;
    }

    private static SVG createWithShedFlatRoofDrawing(int depth, int width, int shedDepth, int shedWidth) {
        SVG svg = createSVGObject(depth,width);
        int startingX = 100;
        int startingY = 100;
        PartBuilderCarport.drawOuterBox(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawRems(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawSper(svg, startingX,startingY, depth, width);

        PartBuilderShed.drawShed(svg, startingX, startingY, depth,width,shedDepth,shedWidth);
        PartBuilderShed.drawPostsWithShed(svg, startingX, startingY, depth, width, shedDepth, shedWidth);
        PartBuilderShed.drawPerforatedBandWithShed(svg, startingX, startingY, depth, width, shedDepth, shedWidth);

        PartBuilderCarport.drawDepthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawInnerWidthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawOuterWidthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawSperSpaceArrows(svg, startingX,startingY, depth, width);
        return svg;
    }

    private static SVG createNoShedRaisedRoofDrawing(int fulldepth, int fullwidth, int incline) {
        SVG svg = createSVGObject(fulldepth,fullwidth);
        int startingX = 100;
        int startingY = 100;

        //Some parts of the drawing are reused from the flat-roof one.
        // But these parts are about 40cm smaller than they would be with a flat roof.
        int xmoved = startingX + 20;
        int ymoved = startingY + 20;
        int depth = fulldepth - 40;
        int width = fulldepth - 40;
        int differenceForRems = 15; //The rems are 5 wide, so they should be drawn a bit further up

        PartBuilderCarport.drawOuterBox(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawRemsInclined(svg, xmoved,startingY - differenceForRems, depth, fullwidth + differenceForRems);
        PartBuilderCarport.drawSper(svg, xmoved,startingY, depth, fullwidth);
        PartBuilderCarport.drawPostsWithoutShed(svg, startingX,startingY - differenceForRems, fulldepth, fullwidth + differenceForRems);
        PartBuilderInclinedRoof.drawLaths(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawSterns(svg, startingX,startingY, fulldepth, fullwidth);

        PartBuilderCarport.drawDepthArrow(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawInclineInnerWidthArrow(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderCarport.drawOuterWidthArrow(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderCarport.drawSperSpaceArrows(svg, xmoved,ymoved, depth, width);
        return svg;
    }

    private static SVG createWithShedRaisedRoofDrawing(int fulldepth, int fullwidth, int shedDepth, int shedWidth, int incline) {
        //Shed looks messed up, needs to be reworked somehow
        /*SVG svg = createSVGObject(fulldepth,fullwidth);
        int startingX = 100;
        int startingY = 100;

        //Some parts of the drawing are reused from the flat-roof one.
        // But these parts are about 40cm smaller than they would be with a flat roof.
        int xmoved = startingX + 20;
        int ymoved = startingY + 20;
        int depth = fulldepth - 40;
        int width = fulldepth - 40;
        int differenceForRems = 15; //The rems are 5 wide, so they should be drawn a bit further up

        PartBuilderCarport.drawOuterBox(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderShed.drawShed(svg, xmoved, startingY -differenceForRems, fulldepth,fullwidth + differenceForRems,shedDepth,shedWidth);

        PartBuilderInclinedRoof.drawRemsInclined(svg, xmoved,startingY - differenceForRems, depth, fullwidth + differenceForRems);
        PartBuilderCarport.drawSper(svg, xmoved,startingY, depth, fullwidth);
        PartBuilderInclinedRoof.drawLaths(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawSterns(svg, startingX,startingY, fulldepth, fullwidth);

        PartBuilderShed.drawPostsWithShed(svg, xmoved,ymoved - differenceForRems, fulldepth, fullwidth + differenceForRems, shedDepth, shedWidth);

        PartBuilderCarport.drawDepthArrow(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderInclinedRoof.drawInclineInnerWidthArrow(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderCarport.drawOuterWidthArrow(svg, startingX,startingY, fulldepth, fullwidth);
        PartBuilderCarport.drawSperSpaceArrows(svg, xmoved,ymoved, depth, width);
        return svg;*/
        throw new NotImplementedException();
    }

    private static SVG createSVGObject(int carportDepth, int carportWidth) {
        int width = carportDepth + 200;
        int height = carportWidth + 200;
        String viewbox = "0,0," + width + "," + height;
        return new SVG(width, height, viewbox, 0,0);
    }
}
