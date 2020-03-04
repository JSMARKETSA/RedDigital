package redDigital.automation.features.steps.serenity;

import net.thucydides.core.annotations.Step;
import redDigital.automation.pages.ModalLogin;
//import redDigital.automation.util.DataBaseValidations;

import java.sql.SQLException;

public class StepLogin {

    ModalLogin step;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LOGIN FROM HOME<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Step
    public void getUrl() {
        step.getUrl();
    }
    @Step
    public void loginRedDigital() throws InterruptedException, SQLException {
        step.loginRedDigital();
    }

    public void concexionBD() throws InterruptedException, SQLException {
        step.conexionbd();
    }


    }