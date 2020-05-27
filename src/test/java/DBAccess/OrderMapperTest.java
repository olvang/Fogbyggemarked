package DBAccess;

import Components.*;
import FunctionLayer.BillLine;
import FunctionLayer.Customer;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderMapperTest extends TestDataSetup {

    DepthComponent depth;
    HeightComponent height;
    WidthComponent width;
    InclineComponent incline;
    Order ord;
    Order order;
    Customer customer;
    {
        try {
            depth = new DepthComponent("200");
            height = new HeightComponent("200");
            width = new WidthComponent("200");
            incline = new InclineComponent(0);
            customer = new Customer(new NameComponent("John"),new AddressComponent("Vej vej"), new EmailComponent("john@mail.com"), new PhoneComponent("12345678"), new ZipCodeComponent("1234"));

        } catch (ValidationFailedException e) {
            e.printStackTrace();
        }

        ord = new Order(depth,height,width, incline, false,customer);
    }
    @Test
    public void updateOrderTest() throws SQLException, DatabaseException {

        Order order1 = OrderMapper.getOrder(1);

        int exspected1 = 300;

        assertEquals(exspected1, order1.getHeight());

        OrderMapper.updateOrder(1,ord);

        Order order = OrderMapper.getOrder(1);

        int exspectedDepth = 200;
        int exspectedHeight = 200;
        int exspectedWidth = 200;
        int exspectedIncline = 0;

        assertEquals(exspectedDepth,order.getDepth());
        assertEquals(exspectedHeight,order.getHeight());
        assertEquals(exspectedWidth, order.getWidth());
        assertEquals(exspectedIncline, order.getIncline());
    }
    @Test
    public void createOrderWithoutShed() {
        try {
            OrderMapper om = new OrderMapper();
            om.createOrder(ord);

        } catch (DatabaseException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void TestGetOrder(){
        OrderMapper om = new OrderMapper();
        int carportHeight = 300;
        try {
            order = om.getOrder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(carportHeight, order.getHeight());
    }

    @Test
    public void TestGetAllOrders(){
        OrderMapper om = new OrderMapper();
        ArrayList<Order> orders = null;
        try {
            orders = om.getAllOrders();
        } catch (Exception e) {
            fail("Could not get orders, exception: " + e);
        }

        assertEquals(1, orders.get(0).getOrderId());
        assertEquals(3, orders.get(2).getOrderId());
        assertEquals(5, orders.get(4).getOrderId());
        assertEquals(9, orders.get(8).getOrderId());

    }
}