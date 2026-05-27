package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.project.PipelineProjectPage;

public class PipelineProjectConfigPage extends BaseConfigPage<PipelineProjectConfigPage> {

    @FindBy(id = "toggle-switch-enable-disable-project")
    private WebElement toggleSwitch;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(id = "notification-bar")
    private WebElement notification;

    public PipelineProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PipelineProjectConfigPage self() {
        return this;
    }

    public PipelineProjectConfigPage toggleProjectState() {
        getWait2().until(ExpectedConditions.elementToBeClickable(toggleSwitch)).click();

        return this;
    }

    public PipelineProjectPage clickSaveButton() {
        saveButton.click();

        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div>#main-panel>div>div>h1")));

        return new PipelineProjectPage(getDriver());
    }

    public String getSaveText() {
        return getWait5().until(ExpectedConditions.visibilityOf(notification)).getText();
    }
}
