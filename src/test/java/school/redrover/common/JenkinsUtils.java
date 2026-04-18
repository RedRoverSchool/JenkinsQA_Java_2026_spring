package school.redrover.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;

import java.net.CookieManager;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public final class JenkinsUtils {

    private static final List<String> HEADER_FORM = List.of("Content-Type", "application/x-www-form-urlencoded");

    private static final HttpClient client = HttpClient.newBuilder()
            .cookieHandler(new CookieManager())
            .build();

    private static List<String> headerAuthorization;
    private static List<String> headerCrumb;

    private JenkinsUtils() {
        throw new UnsupportedOperationException();
    }

    private static HttpResponse<String> getHttp(String url, List<String> headerList) {
        return getHttp(url, headerList.toArray(String[]::new));
    }

    private static HttpResponse<String> getHttp(String url, String... headers) {
        try {
            return client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .headers(headers)
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> getHttp(String url) {
        return getHttp(url, getDefaultHeader());
    }

    private static HttpResponse<String> postHttp(String url, String body) {
        try {
            return client.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .headers(getDefaultHeader())
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCrumbAsString() {
        return "%s=%s".formatted(getHeaderCrumb().get(0), getHeaderCrumb().get(1));
    }

    private static List<String> getCrumbFromJson(String json) {
        Map<String, String> jsonMap = new Json().toType(json, HashMap.class);

        return List.of(jsonMap.get("crumbRequestField"), jsonMap.get("crumb"));
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

    private static String[] getDefaultHeader() {
        List<String> result = new ArrayList<>();

        result.addAll(HEADER_FORM);
        result.addAll(getHeaderAuthorization());
        result.addAll(getHeaderCrumb());

        return result.toArray(String[]::new);
    }

    private static List<String> getHeaderAuthorization() {
        if (headerAuthorization == null) {
            headerAuthorization = List.of(
                    "Authorization",
                    "Basic " + Base64.getEncoder().encodeToString((ProjectUtils.getUserName() + ":" + ProjectUtils.getPassword()).getBytes()));
        }

        return headerAuthorization;
    }

    private static List<String> getHeaderCrumb() {
        if (headerCrumb == null) {
            HttpResponse<String> jsonResponse = getHttp(ProjectUtils.getUrl() + "crumbIssuer/api/json", getHeaderAuthorization());
            if (jsonResponse.statusCode() == 403) {
                throw new RuntimeException(String.format("Authorization does not work with user: \"%s\" and password: \"%s\"", ProjectUtils.getUserName(), ProjectUtils.getUserName()));
            } else if (jsonResponse.statusCode() != 200) {
                throw new RuntimeException("Something went wrong while clearing data");
            }

            headerCrumb = getCrumbFromJson(jsonResponse.body());
        }

        return headerCrumb;
    }

    private static String getPage(String uri) {
        HttpResponse<String> page = getHttp(ProjectUtils.getUrl() + uri);
        if (page.statusCode() != 200) {
            throw new RuntimeException("Something went wrong while clearing data");
        }

        return page.body();
    }

    private static void deleteByLink(String link, Set<String> names, String crumb) {
        for (String name : names) {
            postHttp(String.format(ProjectUtils.getUrl() + link, name), crumb);
        }
    }

    private static void resetTheme() {
        String url = ProjectUtils.getUrl() + "user/" + ProjectUtils.getUserName() + "/appearance/configSubmit";
        String jsonPayload = "{\"userProperty0\":{\"theme\":{\"value\":\"0\",\"stapler-class\":\"io.jenkins.plugins.thememanager.none.NoOpThemeManagerFactory\",\"$class\":\"io.jenkins.plugins.thememanager.none.NoOpThemeManagerFactory\"}}}";
        String encodedJson = URLEncoder.encode(jsonPayload, StandardCharsets.UTF_8);
        String body = String.format("%s&json=%s&Submit=Submit&core:apply=true",
                getCrumbAsString(),
                encodedJson);
        postHttp(url, body);
    }

    private static void deleteJobs() {
        String mainPage = getPage("");
        deleteByLink("job/%s/doDelete",
                getSubstringsFromPage(mainPage, "href=\"job/", "/\""),
                getCrumbAsString());
    }

    private static void deleteViews() {
        String mainPage = getPage("");
        deleteByLink("view/%s/doDelete",
                getSubstringsFromPage(mainPage, "href=\"/view/", "/\""),
                getCrumbAsString());

        String viewPage = getPage("me/my-views/view/all/");
        deleteByLink("user/admin/my-views/view/%s/doDelete",
                getSubstringsFromPage(viewPage, "href=\"/user/admin/my-views/view/", "/\""),
                getCrumbAsString());
    }

    private static void deleteUsers() {
        String userPage = getPage("manage/securityRealm/");
        deleteByLink("manage/securityRealm/user/%s/doDelete",
                getSubstringsFromPage(userPage, "href=\"user/", "/\"").stream()
                        .filter(user -> !user.equals(ProjectUtils.getUserName())).collect(Collectors.toSet()),
                getCrumbAsString());
    }

    private static void deleteNodes() {
        String mainPage = getPage("computer/");
        Set<String> nodes = getSubstringsFromPage(mainPage, "href=\"../computer/", "/\" ");
        nodes.remove("(built-in)");
        deleteByLink("manage/computer/%s/doDelete",
                nodes,
                getCrumbAsString());
    }

    private static void deleteDescription(String uri) {
        String mainPage = getPage("");
        postHttp(ProjectUtils.getUrl() + uri,
                String.format(
                        "description=&Submit=&%1$s&json=%%7B%%22description%%22%%3A+%%22%%22%%2C+%%22Submit%%22%%3A+%%22%%22%%2C+%%22Jenkins-Crumb%%22%%3A+%%22%1$s%%22%%7D",
                        getCrumbAsString()));
    }

    private static void deleteMainDescription() {
        JenkinsUtils.deleteDescription("submitDescription");
    }

    private static void deleteViewDescription() {
        JenkinsUtils.deleteDescription("me/my-views/view/all/submitDescription");
    }

    private static void deleteDomains() {
        String systemPage = getPage("manage/credentials/store/system/");
        deleteByLink("manage/credentials/store/system/domain/%s/doDelete",
                getSubstringsFromPage(systemPage, "<a href=\"domain/", "\" class"),
                getCrumbAsString());

//        postHttp(ProjectUtils.getUrl() + "user/admin/credentials/store/user/domain/_/doDelete",
//                String.format("Jenkins-Crumb=%s", getCrumbFromPage(systemPage)));
    }

    private static void deleteSystemMessage() {
        postHttp(ProjectUtils.getUrl() + "manage/configSubmit",
                String.format(
                        "system_message=&%1$s&json=%%7B%%22system_message%%22%%3A%%22%%22%%2C%%22Jenkins-Crumb%%22%%3A%%22%1$s%%22%%7D",
                        getCrumbAsString()));
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
}

