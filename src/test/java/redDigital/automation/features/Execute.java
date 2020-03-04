package redDigital.automation.features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "json:target/sire/serenity/cucumber.json" }, features = {
        "src//test//resources//features" }
        ,tags ={"@login"}

        )

public class Execute {

}
