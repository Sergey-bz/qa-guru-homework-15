package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UserSpecs {

    private static final String BASE_URI = "https://reqres.in/";

    public static RequestSpecification singleUserRequestSpec =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setBasePath("api/users/{userId}")
                    .build();

    public static RequestSpecification createUserRequestSpec =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setBasePath("api/users")
                    .setContentType(ContentType.JSON)
                    .build();
}
