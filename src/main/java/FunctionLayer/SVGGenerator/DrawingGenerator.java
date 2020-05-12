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
        PartBuilderCarport.drawDepthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawInnerWidthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawOuterWidthArrow(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawPostsWithoutShed(svg, startingX,startingY, depth, width);
        PartBuilderCarport.drawSperSpaceArrows(svg, startingX,startingY, depth, width);
        return svg;
    }

    private static SVG createWithShedFlatRoofDrawing(int depth, int width, int shedDepth, int shedWidth) {
        throw new NotImplementedException();
    }

    private static SVG createNoShedRaisedRoofDrawing(int depth, int width, int incline) {
        throw new NotImplementedException();
    }

    private static SVG createWithShedRaisedRoofDrawing(int depth, int width, int shedDepth, int shedWidth, int incline) {
        throw new NotImplementedException();
    }

    private static SVG createSVGObject(int carportDepth, int carportWidth) {
        int width = carportDepth + 200;
        int height = carportWidth + 200;
        String viewbox = "0,0," + width + "," + height;
        return new SVG(width, height, viewbox, 0,0);
    }
}
