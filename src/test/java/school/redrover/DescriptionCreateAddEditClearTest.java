package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

@Ignore
public class DescriptionCreateAddEditClearTest extends BaseTest {

    @Test
    public void testCreateAddEditClearDescription() {
        final String text = "My first project";
        final String text2 = "My first successful project";

        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebElement descriptionField = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));

        descriptionField.sendKeys(text);
        getDriver().findElement(By.className("textarea-show-preview")).click();

        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), text);

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).
                getText(), text);

        getDriver().findElement(By.id("description-link")).click();
        WebElement textArea = getDriver().findElement(By.name("description"));
        textArea.clear();
        textArea.sendKeys(text2);
        getDriver().findElement(By.className("textarea-show-preview")).click();

        Assert.assertEquals(getDriver().findElement(By.className("textarea-preview")).getText(), text2);

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).
                getText(), text2);

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.name("description")).clear();

        getDriver().findElement(By.name("Submit")).click();

        boolean isEmpty = getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("description-content"))).getText().isEmpty();
        Assert.assertTrue(isEmpty, "Описание не очистилось!");
    }
}
