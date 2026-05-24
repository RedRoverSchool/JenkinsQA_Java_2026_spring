package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class AppearancePage extends BasePage {

    @FindBy(xpath = "//label[@for='radio-block-1']")
    private WebElement darkTheme;

    @FindBy(xpath = "//label[@for='radio-block-0']")
    private WebElement lightTheme;

    @FindBy(xpath = "//button[@class='jenkins-button apply-button']")
    private WebElement applyButton;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    public AppearancePage(WebDriver driver) {
        super(driver);
    }

    public AppearancePage clickDarkTheme() {
        darkTheme.click();
        return this;
    }

    public AppearancePage clickLightTheme() {
        darkTheme.click();
        return this;
    }

    public AppearancePage clickApply() {
        applyButton.click();
        return this;
    }

    public ManagePage clickOK() {
        saveButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("settings-search-bar")));

        return new ManagePage(getDriver());
    }

    public Object getThemeAttribute() {
        return ((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.getAttribute('data-theme')");
    }
}
