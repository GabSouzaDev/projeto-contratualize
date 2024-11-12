/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author gabri
 */
public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/contratualize";
        String user = "root";
        String password = "";
        
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if(connection != null) {
                System.out.println("Conexão bem-sucedida!");
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
