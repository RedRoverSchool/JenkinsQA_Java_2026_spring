import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

@Ignore
public class CodeWarTests {
    @Test
    public void testLoginOnHP() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.codewars.com/");
        WebElement loginButton = driver.findElement(By.xpath("//a[@id='login-btn']"));
        loginButton.click();
        String loginURL = driver.getCurrentUrl();

        Assert.assertEquals(loginURL, "https://www.codewars.com/users/sign_in");

        driver.quit();
    }

    @Test
    public void testBlog() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.codewars.com/");
        WebElement blogButton = driver.findElement(By.xpath("//div[@class='navbar_menu-links']/a[@href='/blog']"));
        blogButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        WebElement mainBlogTitleElement = driver.findElement(By.xpath("//h1[@class='text-color-white']"));
        String mainBlogTitle = mainBlogTitleElement.getText();

        Assert.assertEquals(mainBlogTitle, "Developer Blog");

        driver.quit();
    }
}
