package PresentationLayer;

import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BeginEditOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        try {
            Order order = LogicFacade.getOrder(Integer.parseInt(request.getParameter("order")));
            request.setAttribute("order", order);
            request.setAttribute("editing", true);

            request.setAttribute("carportWidth",order.getWidth());
            request.setAttribute("carportDepth",order.getDepth());
            request.setAttribute("carportHeight",order.getHeight());

            if(order.isWithShed()) {
                request.setAttribute("shedWidth", order.getShedWidth());
                request.setAttribute("shedDepth", order.getShedDepth());
            }

            if(order.getIncline() == 0) {
                request.setAttribute("rooftype", "flat");
            } else {
                request.setAttribute("rooftype", "inclined");
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage() + ", Kunne ikke åbne ordre til redigering.");
            return "request";
        }


        return "vieworder";
    }
}
