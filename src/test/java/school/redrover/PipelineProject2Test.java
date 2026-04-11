package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class PipelineProject2Test extends BaseTest {

    private static final String PROJECT_NAME = "MyPipelineProject";

    private void createItemAndGoToMainPage(String name) {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='jenkins-mobile-hide']"))).click();
    }

    @Test
    public void testCreateWithValidName() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        createItemAndGoToMainPage(PROJECT_NAME);
        WebElement projectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='%s']".formatted(PROJECT_NAME))));

        Assert.assertEquals(projectName.getText(), PROJECT_NAME);
    }

    @Test
    public void testCreateWithEmptyName() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        boolean isEnabled = getDriver().findElement(By.id("ok-button")).isEnabled();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='itemname-required']")).getText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertFalse(isEnabled);
    }

    @Test
    public void testCreateWithDuplicateName() {

        createItemAndGoToMainPage(PROJECT_NAME);

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText(),
                "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }
}
