package api.CucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(tags = "@login or @user",
		features="src/test/resources/features",glue= {"api.stepdefinitions"})
public class TestRunner {

}
