package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartHelper extends HelperBase {
    private final WebDriverWait wait;

    public CartHelper(WebDriver wd, WebDriverWait wait) {
        super(wd);
        this.wait = wait;
    }

    public List<Product> getCartProducts() throws InterruptedException {
        //wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.cart_list div.cart_item"),1));
        Thread.sleep(3000);
        List<Product> products = new ArrayList<>();
        List<WebElement> webProducts = new ArrayList<>();
        webProducts = wd.findElements(By.cssSelector("div.cart_list div.cart_item"));
        for (WebElement e : webProducts) {
            String name = e.findElement(By.cssSelector("a[id^='item'] div")).getText();
            String description = e.findElement(By.cssSelector("div.inventory_item_desc")).getText();
            String price = e.findElement(By.cssSelector("div.inventory_item_price")).getText();
            String id = e.findElement(By.cssSelector("div.cart_item_label a[id^='item']")).getAttribute("id");
            products.add(new Product().withId(id).withName(name).withPrice(cleaned(price)).withDescription(description));
        }
        return products;
    }

    public void checkOut() {
        wd.findElement(By.cssSelector("div.cart_footer button#checkout")).click();
    }

    public void fillPersonalData() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.form_group input"), 2));
        wd.findElement(By.cssSelector("div.checkout_info input#first-name")).sendKeys("Name");
        wd.findElement(By.cssSelector("div.checkout_info input#last-name")).sendKeys("Lastname");
        wd.findElement(By.cssSelector("div.checkout_info input#postal-code")).sendKeys("777");
        wd.findElement(By.cssSelector("div.checkout_buttons input#continue")).click();
    }

    public String getStepTwoTotalValue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.summary_info div.summary_total_label")));
        String dirtyTotal = wd.findElement(By.cssSelector("div.summary_info div.summary_total_label")).getText();
        return cleaned(dirtyTotal);
    }

    public String getStepTwoNettoValue() throws InterruptedException {
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.summary_info div.summary_subtotal_label")));
        Thread.sleep(2000);
        String dirtyNeto = wd.findElement(By.cssSelector("div.summary_info div.summary_subtotal_label")).getText();
        return cleaned(dirtyNeto);
    }

    public void finish() {
        wd.findElement(By.cssSelector("div.cart_footer button#finish")).click();
    }


    public String H2CheckoutComplete() {
        return wd.findElement(By.cssSelector("div.checkout_complete_container h2")).getText();
    }

    public void continueShopping() throws InterruptedException {
        wd.findElement(By.cssSelector("div.cart_footer button#continue-shopping")).click();
        Thread.sleep(2000);
    }
}
