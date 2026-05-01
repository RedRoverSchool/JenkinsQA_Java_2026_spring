package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseConfigPage extends BasePage {
    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    public <JobPage extends BasePage> JobPage clickSave(JobPage jobpage) {
        getDriver().findElement(By.name("Submit")).click();
        return jobpage;
    }
}
