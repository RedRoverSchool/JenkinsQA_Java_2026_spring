package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FolderPage extends BasePage {
    public FolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage clickRename(String projectName) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));
        getDriver().findElement(By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(projectName))).click();

        return this;
    }

    public FolderPage enterNewName(String newName) {
        WebElement newNameEl = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        newNameEl.clear();
        newNameEl.sendKeys(newName);

        return this;
    }

    public FolderPage clickRenameButton() {
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));

        return this;
    }
}
