package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreestyleProjectPage extends BaseModel {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectPage waitToLoadStatusPage(){
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='permalinks-header']")));

        return new FreestyleProjectPage(getDriver());
    }

    public FreestyleProjectConfigPage clickToConfigurePage() {
        getDriver().findElement(By.xpath("//a[contains(@href, 'configure')]")).click();

        return new FreestyleProjectConfigPage(getDriver());
    }
}
