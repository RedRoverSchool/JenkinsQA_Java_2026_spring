package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BasePage;
import school.redrover.page.common.BaseProjectPage;

public class MultibranchProjectPage extends BaseProjectPage {

    public MultibranchProjectPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchProjectPage clickDeleteInSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Delete Multibranch Pipeline')]"))).click();

        return this;
    }

    public HomePage confirmDelete() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}
