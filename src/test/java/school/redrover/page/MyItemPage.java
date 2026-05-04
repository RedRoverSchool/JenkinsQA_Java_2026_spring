package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyItemPage extends BasePage {

    public MyItemPage(WebDriver driver) { super(driver); }

    public MyItemPage clickEnableButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("form#enable-project button[name='Submit']"))).click();

        return this;
    }
}
