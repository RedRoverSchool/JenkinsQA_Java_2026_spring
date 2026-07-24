package school.redrover;


import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.ProjectUtils;
import school.redrover.common.api.JobInfo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APITest {
    String BASE_URL = ProjectUtils.getUrl();
    String JOB_NAME = "apiFreestyle";

    private final RequestSpecification jenkinsSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setAuth(RestAssured.preemptive().basic(ProjectUtils.getUserName(), ProjectUtils.getPassword()))
            .build();

    private static final class Pokemon {
        private final String name;
        private final String url;

        Pokemon(String name, String url) {
            this.name = name;
            this.url = url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pokemon pokemon = (Pokemon) o;
            return Objects.equals(name, pokemon.name) && Objects.equals(url, pokemon.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, url);
        }
    }

    private static final class Pokemons {
        private int count;
        private String previous;
        private String next;
        private List<Pokemon> results;
    }

    @Test
    public void httpTest() throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://pokeapi.co/api/v2/pokemon"))
                .headers(HttpHeaders.USER_AGENT, "Googlebot")
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(httpResponse.statusCode(), 200);

        String body = httpResponse.body();
        Assert.assertNotNull(body);

        // simple check
        Assert.assertTrue(body.startsWith("{\"count\":1351"));

        // regular check
        Pokemons pokemons = new Gson().fromJson(body, Pokemons.class);
        Assert.assertEquals(pokemons.count, 1351);
        Assert.assertNull(pokemons.previous);
        Assert.assertEquals(pokemons.next, "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20");
        Assert.assertEquals(pokemons.results.size(), 20);
        Assert.assertEquals(
                pokemons.results.get(0),
                new Pokemon("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"));
    }

    @Test
    public void restAssuredTest() {
        RestAssured
        .when()
                .get("https://pokeapi.co/api/v2/pokemon")
        .then()
                .statusCode(200)
                .body("count", Matchers.equalTo(1351),
                        "results.name", Matchers.hasItems("bulbasaur", "ivysaur"));
    }

    @Test
    public void testCheckJenkinsIsRunning() {
        given()
                .spec(jenkinsSpec)
        .when()
                .get("/api/json")
        .then()
                .statusCode(200)
                .body("mode", Matchers.equalTo("NORMAL"));
    }

    private Response getCrumbResponse() {
        return given()
                .spec(jenkinsSpec)
        .when()
                .get("/crumbIssuer/api/json");
    }

    @Test
    public void testCreateJob() {
        Response crumbresponse = getCrumbResponse();
        String crumb = crumbresponse.jsonPath().getString("crumb");
        String crumbRequestField = crumbresponse.jsonPath().getString("crumbRequestField");
        File configFile = new File("src/test/resources/api/job-config.xml");

        given()
                .spec(jenkinsSpec)
                .cookies(crumbresponse.getCookies())
                .header("Content-Type", "application/xml")
                .header (crumbRequestField, crumb)
                .body (configFile)
                .queryParam("name", JOB_NAME)
        .when()
                .post("/createItem")
        .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "testCreateJob")
    public void testJobCreated(){
        given()
                .spec(jenkinsSpec)
        .when()
                .get("/api/json")
        .then()
                .statusCode(200)
                .body("jobs.name", Matchers.hasItem(JOB_NAME));

    }

    @Test(dependsOnMethods = "testJobCreated")
    public void testJobInfoPOJO(){
        JobInfo job = given()
                .spec(jenkinsSpec)
        .when()
                .get("/job/%s/api/json".formatted(JOB_NAME))
        .then()
                .statusCode(200)
                .extract().as(JobInfo.class);

        Assert.assertEquals(job.getName(), JOB_NAME);
        Assert.assertEquals(job.getDescription(), "");

    }

    @Test(dependsOnMethods = "testJobInfoPOJO")
        public void testJobSchemaValidation() {
        given()
                .spec(jenkinsSpec)
        .when()
                .get("/job/%s/api/json".formatted(JOB_NAME))
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("api/job-schema.json"));
    }

    @Test(dependsOnMethods = "testJobSchemaValidation")
    public void testJobDelete(){
        Response crumbresponse = getCrumbResponse();
        String crumb = crumbresponse.jsonPath().getString("crumb");
        String crumbRequestField = crumbresponse.jsonPath().getString("crumbRequestField");

        given()
                .spec(jenkinsSpec)
                .cookies(crumbresponse.getCookies())
                .header("Content-Type", "application/xml")
                .header (crumbRequestField, crumb)
        .when()
                .post("/job/%s/doDelete".formatted(JOB_NAME))
        .then()
                .statusCode(302);

        given()
                .spec(jenkinsSpec)
        .when()
                .get("/job/%s/api/json".formatted(JOB_NAME))
        .then()
                .statusCode(404);
    }
}
