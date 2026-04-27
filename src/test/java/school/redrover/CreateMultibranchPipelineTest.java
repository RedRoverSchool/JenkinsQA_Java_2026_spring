package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateMultibranchPipelineTest extends BaseTest {
    @Test
    public void testCreateMultibranchPipeline() {
        String itemName = "test-pipeline";
        getDriver().findElement(By.linkText("New Item")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")))
                .sendKeys(itemName);
        WebElement multibranch = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[text()='Multibranch Pipeline']")
                )
        );

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                multibranch
        );

        getWait10().until(ExpectedConditions.elementToBeClickable(multibranch)).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]"))
        ).click();

        WebElement actualProjectName = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'%s')]".formatted(itemName))));
        Assert.assertEquals(actualProjectName.getText(), itemName);
    }
}
