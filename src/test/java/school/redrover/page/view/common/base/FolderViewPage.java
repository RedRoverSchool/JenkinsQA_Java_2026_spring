package school.redrover.page.view.common.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.components.ViewSideMenuComponent;
import school.redrover.page.project.config.FolderConfigPage;
import school.redrover.page.view.config.BaseViewConfigPage;

public class FolderViewPage extends BaseViewPage {

    public FolderViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseViewConfigPage<?> clickConfigure() {
        return null;
    }


    public ViewSideMenuComponent<FolderViewPage> getSideMenu() {
        return new ViewSideMenuComponent<>(getDriver(), this);
    }

    public FolderViewPage addDescription(String expectedDescription) {
        editDescription.click();
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
        return getWait10().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(descriptionMessage))).getText();
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
