//package redDigital.automation.util;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//import static redDigital.automation.util.Constants.PROPERTY_HOME_URL;
//import static redDigital.automation.util.PropertyUtil.getConfiguration;
//
//public class Conexion {
//
//    public Connection con;
//    private Connection cnx;
//    private String ipHost;
//    private String puerto;
//    private String baseDatos;
//    private String usuario;
//    private String contrasena;
//    private String cadenaConexion;
//    private String gestorBD;
//    private String driver;
//
//    public Conexion() {
//
//        //            this.ipHost = properties.getProperty("driver.host");
////            ;
////            this.puerto = "3306";
////            this.baseDatos = properties.getProperty("driver.database");
////            this.usuario = properties.getProperty("driver.usuario");
////            ;
////            ;
////            this.contrasena = properties.getProperty("driver.contrasena");
////            ;
////            ;
//        this.gestorBD = "MySQL";
//        if (this.gestorBD.equalsIgnoreCase("MySQL")) {
////            this.cadenaConexion = ("jdbc:mysql://" + this.ipHost + ":" + this.puerto + "/" + this.baseDatos);
//            this.cadenaConexion = ("jdbc:mysql://localhost:3306/redDigital");
//            this.driver = "org.mysql.Driver";
//        } else {
//            System.err.println("No existe implementacion para ese gestor");
//        }
//    }
//
//    public boolean conectarBD()
//            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
//        try {
//            Class.forName(this.driver).newInstance();
//            DriverManager.setLoginTimeout(1000);
//            this.cnx = DriverManager.getConnection(this.cadenaConexion, "root", "Passwod$1");
//            this.cnx.setTransactionIsolation(2);
//            this.cnx.setAutoCommit(true);
//            System.out.println("Conexión OK");
//            return true;
//        } catch (ClassNotFoundException ex) {
//            System.out.println("causa: " + ex.getCause());
//            System.out.println("Mensaje: " + ex.getMessage());
//            System.out.println("Localizacion: " + ex.getLocalizedMessage());
//            System.out.println("Traza: " + ex.getStackTrace());
//            throw ex;
//        } catch (InstantiationException ex) {
//            System.out.println("causa: " + ex.getCause());
//            System.out.println("Mensaje: " + ex.getMessage());
//            System.out.println("Localizacion: " + ex.getLocalizedMessage());
//            System.out.println("Traza: " + ex.getStackTrace());
//            throw ex;
//        } catch (IllegalAccessException ex) {
//            System.out.println("causa: " + ex.getCause());
//            System.out.println("Mensaje: " + ex.getMessage());
//            System.out.println("Localizacion: " + ex.getLocalizedMessage());
//            System.out.println("Traza: " + ex.getStackTrace());
//            throw ex;
//        } catch (SQLException ex) {
//            System.out.println("causa: " + ex.getCause());
//            System.out.println("Mensaje: " + ex.getMessage());
//            System.out.println("Localizacion: " + ex.getLocalizedMessage());
//            System.out.println("Traza: " + ex.getStackTrace());
//            throw ex;
//        }
//    }
//
////    public boolean desconectarBD()
////            throws SQLException {
////        try {
////            this.cnx.close();
////            return true;
////        } catch (SQLException ex) {
////            System.err.println("causa: " + ex.getCause());
////            System.err.println("Mensaje: " + ex.getMessage());
////            System.err.println("Localizacion: " + ex.getLocalizedMessage());
////            System.err.println("Traza: " + ex.getStackTrace());
////            throw ex;
////        }
////    }
//
//    public boolean confirmar()
//            throws SQLException {
//        try {
//            this.cnx.commit();
//            return true;
//        } catch (SQLException ex) {
//            System.err.println("causa: " + ex.getCause());
//            System.err.println("Mensaje: " + ex.getMessage());
//            System.err.println("Localizacion: " + ex.getLocalizedMessage());
//            System.err.println("Traza: " + ex.getStackTrace());
//            throw ex;
//        }
//    }
//
//    public boolean deshacer()
//            throws SQLException {
//        try {
//            this.cnx.rollback();
//            return true;
//        } catch (SQLException ex) {
//            System.err.println("causa: " + ex.getCause());
//            System.err.println("Mensaje: " + ex.getMessage());
//            System.err.println("Localizacion: " + ex.getLocalizedMessage());
//            System.err.println("Traza: " + ex.getStackTrace());
//            throw ex;
//        }
//    }
//
//    public Connection getCnx() {
//        return this.cnx;
//    }
//
//    public void setCnx(Connection cnx) {
//        this.cnx = cnx;
//    }
//
//    public String getIpHost() {
//        return this.ipHost;
//    }
//
//    public void setIpHost(String ipHost) {
//        this.ipHost = ipHost;
//    }
//
//    public String getPuerto() {
//        return this.puerto;
//    }
//
//    public void setPuerto(String puerto) {
//        this.puerto = puerto;
//    }
//
//    public String getBaseDatos() {
//        return this.baseDatos;
//    }
//
//    public void setBaseDatos(String baseDatos) {
//        this.baseDatos = baseDatos;
//    }
//
//    public String getUsuario() {
//        return this.usuario;
//    }
//
//    public void setUsuario(String usuario) {
//        this.usuario = usuario;
//    }
//
//    public String getContrasena() {
//        return this.contrasena;
//    }
//
//    public void setContrasena(String contrasena) {
//        this.contrasena = contrasena;
//    }
//
//    public String getCadenaConexion() {
//        return this.cadenaConexion;
//    }
//
//    public void setCadenaConexion(String cadenaConexion) {
//        this.cadenaConexion = cadenaConexion;
//    }
//
//    public String getGestorBD() {
//        return this.gestorBD;
//    }
//
//    public void setGestorBD(String gestorBD) {
//        this.gestorBD = gestorBD;
//    }
//
//    public String getDriver() {
//        return this.driver;
//    }
//
//    public void setDriver(String driver) {
//        this.driver = driver;
//    }
//}
//
