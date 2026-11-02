/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbCon;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author cv
 */
public class DbConnection 
{
    static Connection con;
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_software", "root", "root");
        }
        catch(Exception e)
        {
            System.out.println("DB Connection Error: "+e);
        }
        return con;
    }
}
