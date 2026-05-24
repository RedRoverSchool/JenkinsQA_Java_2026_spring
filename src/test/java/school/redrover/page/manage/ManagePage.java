package school.redrover.page.manage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ManagePage extends BasePage {

    @FindBy(xpath = "//a[@href='configure']")
    private WebElement systemButton;

    @FindBy(xpath = "//a[@href='appearance']")
    private WebElement appearanceButton;


    private static final By SEARCH_BAR = By.id("settings-search-bar");
    private static final By EMPTY_DROPDOWN = By.className("jenkins-search__results__no-results-label");
    private static final By HEADER = By.xpath("//h1");
    private final List<WebElement> manageItems = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']/a/dl/dt"));

    public ManagePage(WebDriver driver) {
        super(driver);
    }

    public SystemPage clickSystem() {
        systemButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']")));

        return new SystemPage(getDriver());
    }

    public AppearancePage clickAppearance() {
        appearanceButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']")));

        return new AppearancePage(getDriver());
    }

    public ToolsPage clickToolsButton() {
        getDriver().findElement(By.xpath("//a[@href='configureTools']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        return new ToolsPage(getDriver());
    }

    public UserManagementPage clickUsersButton() {
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        return new UserManagementPage(getDriver());
    }

    public CredentialsPage clickCredentials() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='credentials']"))).click();
        return new CredentialsPage(getDriver());
    }

    public NodesPage clickNodesButton() {
        getDriver().findElement(By.xpath("//a[contains(@href, 'computer')]")).click();
        return new NodesPage(getDriver());
    }

    public List<String> getManageItems() {
        List<String> actualItems = new ArrayList<>();
        for (WebElement manageitem : manageItems) {
            actualItems.add(manageitem.getText());
        }
        return actualItems;
    }

    public ManagePage typeSearchQuery(String text) {
        getDriver().findElement(By.id("settings-search-bar")).sendKeys(text);

        return this;
    }

    public String getActualOutput() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@class, 'jenkins-dropdown__item')]"))).getText();
    }

    public boolean isNoResultsMessageDisplayed() {
        return getWait5().until(ExpectedConditions.textToBePresentInElementLocated(EMPTY_DROPDOWN, "No results"));
    }

    public BasePage submitSearchByEnter() {
        getWait10().until(d -> {
            try {
                WebElement searchBar = d.findElement(SEARCH_BAR);
                searchBar.sendKeys(Keys.ENTER);

                return ExpectedConditions.not(
                        ExpectedConditions.textToBePresentInElementLocated(HEADER, "Manage Jenkins")).apply(d);
            } catch (StaleElementReferenceException e) {
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return new BasePage(getDriver());
    }
}
