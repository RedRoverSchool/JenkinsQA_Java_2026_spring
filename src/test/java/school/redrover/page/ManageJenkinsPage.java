package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageJenkinsPage extends BasePage{

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public UserDatabasePage clickUsersButton(){

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='securityRealm/']"))).click();

        return new UserDatabasePage(getDriver());
    }
}
