package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import model.Category;
import model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestAssuredTest {

    @BeforeClass
    public void init() {
        //RestAssured.authentication = RestAssured.oauth2("special-key");
    }

    @Test(priority = 1)
    public void testNotFoundPetAPI() {

        String notFound = RestAssured.get("https://petstore.swagger.io/v2/pet/9177765").asString();
        assertThat(notFound, equalTo("{\"code\":1,\"type\":\"error\",\"message\":\"Pet not found\"}"));
    }

    @Test(priority = 2)
    public void testSendAndFoundPetApi() throws InterruptedException {

        Pet pet = new Pet().withName("petName").withStatus("available").withCategory(new Category().withId(1).withName("categoryName"));
        Gson gson = new GsonBuilder().create();
        String petJson = gson.toJson(pet);

        String petToSend = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(petJson)
                .post("https://petstore.swagger.io/v2/pet").asString();

        Pet receivedPet = gson.fromJson(petToSend, Pet.class);

        assertThat(pet.getName(), equalTo(receivedPet.getName()));
        assertThat(pet.getStatus(), equalTo(receivedPet.getStatus()));
        assertThat(pet.getCategory(), equalTo(receivedPet.getCategory()));

        Thread.sleep(10000);
        //Test will find by id previous send pet
        String foundPet = RestAssured.get("https://petstore.swagger.io/v2/pet/" + receivedPet.getId()).asString();

        //TODO needed verify that test sometimes gives null id
        //assertThat(gson.fromJson(foundPet, Pet.class).getId(), equalTo(receivedPet.getId()));
    }
}
