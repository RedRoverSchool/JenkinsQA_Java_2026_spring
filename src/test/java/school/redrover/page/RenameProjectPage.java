package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RenameProjectPage extends BasePage {

    public RenameProjectPage(WebDriver driver) {
        super(driver);
    }

    public RenameProjectPage setNewProjectName(String projectName){
        WebElement nameField = getDriver().findElement(By.xpath("//input[@name='newName']"));
        nameField.clear();
        nameField.sendKeys(projectName);
        return this;
    }

    public RenameProjectPage submitNewProjectName(){
        getDriver().findElement(By.name("Submit")).click();
        return this;
    }

    @Override
    public HomePage goHomePage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.className("app-jenkins-logo")));
        getDriver().findElement(By.className("app-jenkins-logo")).click();
        return new HomePage(getDriver());
    }
}
