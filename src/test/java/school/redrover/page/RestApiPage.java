package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;

public class RestApiPage extends BasePage {
    @FindBy(xpath = "//footer//a[contains(text(),'REST API')]")
    private WebElement restApiLinkInFooter;

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRestApiLinkDisplayedInFooter() {
        try {
            return restApiLinkInFooter.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
