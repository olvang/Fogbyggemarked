package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Orders extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        //Sets the session
        HttpSession session = request.getSession();

        //Get all orders
        //If Exception sets error
        ArrayList<Order> orders = null;
        try {
            orders = LogicFacade.getAllOrders();
        } catch (DatabaseException e) {
            request.setAttribute( "error", e.getMessage() + ". Kunne ikke hente ordrene fra databasen" );
            return "orders";
        }

        //Sets the order array on the request
        request.setAttribute( "orders", orders );
        return "orders";
    }

}
