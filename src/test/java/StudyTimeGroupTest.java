import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;
import java.util.Random;

public class StudyTimeGroupTest {
    private static final Logger logger = Logger.getLogger(StudyTimeGroupTest.class.getName());

    @Test
    public void testDownloadPageSecondLevelHeaders() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.get("https://www.jenkins.io/download/");

            List<WebElement> headers = driver.findElements(By.xpath("//h2"));
            List<String> actualHeadersText = new ArrayList<>();
            for (WebElement header : headers) {
                actualHeadersText.add(header.getText());
            }

            Assert.assertEquals(
                    actualHeadersText,
                    List.of("Downloading Jenkins", "Deploying Jenkins in public cloud"),
                    "The expected list of second-level headers does not match the reference list.");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testDocPageJenkinsCodeOfConduct() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("https://www.jenkins.io/doc/");

            WebElement shadowHost = driver.findElement(By.xpath("//*[@id='ji-toolbar']"));
            SearchContext shadowRoot = shadowHost.getShadowRoot();
            js.executeScript(
                    "arguments[0].click();",
                    shadowRoot.findElement(By.cssSelector("button[data-idx='8']")));

            WebElement shadowSecondHost = shadowRoot.findElement(By.cssSelector("jio-navbar-link[href='/project/conduct/']"));
            SearchContext shadowSecondRoot = shadowSecondHost.getShadowRoot();
            js.executeScript(
                    "arguments[0].click();",
                    shadowSecondRoot.findElement(By.cssSelector("a.nav-link")));

            Assert.assertEquals(driver.getTitle(), "Jenkins Code of Conduct");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testPluginsPageSearchNoResults() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            driver.get("https://plugins.jenkins.io/");

            WebElement inpFindPlugins = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='query']")));
            inpFindPlugins.clear();
            inpFindPlugins.sendKeys("qwertyuiop" + Keys.ENTER);

            WebElement txtNoResultsFound = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='no-results']/p")));

            String actualText = txtNoResultsFound.getText();
            Assert.assertTrue(
                    actualText.contains("did not return any results"),
                    "The error text does not match. Received: " + actualText);

        } finally {
            driver.quit();
        }
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
    public void testMtsTitleCheck() {
        WebDriver driver = new ChromeDriver();


        driver.get("https://www.mts.by/");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement cookieButton = driver.findElement(By.xpath("//button[@class=\"btn btn_black cookie__ok\"]"));
        cookieButton.click();

        Assert.assertEquals(driver.getTitle(), "МТС – мобильный оператор в Беларуси");

        driver.quit();
    }

    @Test
    public void testContribPage() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            driver.get("https://contributors.jenkins.io/");

            List<WebElement> weContributorNames = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3")));
            List<String> contributorNames = new ArrayList<>();
            for (WebElement contributorName : weContributorNames) {
                contributorNames.add(contributorName.getText());
            }

            Assert.assertTrue(
                    contributorNames.contains("Bruno Verachten"),
                    "The list does not contain 'Bruno Verachten'. Names found: " + contributorNames.size() + "/32");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testChangelogPage() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.get("https://www.jenkins.io/download/");

            driver.findElement(By.xpath("//a[@href='/changelog-stable']")).click();

            List<WebElement> headersThirdLevel = driver.findElements(By.xpath("//h3"));
            List<String> jenkinsVersions = new ArrayList<>();
            for (WebElement headerThirdLevel : headersThirdLevel) {
                jenkinsVersions.add(headerThirdLevel.getText());
            }

            List<String> actualResults = new ArrayList<>();
            actualResults.add(Integer.toString(jenkinsVersions.size()));
            actualResults.add(jenkinsVersions.getLast());
            actualResults.add(jenkinsVersions.getFirst());

            Assert.assertEquals(
                    actualResults,
                    List.of("25", "2.452.1", "2.541.3"),
                    "The list length or version numbers are not as expected.");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testBlogPageSocialNetworksOnTopOfArticle() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        try {
            driver.get("https://www.jenkins.io/blog/");

            List<WebElement> cards = driver.findElements(By.xpath("//li[@class='app-card']/a"));
            if (!cards.isEmpty()) {
                int randomIndex = new Random().nextInt(cards.size());
                cards.get(randomIndex).click();
            } else {
                logger.info("No cards found on the 'Blog' page!");
            }

            List<WebElement> socialLinks = driver.findElements(
                    By.xpath("//div[@class='app-social-media-buttons__container share-buttons__container']//li/a"));  // /ul[@class='app-social-media-buttons']
            List<String> socialLinksTooltips = new ArrayList<>();
            for (WebElement socialLink : socialLinks) {
                socialLinksTooltips.add(socialLink.getAttribute("data-tooltip"));
            }

            Assert.assertEquals(
                    socialLinksTooltips,
                    List.of("𝕏 (formerly Twitter)", "LinkedIn", "Mastodon", "Bluesky"),
                    "The list of social links differs from the reference one.");
        } finally {
            driver.quit();
        }
    }

}
