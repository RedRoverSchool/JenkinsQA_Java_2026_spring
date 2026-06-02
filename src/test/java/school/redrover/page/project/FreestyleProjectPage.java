package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseJobPage;
import school.redrover.page.components.FreestylePipelineMulticonfigSideMenuComponent;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

import java.util.List;

public class FreestyleProjectPage extends BaseJobPage<FreestyleProjectPage> {

    @FindBy(id = "description-content")
    private WebElement descriptionText;

    private final FreestylePipelineMulticonfigSideMenuComponent<FreestyleProjectPage> fullSideMenu;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
        this.fullSideMenu = new FreestylePipelineMulticonfigSideMenuComponent<>(driver, this);
    }

    @Override
    public FreestylePipelineMulticonfigSideMenuComponent<FreestyleProjectPage> getSideMenu() {
        return this.fullSideMenu;
    }

    public FreestyleProjectConfigPage clickConfigure() {

        By configuration = By.xpath("//a[contains(@href, '/configure')]");

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

    public FreestyleProjectPage enableProject() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(., 'Enable')]"))).click();

        return this;
    }

    public List<String> getBuilds() {
        By builds = By.className("app-builds-container__item");

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(builds));

        return getDriver().findElements(builds).stream()
                .map(WebElement::getText)
                .toList();
    }

    public Boolean isPopupMessageDisplayed(String popUpMessage) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Build Now']/.."))).click();

        return getWait10().until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("notification-bar"), popUpMessage));
    }
}
