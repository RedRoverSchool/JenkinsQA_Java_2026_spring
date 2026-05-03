package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelinePage extends BasePage{

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    public PipelinePage clickAddDescription() {
        getDriver().findElement(By.id("description-link")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public PipelinePage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

        return this;
    }

    public PipelinePage clickSaveDescription() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        return this;
    }
}
