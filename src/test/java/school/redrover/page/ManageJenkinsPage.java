package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageJenkinsPage extends BasePage{

    private By userManagementLink = By.xpath("//a[@href='securityRealm/']");
    private By addUserButton = By.xpath("//div[@class='jenkins-app-bar__controls']");

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public UserManagementPage goToUserManagement() {
        getWait2().until(ExpectedConditions.elementToBeClickable(userManagementLink)).click();
        return new UserManagementPage(getDriver());
    }
}
