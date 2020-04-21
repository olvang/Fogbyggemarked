package FunctionLayer;

import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import FunctionLayer.Exceptions.CommandException;

import java.sql.SQLException;

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

}
