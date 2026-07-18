package school.redrover.page.view.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public abstract class BaseViewPage extends BasePage {
    public BaseViewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='job-index-headline page-headline']")
    protected WebElement jobName;

    @FindBy(name = "description")
    protected WebElement fieldDescription;

    @FindBy(xpath = "//button[@name='Submit']")
    protected WebElement buttonSave;

    @FindBy(id = "description-link")
    protected WebElement editDescription;

    @FindBy(xpath = "//div[@class='tab active']/a")
    protected WebElement currentViewName;

    @FindBy(id = "description-content")
    protected WebElement descriptionMessage;

    @FindBy(css = "#description-edit-form .description-cancel-button")
    protected WebElement buttonCancel;

    @FindBy(css = "#description-edit-form .textarea-preview")
    protected WebElement areaPreview;

    @FindBy(xpath = "//*[@id='description-edit-form']//a[normalize-space()='Preview']")
    protected WebElement linkPreview;

    @FindBy(className = "textarea-show-preview")
    protected WebElement previewButton;

    @FindBy(className = "textarea-hide-preview")
    protected WebElement hidePreview;

    public String getCurrentViewName() {
        return getWait5().until(ExpectedConditions.visibilityOf(currentViewName)).getText();
    }
}
