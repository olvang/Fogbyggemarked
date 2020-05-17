package FunctionLayer;

import DBAccess.MaterialsMapper;
import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.GeneratorException;
import FunctionLayer.Exceptions.ValidationFailedException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to connect the function layer with the other layers of the application
 */
public class LogicFacade {

    public static User login( String email, String password ) throws DatabaseException {
        return UserMapper.login( email, password );
    }
    /**
     * <p>Creates a User through the UserMapper</p>
     * @param email The user Email
     * @param email The user Password
     * @return User Returs a user object
     */
    public static User createUser( String email, String password ) throws DatabaseException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    /**
     * <p>Creates a Order through the OrderMapper </p>
     * @param order The order object to be created
     */
    public static void createOrder( Order order) throws DatabaseException {
        OrderMapper.createOrder(order);
    }

    /**
     * <p>Gets a Order through the OrderMapper </p>
     * @param orderID The order ID to be received from the db
     * @return Order A order object
     */
    public static Order getOrder(int orderID) throws DatabaseException {
        return OrderMapper.getOrder(orderID);
    }

    /**
     * <p>Updates a Order through the OrderMapper </p>
     * @param orderID The order ID to be updated within the db
     * @param order The updated order object
     */
    public static void updateOrder(int orderID, Order order) throws DatabaseException {
        OrderMapper.updateOrder(orderID, order);
    }

    /**
     * <p>Gets an arraylist of category objects from the db</p>
     * @param categoriesNeeded Array of category Id's to be received from the db
     * @return ArrayList<Category> an arraylist of category objects
     */
    public static ArrayList<Category> getTheseCategories(int[] categoriesNeeded) throws DatabaseException {
        return MaterialsMapper.getTheseCategories(categoriesNeeded);
    }

    /**
     * <p>Gets an arraylist of BillLine's through the BillCalculator </p>
     * @param orderID The OrderID to be calculated
     * @return ArrayList<BillLine> an Arraylist of BillLine's
     */
    public static ArrayList<BillLine> getBillLines(int orderID) throws GeneratorException, DatabaseException {
        BillCalculator billCalculator = new BillCalculator();
        return billCalculator.calculateBillFromOrder(getOrder(orderID));
    }

    /**
     * <p>Gets an arraylist of all the Order's in the db through the OrderMapper </p>
     * @return ArrayList<BillLine> an Arraylist of BillLine's
     */
    public static ArrayList<Order> getAllOrders() throws DatabaseException {
        return OrderMapper.getAllOrders();
    }
}
