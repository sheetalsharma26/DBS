package dbs.runner;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "features",plugin = {"pretty", "html:target/cucumber-html-report","json:target/cucumber.json"},
glue = {"dbs.stepDefination"})
public class TestRunner {
//
}
