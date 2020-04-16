//package redDigital.automation.util;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//
//public class conexion {
//    public Connection con;
//    public conexion(){
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con= DriverManager.getConnection("jdbc:sqlserver://JSMARKET\\JSMSPROD;databaseName=RedDigital","ROBOTJSMS","R0b0t_JSMS");
//            System.out.println("conexion hecha sql");
//
//        } catch (Exception e) {
//            System.err.println("Error:" +e);
//        }
//    }
//}
//
