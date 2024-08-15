package fetch.challenge.tools;

import io.cucumber.datatable.internal.difflib.StringUtills;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.exec.util.StringUtils;

public class ChallengeProperties {

  public enum Browser {
    CHROME, FIREFOX, EDGE
  }

  @Getter
  private static String url;

  @Getter
  private static Browser browser;

  private ChallengeProperties() {
  }

  static {
    init();
  }

  @SneakyThrows
  private static void init() {
    Properties properties = new Properties();
    InputStream propStream = ChallengeProperties.class.getClassLoader()
        .getResourceAsStream("challenge.properties");
    properties.load(propStream);

    url = properties.getProperty("challenge.url");

    String browserProp = Objects.toString(properties.getProperty("browser"),"").toUpperCase();
    browser = browserProp.isEmpty() ? Browser.CHROME : Browser.valueOf(browserProp);
  }

}
