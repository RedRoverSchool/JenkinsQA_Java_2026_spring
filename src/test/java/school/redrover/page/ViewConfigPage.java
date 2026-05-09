package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;

public class ViewConfigPage extends BasePage {

    @FindBy(id = "name")
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

    public ViewConfigPage(WebDriver driver) {
        super(driver);
    }

    public ViewConfigPage inputName(String newName){
        elementName.clear();
        elementName.sendKeys(newName);
        return this;
    }

    public ViewConfigPage inputDescription(String newDescription){
        elementDescription.clear();
        elementDescription.sendKeys(newDescription);
        return this;
    }

    public ViewConfigPage clickSave(){
        buttonSave.click();
        return this;
    }

    public ViewConfigPage clickApply(){
        buttonApply.click();
        return this;
    }
}
