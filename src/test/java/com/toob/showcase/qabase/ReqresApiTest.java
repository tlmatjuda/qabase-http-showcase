package com.toob.showcase.qabase;

import com.toob.qabase.http.AbstractHttpTest;
import com.toob.qabase.http.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Epic("REQRES API")
@Feature("User Operations API")
@SpringBootTest
class ReqresApiTest extends AbstractHttpTest {

    @Test
    @Story("Fetch Single User")
    void shouldGetSingleUserById() {
        final String id = "2";

        Response response = step( String.format("Fetch User By Id : %s", id), () -> {
            Response resp = RestClient.get(String.format("/api/user/%s", id));
            assertNotNull(resp);
            return resp;
        });

        confirmStatusCode(200, response);

        step("Verify specific response properties", () -> {
            response.then().body("data.id", equalTo(2))
                    .body("data.name", equalTo("fuchsia rose"))
                    .body("data.year", equalTo(2001))
                    .body("data.color", equalTo("#C74375"))
                    .body("data.pantone_value", equalTo("17-2031"));
        });

        step("Validate response contains at least one User record", () -> {
            response.then().body("size()", greaterThan(0));
        });

        attachHttpResponse(response);
    }
}
