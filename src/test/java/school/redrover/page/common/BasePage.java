package school.redrover.page.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.LoginPage;
import school.redrover.page.ManagePage;

public class BasePage extends BaseModel {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By HEADER = By.xpath("//h1");

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public HomePage goHomePage() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();
        // waiting for the home page
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']")));

        return new HomePage(getDriver());
    }

    public ManagePage clickManageButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK)).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(HEADER, "Manage Jenkins"));

        return new ManagePage(getDriver());
    }

    public LoginPage openUserActionMenuAndLogout() {
        WebElement hoverOverAccountIcon = getDriver().findElement(By.id("root-action-UserAction"));
        new Actions(getDriver()).moveToElement(hoverOverAccountIcon).perform();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']")));
        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();

        return new LoginPage(getDriver());
    }

    public String getHeaderText() {
        return getDriver().findElement(By.cssSelector("div>#main-panel>div>div>h1")).getText();
    }
}
