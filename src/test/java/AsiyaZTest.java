import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AsiyaZTest {

    @Test
    public void passwordError(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://practice.expandtesting.com/login");

            driver.findElement(By.xpath("//input[@id='username']")).sendKeys("practice");
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys("WrongPassword");

            driver.findElement(By.xpath("//button[@id='submit-login']")).click();

            String errorReal = driver.findElement(By.xpath("//*[@id='flash']")).getText();

            Assert.assertEquals(
                    errorReal,
                    "Invalid username");
        }finally {
            driver.quit();
        }
    }

    @Test
    public void successfulAuthorization(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/login?from=%2F");

            driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("admin");
            driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("admin");

            driver.findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary']")).click();

            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    "http://localhost:8080/" );
        }finally {
            driver.quit();
        }

    }

    @Test
    public void goToItem(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/");

            driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("admin");
            driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("admin");
            driver.findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary']")).click();

            driver.findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    "http://localhost:8080/view/all/newJob");
        }finally {
            driver.quit();
        }
    }
}
