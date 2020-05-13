/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.entities;

import static database.DBConstants.DB_PASS;
import static database.DBConstants.DB_URL;
import static database.DBConstants.DB_USER;
import database.exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Provider extends User{
    
    private static final String LOAD_BY_USERNAME = "SELECT * FROM PROVIDERS WHERE USERNAME = ? AND PASSWORD = ?";

    public Provider(int id, String username, String password) {
        super(id, username, password);
    }
    
    public static Provider load(String username, String password) throws SQLException, UserNotFoundException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement stmt = conn.prepareStatement(LOAD_BY_USERNAME);

        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet loaded = stmt.executeQuery();
        if (loaded.next()) {
            return new Provider(
                    loaded.getInt("ID"),
                    loaded.getString("USERNAME"),
                    loaded.getString("PASSWORD")
            );
        }
        throw new UserNotFoundException("User: " + username + " not found!");
    }
    
    public ArrayList<Job> getJobs() throws SQLException {
        return Job.getByProvider(this.getId());
    }

}
