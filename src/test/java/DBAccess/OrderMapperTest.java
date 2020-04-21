package DBAccess;

import Components.DepthComponent;
import Components.HeightComponent;
import Components.WidthComponent;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrderMapperTest {

    DepthComponent depth;
    HeightComponent height;
    WidthComponent width;
    Order ord;
    {
        try {
            depth = new DepthComponent("200");
            height = new HeightComponent("200");
            width = new WidthComponent("200");

        } catch (ValidationFailedException e) {
            e.printStackTrace();
        }

        ord = new Order(depth,height,width,0);
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
}