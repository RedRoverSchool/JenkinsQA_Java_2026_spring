package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.RenameProjectPage;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

public class FreestyleProjectPage extends BaseProjectPage {

    @FindBy(id = "description-content")
    private WebElement descriptionText;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectConfigPage clickConfigure() {

        By configuration =  By.xpath("//a[contains(@href, '/configure')]");

        getWait10().until(webDriver -> {
            try {
                WebElement element = getDriver().findElement(configuration);

                if(element.isDisplayed() && element.isEnabled()) {
                    element.click();

                    return true;
                }

                return false;

            } catch (Exception e) {
                return false;
            }
        });

        return new FreestyleProjectConfigPage(getDriver());
    }

    public String getDescription() {
        return getWait5().until(ExpectedConditions.visibilityOf(descriptionText)).getText();
    }

    public Boolean getProjectIsDisabledMessage() {
        String projectIsDisabledMessage = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("enable-project"))).getText();

        return projectIsDisabledMessage.contains("This project is currently disabled");
    }

    public FreestyleProjectPage enableProject() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(., 'Enable')]"))).click();

        return this;
    }

    public RenameProjectPage clickRenameProjectSideMenuButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Rename']/.."))).click();

        return new RenameProjectPage(getDriver());
    }
}
