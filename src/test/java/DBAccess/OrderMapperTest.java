package DBAccess;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.InclineComponent;
import Components.WidthComponent;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrderMapperTest extends TestDataSetup {

    DepthComponent depth;
    HeightComponent height;
    WidthComponent width;
    InclineComponent incline;
    Order ord;
    Order order;
    {
        try {
            depth = new DepthComponent("200");
            height = new HeightComponent("200");
            width = new WidthComponent("200");
            incline = new InclineComponent(0);

        } catch (ValidationFailedException e) {
            e.printStackTrace();
        }

        ord = new Order(depth,height,width, incline, false);
    }

    @Test
    public void createOrderWithoutShed() {
        try {
            OrderMapper om = new OrderMapper();
            om.createOrder(ord);

        } catch (SQLException throwables) {
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

        assertEquals(carportHeight, order.getHeight().getHeight());
    }
}