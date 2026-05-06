package school.redrover.page.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseConfigPage<T extends BaseConfigPage<T>> extends BasePage {
    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    public <JobPage extends BasePage> JobPage clickSave(JobPage jobpage) {
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return jobpage;
    }

    public T clickApply() {
        getDriver().findElement(By.name("Apply")).click();
        return (T) this;
    }

    public T enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[contains(@name, 'description')]")).sendKeys(description);
        return (T) this;

    }
}
