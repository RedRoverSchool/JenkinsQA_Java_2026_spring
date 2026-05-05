package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuildHistoryPage extends BasePage {

    public BuildHistoryPage(WebDriver driver) {super(driver);}

    public ConsolePage clickConsole(){
        getDriver().findElement(By.xpath("//tbody/tr[1]/td[5]")).click();

        return new ConsolePage(getDriver());
    }


}
