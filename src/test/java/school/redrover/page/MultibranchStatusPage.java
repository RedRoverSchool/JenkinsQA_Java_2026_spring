package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class MultibranchStatusPage extends BasePage {

    public MultibranchStatusPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchStatusPage clickDeleteInSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Delete Multibranch Pipeline')]"))).click();

        return this;
    }

    public HomePage confirmDelete() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}
