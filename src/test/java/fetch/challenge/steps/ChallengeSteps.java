package fetch.challenge.steps;

import static fetch.challenge.tools.Driver.getDriver;
import static fetch.challenge.tools.Driver.getWait;

import fetch.challenge.bar.Bar;
import fetch.challenge.bar.BarCollection;
import fetch.challenge.bar.BarCollection.OneThirdNumber;
import fetch.challenge.pages.BowlComponent;
import fetch.challenge.pages.ChallengePage;
import fetch.challenge.pages.ChallengePage.BowlType;
import fetch.challenge.tools.ChallengeProperties;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class ChallengeSteps {

  private final int BARS_COUNT = 9;

  private ChallengePage page = new ChallengePage();
  private BarCollection bars = new BarCollection(BARS_COUNT);

  @Given("Challenge page is open")
  public void challengePageIsOpen() {
    String url = ChallengeProperties.getUrl();
    log.info("Open challenge page: {}", url);
    getDriver().get(url);
  }

  @When("User clicks on Weigh button")
  public void userClicksOnWeighButton() {
    log.info("Click Weigh button");
    page.weighBtn.click();
  }

  @When("User clicks on Reset button")
  public void userClicksOnResetButton() {
    log.info("Click Reset button");
    page.resetBtn.click();

    log.info("Wait until reset done");
    getWait().until(ExpectedConditions.textToBePresentInElement(page.resultBtn, "?"));
  }

  public void addBarToBorwl(int barIndex, BowlType bowlType) {
    BowlComponent bowlBlock = page.getBowlComponent(bowlType);
    List<WebElement> cells = bowlBlock.cellInputs;
    WebElement cell = cells.stream()
        .filter(c -> c.getAttribute("value").isEmpty())
        .findFirst().orElseThrow();
    cell.sendKeys(Integer.toString(barIndex));
  }

  @When("User enters indexes of the {convertOneThirdNumber} one third of all bars to the {convertBowlBlockType} bowl")
  public void userEntersIndexesOfTheFirstOneThirdOfAllBarsToTheLeftBowl(
      OneThirdNumber oneThirdNumber, BowlType bowlType) {
    log.info("Enter indexes to the {} bowl:", bowlType);
    List<Bar> oneThirdOfBars = bars.getOneThirdOfBars(oneThirdNumber);
    log.info("{}", oneThirdOfBars);
    oneThirdOfBars.forEach(b -> addBarToBorwl(b.getIndex(), bowlType));
  }

  @ParameterType("left|right")
  public BowlType convertBowlBlockType(String bowlType) {
    return BowlType.valueOf(bowlType.toUpperCase());
  }

  @ParameterType("first|second")
  public OneThirdNumber convertOneThirdNumber(String oneThirdNumber) {
    return OneThirdNumber.valueOf(oneThirdNumber.toUpperCase());
  }

  @When("User makes decision on what group contains fake bar and removes genuine bars from the bar collection")
  public void userMakesDecisionWhatGroupContainsFakeBarAndRemovesGenuineBarsFromConsideration() {
    log.info("Wait until the result is ready");
    getWait().until(
        ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(page.resultBtn, "?")));

    String result = page.resultBtn.getText().trim();
    log.info("Weighing result: {}", result);

    switch (result) {
      case "<" -> bars.keepOneThirdOfBars(OneThirdNumber.FIRST);
      case ">" -> bars.keepOneThirdOfBars(OneThirdNumber.SECOND);
      case "=" -> bars.keepOneThirdOfBars(OneThirdNumber.THIRD);
      default -> throw new IllegalStateException("Unknown result");
    }
  }

  @Then("User clicks on the fake bar button")
  public void userClicksOnTheFakeBarButton() {
    log.info("Click on the fake bar button: {}", bars.getBarIndex(0));
    page.barBtns.get(bars.getBarIndex(0)).click();
  }

  @Then("Get the result")
  public void getTheResult() {
    Alert alert = getWait().until(ExpectedConditions.alertIsPresent());
    String result = alert.getText();
    alert.accept();
    log.info("Result: {}", result);
    log.info("FAKE BAR INDEX: {}", bars.getBarIndex(0));
  }

  @When("Print list of weighings")
  public void printListOfWeighings() {
    log.info("Weighings:");
    page.weighings.forEach(w -> log.info(w.getText()));
  }

}
