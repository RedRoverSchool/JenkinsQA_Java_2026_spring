package school.redrover.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class RenameProjectPage extends BasePage {

    private static final By NEW_NAME_INPUT = By.xpath("//input[@name='newName']");
    private static final By SUBMIT_BUTTON = By.name("Submit");

    public RenameProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Set new name for the project")
    public RenameProjectPage setNewProjectName(String projectName){
        WebElement nameField = getDriver().findElement(NEW_NAME_INPUT);
        nameField.clear();
        nameField.sendKeys(projectName);

        return this;
    }

    public RenameProjectPage clickRenameButton(){
        getDriver().findElement(SUBMIT_BUTTON).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link")));

        return this;
    }

    public Boolean getUpdatedProjectName(String projectName) {
        String getProjectName = getDriver().findElement(
                By.xpath("//*[@id='main-panel']//h1")).getText();

        return getProjectName.equals(projectName);
    }
}
