package FunctionLayer;

import DBAccess.MaterialsMapper;
import DBAccess.OrderMapper;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.GeneratorException;

import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to connect the function layer with the other layers of the application
 */
public class LogicFacade {
    
    public static void createOrder( Order order) throws DatabaseException {
        OrderMapper.createOrder(order);
    }

    /**
     * <p>Gets a Order through the OrderMapper </p>
     * @param orderID The order ID to be received from the db
     * @return Order A order object
     * @throws DatabaseException A DatabaseException
     */
    public static Order getOrder(int orderID) throws DatabaseException {
        return OrderMapper.getOrder(orderID);
    }

    /**
     * <p>Updates a Order through the OrderMapper </p>
     * @param orderID The order ID to be updated within the db
     * @param order The updated order object
     * @throws DatabaseException A DatabaseException
     */
    public static void updateOrder(int orderID, Order order) throws DatabaseException {
        OrderMapper.updateOrder(orderID, order);
    }

    /**
     * <p>Gets an arraylist of category objects from the db</p>
     * @param categoriesNeeded Array of category Id's to be received from the db
     * @return an arraylist of category objects
     * @throws DatabaseException A DatabaseException
     */
    public static ArrayList<Category> getTheseCategories(int[] categoriesNeeded) throws DatabaseException {
        return MaterialsMapper.getTheseCategories(categoriesNeeded);
    }

    /**
     * <p>Gets an arraylist of BillLine's through the BillCalculator </p>
     * @param orderID The OrderID to be calculated
     * @return an Arraylist of BillLine's
     * @throws DatabaseException A DatabaseException
     * @throws GeneratorException An exception thrown when an error happens in a Generator
     */
    public static ArrayList<BillLine> getBillLines(int orderID) throws GeneratorException, DatabaseException {
        BillCalculator billCalculator = new BillCalculator();
        return billCalculator.calculateBillFromOrder(getOrder(orderID));
    }

    /**
     * <p>Gets an arraylist of all the Order's in the db through the OrderMapper </p>
     * @return an Arraylist of orders's
     * @throws DatabaseException A DatabaseException
     */
    public static ArrayList<Order> getAllOrders() throws DatabaseException {
        return OrderMapper.getAllOrders();
    }
}
