package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BasePage;

import java.util.List;

public class ChangesProjectPage extends BasePage {

    public ChangesProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {
        return getDriver().findElement(By.xpath("//div[@id='main-panel' and contains(., 'No builds')]")).getText();
    }
}
