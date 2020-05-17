package FunctionLayer;

import Components.*;

import java.util.Date;

/**
 * Represents a order
 */
public class Order {

    private int orderId;
    private DepthComponent depth;
    private HeightComponent height;
    private WidthComponent width;
    private ShedDepthComponent shedDepth;
    private ShedWidthComponent shedWidth;
    private InclineComponent incline;
    private boolean withShed;
    private Date orderDate;
    private Customer customer;


    /**
     * <p>Constructor for an order with shed </p>
     * @param depth The depth of the carport in cm
     * @param height The depth of the carport in cm
     * @param width The width of the carport in cm
     * @param shedDepth The depth of the shed in cm
     * @param shedWidth The width of the shed in cm
     * @param incline How much incline the roof has. (0 = flat roof)
     * @param withShed Whether or not the order has a shed
     * @param customer The customer connected with the order
     */
    public Order(DepthComponent depth, HeightComponent height, WidthComponent width , ShedDepthComponent shedDepth,
                 ShedWidthComponent shedWidth, InclineComponent incline, boolean withShed, Customer customer) {

        this.depth = depth;
        this.height = height;
        this.width = width;
        this.shedDepth = shedDepth;
        this.shedWidth = shedWidth;
        this.incline = incline;
        this.withShed = withShed;
        this.customer = customer;
    }

    /**
     * <p>Constructor for an order without a shed </p>
     * @param depth The depth of the carport in cm
     * @param height The depth of the carport in cm
     * @param width The width of the carport in cm
     * @param incline How much incline the roof has. (0 = flat roof)
     * @param withShed Whether or not the order has a shed
     * @param customer The customer connected with the order
     */
    public Order(DepthComponent depth, HeightComponent height, WidthComponent width, InclineComponent incline, boolean withShed,
                 Customer customer) {

        this.depth = depth;
        this.height = height;
        this.width = width;
        this.incline = incline;
        this.withShed = withShed;
        this.customer = customer;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public DepthComponent getDepthComponent() {
        return depth;
    }

    public void setDepth(DepthComponent depth) {
        this.depth = depth;
    }

    public HeightComponent getHeightComponent() {
        return height;
    }

    public void setHeight(HeightComponent height) {
        this.height = height;
    }

    public WidthComponent getWidthComponent() {
        return width;
    }

    public void setWidth(WidthComponent width) {
        this.width = width;
    }

    public int getDepth() {
        return depth.getDepth();
    }

    public int getHeight() {
        return height.getHeight();
    }

    public int getWidth() {
        return width.getWidth();
    }

    public int getShedWidth() {
        return shedWidth.getWidth();
    }

    public int getShedDepth() {
        return shedDepth.getDepth();
    }

    public ShedDepthComponent getShedDepthComponent() {
        return shedDepth;
    }

    public void setShedDepth(ShedDepthComponent shedDepth) {
        this.shedDepth = shedDepth;
    }

    public ShedWidthComponent getShedWidthComponent() {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer(){
        return customer;
    }

    public String getCustomerName(){
        return customer.getName();
    }
    public String getCustomerAddress(){
        return customer.getAdresse();
    }
    public String getCustomerPhone(){
        return customer.getPhone();
    }
    public String getCustomerEmail(){
        return customer.getEmail();
    }
    public String getCustomerZipcode(){
        return customer.getZipcode();
    }

}
