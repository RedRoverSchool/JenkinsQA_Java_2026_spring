package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.common.BaseProjectPage;

public class BuildNowPage extends BaseProjectPage {

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
}
