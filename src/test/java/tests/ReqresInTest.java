package tests;

import io.restassured.response.Response;
import models.ResourceData;
import models.SingleResource;
import models.SingleUser;
import models.UserData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static specs.ResourceSpec.resourcesListRequestSpec;
import static specs.ResourceSpec.singleResourceRequestSpec;
import static specs.UserSpecs.createUserRequestSpec;
import static specs.UserSpecs.singleUserRequestSpec;

public class ReqresInTest {

    @Test
    public void getSingleUserTest() {
        int userId = 2;
        UserData user = given()
                .spec(singleUserRequestSpec)
                .pathParam("userId", userId)
                .when()
                .get()
                .then()
                .extract()
                .body()
                .as(SingleUser.class)
                .getData();

        assertThat(user.getId(), equalTo(2));
        assertThat(user.getEmail(), equalTo("janet.weaver@reqres.in"));
        assertThat(user.getFirstName(), equalTo("Janet"));
        assertThat(user.getLastName(), equalTo("Weaver"));
        assertThat(user.getAvatar(), equalTo("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Test
    public void singleUserNotFoundTest() {
        String userId = RandomStringUtils.random(5);
        Response response = given()
                .spec(singleUserRequestSpec)
                .pathParam("userId", userId)
                .get();

        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.body().asString(), equalTo("{}"));
    }

    @Test
    public void createUserTest() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "morpheus");
        body.put("job", "leader");

        Response response = given()
                .spec(createUserRequestSpec)
                .body(body)
                .when()
                .post();

        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.body().path("name"), equalTo("morpheus"));
        assertThat(response.body().path("job"), equalTo("leader"));
        assertThat(response.body().path("id"), not(emptyOrNullString()));
        assertThat(response.body().path("createdAt"), not(emptyOrNullString()));
    }

    @Test
    public void getSingleResourceTest() {
        int resourceId = 2;
        ResourceData resource = given()
                .spec(singleResourceRequestSpec)
                .pathParam("resourceId", resourceId)
                .when()
                .get()
                .then()
                .extract()
                .body()
                .as(SingleResource.class)
                .getData();

        assertThat(resource.getId(), equalTo(2));
        assertThat(resource.getName(), equalTo("fuchsia rose"));
        assertThat(resource.getYear(), equalTo(2001));
        assertThat(resource.getColor(), equalTo("#C74375"));
        assertThat(resource.getPantoneValue(), equalTo("17-2031"));
    }

    @Test
    public void getResourcesListTest() {
        Response response = given()
                .spec(resourcesListRequestSpec)
                .get();

        assertThat(response.body().path("data.id"), everyItem(notNullValue()));
        assertThat(response.body().path("data.name"), everyItem(not(emptyOrNullString())));
        assertThat(response.body().path("data.year"), everyItem(notNullValue()));
        assertThat(response.body().path("data.color"), everyItem(not(emptyOrNullString())));
        assertThat(response.body().path("data.pantone_value"), everyItem(not(emptyOrNullString())));
    }
}
