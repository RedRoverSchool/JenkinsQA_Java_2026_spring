package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserManagementPage extends BasePage{

    private By addUserButton = By.xpath("//div[@class='jenkins-app-bar__controls']");

    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickAddUser() {
        getDriver().findElement(addUserButton).click();
        return new CreateUserPage(getDriver());
    }
}
