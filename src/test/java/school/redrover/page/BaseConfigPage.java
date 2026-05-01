package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseConfigPage extends BasePage{
    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    public <JobConfigPage extends BasePage> JobConfigPage clickSave (JobConfigPage jobconfig) {
        getDriver().findElement(By.name("Submit"));
        return jobconfig;
    }
}
