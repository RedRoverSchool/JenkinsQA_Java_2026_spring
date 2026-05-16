package school.redrover.page.project;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.project.config.PipelineProjectConfigPage;
import school.redrover.page.PipelineProjectRenamePage;

public class PipelineProjectPage extends BaseProjectPage {

    @FindBy(id = "description-link")
    private WebElement descriptionLinkElement;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionElement;

    @FindBy(xpath = "//button[@value='Save']")
    private WebElement saveButton;

    @FindBy(id = "description-content")
    private WebElement description;

    @FindBy(xpath = "//a[contains(@href, 'configure')]")
    private WebElement configureSidebar;

    @FindBy(xpath = "//div[@class='warning']")
    private WebElement warning;

    @FindBy(xpath = "(//span[normalize-space()='Build Now'])[1]")
    private WebElement buildNowButtonSidebar;

    @FindBy(xpath = "//a[contains(@href, 'confirm-rename')]")
    private WebElement renameButtonSidebar;

    @FindBy(xpath = "//a[@data-title='Delete Pipeline']")
    private WebElement deleteButtonSidebar;

    @FindBy(xpath = "//button[@data-id='ok']")
    private WebElement confirmButton;

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement addNewItemButton;

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectPage clickAddDescription() {
        descriptionLinkElement.click();
        getWait5().until(ExpectedConditions.visibilityOf(descriptionElement));

        return this;
    }

    public PipelineProjectPage enterDescription(String description) {
        descriptionElement.sendKeys(description);

        return this;
    }

    public PipelineProjectPage clickSaveDescription() {
        saveButton.click();

        return this;
    }

    public String getDescriptionText() {
        return getWait5().until(ExpectedConditions.visibilityOf(description)).getText();
    }

    public PipelineProjectRenamePage clickRenameSidebarButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(renameButtonSidebar)).click();

        return new PipelineProjectRenamePage(getDriver());
    }

    public PipelineProjectConfigPage clickConfigureSidebarButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(configureSidebar)).click();

        return new PipelineProjectConfigPage(getDriver());

    }

    public String getDisabledWarningText() {
        return getWait10().until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(warning))).getText();
    }

    public boolean isBuildNowDisplayed() {
        try {
            getWait5().until(ExpectedConditions.visibilityOf(buildNowButtonSidebar));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public HomePage deletePipelineAndConfirm() {
        deleteButtonSidebar.click();
        getWait2().until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
        getWait5().until(ExpectedConditions.visibilityOf(addNewItemButton));

        return new HomePage(getDriver());
    }
}
