package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BasePage;
import school.redrover.page.view.config.ViewMyConfigPage;

import static java.lang.Thread.sleep;

public class GlobalViewPage extends BasePage {

    @FindBy(xpath = "//h1[@class='job-index-headline page-headline']")
    private WebElement jobName;

    @FindBy(name = "description")
    private WebElement descriptionInput;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement saveButton;

    @FindBy(id = "description-content")
    private WebElement descriptionMessage;

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(className = "textarea-show-preview")
    private WebElement previewButton;

    @FindBy(className = "textarea-preview")
    private WebElement previewText;

    @FindBy(className = "textarea-hide-preview")
    private WebElement hidePreview;

    @FindBy(xpath = "//div[@class='tab active']/a")
    private WebElement currentViewName;

    public GlobalViewPage(WebDriver driver) {
        super(driver);
    }

    public String getJobTitle() {
        return getWait5().until(ExpectedConditions.visibilityOf(jobName)).getText();
    }

    public GlobalViewPage inputDescription(String textInput) {
        getWait5().until(ExpectedConditions.visibilityOf(descriptionInput));
        descriptionInput.sendKeys(textInput);

        return this;
    }

    public GlobalViewPage clearDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(descriptionInput)).clear();

        return this;
    }

    public HomePage clickSave() {
        getWait10().until(ExpectedConditions.visibilityOf(saveButton)).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(addDescriptionButton));

        return new HomePage(getDriver());
    }

    public HomePage cancelButton() {
        cancelButton.click();

        getWait5().until(ExpectedConditions.visibilityOf(descriptionMessage));

        return new HomePage(getDriver());
    }

    public GlobalViewPage clickPreviewButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(previewButton)).click();

        return this;
    }

    public String getPreviewText() {
        return getWait5().until(ExpectedConditions.visibilityOf(previewText)).getText();
    }

    public GlobalViewPage clickHideButton() {
        getWait5().until(ExpectedConditions.visibilityOf(hidePreview)).click();
        return this;
    }

    public boolean isPreviewDisplayed() {
        return previewText.isDisplayed();
    }

    public boolean isHidePreviewButtonDisplayed() {
        return getWait5().until(ExpectedConditions.visibilityOf(hidePreview)).isDisplayed();
    }

    public ViewMyConfigPage clickEditView() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("Edit View"))).click();
        return new ViewMyConfigPage(getDriver());
    }

    public String getCurrentViewName(){
        return getWait5().until(ExpectedConditions.visibilityOf(currentViewName)).getText();
    }
}
