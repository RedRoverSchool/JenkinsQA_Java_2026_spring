package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManagePage extends BasePage {

    public ManagePage(WebDriver driver) {
        super(driver);
    }

    public ToolsPage goToTools() {
        getDriver().findElement(By.xpath("//a[@href='configureTools']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        return new ToolsPage(getDriver());
    }


}
