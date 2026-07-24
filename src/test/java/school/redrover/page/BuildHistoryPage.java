package school.redrover.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

    @Step("Open the context dropdown menu for the job: '{jobName}'")
    public BuildHistoryPage clickDropDownMenu(String jobName) {
        getDriver().findElement(By.xpath("//a[contains(., '%s')]//button[@class='jenkins-menu-dropdown-chevron']".formatted(jobName))).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='jenkins-dropdown']//button[contains(@href, 'Delete')]")));

        return this;
    }

    @Step("Delete the project via dropdown menu and confirm deletion")
    public HomePage clickDeleteProjectWithConfirmation() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='jenkins-dropdown']//button[contains(@href, 'Delete')]"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//dialog[@class='jenkins-dialog']//button[@data-id='ok']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));

        return new HomePage(getDriver());
    }

    @Step("Click on the 'Console Output' icon to view build logs")
    public ConsolePage clickConsole() {
        getWait10().until(ExpectedConditions.elementToBeClickable(clickButtonConsole)).click();
        return new ConsolePage(getDriver());
    }

    @Step("Get the list of all build names from the history table")
    public List<String> getBuildHistoryList() {
        List<WebElement> elements;
        try {
            elements = getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[@class='jenkins-table__link model-link']")));
        } catch (TimeoutException e) {
            return List.of();
        }

        return elements.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toList();
    }
}
