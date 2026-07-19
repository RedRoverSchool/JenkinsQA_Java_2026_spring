package school.redrover.page.view.create;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;

public abstract class CreateViewBasePage<T extends CreateViewBasePage<T>> extends BasePage {

    @FindBy(id = "name")
    protected WebElement elementName;

    @FindBy(xpath = "//label[@for='hudson.model.ListView']")
    protected WebElement ratioListView;

    @FindBy(xpath = "//label[@for='hudson.model.MyView']")
    protected WebElement ratioMyView;

    @FindBy(id = "ok")
    protected WebElement buttonCreate;

    public CreateViewBasePage(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("unchecked")
    public T inputName(String name) {
        elementName.sendKeys(name);
        return (T) this;
    }
}
