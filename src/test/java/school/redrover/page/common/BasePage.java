package school.redrover.page.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.ManagePage;

public class BasePage extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goHomePage() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();
        // waiting for the home page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")));

        return new HomePage(getDriver());
    }

    public ManagePage clickManageButton() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        return new ManagePage(getDriver());
    }

    public String getHeaderText() {
        return getDriver().findElement(By.xpath("//h1")).getText();
    }
}
