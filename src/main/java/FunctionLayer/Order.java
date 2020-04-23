package FunctionLayer;

import Components.*;

public class Order {

    private DepthComponent depth;
    private HeightComponent height;
    private WidthComponent width;
    private ShedDepthComponent shedDepth;
    private ShedWidthComponent shedWidth;
    private int incline;
    private boolean withShed;

    public Order(DepthComponent depth, HeightComponent height, WidthComponent width , ShedDepthComponent shedDepth,
                 ShedWidthComponent shedWidth, int incline, boolean withShed){

        this.depth = depth;
        this.height = height;
        this.width = width;
        this.shedDepth = shedDepth;
        this.shedWidth = shedWidth;
        this.incline = incline;
        this.withShed = withShed;
    }

    public Order(DepthComponent depth, HeightComponent height, WidthComponent width, int incline, boolean withShed){

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
        return incline;
    }

    public void setIncline(int incline) {
        this.incline = incline;
    }

    public boolean isWithShed() {
        return withShed;
    }

    public void setWithShed(boolean withShed) {
        this.withShed = withShed;
    }

    @Override
    public String toString() {
        return "depth " + depth.getDepth() + " height " + height.getHeight() + "  width" + width.getWidth()
                + " shed depth " + shedDepth.getDepth() + " shed width " + shedWidth.getWidth();

    }
}
