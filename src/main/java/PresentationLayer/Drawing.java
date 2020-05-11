package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.SVGGenerator.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        SVG svg = new SVG(800, 600, "0,0,800,600",0,0);

        svg.addRect(0,0,600,780);

        request.setAttribute("svgdrawing", svg.toString());
        return "Drawing";
    }
}
