package school.redrover.page.view.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.view.GlobalViewPage;

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

    public ViewMyConfigPage inputName(String newName){
        getWait5().until(ExpectedConditions.elementToBeClickable(elementName)).clear();
        elementName.sendKeys(newName);
        return this;
    }

    public ViewMyConfigPage inputDescription(String newDescription){
        elementDescription.clear();
        elementDescription.sendKeys(newDescription);
        return this;
    }

    public GlobalViewPage clickSave(){
        buttonSave.click();
        return new GlobalViewPage(getDriver());
    }

    public ViewMyConfigPage clickApply(){
        buttonApply.click();
        return this;
    }
}
