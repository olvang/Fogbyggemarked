package DBAccess;

import FunctionLayer.Order;

import java.sql.*;

public class OrderMapper {
    /*
    Create orders takes an order object and check wether there is a shed in that order
    by looking at shedDepth which would null if there is not.

    Then it execute two diffrent sets of SQL statemenets depending on the
    information.,
     */
    public void createOrder(Order order) throws SQLException {
        try{
            Connection con = Connector.connection();

                String SQL = "INSERT INTO orders (carport_width, carport_height, carport_incline, carport_depth" +
                             ") VALUES (?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,order.getWidth().getWidth());
                ps.setInt(2,order.getHeight().getHeight());
                ps.setInt(3,order.getIncline());
                ps.setInt(4,order.getDepth().getDepth());

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);

            if(order.getShedDepth() != null){

                String nSQL = "INSERT INTO sheds (order_id,shed_width, shed_depth) VALUES (?,?,?)";

                PreparedStatement nps = con.prepareStatement(nSQL, Statement.RETURN_GENERATED_KEYS);
                nps.setInt(1,id);
                nps.setInt(2,order.getShedWidth().getWidth());
                nps.setInt(3,order.getDepth().getDepth());

                nps.executeUpdate();
            }

        }catch (SQLException e){
            throw new SQLException( e.getMessage() + "Could not create order");

        }catch(ClassNotFoundException e){
            throw new ClassCastException(e.getMessage() + "Connection could not be created");
        }

    }

}
