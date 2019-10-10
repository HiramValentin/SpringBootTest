package com.ang.test.offer;

import com.ang.test.offer.dto.OfferDTO;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RestServicesTest {

    @Test
    public void findActiveOffers() {
        OfferDTO[] offers = given().when().get("/offer/list").then().statusCode(HttpStatus.OK.value()).extract().as(OfferDTO[].class);
        assertThat(offers.length).isEqualTo(2);
    }

    @Test
    public void findAllOffers() {
        OfferDTO[] offers = given().when().get("/offer/findAll").then().statusCode(HttpStatus.OK.value()).extract().as(OfferDTO[].class);
        assertThat(offers.length).isEqualTo(8);
    }

    @Test
    public void createOffer() {

        String offer = "{\n" +
                " \n" +
                "\t\"discountPct\" : \"0.1\",\n" +
                "\t\"activeFrom\": \"01/10/2019\",\n" +
                "\t\"activeUntil\": \"63/10/2019\",\n" +
                "  \t\"product\": {\n" +
                "      \"id\":\"5\",\n" +
                "    \t\"name\" : \"Atari\",\n" +
                "  \t\t\"price\" : \"7000\"\n" +
                "\t} \n" +
                "}";

        int status = given().contentType(ContentType.JSON).body(offer).post("/offer/").then().statusCode(HttpStatus.OK.value()).extract().statusCode();
        Assert.assertEquals(200, status);
    }
}
