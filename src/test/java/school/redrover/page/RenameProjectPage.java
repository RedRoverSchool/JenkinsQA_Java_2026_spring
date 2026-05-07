package school.redrover.page;

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

    public RenameProjectPage setNewProjectName(String projectName){
        WebElement nameField = getDriver().findElement(NEW_NAME_INPUT);
        nameField.clear();
        nameField.sendKeys(projectName);
        return this;
    }

    public RenameProjectPage submitNewProjectName(){
        getDriver().findElement(SUBMIT_BUTTON).click();
        return this;
    }

    @Override
    public HomePage goHomePage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.className("app-jenkins-logo")));
        getDriver().findElement(By.className("app-jenkins-logo")).click();
        return new HomePage(getDriver());
    }
}
