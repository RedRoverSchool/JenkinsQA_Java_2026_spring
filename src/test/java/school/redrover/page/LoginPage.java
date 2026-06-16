package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.JenkinsUtils;
import school.redrover.page.common.BasePage;

public class LoginPage extends BasePage {

    @FindBy(css = "#j_username")
    private WebElement usernameField;

    @FindBy(css = "#j_password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage logout() {
        JenkinsUtils.logout(getDriver());
        return this;
    }

    public LoginPage enterUsername(String username) {
        getWait5().until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        getWait5().until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        return this;
    }

    public ErrorLoginPage clickSignIn() {
        getWait5().until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        return new ErrorLoginPage(getDriver());
    }

    public boolean isUsernameFieldDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOf(usernameField)).isDisplayed();
    }

    public boolean isUsernameFieldEnabled() {
        return usernameField.isEnabled();
    }

    public boolean isPasswordFieldDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOf(passwordField)).isDisplayed();
    }

    public boolean isPasswordFieldEnabled() {
        return passwordField.isEnabled();
    }

    public boolean isSignInButtonDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOf(submitButton)).isDisplayed();
    }

    public boolean isSignInButtonEnabled() {
        return submitButton.isEnabled();
    }

    public String getSignInButtonText() {
        return getWait5().until(ExpectedConditions.visibilityOf(submitButton)).getText();
    }

    public String getUsernameValue() {
        return getWait10().until(ExpectedConditions.visibilityOf(usernameField)).getAttribute("value");
    }

    public String getPasswordValue() {
        return getWait10().until(ExpectedConditions.visibilityOf(passwordField)).getAttribute("value");
    }

    public boolean isUsernameFieldEmpty() {
        return getUsernameValue().isEmpty();
    }

    public boolean isPasswordFieldEmpty() {
        return getPasswordValue().isEmpty();
    }
}
