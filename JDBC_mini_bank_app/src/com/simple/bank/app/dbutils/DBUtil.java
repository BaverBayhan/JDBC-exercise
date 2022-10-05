package com.simple.bank.app.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static Properties get_DB_properties() throws IOException {
        FileInputStream in = new FileInputStream("src/dbconfig.properties");
        Properties dbproperties = new Properties();
        dbproperties.load(in);
        in.close();
        return dbproperties;
    }
    public static Connection getConnection() throws IOException, SQLException {
        Properties prop = get_DB_properties();
        return DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("username"), prop.getProperty("password"));
    }

}
