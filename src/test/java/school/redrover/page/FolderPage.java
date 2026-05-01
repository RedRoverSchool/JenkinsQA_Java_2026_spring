package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FolderPage extends BasePage {
    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage waitToLoadFolderPage(){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));

        return this;
    }
}
