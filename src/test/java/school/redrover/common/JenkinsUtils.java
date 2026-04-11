package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public final class JenkinsUtils {

    private static final HttpClient client = HttpClient.newBuilder()
            .cookieHandler(new java.net.CookieManager())
            .build();

    private JenkinsUtils() {
        throw new UnsupportedOperationException();
    }

    private static String getAuthHeader() {
        String token = ProjectUtils.getCachedToken();

        String secret = (token != null) ? token : ProjectUtils.getPassword();

        return "Basic " + Base64.getEncoder().encodeToString(
                (ProjectUtils.getUserName() + ":" + secret).getBytes(StandardCharsets.UTF_8));
    }

    private static String[] fetchCrumb() {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(ProjectUtils.getUrl() + "crumbIssuer/api/json"))
                    .header("Authorization", getAuthHeader())
                    .GET().build();
            String body = client.send(req, HttpResponse.BodyHandlers.ofString()).body();

            String field = getSubstringsFromPage(body, "\"crumbRequestField\":\"", "\"").iterator().next();
            String value = getSubstringsFromPage(body, "\"crumb\":\"", "\"").iterator().next();
            return new String[]{field, value};
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Jenkins Crumb", e);
        }
    }

    private static Set<String> getSubstringsFromPage(String page, String from, String to) {
        // 255 - максимально возможная длинна имени, но если используется не латиница или специальные символы, строка будет длинней из-за кодирования (пробел - %20)
        return getSubstringsFromPage(page, from, to, 256);
    }

    private static Set<String> getSubstringsFromPage(String page, String from, String to, int maxSubstringLength) {
        Set<String> result = new HashSet<>();

        int index = page.indexOf(from);
        while (index != -1) {
            index += from.length();
            int endIndex = page.indexOf(to, index);

            if (endIndex != -1 && endIndex - index < maxSubstringLength) {
                result.add(page.substring(index, endIndex));
            } else {
                endIndex = index;
            }

            index = page.indexOf(from, endIndex);
        }

        return result;
    }

    private static HttpResponse<String> getHttp(String url, String... headers) {
        try {
            return client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header("Authorization", getAuthHeader())
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> postHttp(String url, String body) {
        String[] crumb = fetchCrumb();
        try {
            return client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .header("Authorization", getAuthHeader())
                            .header(crumb[0], crumb[1])
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPage(String uri) {
        HttpResponse<String> page = getHttp(ProjectUtils.getUrl() + uri);
        if (page.statusCode() == 403) {
            String status = (ProjectUtils.getCachedToken() != null) ? "TOKEN" : "PASSWORD";
            throw new RuntimeException("403 Forbidden: Auth failed using " + status);
        }
        if (page.statusCode() != 200) {
            throw new RuntimeException("HTTP Error: " + page.statusCode() + " for " + uri);
        }

        return page.body();
    }

    private static void deleteByLink(String link, Set<String> names) {
        for (String name : names) {
            postHttp(String.format(ProjectUtils.getUrl() + link, name), "");
        }
    }

    private static void resetTheme() {
        String url = ProjectUtils.getUrl() + "user/" + ProjectUtils.getUserName() + "/appearance/configSubmit";
        String jsonPayload = "{\"userProperty0\":{\"theme\":{\"value\":\"0\",\"stapler-class\":\"io.jenkins.plugins.thememanager.none.NoOpThemeManagerFactory\",\"$class\":\"io.jenkins.plugins.thememanager.none.NoOpThemeManagerFactory\"}}}";
        String encodedJson = URLEncoder.encode(jsonPayload, StandardCharsets.UTF_8);
        String body = String.format("json=%s&Submit=Submit&core:apply=true", encodedJson);
        postHttp(url, body);
    }

    private static void deleteJobs() {
        String mainPage = getPage("");
        deleteByLink("job/%s/doDelete",
                getSubstringsFromPage(mainPage, "href=\"job/", "/\""));
    }

    private static void deleteViews() {
        String mainPage = getPage("");
        deleteByLink("view/%s/doDelete",
                getSubstringsFromPage(mainPage, "href=\"/view/", "/\""));

        String viewPage = getPage("me/my-views/view/all/");
        deleteByLink("user/admin/my-views/view/%s/doDelete",
                getSubstringsFromPage(viewPage, "href=\"/user/admin/my-views/view/", "/\""));
    }

    private static void deleteUsers() {
        String userPage = getPage("manage/securityRealm/");
        deleteByLink("manage/securityRealm/user/%s/doDelete",
                getSubstringsFromPage(userPage, "href=\"user/", "/\"").stream()
                        .filter(user -> !user.equals(ProjectUtils.getUserName())).collect(Collectors.toSet()));
    }

    private static void deleteNodes() {
        String mainPage = getPage("computer/");
        Set<String> nodes = getSubstringsFromPage(mainPage, "href=\"../computer/", "/\" ");
        nodes.remove("(built-in)");
        deleteByLink("manage/computer/%s/doDelete",
                nodes);
    }

    private static void deleteDescription(String uri) {
        String body = "description=&Submit=&json=%7B%22description%22%3A%22%22%2C%22Submit%22%3A%22%22%7D";

        postHttp(ProjectUtils.getUrl() + uri, body);
    }

    private static void deleteMainDescription() {
        deleteDescription("submitDescription");
    }

    private static void deleteViewDescription() {
        deleteDescription("me/my-views/view/all/submitDescription");
    }

    private static void deleteDomains() {
        String systemPage = getPage("manage/credentials/store/system/");
        deleteByLink("manage/credentials/store/system/domain/%s/doDelete",
                getSubstringsFromPage(systemPage, "<a href=\"domain/", "\" class"));
    }

    private static void deleteSystemMessage() {
        String url = ProjectUtils.getUrl() + "manage/configSubmit";
        String body = "system_message=&json=%7B%22system_message%22%3A%22%22%7D";

        postHttp(url, body);
    }

    static void clearData() {
        JenkinsUtils.deleteViews();
        JenkinsUtils.deleteJobs();
        JenkinsUtils.deleteUsers();
        JenkinsUtils.deleteNodes();
        JenkinsUtils.deleteMainDescription();
        JenkinsUtils.deleteViewDescription();
        JenkinsUtils.deleteSystemMessage();
        JenkinsUtils.deleteDomains();
        JenkinsUtils.resetTheme();
    }

    public static void login(WebDriver driver, String userName, String password) {
        driver.findElement(By.name("j_username")).sendKeys(userName);
        driver.findElement(By.name("j_password")).sendKeys(password);
        driver.findElement(By.name("Submit")).click();
    }

    public static void login(WebDriver driver) {
        login(driver, ProjectUtils.getUserName(), ProjectUtils.getPassword());
    }

    public static void logout(WebDriver driver) {
        driver.get(ProjectUtils.getUrl() + "logout");
    }

    static String generateApiToken() {
        String url = ProjectUtils.getUrl() + "me/descriptorByName/jenkins.security.ApiTokenProperty/generateNewToken";
        String body = "newTokenName=" + URLEncoder.encode("Automation_Dynamic_Token", StandardCharsets.UTF_8);
        HttpResponse<String> res = postHttp(url, body);

        return getSubstringsFromPage(res.body(), "\"tokenValue\":\"", "\"").iterator().next();
    }

    static void revokeTokens() {
        postHttp(ProjectUtils.getUrl() + "me/descriptorByName/jenkins.security.ApiTokenProperty/revokeAll", "");
    }
}
