package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FolderConfigPage extends BaseConfigPage<FolderConfigPage> {
    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage clickHealthMetrics() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.advanced-button"))).click();

        return this;
    }

    public FolderConfigPage clickAddMetric() {
        getDriver().findElement(By.cssSelector("button.hetero-list-add[suffix='healthMetrics']")).click();
        return this;
    }

    public FolderConfigPage chooseFilterChildName() {
        getDriver().findElement(By.xpath("//button[contains(@class, 'jenkins-dropdown__item') and contains(text(), 'Child item with the given name')]")).click();

        return this;
    }

    public FolderConfigPage enterChildName(String childname) {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.childName']"))).sendKeys(childname);
        return this;

    }

    public String getTextOfMetric() {
        return
                getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.childName']"))).getAttribute("value");
    }
}
