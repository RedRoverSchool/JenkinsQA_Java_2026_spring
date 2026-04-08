package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class RestApiLinkHiddenTest extends BaseTest {
    @Test
    public void testRestApiLinkIsHiddenOnApiPage() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        ).click();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        boolean isRestApiLinkPresentInFooter = !getDriver().findElements(
                By.xpath("//footer//a[contains(text(),'REST API')]")
        ).isEmpty();

        Assert.assertFalse(isRestApiLinkPresentInFooter,
                "Ссылка 'REST API' не должна отображаться в футере на странице REST API (сама на себя)");
    }
}
