package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Orders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response ) throws CommandException {
        //Sets the session
        HttpSession session = request.getSession();

        //Get all orders
        //If Exception sets error
        ArrayList<Order> orders = null;
        try {
            orders = LogicFacade.getAllOrders();
        } catch (Exception e) {
            session.setAttribute( "error", "Kunne ikke hente ordrene fra databasen" );
            return "orders";
        }

        //Sets the order array on the session
        session.setAttribute( "orders", orders );
        return "orders";
    }

}
