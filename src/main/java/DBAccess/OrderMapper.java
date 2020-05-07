package DBAccess;

import Components.*;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderMapper {
    /*
    Create orders takes an order object and check wether there is a shed in that order
    by looking at shedDepth which would null if there is not.

    Then it execute two diffrent sets of SQL statemenets depending on the
    information.,
     */
    public static void createOrder(Order order) throws DatabaseException {
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

        }catch (SQLException e) {
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        }catch(ClassNotFoundException e){
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        }

    }
    /*
        Gets an order on a given ID. Check whether the order has
        a shed on it or not.  
     */

    public static Order getOrder(int ID) throws DatabaseException {
        Order ord;
        int order_id;
        Date orderDate;
        try{
            Connection con = Connector.connection();

            String SQL = "SELECT * FROM ORDERS left join sheds on orders.order_id = sheds.order_id  where orders.order_id = ?;";

            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1,ID);

            ResultSet rs = ps.executeQuery();
            rs.next();

            order_id = rs.getInt("order_id");
            WidthComponent widthComponent = new WidthComponent(rs.getInt("carport_width"));
            DepthComponent depthComponent = new DepthComponent(rs.getInt("carport_depth"));
            HeightComponent heightComponent = new HeightComponent(rs.getInt("carport_height"));
            InclineComponent carportIncline = new InclineComponent(rs.getInt("carport_incline"));
            Timestamp ts = rs.getTimestamp("order_date");
            orderDate = new Date(ts.getTime());


            if(doesShedExists(ID)) {

                ShedWidthComponent shedWidthComponent = new ShedWidthComponent(rs.getInt("shed_width"),widthComponent);
                ShedDepthComponent shedDepthComponent = new ShedDepthComponent(rs.getInt("shed_depth"),depthComponent);

                ord = new Order(depthComponent,heightComponent,widthComponent,shedDepthComponent, shedWidthComponent,carportIncline,true);
            }else{
                ord = new Order(depthComponent,heightComponent,widthComponent,carportIncline,false);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        } catch (ValidationFailedException e) {
            throw new DatabaseException("Et element i ordre databasen fejlede validering: " + e.getMessage());
        }

        ord.setOrderId(order_id);
        ord.setOrderDate(orderDate);
        return ord;

    }

    /*
        Gets all orders. Check whether the order has
        a shed on it or not.
     */

    public static ArrayList<Order> getAllOrders() throws DatabaseException {
        ArrayList<Order> orders = new ArrayList<>();
        Order order;
        try{
            Connection con = Connector.connection();

            String SQL = "SELECT * FROM ORDERS left join sheds on orders.order_id = sheds.order_id;";

            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                WidthComponent widthComponent = new WidthComponent(rs.getInt("carport_width"));
                DepthComponent depthComponent = new DepthComponent(rs.getInt("carport_depth"));
                HeightComponent heightComponent = new HeightComponent(rs.getInt("carport_height"));
                InclineComponent carportIncline = new InclineComponent(rs.getInt("carport_incline"));
                Timestamp ts = rs.getTimestamp("order_date");
                Date orderDate = new Date(ts.getTime());


                if(doesShedExists(order_id)) {

                    ShedWidthComponent shedWidthComponent = new ShedWidthComponent(rs.getInt("shed_width"),widthComponent);
                    ShedDepthComponent shedDepthComponent = new ShedDepthComponent(rs.getInt("shed_depth"),depthComponent);

                    order = new Order(depthComponent,heightComponent,widthComponent,shedDepthComponent, shedWidthComponent,carportIncline,true);
                }else{
                    order = new Order(depthComponent,heightComponent,widthComponent,carportIncline,false);
                }
                order.setOrderId(order_id);
                order.setOrderDate(orderDate);
                orders.add(order);
            }


        } catch (SQLException e) {
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        } catch (ValidationFailedException e) {
            throw new DatabaseException("Et element i ordre databasen fejlede validering: " + e.getMessage());
        }

        return orders;

    }


    /*
        Check whether or not a shed exists to the given ID.
        Primary job, being a helper class to the method - getOrder
     */
    private static boolean doesShedExists(int ID) throws SQLException, ClassNotFoundException{
            Connection con = Connector.connection();

            String SQL = "SELECT count(*) from sheds where order_id = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,ID);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt("count(*)");

            if(count > 0){
                return true;
            }

            return false;
    }

}
