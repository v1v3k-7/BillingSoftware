
package DbCon;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Clock;


public class DbOperations 
{
    public static ResultSet login(String mail, String pass) 
    {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try 
        {
            con = DbConnection.getConnection();

            ps = con.prepareStatement(
                "SELECT * FROM register WHERE email=? AND password=?"
            );

            ps.setString(1, mail);
            ps.setString(2, pass);

            rs = ps.executeQuery();
        }
        catch (SQLException e) 
        {
            System.out.println("DbOperation Login Error: " + e);
        }

        return rs;
    }
    public static boolean regEmployee(String... str)
    {
        boolean res = false;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO register(email, name, module, phone, gender, password) VALUES(?, ?, ?, ?, ?, ?);");
                )
        {
            ps.setString(1, str[0]);
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.setString(5, str[4]);
            ps.setString(6, str[5]);

            int rs=ps.executeUpdate();
            if(rs>0) res=true;
        }
        catch (Exception e) 
        {
            System.out.println("DbOperation Register Employee Error: " + e);
        }

        return res;
    }
    
    public static ResultSet getAllEmp() 
    {
        try 
        {
            Connection con = DbConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM register");

            ResultSet rs = ps.executeQuery();

            return rs;
        } 
        catch (Exception e) 
        {
            System.out.println("DbOperation Get All Employee Error: " + e);
            return null;
        }
    }
    public static ResultSet getAllItems() 
    {
        try 
        {
            Connection con = DbConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement("SELECT item_id, name, quantity FROM items");

            ResultSet rs = ps.executeQuery();

            return rs;
        } 
        catch (Exception e) 
        {
            System.out.println("DbOperation Get All Items Error: " + e);
            return null;
        }
    }
    public static ResultSet getItem(String itemId)
    {
        ResultSet rs=null;
        try
        {
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM items WHERE item_id=?");
            ps.setString(1, itemId);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println("[DbOperation.getItem] Error: " + e);
        }
        return rs;
    }
    public static boolean update(String... str)
    {
        boolean res=false;
        try (
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("UPDATE register SET email=?, name=?, phone=?, gender=?, password=? WHERE email=?");
                )
        {
            ps.setString(6, str[0]);
            ps.setString(1, str[1]);
            ps.setString(2, str[2]);
            ps.setString(3, str[3]);
            ps.setString(4, str[4]);
            ps.setString(5, str[5]);
            
            int rs=ps.executeUpdate();
            if(rs>0) res=true;
        }
        catch(Exception e)
        {
            System.out.println("[DbOperation.update] Error: "+e);
        }
        return res;
    }
    public static boolean checkPass(String mail, String password)
    {
        boolean res=false;
        try (
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("SELECT * FROM register WHERE email = ? AND password = ?");
                )
        {
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) res=true;
            rs.close();
        }
        catch(Exception e)
        {
            System.err.println("[DbOperations.checkPass] Error: " + e);
        }
        return res;
    }
    public static boolean setNewPass(String mail, String password)
    {
        boolean res=false;
        try(
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("UPDATE register SET password=? WHERE email=?;")
                )
        {
            ps.setString(2, mail);
            ps.setString(1, password);
            int rs=ps.executeUpdate();
            if(rs>0) res=true;
        }
        catch(Exception e)
        {
            System.err.println("[DbOperations.setNewPass] Error: " + e);
        }
        return res;
    }
    public static boolean updateItem(String item_id, InputStream is, String... str)
    {
        boolean res=false;
        try (
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("UPDATE items SET name=?, description=?, quantity=?, price=?, img=? WHERE id=?");
                )
        {
            ps.setString(6, item_id);
            ps.setString(1, str[0]);
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.setBinaryStream(5, is);
            
            int rs=ps.executeUpdate();
            if(rs>0) res=true;
        }
        catch(Exception e)
        {
            System.out.println("[DbOperation.updateItem] Error: "+e);
        }
        return res;
    }
    public static ResultSet getInfo(String mail)
    {
        ResultSet rs=null;
        try
        {
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM register WHERE email=?");
            ps.setString(1, mail);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println("[DbOperation.getInfo] Error: " + e);
        }
        return rs;
    }
    public static boolean deleteItem(String item_id)
    {
        boolean res=false;
        try(
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("DELETE FROM items WHERE item_id=?");
                )
        {
            ps.setString(1, item_id);
            int rs=ps.executeUpdate();
            if(rs>0) res=true;
        }
        catch(Exception e)
        {
            System.out.println("[DbOperations.deleteItem] Error: " + e);
        }
        return res;
    }
    public static boolean deleteEmp(String mail)
    {
        boolean res=false;
        try(
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("DELETE FROM register WHERE email=?");
                )
        {
            ps.setString(1, mail);
            int rs=ps.executeUpdate();
            if(rs>0) res=true;
        }
        catch(Exception e)
        {
            System.out.println("[DbOperations.deleteEmp] Error: " + e);
        }
        return res;
    }
    
    public static boolean addItem(FileInputStream fis, String... str)
    {
        boolean res=false;
        try(
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("INSERT INTO items VALUES(?, ?, ?, ?, ?, ?);")
                )
        {
            ps.setString(1, str[0]);
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.setString(5, str[4]);
            ps.setBinaryStream(6, fis);
            int i=ps.executeUpdate();
            if(i>0) res=true;
        }
        catch(Exception e)
        {
            System.err.println("[DbOperations.addItem] Error: " + e);
        }
        return res;
    }
    
    public static int get_id(String email) 
    {
        int id = -1;

        String sql = "SELECT id FROM register WHERE email=?";

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) 
        {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    id = rs.getInt("id");
                }
            }

        } 
        catch (SQLException e) 
        {
            System.out.println("[DBOperations.get_id] Error: " + e);
        }
        return id;
    }
    
    public static boolean insert_order(String products, float price, int admin_id, String... info)
    {
        boolean res=false;
        String sql="INSERT INTO orders(products, price, user_id, email, phone, gender, name, payment_method, admin_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(
                Connection con=DbConnection.getConnection();
                PreparedStatement ps=con.prepareStatement(sql)
                )
        {
            ps.setString(1, products);
            ps.setFloat(2, price);
            String email=info[1];
            int id=get_id(email);
            if(id==-1) ps.setNull(3, Types.INTEGER);
            else ps.setInt(3, id);
            ps.setString(4, email);
            ps.setString(5, info[2]);
            ps.setString(6, info[3]);
            ps.setString(7, info[0]);
            ps.setString(8, info[4]);
            ps.setInt(9, admin_id);
            int i=ps.executeUpdate();
            if(i>0) res=true;
        }
        catch(Exception e)
        {
            System.out.println("[DBOperations.Insert_Order] Error: "+e);
        }
        return res;
    }
    public static ResultSet get_orders(int userid)
    {
        ResultSet rs=null;
        try
        {
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("SELECT order_id, price, payment_method, datetime FROM orders WHERE user_id=?");
            ps.setInt(1, userid);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.err.println("[DbOperation.get_orders] Error: "+e);
        }
        return rs;
    }
    public static ResultSet get_order(int order_id)
    {
        ResultSet rs=null;
        try
        {
            Connection con=DbConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("SELECT products, price FROM orders WHERE order_id=?");
            ps.setInt(1, order_id);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.err.println("[DbOperation.get_orders] Error: "+e);
        }
        return rs;
    }
}
