package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.List;

public class BuildHistoryPage extends BasePage {

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    private WebElement clickButtonConsole;

    @FindBy(xpath = "//a[@class='jenkins-table__link model-link']")
    private List<WebElement> buildTable;

    public BuildHistoryPage clickDropDownMenu(String jobName) {
        getDriver().findElement(By.xpath("//a[contains(., '%s')]//button[@class='jenkins-menu-dropdown-chevron']".formatted(jobName))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']//button[contains(@href, 'Delete')]")));

        return this;
    }

    public HomePage clickDeleteProjectWithConfirmation() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='jenkins-dropdown']//button[contains(@href, 'Delete')]"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//dialog[@class='jenkins-dialog']//button[@data-id='ok']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));

        return new HomePage(getDriver());
    }

    public ConsolePage clickConsole() {
        getWait10().until(ExpectedConditions.elementToBeClickable(clickButtonConsole)).click();
        return new ConsolePage(getDriver());
    }

    public List<String> getBuildHistoryList() {
        return buildTable.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toList();
    }
}
