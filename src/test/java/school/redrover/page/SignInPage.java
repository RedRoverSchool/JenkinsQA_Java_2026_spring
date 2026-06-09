package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage isSignInFormVisible() {

        getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.className("app-sign-in-register__content-inner")));

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.className("app-sign-in-register__content-inner")));

        return this;
    }

    public SignInPage enterLogin(String userLogin) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("j_username"))).sendKeys(userLogin);

        return this;
    }

    public SignInPage enterPassword(String userPassword) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("j_password"))).sendKeys(userPassword);

        return this;
    }

    public HomePage clickSignInButton() {
        getDriver().findElement(By.name("Submit")).click();

        //getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.className("empty-state-block")));

        return new HomePage(getDriver());
    }
}
