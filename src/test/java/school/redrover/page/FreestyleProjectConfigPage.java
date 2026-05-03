package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreestyleProjectConfigPage extends BaseConfigPage {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectPage clickSubmitButton() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        // todo: изменить ожидание FreestyleProjectPage, чтобы не работало с url
        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlContains("configure")));

        return new FreestyleProjectPage(getDriver());
    }
}
