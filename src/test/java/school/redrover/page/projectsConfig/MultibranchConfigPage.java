package school.redrover.page.projectsConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BaseConfigPage;

public class MultibranchConfigPage extends BaseConfigPage<MultibranchConfigPage> {
    public MultibranchConfigPage(WebDriver driver) {
        super(driver);
    }

    public HomePage confirmDelete() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}
