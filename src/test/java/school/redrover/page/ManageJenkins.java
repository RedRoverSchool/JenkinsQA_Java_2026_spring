package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageJenkins extends BasePage{

    public ManageJenkins(WebDriver driver) {
        super(driver);
    }

    public UserDatabase clickUsersButton(){

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='securityRealm/']"))).click();

        return new UserDatabase(getDriver());
    }
}
