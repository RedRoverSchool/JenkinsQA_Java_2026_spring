package school.redrover.common;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private WebDriver driver;

    private void startDriver() {
        ProjectUtils.log("Browser open");
        driver = ProjectUtils.createDriver();
    }

    private void clearData() {
        ProjectUtils.log("Clear data");
        JenkinsUtils.clearData();
    }

    private void loginWeb() {
        ProjectUtils.log("Login");
        JenkinsUtils.login(getDriver());
    }

    private void getWeb() {
        ProjectUtils.log("Get web page");
        ProjectUtils.get(getDriver());
    }

    private void stopDriver() {
        JenkinsUtils.logout(getDriver());
        closeDriver();
    }

    private void closeDriver() {
        if (driver != null) {
            driver.quit();

            driver = null;

            ProjectUtils.log("Browser closed");
        }
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        ProjectUtils.log("Run %s.%s", this.getClass().getName(), method.getName());

        clearData();
        startDriver();
        getWeb();
        loginWeb();
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (ProjectUtils.isRunCI() || testResult.isSuccess() || ProjectUtils.closeIfError()) {
            stopDriver();
        }

        ProjectUtils.log("Execution time is %.3f sec", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000.0);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
