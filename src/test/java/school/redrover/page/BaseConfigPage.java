package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseConfigPage extends BasePage {
    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    public <JobPage extends BasePage> JobPage clickSave(JobPage jobpage) {
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return jobpage;
    }
}
