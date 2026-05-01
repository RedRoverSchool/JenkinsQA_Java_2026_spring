package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

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

    public FolderPage clickAddDescription() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));
        getDriver().findElement(By.id("description-link")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public FolderPage enterDescription(String description) {
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

        return this;
    }

    public FolderPage clickSaveDescription() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        getWait2().until(ExpectedConditions.textMatches(By.id("description-content"), Pattern.compile("\\S")));

        return this;
    }

    public FolderConfigPage clickConfigure() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));
        getDriver().findElement(By.linkText("Configure")).click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Configuration"));

        return new FolderConfigPage(getDriver());
    }

}
