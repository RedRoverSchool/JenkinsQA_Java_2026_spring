package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ManagePage extends BasePage {

    public ManagePage(WebDriver driver) {
        super(driver);
    }

    private static final By EMPTY_DROPDOWN = By.className("jenkins-search__results__no-results-label");
    private final List<WebElement> manageItems = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']/a/dl/dt"));

    public ToolsPage clickToolsButton() {
        getDriver().findElement(By.xpath("//a[@href='configureTools']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        return new ToolsPage(getDriver());
    }

    public UserManagementPage clickUsersButton() {
        getDriver().findElement(By.xpath("//a[@href='securityRealm/']")).click();
        return new UserManagementPage(getDriver());
    }

    public CredentialsPage goToCredentials() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='credentials']"))).click();
        return new CredentialsPage(getDriver());
    }

    public NodesPage goToNodes() {
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

    public ManagePage searchBarInput(String text) {
        getDriver().findElement(By.id("settings-search-bar")).sendKeys(text);

        return this;
    }

    public String getActualOutput() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@class, 'jenkins-dropdown__item')]"))).getText();
    }

    public boolean isNoResultsMessageDisplayed(){
        return getWait5().until(ExpectedConditions.textToBePresentInElementLocated(EMPTY_DROPDOWN, "No results"));
    }
}
