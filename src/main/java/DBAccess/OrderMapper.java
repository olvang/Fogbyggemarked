package DBAccess;

import Components.*;
import FunctionLayer.Customer;
import FunctionLayer.Exceptions.DatabaseException;
import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Log;
import FunctionLayer.Order;

import java.sql.*;
import java.util.ArrayList;

/**
 * Used to get information from the database associated with an order
 */
public class OrderMapper {
    /**
     * <p>Creates a new order</p>
     * <p>Create orders takes an order object and check whether there is a shed in that order</p>
     * <p>by looking at shedDepth which would null if there is not.</p>
     * <p>Then it execute two different sets of SQL statements depending on the information.</p>
     * @param order The order object with the information to be created in the db
     * @throws DatabaseException An exception for database erros
     */
    public static void createOrder(Order order) throws DatabaseException {
        try{
            Connection con = Connector.connection();

            String SQL = "INSERT INTO orders (carport_width, carport_height, carport_incline, carport_depth, " +
                    "customer_name, customer_adresse, customer_email, customer_phonenumber, customer_zipcode) " +
                    "VALUES (?, ?, ?, ?,?, ?, ?, ?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,order.getWidth());
            ps.setInt(2,order.getHeight());
            ps.setInt(3,order.getIncline());
            ps.setInt(4,order.getDepth());

            ps.setString(5,order.getCustomerName());
            ps.setString(6,order.getCustomerAddress());
            ps.setString(7,order.getCustomerEmail());
            ps.setString(8,order.getCustomerPhone());
            ps.setString(9,order.getCustomerZipcode());

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);

            if(order.getShedDepthComponent() != null){

                String nSQL = "INSERT INTO sheds (order_id,shed_width, shed_depth) VALUES (?,?,?)";

                PreparedStatement nps = con.prepareStatement(nSQL, Statement.RETURN_GENERATED_KEYS);
                nps.setInt(1,id);
                nps.setInt(2,order.getShedWidth());
                nps.setInt(3,order.getShedDepth());

                nps.executeUpdate();
            }

        }catch (SQLException e) {
            Log.severe("Order mapper: Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        }catch(ClassNotFoundException e){
            Log.severe("Order mapper: Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        }

    }
    /**
     * <p>Updates an existing order</p>
     * @param orderID The Order ID to be updated
     * @param order The Order object with the updated information
     * @throws DatabaseException An exception for database erros
     */
    public static void updateOrder(int orderID, Order order) throws DatabaseException {
        try {
            Connection con = Connector.connection();

            String SQL = "UPDATE orders " +
                    "SET carport_width = ?, carport_depth = ?, carport_height = ?, carport_incline = ? " +
                    "where order_id = ?";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,order.getWidth());
            ps.setInt(2,order.getDepth());
            ps.setInt(3,order.getHeight());
            ps.setInt(4,order.getIncline());
            ps.setInt(5,orderID);

            ps.executeUpdate();

            if(order.isWithShed()){
                String nSQL = "UPDATE sheds SET shed_width = ?, shed_depth = ? WHERE order_id = ?";

                PreparedStatement nps = con.prepareStatement(nSQL, Statement.RETURN_GENERATED_KEYS);

                nps.setInt(1,order.getShedWidth());
                nps.setInt(2,order.getShedDepth());
                nps.setInt(3,orderID);

                nps.executeUpdate();
            }

        }catch (SQLException e) {
            Log.severe("Order mapper: Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        }catch(ClassNotFoundException e){
            Log.severe("Order mapper: Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        }
    }
    /**
     * <p>Gets a single order from database based on the ID</p>
     * @param ID The Order ID to be updated
     * @return Order The Order object pulled from the database,
     * @throws DatabaseException An exception for database erros
     */
    public static Order getOrder(int ID) throws DatabaseException {
        Order ord;
        int order_id;
        Date orderDate;
        try{
            Connection con = Connector.connection();

            String SQL = "SELECT * FROM orders left join sheds on orders.order_id = sheds.order_id  where orders.order_id = ?;";

            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1,ID);

            ResultSet rs = ps.executeQuery();
            rs.next();

            order_id = rs.getInt("order_id");
            WidthComponent widthComponent = new WidthComponent(rs.getInt("carport_width"));
            DepthComponent depthComponent = new DepthComponent(rs.getInt("carport_depth"));
            HeightComponent heightComponent = new HeightComponent(rs.getInt("carport_height"));
            InclineComponent carportIncline = new InclineComponent(rs.getInt("carport_incline"));

            NameComponent name = new NameComponent(rs.getString("customer_name"));
            AddressComponent adress = new AddressComponent(rs.getString("customer_adresse"));
            EmailComponent email = new EmailComponent(rs.getString("customer_email"));
            PhoneComponent phonenumber = new PhoneComponent(rs.getString("customer_phonenumber"));
            ZipCodeComponent zipCode = new ZipCodeComponent(rs.getString("customer_zipcode"));

            Customer customer = new Customer(name,adress,email,phonenumber,zipCode);

            Timestamp ts = rs.getTimestamp("order_date");
            orderDate = new Date(ts.getTime());


            if(doesShedExists(ID)) {

                ShedWidthComponent shedWidthComponent = new ShedWidthComponent(rs.getInt("shed_width"),widthComponent);
                ShedDepthComponent shedDepthComponent = new ShedDepthComponent(rs.getInt("shed_depth"),depthComponent);

                ord = new Order(depthComponent,heightComponent,widthComponent,shedDepthComponent, shedWidthComponent,carportIncline,true,customer);
            }else{
                ord = new Order(depthComponent,heightComponent,widthComponent,carportIncline,false,customer);
            }

        }catch (SQLException e) {
            Log.severe("Order mapper: Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        }catch(ClassNotFoundException e){
            Log.severe("Order mapper: Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        } catch (ValidationFailedException e) {
            Log.warning("Order mapper: Et element i ordre databasen fejlede validering: " + e.getMessage());
            throw new DatabaseException("Et element i ordre databasen fejlede validering: " + e.getMessage());
        }

        ord.setOrderId(order_id);
        ord.setOrderDate(orderDate);
        return ord;

    }
    /**
     * <p>Gets all orders from the database</p>
     * <p>Also checks if each order has a shed connected with it</p>
     * @return Arraylist of orders with all orders in the database
     * @throws DatabaseException An exception for database erros
     */
    public static ArrayList<Order> getAllOrders() throws DatabaseException {

        ArrayList<Order> orders = new ArrayList<>();
        Order order;
        try{
            Connection con = Connector.connection();

            String SQL = "SELECT * FROM orders left join sheds on orders.order_id = sheds.order_id;";

            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                WidthComponent widthComponent = new WidthComponent(rs.getInt("carport_width"));
                DepthComponent depthComponent = new DepthComponent(rs.getInt("carport_depth"));
                HeightComponent heightComponent = new HeightComponent(rs.getInt("carport_height"));
                InclineComponent carportIncline = new InclineComponent(rs.getInt("carport_incline"));

                NameComponent name = new NameComponent(rs.getString("customer_name"));
                AddressComponent adress = new AddressComponent(rs.getString("customer_adresse"));
                EmailComponent email = new EmailComponent(rs.getString("customer_email"));
                PhoneComponent phonenumber = new PhoneComponent(rs.getString("customer_phonenumber"));
                ZipCodeComponent zipCode = new ZipCodeComponent(rs.getString("customer_zipcode"));

                Customer customer = new Customer(name,adress,email,phonenumber,zipCode);


                Timestamp ts = rs.getTimestamp("order_date");
                Date orderDate = new Date(ts.getTime());


                if(doesShedExists(order_id)) {

                    ShedWidthComponent shedWidthComponent = new ShedWidthComponent(rs.getInt("shed_width"),widthComponent);
                    ShedDepthComponent shedDepthComponent = new ShedDepthComponent(rs.getInt("shed_depth"),depthComponent);

                    order = new Order(depthComponent,heightComponent,widthComponent,shedDepthComponent, shedWidthComponent,carportIncline,true,customer);
                }else{
                    order = new Order(depthComponent,heightComponent,widthComponent,carportIncline,false,customer);
                }
                order.setOrderId(order_id);
                order.setOrderDate(orderDate);
                orders.add(order);
            }


        } catch (SQLException e) {
            Log.severe("Order mapper: Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
            throw new DatabaseException("Der kunne ikke oprettes forbindelse til ordre databasen: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.severe("Order mapper: Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
            throw new DatabaseException("Der skete en serverfejl. ClassNotFound in OrderMapper: " + e.getMessage());
        } catch (ValidationFailedException e) {
            Log.warning("Order mapper: Et element i ordre databasen fejlede validering: " + e.getMessage());
            throw new DatabaseException("Et element i ordre databasen fejlede validering: " + e.getMessage());
        }

        return orders;

    }


    /**
     * <p>Check if a shed exists for an order</p>
     * @param ID The Order ID to be checked for a shed
     * @return True if exist, else false
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
