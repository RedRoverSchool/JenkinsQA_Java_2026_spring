package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public abstract class BaseViewConfigPage<T extends BaseViewConfigPage<T>> extends BasePage {

    @FindBy(name = "name")
    private WebElement elementName;

    @FindBy(name = "_.description")
    private WebElement elementDescription;

    @FindBy(name = "_.filterQueue")
    private WebElement chekboxQueue;

    @FindBy(name = "_.filterExecutions")
    private WebElement chekboxExecutions;

    @FindBy(name = "Submit")
    private WebElement buttonSave;

    @FindBy(name = "Apply")
    private WebElement buttonApply;

    public BaseViewConfigPage(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public T inputName(String newName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(elementName)).clear();
        elementName.sendKeys(newName);

        return self();
    }

    public T inputDescription(String newDescription) {
        elementDescription.clear();
        elementDescription.sendKeys(newDescription);

        return self();
    }

    public T clickFilterQueue() {
        chekboxQueue.click();
        return self();
    }

    public T clickFilterExecutions() {
        chekboxExecutions.click();
        return self();
    }

    protected void clickButtonSave() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
    }

    protected void clickButtonApply() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonApply)).click();
    }
}
