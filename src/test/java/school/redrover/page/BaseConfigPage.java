package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseConfigPage extends BasePage{
    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    public <JobConfigPage extends BasePage> JobConfigPage presenceOfSaveButton (JobConfigPage jobconfig) {
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        return jobconfig;
    }

    public <JobConfigPage extends BasePage> JobConfigPage clickSave (JobConfigPage jobconfig) {
        getDriver().findElement(By.name("Submit"));
        return jobconfig;
    }

}
