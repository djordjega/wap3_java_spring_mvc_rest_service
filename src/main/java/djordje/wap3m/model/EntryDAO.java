package djordje.wap3m.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author djordje gavrilovic
 */
public class EntryDAO {
    
    DbConfig db;
    private static Map<String, Entry> entryMap = new HashMap<String, Entry>();
    
    /* 
    * returns a map of all entries
     */
    public Map<String, Entry> getAllEntries() {

        db = new DbConfig();
        try (Connection con = db.getCon();) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from waptable");
            while (rs.next()) {
                entryMap.put(rs.getString("id"), new Entry(rs.getInt("id"), rs.getString("title"), rs.getString("text")));
            }
            return (entryMap);
        } catch (SQLException ex) {
            System.out.println("GET allEntries: " + ex.getMessage());
            return null;
        }
    }
    
    /* 
    * creates new entry
     */
    public void addNewEntry(String title, String text) {

        db = new DbConfig();
        try (Connection con = db.getCon();) {
            PreparedStatement st = con.prepareStatement("insert into waptable(title,text) values(?,?)");
            st.setString(1, title);
            st.setString(2, text);
            st.execute();
        } catch (SQLException ex) {
            System.out.println("POST newEntry: " + ex.getMessage());
        }
    }
    
    /* 
    * deletes selected entry 
     */
    public void removeEntry(String id) {
        db = new DbConfig();
        try (Connection con = db.getCon();) {
            PreparedStatement st = con.prepareStatement("delete from waptable where id=?");
            st.setString(1, id);
            st.execute();
        } catch (SQLException ex) {
            System.out.println("DELETE removeEntry: " + ex.getMessage());
        }
    }
    
}
