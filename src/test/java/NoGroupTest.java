import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NoGroupTest {

    @Test
    public void testFormsInputsPositivePetrP(){
        final String validUserName = "MyTestUserName";
        final String validUserEmail = "My@test.email";
        final String validUserPass = "MyTestPass11";
        String outText = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
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

            WebElement success = driver.findElement(By.id("formResult"));
            outText = success.getText();
        } finally {
            driver.quit();
            Assert.assertEquals(outText, "Форма успешно отправлена!");
        }
    }


    @Test
    public void testFormWithValidPositivePetrP(){
        final String validUserName = "MyTestUserName";
        final String validUserEmail = "My@test.email";
        final String validUserPass = "MyTestPass11";
        String outMessage = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.findElement(By.id("val-username")).sendKeys(validUserName);
            driver.findElement(By.id("val-email")).sendKeys(validUserEmail);
            driver.findElement(By.id("val-password")).sendKeys(validUserPass);
            driver.findElement(By.id("val-confirm-password")).sendKeys(validUserPass);
            driver.findElement(By.id("valSubmitBtn")).click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement resalt = driver.findElement(By.id("valFormResult"));
            outMessage = resalt.getText();
        } finally {
            driver.quit();
            Assert.assertEquals(outMessage, "Все проверки пройдены! Форма валидна.");
        }
    }


    @Test
    public void testFormWithValidNegativeUserNamePetrP(){
        final String invalidUserName = "User";
        final String validUserEmail = "My@test.email";
        final String validUserPass = "MyTestPass11";
        String outMessage = "";
        String errNameMessage = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.findElement(By.id("val-username")).sendKeys(invalidUserName);
            driver.findElement(By.id("val-email")).sendKeys(validUserEmail);
            driver.findElement(By.id("val-password")).sendKeys(validUserPass);
            driver.findElement(By.id("val-confirm-password")).sendKeys(validUserPass);
            driver.findElement(By.id("valSubmitBtn")).click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement resalt = driver.findElement(By.id("valFormResult"));
            WebElement errName = driver.findElement(By.id("username-error"));

            outMessage = resalt.getText();
            errNameMessage = errName.getText();
        } finally {
            driver.quit();

            Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
            Assert.assertEquals(errNameMessage, "Username должен содержать минимум 5 символов");
        }
    }


    @Test
    public void testFormWithValidNegativeUserMail1PetrP(){
        final String validUserName = "MyTestUserName";
        final String invalidUserEmail = "Mytest.email";
        final String validUserPass = "MyTestPass11";
        String outMessage = "";
        String errEmailMessage = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.findElement(By.id("val-username")).sendKeys(validUserName);
            driver.findElement(By.id("val-email")).sendKeys(invalidUserEmail);
            driver.findElement(By.id("val-password")).sendKeys(validUserPass);
            driver.findElement(By.id("val-confirm-password")).sendKeys(validUserPass);
            driver.findElement(By.id("valSubmitBtn")).click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement resalt = driver.findElement(By.id("valFormResult"));
            WebElement errEmail = driver.findElement(By.id("email-error"));

            outMessage = resalt.getText();
            errEmailMessage = errEmail.getText();
        } finally {
            driver.quit();

            Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
            Assert.assertEquals(errEmailMessage, "Email должен содержать символ @");
        }

    }


    @Test
    public void testFormWithValidNegativeUserMail2PetrP(){
        final String validUserName = "MyTestUserName";
        final String invalidUserEmail = "My@testemail";
        final String validUserPass = "MyTestPass11";
        String outMessage = "";
        String errEmailMessage = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.findElement(By.id("val-username")).sendKeys(validUserName);
            driver.findElement(By.id("val-email")).sendKeys(invalidUserEmail);
            driver.findElement(By.id("val-password")).sendKeys(validUserPass);
            driver.findElement(By.id("val-confirm-password")).sendKeys(validUserPass);
            driver.findElement(By.id("valSubmitBtn")).click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement resalt = driver.findElement(By.id("valFormResult"));
            WebElement errEmail = driver.findElement(By.id("email-error"));

            outMessage = resalt.getText();
            errEmailMessage = errEmail.getText();
        } finally {
            driver.quit();

            Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
            Assert.assertEquals(errEmailMessage, "Email должен содержать символ .");
        }

    }


    @Test
    public void testFormWithValidNegativeUserPass1PetrP(){
        final String validUserName = "MyTestUserName";
        final String validUserEmail = "My@test.email";
        final String validUserPass = "MyTestPass11";
        String outMessage = "";
        String errPassMessage = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.findElement(By.id("val-username")).sendKeys(validUserName);
            driver.findElement(By.id("val-email")).sendKeys(validUserEmail);
            driver.findElement(By.id("val-password")).sendKeys(validUserPass);
            driver.findElement(By.id("val-confirm-password")).sendKeys("");
            driver.findElement(By.id("valSubmitBtn")).click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement resalt = driver.findElement(By.id("valFormResult"));
            WebElement errPass = driver.findElement(By.id("confirm-password-error"));

            outMessage = resalt.getText();
            errPassMessage = errPass.getText();
        }finally {
            driver.quit();

            Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
            Assert.assertEquals(errPassMessage, "Пароли не совпадают");
        }
    }


    @Test
    public void testFormWithValidNegativeUserPass2PetrP(){
        final String validUserName = "MyTestUserName";
        final String validUserEmail = "My@test.email";
        final String invalidUserPass = "PassPass";
        String outMessage = "";
        String errPassMessage = "";

        WebDriver driver = new ChromeDriver();
        driver.get("https://aqa-proka4.org/sandbox/web");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.findElement(By.id("val-username")).sendKeys(validUserName);
            driver.findElement(By.id("val-email")).sendKeys(validUserEmail);
            driver.findElement(By.id("val-password")).sendKeys(invalidUserPass);
            driver.findElement(By.id("val-confirm-password")).sendKeys(invalidUserPass);
            driver.findElement(By.id("valSubmitBtn")).click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement resalt = driver.findElement(By.id("valFormResult"));
            WebElement errPass = driver.findElement(By.id("password-error"));

            outMessage = resalt.getText();
            errPassMessage = errPass.getText();
        }finally {
            driver.quit();

            Assert.assertEquals(outMessage, "Форма содержит ошибки. Исправьте их и попробуйте снова.");
            Assert.assertEquals(errPassMessage, "Password должен содержать минимум 8 символов, включая буквы и цифры");
        }
    }

    @Test
    public void testCheckFindLine() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://wooordhunt.ru/dic/content/en_ru");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            WebElement findLine = driver.findElement(By.id("hunted_word"));
            findLine.sendKeys("cat");
            WebElement buttonFind = driver.findElement(By.id("hunted_word_submit"));
            buttonFind.click();
            WebElement word = driver.findElement(By.xpath("//div[@id = 'wd_title']/h1"));
            word.getText();

            Assert.assertEquals(word.getText(), "Cat");
        } finally {
            driver.quit();
        }
    }
}
