package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.DrawingFailedException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.SVGGenerator.DrawingGenerator;
import FunctionLayer.SVGGenerator.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        SVG svg;
        int orderID;
        try {
            orderID = Integer.parseInt(request.getParameter("order_id"));
            Order order = LogicFacade.getOrder(orderID);
            svg = DrawingGenerator.createCarportDrawing(order);

        } catch (DatabaseException e) {
            return "request";
        } catch (DrawingFailedException e) {
            return "request";
        }
        request.setAttribute("svgdrawing", svg.toString());
        request.setAttribute("order_id", orderID);
        return "drawing";
    }
}
