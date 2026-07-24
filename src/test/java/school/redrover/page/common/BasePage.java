package school.redrover.page.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.components.BreadcrumbComponent;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;
import school.redrover.page.components.FooterVersionComponent;
import school.redrover.page.manage.ManagePage;

import java.util.function.BiFunction;

public class BasePage extends BaseModel {

    private static final By HEADER = By.xpath("//h1");
    private static final By USER_ACTION_BUTTON = By.id("root-action-UserAction");
    private static final By USER_ACTION_DROPDOWN = By.xpath("//div[contains(@class,'jenkins-dropdown')]");
    private static final By SIGN_OUT_LINK = By.xpath(".//a[@href='/logout']");

    private BreadcrumbComponent breadcrumb;
    private FooterVersionComponent version;

    public BasePage(WebDriver driver) {
        super(driver);
        this.breadcrumb = new BreadcrumbComponent(driver);
        this.version = new FooterVersionComponent(driver);
    }

    public BreadcrumbComponent getBreadcrumbs() {
        return this.breadcrumb;
    }

    public FooterVersionComponent getFooterVersion() {
        return this.version;
    }

    @Step("Go Home page")
    public HomePage goHomePage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();
        // waiting for the home page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")));

        return new HomePage(getDriver());
    }

    public ManagePage clickManageButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'manage')]"))).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(HEADER, "Manage Jenkins"));

        return new ManagePage(getDriver());
    }

    @Step("Open user action menu")
    public BasePage openUserActionMenu() {
        WebElement userButton = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(USER_ACTION_BUTTON));

        new Actions(getDriver())
                .moveToElement(userButton)
                .perform();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(USER_ACTION_DROPDOWN));

        return this;
    }

    @Step("Click Sign Out")
    public LoginPage clickSignOut() {
        WebElement dropdownMenu = getWait5().until(
                ExpectedConditions.visibilityOfElementLocated(USER_ACTION_DROPDOWN));

        dropdownMenu.findElement(SIGN_OUT_LINK).click();

        return new LoginPage(getDriver());
    }

    @Step("Sign out from Jenkins")
    public LoginPage openUserActionMenuAndLogout() {
        return openUserActionMenu()
                .clickSignOut();
    }

    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String getHeaderText() {
        return getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(HEADER))
                .getText();
    }

    public <R extends BasePage, A extends BasePage> R action(BiFunction<A, WebDriver, R> action) {
        return action.apply((A) this, getDriver());
    }
}
