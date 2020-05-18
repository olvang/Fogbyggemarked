package PresentationLayer;

import Components.*;
import FunctionLayer.Customer;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveEditOrder extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        boolean errorsFound = false;
        WidthComponent carportWidth = null;
        DepthComponent carportDepth = null;
        HeightComponent carportHeight = null;
        ShedDepthComponent shedDepth = null;
        ShedWidthComponent shedWidth = null;
        InclineComponent incline = null;
        int orderId = 0;
        Order oldOrder = null;

        //Used for error handling
        String shedWidthString = "";
        String shedDepthString = "";

        String orderIdString = request.getParameter("orderid");
        try {
            orderId = Integer.parseInt(orderIdString);
            oldOrder = LogicFacade.getOrder(orderId);
        } catch(ClassCastException | DatabaseException ex) {
            request.setAttribute("error", "Serverfejl i ordre id");
            errorsFound = true;
        }

        //Carport
        //Width
        String carportWidthString = request.getParameter( "carportwidth" );
        try {
            carportWidth = new WidthComponent(carportWidthString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportWidthError",e.getMessage());
            carportWidth = oldOrder.getWidthComponent();
            errorsFound = true;
        }

        //Depth
        String carportDepthString = request.getParameter( "carportdepth" );
        try {
            carportDepth = new DepthComponent(carportDepthString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportDepthError",e.getMessage());
            carportDepth = oldOrder.getDepthComponent();
            errorsFound = true;
        }

        //Height
        String carportHeightString = request.getParameter( "carportheight" );
        try {
            carportHeight = new HeightComponent(carportHeightString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportHeightError",e.getMessage());
            carportHeight = oldOrder.getHeightComponent();
            errorsFound = true;
        }

        //Shed
        String shedornotString = request.getParameter( "shedornot" );
        if(shedornotString.equals("true")){
            //Width
            shedWidthString = request.getParameter( "shedwidth" );
            try {
                if(carportWidth != null) {
                    shedWidth = new ShedWidthComponent(shedWidthString,carportWidth);
                } else {
                    shedWidth = new ShedWidthComponent(shedWidthString, carportWidthString);
                }
            } catch (ValidationFailedException e) {
                request.setAttribute("shedWidthError",e.getMessage());
                shedWidth = oldOrder.getShedWidthComponent();
                errorsFound = true;
            }

            //Depth
            shedDepthString = request.getParameter( "sheddepth" );
            try {
                if(carportDepth != null) {
                    shedDepth = new ShedDepthComponent(shedDepthString,carportDepth);
                } else {
                    shedDepth = new ShedDepthComponent(shedDepthString, carportDepthString);
                }
            } catch (ValidationFailedException e) {
                request.setAttribute("shedDepthError",e.getMessage());
                shedDepth = oldOrder.getShedDepthComponent();
                errorsFound = true;
            }
        }


        //Roof
        String roofTypeString = request.getParameter( "rooftype" );
        //TODO update logic when user can choose materials
        try {
            if(roofTypeString.equals("inclined")){
                int roofIncline = Integer.parseInt(request.getParameter("roofIncline"));
                incline = new InclineComponent(roofIncline);
            }else {
                incline = new InclineComponent(0);
            }
        }catch (ValidationFailedException e) {
            request.setAttribute("inclineError",e.getMessage());
            incline = oldOrder.getInclineComponent();
            errorsFound = true;
        }catch (NumberFormatException e) {
            request.setAttribute("inclineError", "Vinkel skal være et tal");
            incline = oldOrder.getInclineComponent();
            errorsFound = true;
        }

        if(!errorsFound) {
            Order order;
            Customer customer = null;

            if(shedornotString.equals("true")) {
                order = new Order(carportDepth, carportHeight, carportWidth, shedDepth,
                        shedWidth, incline, true, customer);
            } else {
                order = new Order(carportDepth, carportHeight, carportWidth, incline, false, customer);
            }
            try {
                LogicFacade.updateOrder(orderId, order);
                Order finished = LogicFacade.getOrder(orderId);
                request.setAttribute("order", finished);
            } catch (DatabaseException ex) {
                request.setAttribute("error","Kunne ikke opdatere bestilling afsted. " + ex.getMessage());
                return "index";
            }


        }else {
            errorHandling(request, carportWidthString, carportDepthString, carportHeightString, shedornotString, shedWidthString, shedDepthString, roofTypeString, roofTypeString);
            Order order;
            if(shedornotString.equals("true")) {
                order = new Order(carportDepth, carportHeight, carportWidth, shedDepth,
                        shedWidth, incline, true, oldOrder.getCustomer());
            } else {
                order = new Order(carportDepth, carportHeight, carportWidth, incline, false, oldOrder.getCustomer());
            }
            order.setOrderId(orderId);
            request.setAttribute("order", order);
            request.setAttribute("editing", true);
        }

        return "vieworder";
    }

    private void errorHandling(HttpServletRequest request, String carportWidthString, String carportDepthString, String carportHeightString, String shedornotString, String shedWidthString, String shedDepthString, String roofTypeString, String inclineString) {
        //Carport
        request.setAttribute("carportWidth",carportWidthString);
        request.setAttribute("carportDepth",carportDepthString);
        request.setAttribute("carportHeight",carportHeightString);
        request.setAttribute("carportWidth",carportWidthString);

        //Shed
        request.setAttribute("shedornot",shedornotString);
        if(!shedWidthString.isEmpty()){
            request.setAttribute("shedWidth", shedWidthString);
        }
        if(!shedDepthString.isEmpty()){
            request.setAttribute("shedDepth", shedDepthString);
        }
        //Roof
        request.setAttribute("rooftype",roofTypeString);
    }
}
