package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FolderConfigPage extends BaseConfigPage{
    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

//    public FolderConfigPage presenceOfSubmit () {
//        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
//        return this;
//    }
//    public FolderPage clickSubmit () {
//        getDriver().findElement(By.name("Submit"));
//        return new FolderPage(getDriver());
//    }

}
