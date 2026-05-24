package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

public class FreestyleProjectPage extends BaseProjectPage {

    @FindBy(id = "description-content")
    private WebElement descriptionText;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectConfigPage clickConfigure() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();

        return new FreestyleProjectConfigPage(getDriver());
    }

    public String getDescription() {
        return descriptionText.getText();
    }
}
