package tests;

import model.Product;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest extends TestBase {

    @Test(priority = 1)
    public void precondition() {
        if (!app.session().is_logged()) {
            app.session().login("standard_user");
        }
    }

    @Test(priority = 2)
    public void testTypicalCartOperations() throws InterruptedException {
        List<Product> productsMainPage = app.product().getProducts();
        app.product().addToChart(1);
        app.product().addToChart(2);
        app.goTo().openChart();
        List<Product> productsCartPage = app.cart().getCartProducts();

        // Assertions for checking that products added to cart appeared in cart page
        assertThat(productsMainPage.get(0).getId(), equalTo(productsCartPage.get(0).getId()));
        assertThat(productsMainPage.get(0).getPrice(), equalTo(productsCartPage.get(0).getPrice()));
        assertThat(productsMainPage.get(0).getName(), equalTo(productsCartPage.get(0).getName()));
        assertThat(productsMainPage.get(0).getDescription(), equalTo(productsCartPage.get(0).getDescription()));

        //Products appear on cart page in column order
        assertThat(productsMainPage.get(2).getId(), equalTo(productsCartPage.get(1).getId()));
        assertThat(productsMainPage.get(2).getPrice(), equalTo(productsCartPage.get(1).getPrice()));
        assertThat(productsMainPage.get(2).getName(), equalTo(productsCartPage.get(1).getName()));
        assertThat(productsMainPage.get(2).getDescription(), equalTo(productsCartPage.get(1).getDescription()));

        app.cart().checkOut();
        app.cart().fillPersonalData();

        String totalNetto = app.cart().getStepTwoNettoValue();
        String totalBrutto = app.cart().getStepTwoTotalValue();

        //Assertion for check, that products sum is equal to totalNetto
        assertThat(new BigDecimal(totalNetto), equalTo(new BigDecimal(productsCartPage.get(0).getPrice()).add(new BigDecimal(productsCartPage.get(1).getPrice()))));

        // Assertion for check, that product price is with Tax
        assertThat(new BigDecimal(totalBrutto), equalTo(new BigDecimal(totalNetto).multiply(new BigDecimal("1.08"))
                .setScale(2, RoundingMode.HALF_UP)));

        app.cart().finish();

        assertThat("THANK YOU FOR YOUR ORDER", containsString(app.cart().H2CheckoutComplete()));

        app.goTo().openChart();

        //Assertions for check, that cart is empty
        assertThat(0, equalTo(app.cart().getCartProducts().size()));

        app.cart().continueShopping();
    }
}
