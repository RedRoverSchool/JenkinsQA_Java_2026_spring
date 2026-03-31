import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class GroupGachiteam {

    @Test
    public void testCheckTitlesNameAndNavigation() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://commitquality.com");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement Product = driver.findElement(By.xpath("//*[@data-testid = 'navbar-products']"));
            Assert.assertEquals("Products", Product.getText());
            String valueOfActiveElement = driver.findElement(By.xpath("//*[@class='nav-link active']")).getAttribute("class");
            Assert.assertTrue(valueOfActiveElement.contains("nav-link active"));

            WebElement AddProduct = driver.findElement(By.xpath("//*[@data-testid = 'navbar-addproduct']"));
            Assert.assertEquals("Add Product", AddProduct.getText());
            AddProduct.click();
            Assert.assertTrue(valueOfActiveElement.contains("nav-link active"));

            WebElement Practice = driver.findElement(By.xpath("//*[@data-testid = 'navbar-practice']"));
            Assert.assertEquals("Practice", Practice.getText());
            Practice.click();
            Assert.assertTrue(valueOfActiveElement.contains("nav-link active"));

            WebElement Login = driver.findElement(By.xpath("//*[@data-testid = 'navbar-login']"));
            Assert.assertEquals("Login", Login.getText());
            Login.click();
            Assert.assertTrue(valueOfActiveElement.contains("nav-link active"));
        } finally {
            driver.quit();
        }
    }
}