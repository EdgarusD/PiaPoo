/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectofinal.models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Egarus
 */
public class Db {
    public static Connection conexion() {
        Connection connection = null;
        try {
            // the mysql driver string
            Class.forName("com.mysql.jdbc.Driver");

            // the mysql url
            String url = "jdbc:mysql://localhost/poo_final";

            // get the mysql database connection
            connection = DriverManager.getConnection(url, "root", "root");

            // now do whatever you want to do with the connection
            // ...
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }
}
