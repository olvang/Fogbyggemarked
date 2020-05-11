package FunctionLayer.SVGGenerator;

import Components.*;
import FunctionLayer.Exceptions.DrawingFailedException;
import FunctionLayer.Order;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DrawingGenerator {

    //Calls the required method, each of which handles their own drawing process.
    public static SVG createCarportDrawing(Order order) throws DrawingFailedException {
        if(!order.isWithShed() && order.getIncline() == 0 ) {
            //If the order is without shed and has flat roof
            return createNoShedFlatRoofDrawing(order.getDepth(), order.getWidth(), order.getHeight());

        } else if (order.isWithShed() && order.getIncline() == 0) {
            //If the order is with shed and has flat roof
            return createWithShedFlatRoofDrawing(order.getDepth(),
                    order.getWidth(), order.getHeight(), order.getShedDepth(), order.getShedWidth());

        } else if( (!order.isWithShed() && order.getIncline() > 0)) {
            //If the order is without shed and has raised roof
            return createNoShedRaisedRoofDrawing(order.getDepth(), order.getWidth(), order.getHeight(), order.getIncline());

        } else if (order.isWithShed() && order.getIncline() > 0 ) {
            //If the order is with shed and has raised roof
            return createWithShedRaisedRoofDrawing(order.getDepth(), order.getWidth(),
                    order.getHeight(), order.getShedDepth(), order.getShedWidth(), order.getIncline());

        } else {
            throw new DrawingFailedException("Order " + order.getOrderId() + " does not fit expected pattern for drawing.");
        }
    }


    private static SVG createNoShedFlatRoofDrawing(int depth, int width, int height) {
        throw new NotImplementedException();
    }

    private static SVG createWithShedFlatRoofDrawing(int depth, int width, int height, int shedDepth, int shedWidth) {
        throw new NotImplementedException();
    }

    private static SVG createNoShedRaisedRoofDrawing(int depth, int width, int height, int incline) {
        throw new NotImplementedException();
    }

    private static SVG createWithShedRaisedRoofDrawing(int depth, int width, int height, int shedDepth, int shedWidth, int incline) {
        throw new NotImplementedException();
    }

}
