package redDigital.automation.features.steps;
import cucumber.api.java.en.Given;
import net.serenity_bdd.jbehave.SerenityStories;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import org.yecht.Data;
import redDigital.automation.features.steps.serenity.StepLogin;

import java.sql.SQLException;

public class CucumberLogin extends SerenityStories{

    @Steps
    StepLogin modalLogin;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LOGINN<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Given("^realizo la conexion a bd$")
    public void concexionBD() throws InterruptedException, SQLException {
        modalLogin.concexionBD();
    }



    @Given("^Ingreso mi usuario y contraseña$")
    public void Ingreso_mi_user_pass () throws InterruptedException, SQLException {

        modalLogin.getUrl();
        modalLogin.loginRedDigital();

    }

//    @Given("^Ingreso mi usuario (.+), contraseña (.+)$")
//    public void Ingreso_mi_user_pass (String user, String password) throws InterruptedException, SQLException {
//
//        modalLogin.getUrl();
//        modalLogin.loginRedDigital(user, password);
//        modalLogin.searchResultado();
//    }



}
/// TENGO QUE ARMAR LA ESTRUTURA PRIMERO PS
