package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goHomePage() {
        getDriver().findElement(By.id("jenkins-head-icon")).click();
        // waiting for the home page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")));

        return new HomePage(getDriver());
    }

    public ManagePage clickManageButton() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        return new ManagePage(getDriver());
    }
}
