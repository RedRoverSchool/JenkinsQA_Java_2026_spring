package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.JenkinsUtils;

public class LoginPage extends BasePage{
    private static final By USERNAME_FIELD = By.cssSelector("#j_username");
    private static final By PASSWORD_FIELD = By.cssSelector("#j_password");
    private static final By SIGN_IN_BUTTON = By.xpath("//button[text()='Sign in']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage logout() {
        JenkinsUtils.logout(getDriver());
        return this;
    }

    public LoginPage enterUsername(String username) {
        getDriver().findElement(USERNAME_FIELD).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        getDriver().findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public ErrorLoginPage clickSignIn() {
        getDriver().findElement(SIGN_IN_BUTTON).click();
        return new ErrorLoginPage(getDriver());
    }

    public boolean isUsernameFieldDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD))
                .isDisplayed();
    }

    public boolean isUsernameFieldEnabled() {
        return getDriver().findElement(USERNAME_FIELD).isEnabled();
    }

    public boolean isPasswordFieldDisplayed() {
        return getDriver().findElement(PASSWORD_FIELD).isDisplayed();
    }

    public boolean isPasswordFieldEnabled() {
        return getDriver().findElement(PASSWORD_FIELD).isEnabled();
    }

    public boolean isSignInButtonDisplayed() {
        return getDriver().findElement(SUBMIT_BUTTON).isDisplayed();
    }

    public boolean isSignInButtonEnabled() {
        return getDriver().findElement(SUBMIT_BUTTON).isEnabled();
    }

    public LoginPage clearUsername() {
        getDriver().findElement(USERNAME_FIELD).clear();
        return this;
    }

    public LoginPage clearPassword() {
        getDriver().findElement(PASSWORD_FIELD).clear();
        return this;
    }

    public String getUsernameValue() {
        return getDriver().findElement(USERNAME_FIELD).getAttribute("value");
    }

    public String getPasswordValue() {
        return getDriver().findElement(PASSWORD_FIELD).getAttribute("value");
    }


    public HomePage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
        return new HomePage(getDriver());
    }
}
