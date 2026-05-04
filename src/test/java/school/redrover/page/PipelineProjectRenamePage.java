package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PipelineProjectRenamePage extends BasePage {

    public PipelineProjectRenamePage(WebDriver driver) {
        super(driver);
    }

    public PipelineProjectRenamePage updateProjectName(String name) {
        WebElement inputField = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        inputField.clear();
        inputField.sendKeys(name);

        return this;
    }

    public PipelineProjectPage clickRenameButton() {
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Permalinks']")));

        return new PipelineProjectPage(getDriver());
    }
}
