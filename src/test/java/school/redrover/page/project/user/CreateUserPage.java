package school.redrover.page.project.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.manage.UserManagementPage;

import java.util.List;

public class CreateUserPage extends BasePage {

    private final By usernameField = By.name("username");
    private final By password1 = By.name("password1");
    private final By password2 = By.name("password2");
    private final By fullnameField = By.name("fullname");
    private final By emailField = By.name("email");
    private final By submitButton = By.name("Submit");
    private final By errorMessages = By.xpath("//div[@class = 'error jenkins-!-margin-bottom-2']");

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage setUsername(String username) {
        getDriver().findElement(usernameField).sendKeys(username);
        return this;
    }

    public CreateUserPage setUserPassword(String password) {
        getDriver().findElement(password1).sendKeys(password);
        return this;
    }

    public CreateUserPage setConfirmUserPassword(String password) {
        getDriver().findElement(password2).sendKeys(password);
        return this;
    }

    public CreateUserPage setUserFullName(String fullName) {
        getDriver().findElement(fullnameField).sendKeys(fullName);
        return this;
    }

    public CreateUserPage setUserEmail(String email) {
        getDriver().findElement(emailField).sendKeys(email);
        return this;
    }

    public UserManagementPage clickCreateUserButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return new UserManagementPage(getDriver());
    }

    public CreateUserPage submitExpectingError() {
        getWait10().until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    public List<String> getErrorMessageList() {
        return getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMessages))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public UserManagementPage createUser(
            String userName,
            String userPassword,
            String userConfirmPassword,
            String userFullName,
            String userEmail
    ) {
        setUsername(userName);
        setUserPassword(userPassword);
        setConfirmUserPassword(userConfirmPassword);
        setUserFullName(userFullName);
        setUserEmail(userEmail);

        return clickCreateUserButton();
    }
}
