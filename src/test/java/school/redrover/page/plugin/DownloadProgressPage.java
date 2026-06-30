package school.redrover.page.plugin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DownloadProgressPage extends PluginPage {

    public DownloadProgressPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessInstall() {
        // Нужно время на установку плагина и на изменения отображаемого статуса на странице
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='log']/tbody/tr[td[contains(text(), 'ChuckNorris')] and td[contains(text(), 'Success')]]"))).getText();
    }
}
