package school.redrover.page.view;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.components.ViewSideMenuComponent;

public class FolderViewPage extends BasePage {

    @FindBy(id = "description-link")
    private WebElement buttonDescription;

    @FindBy(name = "description")
    private WebElement fieldDescription;

    @FindBy(xpath = "//*[@id='description-edit-form']//a[normalize-space()='Preview']")
    private WebElement linkPreview;

    @FindBy(css = "#description-edit-form .textarea-preview")
    private WebElement areaPreview;

    @FindBy(id = "description-content")
    private WebElement content;

    @FindBy(name = "Submit")
    private WebElement buttonSave;

    @FindBy(id = "description-link")
    private WebElement editDescription;

    @FindBy(css = "#description-edit-form .description-cancel-button")
    private WebElement buttonCancel;

 //   private final ViewSideMenuComponent<FolderViewPage> viewSideMenu;

    public FolderViewPage(WebDriver driver) {
        super(driver);
       // this.viewSideMenu = new ViewSideMenuComponent<>(getDriver(), this);
    }

    public ViewSideMenuComponent<FolderViewPage> getSideMenu() {
        return new ViewSideMenuComponent<>(getDriver(), this);
    }

    public FolderViewPage addDescription(String expectedDescription) {
        buttonDescription.click();
        getWait5().until(ExpectedConditions.visibilityOf(fieldDescription)).sendKeys(expectedDescription);

        return this;
    }

    public String getTextPreview() {
        linkPreview.click();
        return getWait10().until(ExpectedConditions.visibilityOf(areaPreview)).getText();
    }

    public FolderViewPage clickSubmitButton() {
        buttonSave.click();
        return this;
    }

    public String getDescriptionText() {
        return getWait10().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(content))).getText();
    }

    public FolderViewPage editDescription(String newDescriptionText) {
        editDescription.click();

        WebElement descriptionField = getWait5().until(ExpectedConditions.visibilityOf(fieldDescription));
        descriptionField.clear();
        descriptionField.sendKeys(newDescriptionText);

        return this;
    }

    public FolderViewPage clickCancelButton() {
        buttonCancel.click();
        return this;
    }
}
