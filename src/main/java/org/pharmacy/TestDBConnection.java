package org.pharmacy;

import java.sql.Connection;
import java.sql.SQLOutput;

public class TestDBConnection {
    public static void main(String [] args){
        try{
            Connection connection =DBConnection.getConnection();
//            if(connection !=null){
//                System.out.println("Connection succesfull");
//            }else{
//                System.out.println("connection failed");
//            }
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT 1;");
            if (resultSet.next()) {
                System.out.println("Database interaction successful!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
