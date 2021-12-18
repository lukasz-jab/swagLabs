package tests;

import com.google.common.collect.Ordering;
import model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsFilterTest extends TestBase {

    @Test(priority = 1)
    public void precondition() {
        if (!app.session().is_logged()) {
            app.session().login("standard_user");
        }
    }

    @Test(priority = 2)
    public void testPriceLowToHigh() throws InterruptedException {
        app.filter().selectFilterMethod("Price (low to high)");
        List<Product> products = app.product().getProducts();
        // String price from website transformed into value for comparing:
        List<Double> productsPriceList = products.stream().map((o) -> Double.parseDouble(o.getPrice())).collect(Collectors.toList());

        // Test should check that products are ordered from low to high price
        Assert.assertTrue(Ordering.natural().isOrdered(productsPriceList));
    }

    @Test(priority = 3)
    public void testPriceHighToLow() throws InterruptedException {
        app.filter().selectFilterMethod("Price (high to low)");
        List<Product> products = app.product().getProducts();
        // String price from website transformed into value for comparing:
        List<Double> productsPriceList = products.stream().map((o) -> Double.parseDouble(o.getPrice())).collect(Collectors.toList());

        // Test should check that products are ordered from high to low price
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(productsPriceList));
    }

    @Test(priority = 4)
    public void testPriceFromAToZ() throws InterruptedException {
        app.filter().selectFilterMethod("Name (A to Z)");
        List<Product> products = app.product().getProducts();
        List<String> productsName = products.stream().map((o) -> o.getName()).collect(Collectors.toList());

        // Test should check that products are ordered from A to Z
        Assert.assertTrue(Ordering.natural().isOrdered(productsName));
    }

    @Test(priority = 5)
    public void testPriceFromZToA() throws InterruptedException {
        app.filter().selectFilterMethod("Name (Z to A)");
        List<Product> products = app.product().getProducts();
        List<String> productsName = products.stream().map((o) -> o.getName()).collect(Collectors.toList());

        // Test should check that products are ordered from Z to A
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(productsName));
    }
}
