package com.toob.showcase.qabase;

import com.toob.qabase.http.AbstractHttpTest;
import com.toob.qabase.http.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.toob.qabase.http.support.HttpSupport.attachHttpResponse;
import static com.toob.qabase.http.support.HttpSupport.confirmStatusCode;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Epic("REQRES API")
@Feature("User Operations API")
@SpringBootTest
class ReqresApiTest extends AbstractHttpTest {

    @Test
    @Story("Fetch Single User")
    void shouldGetSingleUser() {
        final String id = "2";

        Response response = step( String.format("Fetch User By Id : %s", id), () -> {
            Response internalResponse = RestClient.get(String.format("/api/user/%s", id));
            assertNotNull(internalResponse);
            return internalResponse;
        });

        confirmStatusCode(200, response);

        step("Validate response contains at least one TODO item", () -> {
            response.then().body("size()", greaterThan(0));
        });

        attachHttpResponse(response);
    }
}
