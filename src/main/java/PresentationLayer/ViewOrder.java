package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.DatabaseException;
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
            String orderId = request.getParameter("o");
            if(orderId != null) {
                order = LogicFacade.getOrder(Integer.parseInt(orderId));
            } else {
                order = (Order) request.getAttribute("order");
            }

        } catch (DatabaseException e) {
            //Returns to order overview, if Exception happens
            request.setAttribute( "error", e.getMessage() + ". Kunne ikke hente ordren fra databasen." );
            return "orders";
        } catch (ClassCastException e) {
            request.setAttribute( "error", e.getMessage() + ". Kunne ikke få en ordre ud af givne id." );
            return "orders";
        }
        request.setAttribute("order", order);

        return "vieworder";
    }
}
