package fetch.challenge.pages;

import static fetch.challenge.tools.Driver.getDriver;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChallengePage {

  public ChallengePage() {
    PageFactory.initElements(getDriver(), this);
  }

  public enum BowlType {
    LEFT, RIGHT
  }

  @FindBy(xpath = ".//div[@class='game-board'][1]")
  public WebElement leftBowl;

  @FindBy(xpath = ".//div[@class='game-board'][2]")
  public WebElement rightBowl;

  @FindBy(id = "weigh")
  public WebElement weighBtn;

  @FindBy(css = "button#reset:not([disabled])")
  public WebElement resetBtn;

  @FindBy(css = ".result button#reset[disabled]")
  public WebElement resultBtn;

  @FindBy(css = ".coins button.square")
  public List<WebElement> barBtns;

  @FindBy(css = "div.game-info li")
  public List<WebElement> weighings;

  public BowlComponent getBowlComponent(BowlType bowlType) {
    WebElement root = bowlType == BowlType.LEFT ? leftBowl : rightBowl;
    return new BowlComponent(root);
  }


}
