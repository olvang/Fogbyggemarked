package PresentationLayer;

import FunctionLayer.BillLine;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class Bill extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response ) {
        //TODO update when Bill Page Exist

        //Used for printing to console while testing
        ArrayList<BillLine> billLines;
        try {
            billLines = LogicFacade.getBillLines(1);
            for(BillLine line: billLines){
                System.out.println(line.getMaterial().getDescription() + " Antal: " + line.getAmount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "request";
    }
}
