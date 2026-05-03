package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


import java.util.ArrayList;
import java.util.List;

//US_09.001 | Build history > Core Build History Display
//#516
public class BuildHistoryTest extends BaseTest {

    @Test
    public void testCreateMultiConfigurationProject() {

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id=\"tasks\"]//div[1]//span[1]//a[1]"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))).sendKeys("Test");
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='hudson_matrix_MatrixProject jenkins-choice-list__item'] label"))).click();

        getDriver().findElement(By.cssSelector("#ok-button")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[value='Save']"))).click();

        getWait10().until(ExpectedConditions.urlToBe("http://localhost:8080/job/Test/"));
    }

    @Test(dependsOnMethods = "testCreateMultiConfigurationProject")
    public void testScheduleBuild() {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'build?delay=0sec')]"))).click();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Build scheduled')]"))).isDisplayed(), "Уведомление 'Build scheduled' не появилось!");
    }

    @Test(dependsOnMethods = "testScheduleBuild")
    public void testDisplayElementsHistoryBuild() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("//tbody/tr[1]/td[1]/div[1]//*[name()='svg']");
        expectedElements.add("//span[normalize-space()='Test » default']");
        expectedElements.add("//span[normalize-space()='Test']");
        expectedElements.add("//tbody/tr[2]/td[3]");
        expectedElements.add("//tbody/tr[2]/td[4]");
        expectedElements.add("//tbody/tr[2]/td[5]/div[1]/a[1]");

        for (String locator : expectedElements) {
            boolean isDisplayed = getDriver().findElement(By.xpath(locator)).isDisplayed();
            Assert.assertTrue(isDisplayed, "Элемент не найден:" + expectedElements);
        }
    }

    @Test(dependsOnMethods = "testDisplayElementsHistoryBuild")
    public void testDisplayInformationConsole() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();
        getDriver().findElement(By.xpath("//tbody/tr[1]/td[5]")).click();

        String fullText = getDriver().findElement(By.xpath("//pre[@id='out']")).getText();
        Assert.assertTrue(fullText.contains("Finished: SUCCESS"), "Текст не найден!");
    }

}
