package school.redrover.page.external;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BaseModel;

public class JenkinsWebsite extends BaseModel {

    public JenkinsWebsite(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1>span")
    public WebElement titleText;

    public String getTitleText() {
        return titleText.getText();
    }
}