package in.regres;

import data.ResponsePage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Tests {

    private String userEmail = "eve.holt@reqres.in";
    private String userPassword = "cityslicka";
    private String authToken = "QpwL5tke4Pnpja7X4";
    private String userEmailForFailTest = "peter@klaven";

    @Test
    public void checkUsersFromSecondPageDTO() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec());
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .extract().as(ResponsePage.class);
    }

    @Test
    public void checkNameFilesAvatars() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec());
        List<String> fileNameAvatars = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .extract().body().jsonPath().getList("data.avatar");

        for(int i = 0; i < fileNameAvatars.size(); i++) {
            fileNameAvatars.set(i, fileNameAvatars.get(i).replace("https://reqres.in/img/faces/", "")
                    .replace(".jpg", ""));
        }

        for(int i = 1; i < fileNameAvatars.size(); i++) {
            Assert.assertEquals(fileNameAvatars.get(0), fileNameAvatars.get(i));
        }
    }

    @Test
    public void authorisationTest() {
        Specification.installSpecification(Specification.requestSpecForJson());
        Map<String, String> userAccount = new HashMap<>();
        userAccount.put("email", userEmail);
        userAccount.put("password", userPassword);
        Response response = given()
                .body(userAccount)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract()
                .response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("token").toString(), authToken);
    }

    @Test
    public void authorisationTestFail() {
        Specification.installSpecification(Specification.requestSpecForJson());
        Map<String, String> userAccount = new HashMap<>();
        userAccount.put("email", userEmailForFailTest);
        Response response = given()
                .body(userAccount)
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .extract()
                .response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.get("error").toString(), "Missing password");
    }

    @Test
    public void sortedListResources() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpec());
        List<Integer> dataYears = given()
                .when()
                .get("/api/unknown")
                .then()
                .extract().body().jsonPath().getList("data.year");

        for(int i = 1; i < dataYears.size(); i++) {
            Assert.assertTrue(dataYears.get(i-1) < dataYears.get(i));
        }
    }

}
