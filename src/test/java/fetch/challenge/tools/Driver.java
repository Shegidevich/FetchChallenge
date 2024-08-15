package fetch.challenge.tools;

import fetch.challenge.tools.ChallengeProperties.Browser;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

  private Driver() {
  }

  private static WebDriver driver;
  private static Wait<WebDriver> wait;

  public static WebDriver getDriver() {
    if (driver == null) {
      driver = createDriver();
      wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }
    return driver;
  }

  private static WebDriver createDriver() {
    return switch (ChallengeProperties.getBrowser()) {
      case CHROME ->new ChromeDriver();
      case FIREFOX ->new FirefoxDriver();
      case EDGE ->new EdgeDriver();
      default -> throw new IllegalArgumentException("Unsupported driver");
    };
  }

  public static Wait<WebDriver> getWait() {
    return wait;
  }

  public static void quit() {
    if (driver != null) {
      driver.quit();
      driver = null;
      wait = null;
    }
  }
}
