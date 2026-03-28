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

public class KuKuMojMalchikTest {
	@Test
	public void testGismeteo() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.gismeteo.ru/");
		WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/button[1]/p"));
		button.click();

		WebElement nameCity = driver.findElement(By.xpath("//a[contains(text(), 'Москва')]"));
		nameCity.click();
		WebElement text = driver.findElement(By.xpath("//h1"));
		Assert.assertEquals(text.getText(), "Погода в Москве");
		driver.quit();

	}

	@Test
	public void testEditaOrlovaOnliner() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.onliner.by/");
		WebElement button = driver.findElement(By.cssSelector(".auth-bar__item--text"));
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		button.click();

		WebElement text = driver.findElement(By.cssSelector(".auth-form__title.auth-form__title_big.auth-form__title_condensed-default"));
		Assert.assertEquals(text.getText(), "Вход");

		driver.quit();
	}

	@Test
	public void TestEditaOrlovaOnlinerCart() {
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.onliner.by/");
		WebElement button = driver.findElement(By.cssSelector("a[title='Корзина']"));
		button.click();

		WebElement title = driver.findElement(By.cssSelector("div.cart-form__title"));

		Assert.assertEquals(title.getText(), "Корзина");
		driver.quit();
	}

	@Test
	public void test99Bottels() {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://www.99-bottles-of-beer.net/");
			driver.findElement(By.xpath("(//a[text()='Top Lists'])[1]")).click();

			driver.findElement(By.xpath("//ul[@id='submenu']//a[text() = 'Top Rated Esoteric']")).click();
			WebElement h2SubmenuTopEsoteric = driver.findElement(By.xpath("//div[@id='main']/h2"));
			Assert.assertEquals(h2SubmenuTopEsoteric.getText(), "Top Rated Esoteric Languages");
		} finally {
			driver.quit();
		}
	}


	@Test
	public void testEkaterinaTsymbal() {
		WebDriver driver = new ChromeDriver();
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
			driver.get("https://aqa-proka4.org/sandbox/web");

			WebElement textBox = driver.findElement(By.xpath("//*[@id='username']"));
			textBox.sendKeys("test");

			WebElement textBox1 = driver.findElement(By.xpath("//*[@id='email']"));
			textBox1.sendKeys("test@example.com");

			WebElement textBox2 = driver.findElement(By.xpath("//*[@id='password']"));
			textBox2.sendKeys("12345");

			WebElement element = driver.findElement(By.xpath("//*[@id='country']"));
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText("Russia");

			WebElement checkBox = driver.findElement(By.xpath("//*[@id='terms']"));
			checkBox.click();

			WebElement button = driver.findElement(By.xpath("//*[@id='submitBtn']"));
			button.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id("formResult")
			));

			WebElement messageParagraph = message.findElement(By.tagName("p"));
			Assert.assertEquals(
					messageParagraph.getText(),
					"Форма успешно отправлена!",
					"Ожидалось сообщение 'Форма успешно отправлена!', но получено: " + messageParagraph.getText()
			);

		} finally {
			driver.quit();
		}

	}
}
