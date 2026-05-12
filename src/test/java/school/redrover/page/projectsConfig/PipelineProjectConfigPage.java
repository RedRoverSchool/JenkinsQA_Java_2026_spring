package school.redrover.page.projectsConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.projects.PipelineProjectPage;

public class PipelineProjectConfigPage extends BaseConfigPage<PipelineProjectConfigPage> {

    @FindBy(id = "toggle-switch-enable-disable-project")
    private WebElement toggleSwitch;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionInput;

    @FindBy(name = "Apply")
    private WebElement applyButton;

    @FindBy(id = "notification-bar")
    private WebElement notification;

    public PipelineProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectConfigPage toggleProjectState() {
        getWait2().until(ExpectedConditions.elementToBeClickable(toggleSwitch)).click();

        return this;
    }

    public PipelineProjectPage clickSaveButton() {
        saveButton.click();

        return new PipelineProjectPage(getDriver());
    }

    public PipelineProjectConfigPage enterDescription(String description) {
        descriptionInput.sendKeys(description);

        return this;
    }

    public PipelineProjectConfigPage clickApplyButton() {
        applyButton.click();

        return this;
    }

    public String getSaveText() {
        return getWait5().until(ExpectedConditions.visibilityOf(notification)).getText();
    }
}
