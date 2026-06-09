package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.project.user.CreateUserPage;
import school.redrover.page.HomePage;
import school.redrover.page.common.BasePage;
import school.redrover.page.project.user.UserPage;

import java.util.List;

public class UserManagementPage extends BasePage {

    private final By addUserButton = By.xpath("//div[@class='jenkins-app-bar__controls']");

    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUserButton() {
        getDriver().findElement(addUserButton).click();
        return new CreateUserPage(getDriver());
    }

    public List<String> getUsersList() {
        List<String> actualUsersNameList = getWait10().until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                                "//a[@class = 'jenkins-table__link model-link inside']")))
                .stream()
                .map(WebElement::getText)
                .toList();
        return actualUsersNameList;
    }

    public UserPage clickUserByName(String userName) {
        getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[text()='%s']".formatted(userName)))).click();

        return new UserPage(getDriver());
    }

    public UserManagementPage clickUserDropdownByName(String userName) {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='%s']/button[@class = 'jenkins-menu-dropdown-chevron']"
                        .formatted(userName)))).click();

        return this;
    }

    public UserManagementPage clickDeleteFromDropdown() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@href, 'doDelete')]"))).click();

        return this;
    }

    public HomePage confirmDelete() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[text()='Yes']"))).click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("h1"), "Welcome to Jenkins!"));

        return new HomePage(getDriver());
    }
}