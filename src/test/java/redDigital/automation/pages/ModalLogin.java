package redDigital.automation.pages;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
//import redDigital.automation.util.DataBaseValidations;
import org.yecht.Data;
import redDigital.automation.entities.Sale;
import redDigital.automation.entities.User;
import redDigital.automation.util.conexion;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static redDigital.automation.util.Constants.PROPERTY_HOME_URL;
import static redDigital.automation.util.PropertyUtil.getConfiguration;

public class ModalLogin extends PageObject {

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


    public List<User> conexionbd() throws InterruptedException, SQLException {
        conexion cn=new conexion();
        Statement st;
        ResultSet rs;
        List<User> users = new ArrayList<>();
        try {
            st=cn.con.createStatement();
            rs=st.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getString("usuario")+" " +rs.getString("password"));
                User user = new User(rs.getString("usuario"), rs.getString("password"));
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
      List<User> usuarios =  conexionbd();
        usuarios.forEach(user -> {
            try {
                ejecutarPruebas(user);
                if (errorUser.isVisible()){
                    System.out.println(errorUser.getText());}
                else
                searchResultado(user);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void ejecutarPruebas(User usuario) throws InterruptedException, SQLException {
        sleep(2000);
        inputTelefono.sendKeys(usuario.getNombre());
        System.out.println("ingresando el usuario :" +usuario.getNombre());
        sleep(500);
        inputPassword.sendKeys(usuario.getPassword());
        System.out.println("ingresando el password :" +usuario.getPassword());
        sleep(500);
        btnEntrar.click();
    }

    public void cerrarSesion() throws InterruptedException {
        sleep(1000);
        System.out.println("ingresando numero de telfono y contraseña...");
        SignOff.get(0).click();
        sleep(2000);
    }

    private WebDriver getAllWebDriver() {
        WebDriverFacade facade = (WebDriverFacade) getDriver();
//        facade.withOptions("--headless");
        facade.withOptions("--disable-dev-shm-usage");
        return facade.getProxiedDriver();
    }
    public void getUrl() {
        getAllWebDriver().get(getConfiguration(PROPERTY_HOME_URL));
        getAllWebDriver().manage().window().maximize();
    }

    public String fechaActual(){
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = formatter.format(fecha);
        System.out.println(fechaActual);

        return fechaActual;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> LOGIN <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void searchResultado(User user) throws InterruptedException, SQLException {
        sleep(2000);
        fecha1.sendKeys("15/03/2020");
        System.out.println("Ingresando fecha actual...");
        fecha1.sendKeys(Keys.ENTER);
        sleep(800);
        btnConsutar.click();
        System.out.println("Mostrando los resultados...");
        sleep(1000);
        if (reportResults.isVisible()) {
            List<Sale> saleList = new ArrayList<>();
            List<WebElement> Resultado = getAllWebDriver().findElements(By.xpath("//table[2]/tbody/tr/td"));
            System.out.println("Registros Totales " + Resultado.get(3).getText());
            int Registros_Totales = Integer.parseInt(Resultado.get(3).getText());
            for (int t = 0; t < Registros_Totales;  t= t+100) {
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
                        saleList.add(sale);
                    }
                    next.get(0).click();
                    sleep(500);
            }
            insertarbd(saleList,user);
            cerrarSesion();
        }
        else
            cerrarSesion();
    }

    public void insertarbd( List<Sale> sales,User user) throws SQLException {
        System.out.println("Insertando en Base de datos los resultados...");
        conexion cn=new conexion();
        Statement st;
        ResultSet rs;
        Timestamp ts = null;
        try {
            st=cn.con.createStatement();
            rs=st.executeQuery("select max(fecha) from sale where origen = ".concat(user.getNombre()) );
            while (rs.next()) {
                ts = rs.getTimestamp(1);
                System.out.println(rs.getString(" obtener fecha: " + ts));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Timestamp finalTs = ts;
        List<Sale> salesFilter = new ArrayList<>();

        if (null == ts) {
            salesFilter =  sales;
        } else {

            salesFilter =  sales.parallelStream().filter(sale -> sale.getFecha().after(finalTs)).collect(Collectors.toList());
        }
            String query="insert into sale " +
                "(fecha, origen, destino, confirmacion, monto, carrier, operacion, medio, idTerminal)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";
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
                preparedStmt.execute();
                } catch (Exception e) {
                    System.out.println(e);
                    }
        } );
    }

    Timestamp convertStringDate(String cadena) {

        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date6= null;
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