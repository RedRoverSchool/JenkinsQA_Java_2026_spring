package school.redrover.page.external;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BasePage;

public class CommandPalettePage extends BasePage {

    public CommandPalettePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getHeaderText() {
        return getDriver().findElement(By.cssSelector("div>h1")).getText();
    }
}
