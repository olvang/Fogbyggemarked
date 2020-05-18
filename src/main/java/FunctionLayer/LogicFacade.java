package FunctionLayer;

import DBAccess.MaterialsMapper;
import DBAccess.OrderMapper;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.GeneratorException;

import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static void createOrder( Order order) throws DatabaseException {
        OrderMapper.createOrder(order);
    }

    public static Order getOrder(int orderID) throws DatabaseException {
        return OrderMapper.getOrder(orderID);
    }

    public static void updateOrder(int orderID, Order order) throws DatabaseException {
        OrderMapper.updateOrder(orderID, order);
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
