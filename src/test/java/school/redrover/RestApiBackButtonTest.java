package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class RestApiBackButtonTest extends BaseTest {
    @Test
    public  void testReturnWithBackButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

         wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        ).click();

        getDriver().navigate().back();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.equals("http://localhost:8080/") ,
                "После нажатия 'Назад' URL не соответствует Dashboard. Текущий URL: " + currentUrl);

        boolean isDashboardVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/view/')]"))
        ).isDisplayed();
        Assert.assertTrue(isDashboardVisible, "Элементы Dashboard не отображаются. Возможно, пользователь разлогинен.");

    }
}
