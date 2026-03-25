import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StudyTimeGroupTest {

    @Test
    public void testDownloadPageSecondLevelHeaders() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.jenkins.io/download/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<WebElement> headers = driver.findElements(By.xpath("//h2"));

        List<String> actualHeadersText = new ArrayList<>();

        for (WebElement header : headers) {
            actualHeadersText.add(header.getText());
        }

        List<String> expectedHeadersText = List.of("Downloading Jenkins", "Deploying Jenkins in public cloud");
        Assert.assertEquals(actualHeadersText, expectedHeadersText, "The expected list of second-level headers does not match the reference list.");

        driver.quit();
    }

    @Test
    public void testDocPageJenkinsCodeOfConduct() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.jenkins.io/doc/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement shadowHost = driver.findElement(By.xpath("//*[@id='ji-toolbar']"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        WebElement hiddenBtnAbout = shadowRoot.findElement(By.cssSelector("button[data-idx='8']"));
        js.executeScript("arguments[0].click();", hiddenBtnAbout);

        WebElement shadowSecondHost = shadowRoot.findElement(By.cssSelector("jio-navbar-link[href='/project/conduct/']"));
        SearchContext shadowSecondRoot = shadowSecondHost.getShadowRoot();
        WebElement conductLink = shadowSecondRoot.findElement(By.cssSelector("a.nav-link"));
        js.executeScript("arguments[0].click();", conductLink);

        Assert.assertEquals(driver.getTitle(), "Jenkins Code of Conduct");

        driver.quit();
    }

    @Test
    public void testPluginsPageSearchCli() {
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.get("https://plugins.jenkins.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement inpFindPlugins = driver.findElement(By.xpath("//input[@name='query']"));
        actions.moveToElement(inpFindPlugins)
                .click()
                .sendKeys("qwertyuiop")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        WebElement txtNoResultsFound = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='no-results']/p")));

        Assert.assertEquals(txtNoResultsFound.getText(), "You search did not return any results. Please try changing your search criteria or reloading the browser.");

        driver.quit();
    }

    @Test
    public void FurnitureStoreTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://bogatir.online/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebElement authButton = driver.findElement(By.xpath("//*[@class='w-inline-block login-header']"));
        authButton.click();


        WebElement inputEamil = driver.findElement(By.xpath("//*[@id=\"login3\"]"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"password3\"]"));
        inputEamil.sendKeys("cgvngv@ema");
        inputPassword.sendKeys("46564");

        WebElement inputButton = driver.findElement(By.xpath("//form[@id=\"login_form\"] //*[@type=\"submit\"]"));
        inputButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[@class=\"text-not-found\"]//*[@color=\"red\"]"));
        Assert.assertEquals(errorMessage.getText(), "Неверный логин или пароль");

        driver.quit();
    }


    @Test
    public void testContribPage() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://contributors.jenkins.io/");

        List<WebElement> weContributorNames = driver.findElements(By.xpath("//h3"));
        List<String> contributorNames = new ArrayList<>();

        for (WebElement contributorName : weContributorNames) {
            contributorNames.add(contributorName.getText());
        }

        Assert.assertTrue(contributorNames.contains("Bruno Verachten"));

        driver.quit();
    }

    @Test
    public void testChangelogPage() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.jenkins.io/download/");

        WebElement lnkChangelogStable = driver.findElement(By.xpath("//a[@href='/changelog-stable']"));
        lnkChangelogStable.click();

        List<WebElement> headersThirdLevel = driver.findElements(By.xpath("//h3"));

        List<String> jenkinsVersions = new ArrayList<>();

        for (WebElement headerThirdLevel : headersThirdLevel) {
            jenkinsVersions.add(headerThirdLevel.getText());
        }

        List<String> expectedResults = new ArrayList<>();
        String minJenkinsVersion = jenkinsVersions.getLast();
        String maxJenkinsVersion = jenkinsVersions.getFirst();
        expectedResults.add(Integer.toString(jenkinsVersions.size()));
        expectedResults.add(minJenkinsVersion);
        expectedResults.add(maxJenkinsVersion);

        List<String> actualResults = List.of("25", "2.452.1", "2.541.3");

        Assert.assertEquals(actualResults, expectedResults, "The list length or version numbers are not as expected.");

        driver.quit();
    }
}
