package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewOrder extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        Order order = null;

        try {
            order = LogicFacade.getOrder(Integer.parseInt(request.getParameter("o")));
        } catch (Exception e) {
            //Returns to order overview, if Exception happens
            request.setAttribute( "error", "Kunne ikke hente ordren fra databasen" );
            return "orders";
        }
        request.setAttribute("order", order);

        return "vieworder";
    }
}
