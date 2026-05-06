package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.List;

public class UserManagementPage extends BasePage{

    private By addUserButton = By.xpath("//div[@class='jenkins-app-bar__controls']");

    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickAddUser() {
        getDriver().findElement(addUserButton).click();
        return new CreateUserPage(getDriver());
    }

    public List<String> getUsersList() {
        List<String> actualUsersNameList = getWait10().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//a[@class = 'jenkins-table__link model-link inside']")))
                .stream()
                .map(WebElement::getText)
                .toList();
        return actualUsersNameList;
    }
}
