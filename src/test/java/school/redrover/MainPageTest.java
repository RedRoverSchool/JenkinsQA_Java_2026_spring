package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MainPageTest extends BaseTest {

    private static final String TEXT_CONTENT = "TEST";

    private void openDescription(){
        getDriver().findElement(By.cssSelector("#description-link.jenkins-button")).click();
    }

    @Test
    public void testAddDescription() {
        openDescription();

        getDriver().findElement(By.name("description")).sendKeys(TEXT_CONTENT);
        getDriver().findElement(By.name("Submit")).click();

        getWait5().until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                TEXT_CONTENT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testChangeDescription() {
        String changedDescription = TEXT_CONTENT + "_changed";

        openDescription();

        WebElement descriptionField = getDriver().findElement(By.name("description"));
        descriptionField.clear();
        descriptionField.sendKeys(changedDescription);
        getDriver().findElement(By.name("Submit")).click();

        getWait5().until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(),
                changedDescription);
    }

    @Test
    public void testSaveWithoutDescription() {
        openDescription();
        getDriver().findElement(By.name("Submit")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#description-link.jenkins-button")));
        Assert.assertTrue(
                getDriver().findElement(By.id("description-content")).getText().isEmpty(),
                "Description has non-empty content!");
    }
}
