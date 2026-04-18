package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class NodeTest extends BaseTest {

    private static final String NEW_NODE_NAME = "New Test Node";

    @Test
    public void testCreateNewNode(){

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='computer']")).click();
        getDriver().findElement(By.xpath("//a[@href='new']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_NODE_NAME);
        getDriver().findElement(By.className("jenkins-radio__label")).click();
        getDriver().findElement(By.xpath("//button[@value='Create']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.xpath("//button[@value='Save']")))).click();

        List<String> actualNodeList = getDriver().findElements(By
                        .xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertTrue(actualNodeList.contains(NEW_NODE_NAME));
    }
}
