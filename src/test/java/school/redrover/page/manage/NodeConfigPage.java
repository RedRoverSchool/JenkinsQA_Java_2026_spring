package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.page.common.BasePage;

import java.util.ArrayList;
import java.util.List;

public class NodeConfigPage extends BasePage {

    public NodeConfigPage(WebDriver driver) {
        super(driver);
    }

    public NodeConfigPage changeDescription(String desc) {
        getDriver().findElement(By.xpath("//textarea[@name='nodeDescription']")).sendKeys(desc);
        return this;
    }

    public NodeConfigPage changeDir(String dir) {
        getDriver().findElement(By.xpath("//input[@name='_.remoteFS']")).sendKeys(dir);
        return this;
    }

    public NodeConfigPage changeLabel(String label) {
        getDriver().findElement(By.xpath("//input[@name='_.labelString']")).sendKeys(label);
        return this;
    }

    public NodeConfigPage changeUsage(String usage) {

            WebElement usageSelect = getDriver().findElement(By.xpath("//select[@name='mode']"));

            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                    usageSelect
            );

            getWait5().until(ExpectedConditions.elementToBeClickable(usageSelect));

            new Select(usageSelect).selectByValue(usage);

            return this;
        }

    public NodeConfigPage saveChanges() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        return this;
    }

    public List<String> getConfigDescriptionList(String label) {

        List<String> actualAttributes = new ArrayList<>();
        actualAttributes.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")))
                .getText());

        actualAttributes.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/label/%s']".formatted(label)))).getText());

        return actualAttributes;
    }
}
