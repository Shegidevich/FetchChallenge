package fetch.challenge.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    monochrome = true,
    plugin = {
        "html:target/logs/cuke.html",
    },
    features = {"src/test/resources/features"},
    glue = {
        "fetch.challenge.steps",
        "fetch.challenge.hooks",
    })
public class MainRunnerTest extends AbstractTestNGCucumberTests {

}
