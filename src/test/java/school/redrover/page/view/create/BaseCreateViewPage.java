package school.redrover.page.view.create;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public abstract class BaseCreateViewPage<T extends BaseCreateViewPage<T>> extends BasePage {

    @FindBy(id = "name")
    private WebElement elementName;

    @FindBy(xpath = "//label[@for='hudson.model.ListView']")
    private WebElement radioListView;

    @FindBy(xpath = "//label[@for='hudson.model.MyView']")
    private WebElement radioMyView;

    @FindBy(id = "ok")
    private WebElement buttonCreate;

    public BaseCreateViewPage(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("unchecked")
    protected T self(){
        return (T) this;
    }

    public T inputName(String name){
        elementName.sendKeys(name);
        return self();
    }

    protected void selectListViewAndSubmit() {
        radioListView.click();
        getWait2().until(ExpectedConditions.elementToBeClickable(buttonCreate)).click();
    }

    protected void selectMyViewAndSubmit() {
        radioMyView.click();
        getWait2().until(ExpectedConditions.elementToBeClickable(buttonCreate)).click();
    }

    protected void clickButtonCreate() {
        getWait2().until(ExpectedConditions.elementToBeClickable(buttonCreate)).click();
    }
}
