package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseConfigPage;

public class CreateUserPage extends BaseConfigPage {

    private By usernameField = By.name("username");
    private By password1 = By.name("password1");
    private By password2 = By.name("password2");
    private By fullnameField = By.name("fullname");
    private By emailField = By.name("email");
    private By submitButton = By.name("Submit");

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUsername(String username) {
        getDriver().findElement(usernameField).sendKeys(username);
        return this;
    }

    public CreateUserPage setPassword(String password) {
        getDriver().findElement(password1).sendKeys(password);
        return this;
    }

    public CreateUserPage setConfirmPassword(String password) {
        getDriver().findElement(password2).sendKeys(password);
        return this;
    }

    public CreateUserPage setFullName(String fullName) {
        getDriver().findElement(fullnameField).sendKeys(fullName);
        return this;
    }

    public CreateUserPage setEmail(String email) {
        getDriver().findElement(emailField).sendKeys(email);
        return this;
    }

    public UserManagementPage clickCreateUserButton() {
        getDriver().findElement(submitButton).click();
        return new UserManagementPage(getDriver());
    }


}
