import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupGachiteam {
    @Test
    public void testOpenSite() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.xpath("//*[@ng-click='customer()']")).click();
        WebElement userSelect = driver.findElement(By.xpath("//div[@class='form-group']"));
        Assert.assertEquals(userSelect.getText(), "Your Name :\n" +
                "       ---Your Name---\n" +
                " \n" +
                "       Hermoine Granger\n" +
                "Harry Potter\n" +
                "Ron Weasly\n" +
                "Albus Dumbledore\n" +
                "Neville Longbottom\n" +
                "     ");
        driver.quit();
    }
}