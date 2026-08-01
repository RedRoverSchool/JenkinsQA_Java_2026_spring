package school.redrover.page.view.common.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.view.base.BaseGeneralViewConfigPage;
import school.redrover.page.view.config.BaseViewConfigPage;
import school.redrover.page.view.config.GeneralMyViewConfigPage;

public class GeneralViewPage extends BaseViewPage {

    public GeneralViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BaseViewConfigPage<?> clickConfigure() {
        clickConfigureButton();
        return new GeneralMyViewConfigPage(getDriver());
    }

    @Step("Input description: '{textInput}'")
    public GeneralViewPage inputDescription(String textInput) {
        getWait5().until(ExpectedConditions.visibilityOf(fieldDescription));
        fieldDescription.sendKeys(textInput);

        return this;
    }

    @Step("Clear description field")
    public GeneralViewPage clearDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(fieldDescription)).clear();

        return this;
    }

    @Step("Click 'Save' button")
    public HomePage clickSave() {
        getWait10().until(ExpectedConditions.visibilityOf(buttonSave)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(editDescription));

        return new HomePage(getDriver());
    }

    @Step("Click 'Cancel' button")
    public HomePage cancelButton() {
        buttonCancel.click();
        getWait5().until(ExpectedConditions.visibilityOf(descriptionMessage));

        return new HomePage(getDriver());
    }

    @Step("Click 'Preview' button")
    public GeneralViewPage clickPreviewButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(previewButton)).click();

        return this;
    }

    @Step("Click 'Hide' preview button")
    public GeneralViewPage clickHideButton() {
        getWait5().until(ExpectedConditions.visibilityOf(hidePreview)).click();
        return this;
    }

    @Step("Check if preview is displayed")
    public boolean isPreviewDisplayed() {
        return areaPreview.isDisplayed();
    }

    @Step("Check if 'Hide Preview' button is displayed")
    public boolean isHidePreviewButtonDisplayed() {
        return getWait5().until(ExpectedConditions.visibilityOf(hidePreview)).isDisplayed();
    }

    @Step("Click 'Edit View' link in the side menu")
    @SuppressWarnings("unchecked")
    public <T extends BaseGeneralViewConfigPage<T>> T clickEditView(Class<T> pageClass) {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("Edit View"))).click();
        try {
            return pageClass.getConstructor(WebDriver.class).newInstance(getDriver());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
