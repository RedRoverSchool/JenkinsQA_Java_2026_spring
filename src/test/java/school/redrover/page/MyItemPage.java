package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyItemPage extends BasePage {

    public MyItemPage(WebDriver driver) { super(driver); }

    public MyItemPage clickEnableButton() {
        getDriver().findElement(By.xpath("//button[normalize-space()='Enable']")).click();

        return this;
    }
}
