package PresentationLayer;

import Components.*;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Request extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        boolean errorsFound = false;
        WidthComponent carportwidth = null;
        DepthComponent carportDepth = null;

        //Carport
        //Width
        String carportWidthString = request.getParameter( "carportwidth" );
        try {
            carportwidth = new WidthComponent(carportWidthString);
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
            HeightComponent carportHeight = new HeightComponent(carportHeightString);
        } catch (ValidationFailedException e) {
            request.setAttribute("carportHeightError",e.getMessage());
            errorsFound = true;
        }


        //Shed
        String shedornotString = request.getParameter( "shedornot" );
        if(shedornotString.equals("true")){
            //Width
            String shedWidthString = request.getParameter( "shedwidth" );
            try {
                ShedWidthComponent shedWidth = new ShedWidthComponent(shedWidthString,carportwidth);
            } catch (ValidationFailedException e) {
                request.setAttribute("shedWidthError",e.getMessage());
                errorsFound = true;
            }

            //Depth
            String shedDepthString = request.getParameter( "sheddepth" );
            try {
                ShedDepthComponent shedDepth = new ShedDepthComponent(shedDepthString,carportDepth);
            } catch (ValidationFailedException e) {
                request.setAttribute("shedDepthError",e.getMessage());
                errorsFound = true;
            }

            //Only used if errors has been found and returning to request
            request.setAttribute("shedWidth",shedWidthString);
            request.setAttribute("shedDepth",shedDepthString);
        }

        //Roof
        //TODO create logic
        String roofTypeString = request.getParameter( "rooftype" );



        //Error exist
        if(errorsFound){
            //Set all input fields to their input if not empty

            //Carport
            request.setAttribute("carportWidth",carportWidthString);
            request.setAttribute("carportDepth",carportDepthString);
            request.setAttribute("carportHeight",carportHeightString);
            request.setAttribute("carportWidth",carportWidthString);

            //Shed
            request.setAttribute("shedornot",shedornotString);


            //Roof
            request.setAttribute("rooftype",roofTypeString);

            return "request";
        }else{
            //TODO make order
            return "request";
        }

    }

}
