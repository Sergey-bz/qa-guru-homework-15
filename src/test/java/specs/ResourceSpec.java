package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ResourceSpec {

    private static final String BASE_URI = "https://reqres.in/";

    public static RequestSpecification singleResourceRequestSpec =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setBasePath("api/unknown/{resourceId}")
                    .build();

    public static RequestSpecification resourcesListRequestSpec =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setBasePath("api/unknown")
                    .build();
}
