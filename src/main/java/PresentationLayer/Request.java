package PresentationLayer;

import Components.*;
import FunctionLayer.Customer;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Request extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException {
        boolean errorsFound = false;
        WidthComponent carportWidth = null;
        DepthComponent carportDepth = null;
        HeightComponent carportHeight = null;
        ShedDepthComponent shedDepth = null;
        ShedWidthComponent shedWidth = null;
        InclineComponent incline = null;

        AddressComponent address = null;
        EmailComponent email = null;
        NameComponent name = null;
        PhoneComponent phone = null;
        ZipCodeComponent zipCode = null;

        Customer customer = null;
        
        //Used for error handling
        String shedWidthString = "";
        String shedDepthString = "";

        //Address component
        String addressString = request.getParameter("address");
        try{
            address = new AddressComponent(addressString);
        } catch (ValidationFailedException e) {
            request.setAttribute("addressError",e.getMessage());
            errorsFound = true;
        }

        //Email component
        String emailString = request.getParameter("email");
        try{
            email = new EmailComponent(emailString);
        } catch (ValidationFailedException e) {
            request.setAttribute("emailError",e.getMessage());
            errorsFound = true;
        }

        //name component
        String nameString = request.getParameter("name");
        try{
            name = new NameComponent(nameString);
        } catch (ValidationFailedException e) {
            request.setAttribute("nameError",e.getMessage());
            errorsFound = true;
        }

        //Phone component
        String phoneString = request.getParameter("phone");
        try{
            phone = new PhoneComponent(phoneString);
        } catch (ValidationFailedException e) {
            request.setAttribute("phoneError",e.getMessage());
            errorsFound = true;
        }

        //Zipcode component
        String zipString = request.getParameter("zipcode");
        try{
            zipCode = new ZipCodeComponent(zipString);
        } catch (ValidationFailedException e) {
            request.setAttribute("zipcodeError",e.getMessage());
            errorsFound = true;
        }

        //Carport
        //Width
        String carportWidthString = request.getParameter( "carportwidth" );
        try {
            carportWidth = new WidthComponent(carportWidthString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportWidthError",e.getMessage());
            errorsFound = true;
        }

        //Depth
        String carportDepthString = request.getParameter( "carportdepth" );
        try {
            carportDepth = new DepthComponent(carportDepthString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportDepthError",e.getMessage());
            errorsFound = true;
        }

        //Height
        String carportHeightString = request.getParameter( "carportheight" );
        try {
            carportHeight = new HeightComponent(carportHeightString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportHeightError",e.getMessage());
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
            errorsFound = true;
        }catch (NumberFormatException e) {
            request.setAttribute("inclineError", "Vinkel skal være et tal");
            errorsFound = true;
        }







        //Error exist
        if(!errorsFound){
            //No errors,
            // create Order
            Order order;
            customer = new Customer(name,address,email,phone,zipCode);
            //if they selected a Shed
            if(shedornotString.equals("true")){
                order = new Order(carportDepth, carportHeight, carportWidth, shedDepth,
                        shedWidth, incline, true, customer);
            }else{
                //if no shed Shed has been selected
                order = new Order(carportDepth, carportHeight, carportWidth,incline, false, customer);
            }
            try {
                LogicFacade.createOrder(order);
            } catch (DatabaseException e) {
                //Set all input fields to their input if not empty
                errorHandling(request,carportWidthString,carportDepthString,carportHeightString,shedornotString,shedWidthString,shedDepthString,roofTypeString,nameString,addressString,emailString,phoneString,zipString);
                request.setAttribute("error","Kunne ikke sende din bestilling afsted. " + e.getMessage());
                return "request";
            }

            //No errors found - and order inserted in db
            request.setAttribute("success","Tak for din bestilling"+ order.getCustomerName() + "<br>Du vil blive kontaktet af en af vores dygtige sælgere hurtigst muligt.");
        }else{
            //Errors found
            errorHandling(request,carportWidthString,carportDepthString,carportHeightString,shedornotString,shedWidthString,shedDepthString,roofTypeString, nameString, addressString, emailString, phoneString, zipString);

        }
        return "request";
    }

    private void errorHandling(HttpServletRequest request, String carportWidthString, String carportDepthString, String carportHeightString, String shedornotString, String shedWidthString, String shedDepthString, String roofTypeString, String nameString, String addressString, String emailString, String phoneString, String zipString){
        //Set all input fields to their input if not empty

        //Customer
        request.setAttribute("name",nameString);
        request.setAttribute("address",addressString);
        request.setAttribute("email",emailString);
        request.setAttribute("phone",phoneString);
        request.setAttribute("zipcode",zipString);

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
