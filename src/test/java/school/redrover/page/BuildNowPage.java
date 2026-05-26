package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.common.BaseProjectPage;

import java.util.List;

public class BuildNowPage extends BaseProjectPage {

    private static final By BUILD_ITEMS = By.className("app-builds-container__item");

    public BuildNowPage(WebDriver driver) {
        super(driver);
    }

    public BuildNowPage clickBuildNowSideMenuButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Build Now']/.."))).click();

        return this;
    }

    public Boolean isPopUpMessageDisplayed(String popUpMessage) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("notification-bar"))).getText().equals(popUpMessage);
    }

    public List<String> getBuilds() {
        getWait10().until(ExpectedConditions.presenceOfElementLocated(BUILD_ITEMS));

        return getDriver().findElements(BUILD_ITEMS)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
