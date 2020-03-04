package redDigital.automation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexion {
    public Connection con;
    public conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/redDigital","root","Passwod$1");
        } catch (Exception e) {
            System.err.println("Error:" +e);
        }
    }
//    public static void main(String[] args) {
//        conexion cn=new conexion();
//        Statement st;
//        ResultSet rs;
//        String user;
//        String pass;
//        try {
//            st=cn.con.createStatement();
//            rs=st.executeQuery("select * from user");
//            while (rs.next()) {
//                System.out.println(rs.getString("usuario")+" " +rs.getString("password"));
//                user = rs.getString("usuario");
//                System.out.println(user);
//                pass = rs.getString("password");
//                System.out.println(pass);
//            }
//            cn.con.close();
//        } catch (Exception e) {
//        }
//
//    }
}