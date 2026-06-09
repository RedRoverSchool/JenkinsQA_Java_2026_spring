package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.manage.ManagePage;
import school.redrover.page.manage.UserManagementPage;

public class CreateUserPage extends BasePage {

    private By usernameField = By.name("username");
    private By password1 = By.name("password1");
    private By password2 = By.name("password2");
    private By fullnameField = By.name("fullname");
    private By emailField = By.name("email");
    private By submitButton = By.name("Submit");
    private By addUserButton = By.xpath("//div[@class='jenkins-app-bar__controls']");

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUsername(String userName) {
        getDriver().findElement(usernameField).sendKeys(userName);
        return this;
    }

    public CreateUserPage setUserPassword(String userPassword) {
        getDriver().findElement(password1).sendKeys(userPassword);
        return this;
    }

    public CreateUserPage setConfirmUserPassword(String userPassword) {
        getDriver().findElement(password2).sendKeys(userPassword);
        return this;
    }

    public CreateUserPage setUserFullName(String userFullName) {
        getDriver().findElement(fullnameField).sendKeys(userFullName);
        return this;
    }

    public CreateUserPage setUserEmail(String userEmail) {
        getDriver().findElement(emailField).sendKeys(userEmail);
        return this;
    }

    public UserManagementPage createUser(
            String userName,
            String userPassword,
            String userFullName,
            String userEmail
    ) {
        setUsername(userName);
        setUserPassword(userPassword);
        setConfirmUserPassword(userPassword);
        setUserFullName(userFullName);
        setUserEmail(userEmail);

        return clickCreateUserButton();
    }

    public UserManagementPage clickCreateUserButton() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(addUserButton));

        return new UserManagementPage(getDriver());
    }
}
