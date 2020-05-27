package redDigital.automation.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redDigital.automation.entities.Sale;
import redDigital.automation.entities.User;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static java.time.ZoneId.systemDefault;
import static redDigital.automation.util.Constants.PROPERTY_HOME_URL;
import static redDigital.automation.util.PropertyUtil.getConfiguration;
//import java.sql.Date;

public class ModalLogin extends PageObject {
    //region locators
    @FindBy(id = "login-input-telefono")
    private WebElementFacade inputTelefono;
    @FindBy(id = "login-input-password")
    private WebElementFacade inputPassword;
    @FindBy(id = "login-input-uid")
    private WebElementFacade uid;
    @FindBy(id = "mensaje-login-error")
    private WebElementFacade errorUser;
    @FindBy(id = "button-entrar")
    private WebElementFacade btnEntrar;
    @FindBy(name = "fechaInicio")
    private WebElementFacade fecha1;
    @FindBy(name = "destino")
    private WebElementFacade destino1;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade btnConsutar;
    @FindBy(id = "reportResults")
    private WebElementFacade reportResults;
    @FindBy(className = "pagedisplay")
    private List<WebElement> pagedisplay;
    @FindBy(className = "next")
    private List<WebElement> next;
    @FindBy(css = ".exit-option.style-scope.ribbon-buttons")
    private List<WebElement> SignOff;
//endregion

    public class conexion {
        public Connection con;

        public conexion() {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con= DriverManager.getConnection("jdbc:sqlserver://JSMARKET\\JSMSPROD;databaseName=RedDigital","ROBOTJSMS","R0b0t_JSMS");
                System.out.println("conexion hecha sqlServer");
            } catch (Exception e) {
                System.err.println("Error:" +e);
            }

//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/redDigital", "root", "Passwod$1");
//            } catch (Exception e) {
//                System.err.println("Error:" + e);
//            }

        }
    }

    private WebDriver getAllWebDriver() {
        WebDriverFacade facade = (WebDriverFacade) getDriver();
        facade.withOptions("--headless");
        return facade.getProxiedDriver();
    }

    public void getUrl() throws InterruptedException {
        getAllWebDriver().get(getConfiguration(PROPERTY_HOME_URL));
        sleep(1500);
    }

    public List<User> conexionbd() throws InterruptedException, SQLException {
        conexion cn = new conexion();
        Statement st;
        ResultSet rs;
        List<User> users = new ArrayList<>();
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select * from users where activo =1 and fechaActualizada != ''");
            while (rs.next()) {
                User user = new User(rs.getString("usuario"), rs.getString("password"), rs.getString("fechaActualizada"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println(users.toString());
        }
        return users;
    }

    public void loginRedDigital() throws InterruptedException, SQLException {
        List<User> usuarios = conexionbd();
        usuarios.forEach(user -> {
            try {
                ejecutarPruebas(user);
                if (errorUser.isCurrentlyVisible()) {
                    System.out.println(errorUser.getText());
                } else {
                    searchResultado(user);
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        });
    }


    public void ejecutarPruebas(User usuario) throws InterruptedException, SQLException {
        sleep(1000);
        inputTelefono.sendKeys(usuario.getNombre());
        sleep(400);
        inputPassword.sendKeys(usuario.getPassword());
        sleep(400);
        btnEntrar.click();
        System.out.println("El usuario ingreso correctamente :" + usuario.getNombre());
    }

    public void cerrarSesion() throws InterruptedException {
        sleep(500);
        SignOff.get(0).click();
        sleep(800);
    }

    public BigDecimal conversion(String numerofeo) {
        BigDecimal x = new BigDecimal(numerofeo.split(",")[0].replace(".", ""));//coge la parte entera y si existe le quito uel punto
        BigDecimal d = new BigDecimal("." + numerofeo.split(",")[1]); // la parte decimal
        BigDecimal montoValor = x.add(d); // sumna de los valores
        System.out.println("numero correcto: " + montoValor);
        return montoValor;
    }


    public void fechaActualizadaBD(User user, String fechaIn) throws InterruptedException, SQLException {
        sleep(500);
        fecha1.clear();
        sleep(500);
        fecha1.sendKeys(fechaIn);
        System.out.println("Ingresando la fecha actual...");
        fecha1.sendKeys(Keys.ENTER);
        sleep(800);
        btnConsutar.click();
        System.out.println("Mostrando los resultados...");
        sleep(1000);
        if (reportResults.isCurrentlyVisible()) {
            List<Sale> saleList = new ArrayList<>();
            List<WebElement> Resultado = getAllWebDriver().findElements(By.xpath("//table[2]/tbody/tr/td"));
            System.out.println("Registros Totales " + Resultado.get(3).getText());
            int Registros_Totales = Integer.parseInt(Resultado.get(3).getText());
            for (int t = 0; t < Registros_Totales; t = t + 100) {
                List<WebElement> resultsDiv1 = getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr"));
                int resultado1 = resultsDiv1.size();
                for (int i = 1; i <= resultado1; i++) {
                    Sale sale = new Sale();
                    sale.setFecha(convertStringDate(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(0).getText()));
                    sale.setOrigen(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(1).getText());
                    sale.setDestino(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(2).getText());
                    sale.setConfirmacion(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(3).getText());
                    sale.setMonto(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(4).getText());
                    sale.setCarrier(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(5).getText());
                    sale.setOperacion(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(6).getText());
                    sale.setMedio(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(7).getText());
                    sale.setIdTerminal(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(8).getText());
                    String[] tipo = getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(4).getText().split(" ");
                    String valor = tipo[1];
                    String tipoValor = null;
                    if ("-S/".equals(tipo[0])) {
                        tipoValor = "Salida";
                    } else {
                        tipoValor = "Entrada";
                    }
                    sale.setTipo(tipoValor);
                    sale.setMontoValor(conversion(valor));
                    sale.setUserPusher(user.getNombre());
                    saleList.add(sale);
                }
                next.get(0).click();
                sleep(500);
            }
            insertarbd(saleList, user);
        }
        updateUsers(fechaIn, user);
    }


    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public void searchResultado(User user) throws InterruptedException, SQLException {
        sleep(1000);
        System.out.println("entre aqui");
        Date currentDate = new Date();
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String str_date_1 = user.getFechaActualizada();
        System.out.println("la fechaActualizada de la bd es " + str_date_1);
        LocalDate local_date_1 = LocalDate.parse(str_date_1, formatter_1);
        Date dateFromDB = convertToDateViaInstant(local_date_1);
        ZonedDateTime zdtCurrentDate = ZonedDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        ZonedDateTime zdtDateFromDB = ZonedDateTime.ofInstant(dateFromDB.toInstant(), ZoneId.systemDefault());
        LocalDate localDate = zdtCurrentDate.toLocalDate();
        String formattedDateActual = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (zdtDateFromDB.isBefore(zdtCurrentDate)) {
            Period period = Period.between(zdtDateFromDB.toLocalDate(), zdtCurrentDate.toLocalDate());
            int days = period.getDays();
            System.out.println("cantidad de dias " + days);
            Date dateFromDBToAddUp = dateFromDB;
            String fechaIn = dateFormat.format(dateFromDBToAddUp);
            System.out.println("la fecha a ingresar es: " + fechaIn);
            fechaActualizadaBD(user, fechaIn);
            if (fechaIn.equals(formattedDateActual)) {
                cerrarSesion();
            }

            for (int i = 0; i < days; i++) {
                Calendar c = Calendar.getInstance();
                c.setTime(dateFromDBToAddUp);
                c.add(Calendar.DATE, 1);
                dateFromDBToAddUp = c.getTime();
                String fechaIn2 = dateFormat.format(dateFromDBToAddUp);
                System.out.println("la fecha a ingresar es: " + fechaIn2);
                fechaActualizadaBD(user, fechaIn2);
                if (fechaIn2.equals(formattedDateActual)) {
                    cerrarSesion();
                }
            }

        }

    }


//    public void searchResultado(User user) throws InterruptedException, SQLException {
//        sleep(1800);
//        for (int day=10;day<24;day++) {
//            fecha1.clear();
//            sleep(500);
//            fecha1.sendKeys(+day+"/04/2020");
//            System.out.println("Ingresando fecha actual...: " +day+"/04/2020");
//            fecha1.sendKeys(Keys.ENTER);
//            sleep(800);
//            btnConsutar.click();
//            System.out.println("Mostrando los resultados...");
//            sleep(1000);
//            if (reportResults.isVisible()) {
//                List<Sale> saleList = new ArrayList<>();
//                List<WebElement> Resultado = getAllWebDriver().findElements(By.xpath("//table[2]/tbody/tr/td"));
//                System.out.println("Registros Totales " + Resultado.get(3).getText());
//                int Registros_Totales = Integer.parseInt(Resultado.get(3).getText());
//                for (int t = 0; t < Registros_Totales;  t= t+100) {
//                    List<WebElement> resultsDiv1 = getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr"));
//                    int resultado1 = resultsDiv1.size();
//                    for (int i = 1; i <= resultado1; i++) {
//                        Sale sale = new Sale();
//                        sale.setFecha(convertStringDate(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(0).getText()));
//                        sale.setOrigen(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(1).getText());
//                        sale.setDestino(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(2).getText());
//                        sale.setConfirmacion(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(3).getText());
//                        sale.setMonto(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(4).getText());
//                        sale.setCarrier(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(5).getText());
//                        sale.setOperacion(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(6).getText());
//                        sale.setMedio(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(7).getText());
//                        sale.setIdTerminal(getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(8).getText());
//                        String[] tipo= getAllWebDriver().findElements(By.xpath("//table[1]/tbody/tr[" + i + "]" + "/td")).get(4).getText().split(" ");
//                        String valor =tipo[1];
//                        String tipoValor = null;
//                        if ("-S/".equals(tipo[0])) {
//                            tipoValor = "Salida";
//                        }
//                        else {
//                            tipoValor = "Entrada";}
//                        sale.setTipo(tipoValor);
//                        sale.setMontoValor(conversion(valor));
//
//
//                        saleList.add(sale);
//                    }
//                    next.get(0).click();
//                    sleep(500);
//                }
//                insertarbd(saleList,user);
//            }
//        }
//        cerrarSesion();
////            else
////                cerrarSesion();
//    }

    public void insertarbd(List<Sale> sales, User user) throws SQLException {
        System.out.println("Insertando en Base de datos los resultados...");
        conexion cn = new conexion();
        Statement st;
        ResultSet rs;
        Timestamp ts = null;
        try {
            st = cn.con.createStatement();
            rs = st.executeQuery("select max(fecha) from sale where userPusher = '" + user.getNombre() + "'");
            while (rs.next()) {
                System.out.println("la fecha llega aqui");
                ts = rs.getTimestamp(1);
                System.out.println("la fecha " + ts);
            }
        } catch (Exception e) {

            System.out.println(e);
        }

        Timestamp finalTs = ts;
        List<Sale> salesFilter = new ArrayList<>();

        if (null == ts) {
            salesFilter = sales;
        } else {
            System.out.println("la fecha sale: " + sales.toString());
            salesFilter = sales.parallelStream().
                    filter(sale -> sale.getFecha().after(finalTs)).collect(Collectors.toList());
        }
        String query = "insert into sale " +
                "(fecha, origen, destino, confirmacion, monto, carrier, operacion, medio, idTerminal, tipo, montoValor, userPusher)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        salesFilter.forEach(sale -> {
            try {
                PreparedStatement preparedStmt = cn.con.prepareStatement(query);
                preparedStmt.setTimestamp(1, sale.getFecha());
                preparedStmt.setString(2, sale.getOrigen());
                preparedStmt.setString(3, sale.getDestino());
                preparedStmt.setString(4, sale.getConfirmacion());
                preparedStmt.setString(5, sale.getMonto());
                preparedStmt.setString(6, sale.getCarrier());
                preparedStmt.setString(7, sale.getOperacion());
                preparedStmt.setString(8, sale.getMedio());
                preparedStmt.setString(9, sale.getIdTerminal());
                preparedStmt.setString(10, sale.getTipo());
                preparedStmt.setBigDecimal(11, sale.getMontoValor());
                preparedStmt.setString(12, sale.getUserPusher());
                preparedStmt.execute();



            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public void updateUsers(String fechaIn, User user) throws InterruptedException, SQLException {

        System.out.println("Insertando en Base de datos los resultados...");
        conexion cn = new conexion();
//        String userPusher = null;
        try {
            PreparedStatement ps = cn.con.prepareStatement(
                    "UPDATE users SET fechaActualizada = ? WHERE usuario = '" + user.getNombre() + "'");
                  String a =  "UPDATE users SET userPusher = ? WHERE origen = '" + user.getNombre() + "'";
            System.out.println("la consulta :" + a);
            // set the preparedstatement parameters
            ps.setString(1,fechaIn);

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }


    Timestamp convertStringDate(String cadena) {

        SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date6 = null;
        Timestamp dateMysql = null;
        try {
            date6 = formatter6.parse(cadena);
            dateMysql = new Timestamp(date6.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateMysql;
    }
}