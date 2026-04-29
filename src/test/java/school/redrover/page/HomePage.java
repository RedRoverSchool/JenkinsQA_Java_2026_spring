package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ManageJenkins clickSetting(){

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("root-action-ManageJenkinsAction"))).click();

        return new ManageJenkins(getDriver());

    }
}
