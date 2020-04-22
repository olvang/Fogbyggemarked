package FunctionLayer;

import DBAccess.MaterialsMapper;
import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import FunctionLayer.Exceptions.CommandException;
import FunctionLayer.Exceptions.ValidationFailedException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws CommandException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws CommandException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    public static void createOrder( Order order) throws CommandException, SQLException {
        OrderMapper orderMapper = new OrderMapper();
        orderMapper.createOrder(order);
    }

    public static Order getOrder(int orderID) throws Exception {
        OrderMapper orderMapper = new OrderMapper();
        return orderMapper.getOrder(orderID);
    }

    public static ArrayList<Material> getTheseMaterials(int[] categoriesNeeded) throws CommandException, SQLException, ValidationFailedException, ClassNotFoundException {
        MaterialsMapper materialsMapper = new MaterialsMapper();
        return materialsMapper.getTheseMaterials(categoriesNeeded);
    }

    public static ArrayList<BillLine> getBillLines(int orderID) throws Exception {
        BillCalculator billCalculator = new BillCalculator();
        return billCalculator.calculateBillFromOrder(getOrder(orderID));
    }
}
