package school.redrover.page.external;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BaseModel;

public class GetInvolvedPage extends BaseModel {

    public GetInvolvedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement titleText;

    public String getTitleText() {
        return titleText.getText();
    }
}