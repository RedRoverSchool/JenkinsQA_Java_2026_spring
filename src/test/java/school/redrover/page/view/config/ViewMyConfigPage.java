package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.view.GeneralViewPage;

public class ViewMyConfigPage extends BasePage {

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

    public ViewMyConfigPage(WebDriver driver) {
        super(driver);
    }

    public ViewMyConfigPage inputName(String newName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(elementName)).clear();
        elementName.sendKeys(newName);

        return this;
    }

    public String getViewName() {
        return getWait5()
                .until(ExpectedConditions.visibilityOf(elementName))
                .getAttribute("value");
    }

    public ViewMyConfigPage inputDescription(String newDescription) {
        elementDescription.clear();
        elementDescription.sendKeys(newDescription);

        return this;
    }

    public GeneralViewPage clickSave() {
        buttonSave.click();
        return new GeneralViewPage(getDriver());
    }

    public ViewMyConfigPage clickApply() {
        buttonApply.click();
        return this;
    }
}
