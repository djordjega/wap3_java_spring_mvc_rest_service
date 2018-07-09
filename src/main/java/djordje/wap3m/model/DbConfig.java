package djordje.wap3m.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author djordje gavrilovic
 */
public class DbConfig {

    Connection con;

    public Connection getCon() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/wapdb", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("DB EXC: " + e.getMessage());
        }

        return con;

    }

}
