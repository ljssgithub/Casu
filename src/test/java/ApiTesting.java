import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class ApiTesting {

    /*
        I wrote 4 test cases using java, bdd , rest-assured and testng.
        1 - verifyStatusCode200 =  Verify if the application is working fine
        2 - verifyNotCrashing = Validate that the application do not crash
        3 - verifyRightResult =  Verify if the application is returning the right result
        4 - verifyNoVowels =  Verify that there are no vowels in the response.
    */
    public static final String DEFAULT_URI = "http://localhost:8080";

    @Test
    public void verifyStatusCode200(){
        given()
                .baseUri(DEFAULT_URI)
                .contentType(ContentType.JSON)
                .when()
                .get("/lucas/").
                then().assertThat()
                .statusCode(200);
    }

    @Test
    public void verifyNotCrashing(){
        given()
                .baseUri(DEFAULT_URI)
                .contentType(ContentType.JSON)
                .when()
                // I'm going to send an empty request to validate that the application do not crash with error 500
                .get("//").
                then().assertThat()
                .statusCode(200);
    }

    @Test
    public void verifyRightResult(){
        String responseBody = given()
                .baseUri(DEFAULT_URI)
                .contentType(ContentType.JSON)
                .when()
                .get("/lcs/").getBody().asString();
        Assert.assertTrue(responseBody.contains("lcs"));
    }

    @Test
    public void verifyNoVowels(){
        String responseBody = given()
                .baseUri(DEFAULT_URI)
                .contentType(ContentType.JSON)
                .when()
                .get("/lucas/").getBody().asString();
        // Solution using assertFalse to be sure that there are no vowels in the response
        Assert.assertFalse(responseBody.contains("a"));
        Assert.assertFalse(responseBody.contains("e"));
        Assert.assertFalse(responseBody.contains("i"));
        Assert.assertFalse(responseBody.contains("o"));
        Assert.assertFalse(responseBody.contains("u"));
    }

}
