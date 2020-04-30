package FunctionLayer;

import Components.*;
import FunctionLayer.Exceptions.ValidationFailedException;

public class Order {

    private DepthComponent depth;
    private HeightComponent height;
    private WidthComponent width;
    private ShedDepthComponent shedDepth;
    private ShedWidthComponent shedWidth;
    private InclineComponent incline;
    private boolean withShed;

    public Order(DepthComponent depth, HeightComponent height, WidthComponent width , ShedDepthComponent shedDepth,
                 ShedWidthComponent shedWidth, InclineComponent incline, boolean withShed) {

        this.depth = depth;
        this.height = height;
        this.width = width;
        this.shedDepth = shedDepth;
        this.shedWidth = shedWidth;
        this.incline = incline;
        this.withShed = withShed;
    }

    public Order(DepthComponent depth, HeightComponent height, WidthComponent width, InclineComponent incline, boolean withShed) {

        this.depth = depth;
        this.height = height;
        this.width = width;
        this.incline = incline;
        this.withShed = withShed;
    }

    public DepthComponent getDepth() {
        return depth;
    }

    public void setDepth(DepthComponent depth) {
        this.depth = depth;
    }

    public HeightComponent getHeight() {
        return height;
    }

    public void setHeight(HeightComponent height) {
        this.height = height;
    }

    public WidthComponent getWidth() {
        return width;
    }

    public void setWidth(WidthComponent width) {
        this.width = width;
    }

    public ShedDepthComponent getShedDepth() {
        return shedDepth;
    }

    public void setShedDepth(ShedDepthComponent shedDepth) {
        this.shedDepth = shedDepth;
    }

    public ShedWidthComponent getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(ShedWidthComponent shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getIncline() {
        return incline.getIncline();
    }

    public void setIncline(InclineComponent incline) {
        this.incline = incline;
    }

    public InclineComponent getInclineComponent() {
        return incline;
    }

    public boolean isWithShed() {
        return withShed;
    }

    public void setWithShed(boolean withShed) {
        this.withShed = withShed;
    }
}
