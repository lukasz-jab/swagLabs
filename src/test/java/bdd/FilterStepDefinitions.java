package bdd;

import appmanager.ApplicationManager;
import com.google.common.collect.Ordering;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.Product;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FilterStepDefinitions {

    private ApplicationManager app;

    @Before
    public void init() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @After
    public void stop() {
        app.stop();
        app = null;
    }

    @Given("^open product page as (.+)$")
    public void productPage(String user) throws InterruptedException {
        app.session().login(user);
    }

    @When("^I choose filter from (.+)$")
    public void chooseFilter(String filterName) {
        app.filter().selectFilterMethod(filterName);
    }

    @Then("^Products are sorted from (.+)$")
    public void verifyProductsFromLowerToHighestPrice(String filterName) throws InterruptedException {

        if (filterName.equals("Name (A to Z)")) {
            List<Product> products = app.product().getProducts();
            List<String> productsName = products.stream().map((o) -> o.getName()).collect(Collectors.toList());

            Assert.assertTrue(Ordering.natural().isOrdered(productsName));

        } else if (filterName.equals("Name (Z to A)")) {
            List<Product> products = app.product().getProducts();
            List<String> productsName = products.stream().map((o) -> o.getName()).collect(Collectors.toList());

            Assert.assertTrue(Ordering.natural().reverse().isOrdered(productsName));

        } else if (filterName.equals("Price (low to high)")) {
            List<Product> products = app.product().getProducts();
            // String price from website transformed into value for comparing:
            List<Double> productsPriceList = products.stream().map((o) -> Double.parseDouble(o.getPrice())).collect(Collectors.toList());

            Assert.assertTrue(Ordering.natural().isOrdered(productsPriceList));

        } else if (filterName.equals("Price (high to low")) {
            List<Product> products = app.product().getProducts();
            // String price from website transformed into value for comparing:
            List<Double> productsPriceList = products.stream().map((o) -> Double.parseDouble(o.getPrice())).collect(Collectors.toList());

            // Test should check that products are ordered from high to low price
            Assert.assertTrue(Ordering.natural().reverse().isOrdered(productsPriceList));

        }
    }
}
