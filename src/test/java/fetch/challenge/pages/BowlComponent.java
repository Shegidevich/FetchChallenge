package fetch.challenge.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BowlComponent {

  public BowlComponent(WebElement root) {
    PageFactory. initElements(root,this);
  }

  @FindBy(tagName = "input")
  public List<WebElement> cellInputs;

}
