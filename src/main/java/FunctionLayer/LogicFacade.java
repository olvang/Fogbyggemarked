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
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws DatabaseException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws DatabaseException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    public static void createOrder( Order order) throws DatabaseException {
        OrderMapper.createOrder(order);
    }

    public static Order getOrder(int orderID) throws DatabaseException {
        return OrderMapper.getOrder(orderID);
    }

    public static ArrayList<Category> getTheseCategories(int[] categoriesNeeded) throws DatabaseException {
        return MaterialsMapper.getTheseCategories(categoriesNeeded);
    }

    public static ArrayList<BillLine> getBillLines(int orderID) throws GeneratorException, DatabaseException {
        BillCalculator billCalculator = new BillCalculator();
        return billCalculator.calculateBillFromOrder(getOrder(orderID));
    }

    public static ArrayList<Order> getAllOrders() throws DatabaseException {
        return OrderMapper.getAllOrders();
    }
}
