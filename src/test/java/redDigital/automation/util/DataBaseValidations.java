//package redDigital.automation.util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import java.sql.*;
//
//public class DataBaseValidations {
//
//	public String validarCliente() throws SQLException {
//
//		Conexion conx = new Conexion();
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		String query = "";
//		String codigoCliente = "";
//		String nombreMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//		try {
//			conx.conectarBD();
//			con = conx.getCnx();
//			stmt = con.createStatement();
//			query = "SELECT * FROM user ";
//			System.out.println("la consulta es " +query );
//			rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				codigoCliente = rs.getString("usuario" +" " +rs.getString("password"));
//			}
//			System.out.println(codigoCliente);
//			return codigoCliente;
//
//		} catch (Exception e) {
////			logger.error(nombreMethod + ": " + e.getMessage());
//			return "0";
//		} finally {
////			conx.desconectarBD();
//		}
//	}
//}
