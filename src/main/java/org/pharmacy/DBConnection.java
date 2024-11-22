package org.pharmacy;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/pharmacy";
    private static final String USER="postgres";
    private static final String PASSWORD="root";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error connecting to postgres");
        }
    }
}
