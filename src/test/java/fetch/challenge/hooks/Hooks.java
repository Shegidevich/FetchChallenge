package fetch.challenge.hooks;

import fetch.challenge.tools.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

  @After()
  public void afterScenario(Scenario scenario) {
    Driver.quit();
  }


}
