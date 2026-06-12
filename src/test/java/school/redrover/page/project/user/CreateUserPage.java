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
    private final By addUserButton = By.xpath("//div[@class='jenkins-app-bar__controls']");

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

    public UserManagementPage clickCreateUserButton() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(addUserButton));

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
            String confirmUserPassword,
            String userFullName,
            String userEmail
    ) {
        setUsername(userName);
        setUserPassword(userPassword);
        setConfirmUserPassword(confirmUserPassword);
        setUserFullName(userFullName);
        setUserEmail(userEmail);

        return clickCreateUserButton();
    }
}
