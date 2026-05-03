package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    //todo: перепишите на нормальный метод)
    public MyItemPage selectItem() {
        getDriver().findElement(By.xpath("//span[text()=\"My Item\"]")).click();

        return new MyItemPage(getDriver());
    }
}
