package school.redrover.page.view.base;

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
    public T inputName(String newName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(elementName)).clear();
        elementName.sendKeys(newName);

        return (T) this;
    }

    public String getViewName() {
        return getWait5()
                .until(ExpectedConditions.visibilityOf(elementName))
                .getAttribute("value");
    }

    @SuppressWarnings("unchecked")
    public T inputDescription(String newDescription) {
        elementDescription.clear();
        elementDescription.sendKeys(newDescription);

        return (T) this;
    }
}
