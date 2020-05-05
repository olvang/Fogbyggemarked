package PresentationLayer;

import FunctionLayer.BillLine;
import FunctionLayer.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Bill extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response ) {
        //TODO update when Bill Page Exist

        //Used for printing to console while testing
        ArrayList<BillLine> billLines;
        try {
            int orderID = Integer.parseInt(request.getParameter("order_id"));
            billLines = LogicFacade.getBillLines(orderID);
            request.setAttribute("order_id", orderID);
            request.setAttribute("bill", billLines);

            /*for(BillLine line: billLines){
                System.out.println(line.getMaterial().getName() + " Antal: " + line.getAmount() + " - Længde: " + line.getMaterial().getLength() + " CAT ID: " + line.getMaterial().getCategory());
            }*/
        } catch (Exception e) {
            return "request";
        }

        return "material-bill";
    }
}
