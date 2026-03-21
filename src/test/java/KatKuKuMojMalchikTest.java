import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class KatKuKuMojMalchikTest {

	@Test
	public void testEkaterinaTsymbal() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

		driver.get("https://aqa-proka4.org/sandbox/web");

		String title = driver.getTitle();
		System.out.println("Page title: " + title);

		//WebElement textBox = driver.findElement(By.name("my-text"));

		WebElement textBox = driver.findElement(By.xpath("//*[@id='username']"));
		textBox.sendKeys("test");

		WebElement textBox1 = driver.findElement(By.xpath("//*[@id='email']"));
		textBox1.sendKeys("test@example.com");

		WebElement textBox2 = driver.findElement(By.xpath("//*[@id='password']"));
		textBox2.sendKeys("12345");


		WebElement element = driver.findElement(By.xpath("//*[@id='country']"));
		// Создаем объект Select
		Select dropdown = new Select(element);

		dropdown.selectByVisibleText("Russia");

		WebElement checkBox = driver.findElement(By.xpath("//*[@id='terms']"));
		checkBox.click();

		WebElement button = driver.findElement(By.xpath("//*[@id='submitBtn']"));
		button.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Ждём, пока элемент станет видимым (чтобы hidden исчез)
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("formResult")
		));


// Извлекаем текст именно из параграфа, где лежит сообщение
		WebElement messageParagraph = message.findElement(By.tagName("p"));
		System.out.println("Actual message: '" + messageParagraph.getText() + "'");

// Сравниваем с ожидаемым текстом (используем getText() для получения текста элемента)
		Assert.assertEquals(messageParagraph.getText(), "Форма успешно отправлена!");

		driver.quit();

		Assert.assertEquals(title, "WEB Sandbox - Практика автоматизации");
	}

}

