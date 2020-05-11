package FunctionLayer.SVGGenerator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PartBuilder {

    //TODO When writing these, don't forget to add the required positions and dimensions to the paramenters.
    // These will be passed on to here, from the DrawingGenerator.

    public void drawOuterBox(SVG svg, int x, int y, int carportDepth, int carportWidth) {
        svg.addRect(x,  y, carportWidth, carportDepth);
    }

    public void drawRems(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawPillars(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawSper(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawPerforatedBand(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawDepthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawInnerWidthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawOuterWidthArrow(SVG svg) {
        throw new NotImplementedException();
    }

    public void drawSperSpaceArrows(SVG svg) {
        throw new NotImplementedException();
    }


}
