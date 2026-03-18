import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class group_error_418Test {

    String validUserName = "MyTestUserName";
    String validUserEmail = "My@test.email";
    String validUserPass = "MyTestPass11";

    public WebDriver pre(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        return driver;
    }

    public Map<String, WebElement> findElementForTestWithValid(WebDriver driver){
        Map<String, WebElement> outMap = new HashMap<>(5);
        WebElement userName = driver.findElement(By.id("val-username"));
        WebElement userEmail = driver.findElement(By.id("val-email"));
        WebElement userPass = driver.findElement(By.id("val-password"));
        WebElement confirmUserPass = driver.findElement(By.id("val-confirm-password"));
        WebElement button = driver.findElement(By.id("valSubmitBtn"));

        outMap.put("userName", userName);
        outMap.put("userEmail", userEmail);
        outMap.put("userPass", userPass);
        outMap.put("confirmUserPass", confirmUserPass);
        outMap.put("button", button);

        return outMap;
    }

    @Test
    public void testFormsInputsPositiv(){
        WebDriver driver = pre();
        WebElement username1 = driver.findElement(By.id("username"));
        WebElement email1 = driver.findElement(By.id("email"));
        WebElement password1 = driver.findElement(By.id("password"));
        Select selector1 = new Select(driver.findElement(By.id("country")));
        WebElement check = driver.findElement(By.id("terms"));

        WebElement button = driver.findElement(By.id("submitBtn"));

        username1.sendKeys(validUserName);
        email1.sendKeys(validUserEmail);
        password1.sendKeys(validUserPass);
        selector1.selectByVisibleText("Russia");
        check.click();

        button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

//        WebElement success = driver.findElement(By.xpath("//*[@class='fas fa-check-circle mr-2']"));
        WebElement success = driver.findElement(By.id("formResult"));
        String outText = success.getText();
        driver.quit();

        Assert.assertEquals(outText, "Форма успешно отправлена!");

    }

    @Test
    public void testFormWithValidPositiv(){
        WebDriver driver = pre();
        Map<String, WebElement> webElementMap = findElementForTestWithValid(driver);

        webElementMap.get("userName").sendKeys(validUserName);
        webElementMap.get("userEmail").sendKeys(validUserEmail);
        webElementMap.get("userPass").sendKeys(validUserPass);
        webElementMap.get("confirmUserPass").sendKeys(validUserPass);
        webElementMap.get("button").click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement resalt = driver.findElement(By.id("valFormResult"));
        String outMessage = resalt.getText();
        driver.quit();

        Assert.assertEquals(outMessage, "Все проверки пройдены! Форма валидна.");
    }

    @Test
    public void testFormWithValidNegativUserName(){
        WebDriver driver = pre();

        Map<String, WebElement> webElementMap = findElementForTestWithValid(driver);

        webElementMap.get("userName").sendKeys("User");
        webElementMap.get("userEmail").sendKeys(validUserEmail);
        webElementMap.get("userPass").sendKeys(validUserPass);
        webElementMap.get("confirmUserPass").sendKeys(validUserPass);
        webElementMap.get("button").click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement resalt = driver.findElement(By.id("valFormResult"));
        WebElement errName = driver.findElement(By.id("username-error"));
        String outMessage = resalt.getText();
        String errNameMessage = errName.getText();
        driver.quit();

        Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
        Assert.assertEquals(errNameMessage, "Username должен содержать минимум 5 символов");
    }


    @Test
    public void testFormWithValidNegativUserMail(){
        WebDriver driver = pre();

        Map<String, WebElement> webElementMap = findElementForTestWithValid(driver);

        webElementMap.get("userName").sendKeys(validUserName);
        webElementMap.get("userEmail").sendKeys("testmail.com");
        webElementMap.get("userPass").sendKeys(validUserPass);
        webElementMap.get("confirmUserPass").sendKeys(validUserPass);
        webElementMap.get("button").click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement resalt = driver.findElement(By.id("valFormResult"));
        WebElement errEmail = driver.findElement(By.id("email-error"));
        String outMessage = resalt.getText();
        String errEmailMessage = errEmail.getText();

        driver.quit();

        Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
        Assert.assertEquals(errEmailMessage, "Email должен содержать символ @");
    }

    @Test
    public void testFormWithValidNegativUserPass1(){
        WebDriver driver = pre();

        Map<String, WebElement> webElementMap = findElementForTestWithValid(driver);

        webElementMap.get("userName").sendKeys(validUserName);
        webElementMap.get("userEmail").sendKeys(validUserEmail);
        webElementMap.get("userPass").sendKeys(validUserPass);
        webElementMap.get("confirmUserPass").sendKeys("");
        webElementMap.get("button").click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement resalt = driver.findElement(By.id("valFormResult"));
        WebElement errPass = driver.findElement(By.id("confirm-password-error"));
        String outMessage = resalt.getText();
        String errPassMessage = errPass.getText();
        driver.quit();

        Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
        Assert.assertEquals(errPassMessage, "Пароли не совпадают");
    }

    @Test
    public void testFormWithValidNegativUserPass2(){
        WebDriver driver = pre();

        Map<String, WebElement> webElementMap = findElementForTestWithValid(driver);

        webElementMap.get("userName").sendKeys(validUserName);
        webElementMap.get("userEmail").sendKeys(validUserEmail);
        webElementMap.get("userPass").sendKeys("PassPass");
        webElementMap.get("confirmUserPass").sendKeys("PassPass");
        webElementMap.get("button").click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement resalt = driver.findElement(By.id("valFormResult"));
        WebElement errPass = driver.findElement(By.id("password-error"));
        String outMessage = resalt.getText();
        String errPassMessage = errPass.getText();
        driver.quit();

        Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
        Assert.assertEquals(errPassMessage, "Password должен содержать минимум 8 символов, включая буквы и цифры");
    }
}
